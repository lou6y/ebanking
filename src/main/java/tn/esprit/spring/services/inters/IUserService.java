package tn.esprit.spring.services.inters;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.dao.entities.User;

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



}
