package tn.esprit.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.Interface.IUserService;

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
	
	@PostMapping("/clusterprediction")
	public String GetCluster(@RequestParam(name="Age") Integer Age,@RequestParam(name="AnnualIncome") Integer AnnualIncome,@RequestParam(name="SpendingScore") Integer SpendingScore,@RequestParam(name="Male") Integer Male,@RequestParam(name="Female") Integer Female)
	{
		//JsonObject jsobObject=new JsonObject();
		//jsobObject.addProperty("Age", Age);
		//jsobObject.addProperty("AnnualIncome", AnnualIncome);
		//jsobObject.addProperty("SpendingScore", SpendingScore);
		//jsobObject.addProperty("Male", Male);
		//jsobObject.addProperty("Female", Female);
		//System.out.println(jsobObject);
		
		int [][] data = {{Age,AnnualIncome,SpendingScore,Male,Female}};
		
		WebClient client = WebClient.create();
		Mono<String> response= client.post()
		    .uri("http://127.0.0.1:5000/predict")
		    .contentType(MediaType.APPLICATION_JSON)
		    .bodyValue(data)
		    .retrieve()
		    .onStatus(HttpStatus::isError, clientResponse ->{
		    	return Mono.error(new Exception("error"));
		    }).bodyToMono(String.class);
		
		return (response.block());
	}
	
}
