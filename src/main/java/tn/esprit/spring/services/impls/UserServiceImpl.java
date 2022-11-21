package tn.esprit.spring.services.impls;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.dao.repositories.UserRepo;
import tn.esprit.spring.services.inters.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	UserRepo userRepo;
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	
	@Override
	public Boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	@Override
	public User saveUser(User user) {
		String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setVerified(false);
	    user.setCreationDate(new Date(System.currentTimeMillis()));
		return userRepo.save(user); }

	
	@Override
	public String verify(String verificationCode) {
	    User user = userRepo.findByVerificationCode(verificationCode);
	     
	    if (user == null || (user.getVerified()==true)) {
	        return "verify_fail";
	    } else {
	        user.setVerificationCode(null);
	        user.setVerified(true);
	        userRepo.save(user);
	         
	        return "verify_success";
	    }
	     
	}
	
	@Override
	public String forgotPassword(String email) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepo.findByEmail(email));

		if (!userOptional.isPresent()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());

		user = userRepo.save(user);

		return user.getToken();
	}
	
	@Override
	public String resetPassword(String token, String password) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepo.findByToken(token));

		if (!userOptional.isPresent()) {
			return "Invalid token.";
		}

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return "Token expired.";

		}

		User user = userOptional.get();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setToken(null);
		user.setTokenCreationDate(null);

		userRepo.save(user);

		return "Your password successfully updated.";
	}
	
	public String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
	
	public boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}
	@Override
	public List<User> showAllUsers()
	{
		List<User> users= (List<User>) userRepo.findAll(); 
		return users;
	}
	
	@Override
	public String updateUser(Long id, User user) {
		Optional<User> userOptional = userRepo.findById(id);
		if (!userOptional.isPresent()) {
			return "Invalid id.";
		}
		else 
		{
		User _user = userOptional.get();
		_user.setUsername(user.getUsername());
		_user.setName(user.getName());
		_user.setLastname(user.getLastname());
		_user.setEmail(user.getEmail());
		_user.setPassword(user.getPassword());
		_user.setPassword(bCryptPasswordEncoder.encode(_user.getPassword()));
		_user.setBirthday(user.getBirthday());
		_user.setGender(user.getGender());
		userRepo.save(_user);
		return "Your account successfully updated.";
		}
	}
	
	@Override
	public String modifyName(String username, String name, String lastname )
	{
		Optional<User> userOptional = userRepo.findByUsername(username);
		if (!userOptional.isPresent()) {
			return "Invalid username.";
		}
		else 
		{
			User user = userOptional.get();
			user.setName(name);
			user.setLastname(lastname);
			userRepo.save(user);
			return "Your name successfully updated.";
		}
		
	}
	
	@Override
	public String modifyEmail(String username, String Email )
	{
		Optional<User> userOptional = userRepo.findByUsername(username);
		if (!userOptional.isPresent()) {
			return "Invalid username.";
		}
		else 
		{
			User user = userOptional.get();
			user.setEmail(Email);
			userRepo.save(user);
			return "Your email successfully updated.";
		}
		
	}
	
	@Override
	public String modifyPassword(String username, String password )
	{
		Optional<User> userOptional = userRepo.findByUsername(username);
		if (!userOptional.isPresent()) {
			return "Invalid username.";
		}
		else 
		{
			User user = userOptional.get();
			user.setPassword(bCryptPasswordEncoder.encode(password));
			userRepo.save(user);
			return "Your password successfully updated.";
		}
		
	}
	
	
	@Override 
	public String deleteUser(Long id) 
	{ 
		userRepo.deleteById(id);
		return "User has been successfully deleted !";
	}
}
