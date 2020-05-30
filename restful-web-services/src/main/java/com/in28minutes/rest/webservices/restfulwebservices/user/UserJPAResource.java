package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//http://localhost:8080/jpa/users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	//http://localhost:8080/jpa/users/1
	@GetMapping(path="/jpa/users/{id}")
	public Resource<User> retrieveUser( @PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			Resource<User> model = new Resource<>(user.get());
			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			model.add(linkTo.withRel("all-users"));
			return model;
		}else {
			throw new UserNotFoundException("User Id : "+id+" Not Found");
		}
	}
	
	//http://localhost:8080/jpa/delusers/1
	@DeleteMapping(path="/jpa/delusers/{id}")
	public void deleteUser(@PathVariable int id) {
		System.out.println("Hi FindOne");
		userRepository.deleteById(id);
	}
	/**
	 * headers 	: 	Content-Type = application/json
	 * Post 	:	http://localhost:8080/users
	 * Body		:	Type = Custom / Raw
	 *				{
  						"name": "Sri",
  						"birthDate": "2000-05-10T03:29:37.460+0000"
					}
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/jpa/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		  UriComponents location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()); 
		  return ResponseEntity.created(location.toUri()).build();
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity createPost(@PathVariable int id,@RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Id "+id+" is Not Present");
		}
		User user = userOptional.get();
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		  UriComponents location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()); 
		  return ResponseEntity.created(location.toUri()).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id){
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Id "+id+" is Not Present");
		}
		return userOptional.get().getPosts();
	}
	
	/**
	 * Swager UI Link 	: 	http://localhost:8080/swagger-ui.html
	 * Swager Link	  	:	http://localhost:8080/v2/api-docs
	 * actiator Link  	:	http://localhost:8080/actuator
	 * actiator UI Link :	http://localhost:8080/
	 * h2-console		:	http://localhost:8080/h2-console  , jdbc:h2:mem:testdb
	 */
}
