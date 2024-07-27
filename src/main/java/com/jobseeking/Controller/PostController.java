package com.jobseeking.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jobseeking.Model.Post;
import com.jobseeking.Service.PostService;

import jakarta.servlet.http.HttpServletResponse;

//netstat -ano | findstr :8080

@RestController
//@CrossOrigin(origins="http://localhost:3000") -> For the link of react app which I dont have that
public class PostController {
	@Autowired
	PostService postService;
	//,  method = RequestMethod.GET)
	//@ApiIgnore
	@RequestMapping("/")
	public void redirect(HttpServletResponse response) throws IOException
	{
		//response.sendRedirect("/swagger-ui.html");
	}
	@GetMapping("/posts")
	public List<Post> getAllPost() {
		List<Post> pots=postService.getAllPost();
		System.out.println("Before executed");
		return  pots;
	}
	@PostMapping("/posts")
	public Post addPost(@RequestBody Post post)
	{
		return postService.addPost(post);
	}
	//@GetMapping("posts/{name}") -> this will not work
	public List<Post> search(@PathVariable String name)	
	{
		return postService.findByName(name);
	}
	//Code pulled using MONGO DB ATLAS ITSELF
	//FIRST CREATE A ATLEAST SEARCH INDEX
	//THEN IN THE COLLECTION -> AGGREGATION CREATE A PIPEINE , WE USED SEARCH, LIMIT, SORT
	//THEN CONVERT THE CODE TO JAVA
	//WE ALSO USED MONGO UI MONGO COMPASS DESKTOP APP AND CONNECTED THE ATLAS URL THER
	@GetMapping("postsMongo/{name}")
	public List<Post> searchUsingMongo(@PathVariable String name)	
	{
		return postService.findByNameUsingMongo(name);
	}
	@PutMapping("posts/{name}")
	public List<Post> updatePostsByDescription(@PathVariable String name, @RequestBody Post post)	
	{
		return postService.updatePostsByDescription(name, post);
	}

}
