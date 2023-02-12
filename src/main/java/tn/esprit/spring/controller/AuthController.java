package tn.esprit.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.security.JwtUtils;
import tn.esprit.spring.service.Implementation.UserDetailsImpl;
import tn.esprit.spring.service.Interface.IRefreshTokenService;
import tn.esprit.spring.service.Interface.IUserService;
import tn.esprit.spring.model.LogOutRequest;
import tn.esprit.spring.model.LoginRequest;
import tn.esprit.spring.model.ResetPasswordRequest;
import tn.esprit.spring.model.SignupRequest;
import tn.esprit.spring.model.TokenRefreshRequest;
import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.RefreshToken;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.model.JwtResponse;
import tn.esprit.spring.model.MessageResponse;
import tn.esprit.spring.model.TokenRefreshResponse;
import tn.esprit.spring.model.UserInfoResponse;
import tn.esprit.spring.refreshtoken.exception.TokenRefreshException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  IUserService userService;

  @Autowired
  IRefreshTokenService refreshTokenService;
  
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
    
    ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

    return ResponseEntity.ok()
              .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
              .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
              .body(new UserInfoResponse(userDetails.getId(),
                                         userDetails.getUsername(),
                                         userDetails.getEmail(),
                                         roles));
  }
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws UnsupportedEncodingException, MessagingException {
    if (userService.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userService.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));
    
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    Role Client = userService.findByName(ERole.CLIENT);
    roles.add(Client);
    user.setRoles(roles);
    userService.saveUser(user);
    
    String response= user.getVerificationCode();
	response = "http://localhost:8086/api/auth/verify?code=" + response;
	//userService.sendEmail(user.getEmail(), response);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
    String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);
    
    if ((refreshToken != null) && (refreshToken.length() > 0)) {
      return refreshTokenService.findByToken(refreshToken)
          .map(refreshTokenService::verifyExpiration)
          .map(RefreshToken::getUser)
          .map(user -> {
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);
            
            return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new MessageResponse("Token is refreshed successfully!"));
          })
          .orElseThrow(() -> new TokenRefreshException(refreshToken,
              "Refresh token is not in database!"));
    }
    
    return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
  }
    
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principle.toString() != "anonymousUser") {      
      Long userId = ((UserDetailsImpl) principle).getId();
      refreshTokenService.deleteByUserId(userId);
    }
    
    ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
    ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }

  
  @GetMapping("/verify")
	public String verifyUser(@RequestParam String code) {
	    return userService.verify(code);
	    
	}
  
  @PostMapping("/forgot-password")
	public String forgotPassword(@RequestBody String email) throws UnsupportedEncodingException, MessagingException {

		String response = userService.forgotPassword(email);
		//userService.sendEmail(email, response);
		return response;
	}
  
  @PutMapping("/reset-password")
	public String resetPassword(@RequestBody ResetPasswordRequest rpr) {
    return userService.resetPassword(rpr.getToken(), rpr.getPassword());
	}
}

