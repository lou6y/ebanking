package tn.esprit.spring.service.Interface;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;

import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.entity.User;

public interface IUserService {

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);

	User saveUser(User user);

	String verify(String verificationCode);

	String forgotPassword(String email);

	String resetPassword(String token, String password);

	List<User> showAllUsers();

	String updateUser(Long id, User user);

	String modifyName(String username, String name, String lastname);

	String modifyEmail(String username, String Email);
	
	String modifyPassword(String username, String password);

	String deleteUser(Long id);

	Role findByName(ERole name);

	Set<SecuritiesAccount> GetSaccount(String username);



}
