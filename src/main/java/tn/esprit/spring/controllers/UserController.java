package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.services.inters.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	 @Autowired
	  IUserService userService;
	 
	 @GetMapping("getAllUsers") 
	 //@PreAuthorize("hasRole('ADMIN')")
       public List<User> getAllUsers() 
		{
			return userService.showAllUsers();
		} 
		
	@GetMapping("getUser/{username}")
	// @PreAuthorize("hasRole('ADMIN')")
       public Optional<User> getUser(@PathVariable("username")String username)
		{
		return userService.findByUsername(username);
		}
	
	@PutMapping("/updateUser/{idUser}")
	//@PreAuthorize("hasRole('ADMIN')")
	public String updateUser(@PathVariable("idUser")Long idUser, @RequestBody User user)
	{
		return userService.updateUser(idUser, user);
	}
	
	@PutMapping("/modify-name/{username}")
	//@PreAuthorize("hasRole('ADMIN')")
	   public String modifyName(@PathVariable("username")String username, @RequestParam String name,
			   @RequestParam String lastname) 
	   {
		return userService.modifyName(username, name, lastname);
       }
	
	@PutMapping("/modify-email/{username}")
	//@PreAuthorize("hasRole('ADMIN')")
	   public String modifyEmail(@PathVariable("username")String username, @RequestParam String email) 
	   {
		return userService.modifyEmail(username, email);
       }
	
	@PutMapping("/modify-password/{username}")
	//@PreAuthorize("hasRole('ADMIN')")
	   public String modifyPassword(@PathVariable("username")String username, @RequestParam String password) 
	   {
		return userService.modifyEmail(username, password);
       }
	
	@DeleteMapping("deleteUser/{idUser}") 
	//@PreAuthorize("hasRole('ADMIN')")
	public String DeleteUser(@PathVariable("idUser")Long idUser )
	{
		return userService.deleteUser(idUser);
	}
}
