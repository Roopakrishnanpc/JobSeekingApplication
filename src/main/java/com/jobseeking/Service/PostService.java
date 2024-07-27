package com.jobseeking.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import com.jobseeking.DAO.PostRepository;
import com.jobseeking.DAO.SearchRepository;
import com.jobseeking.Model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class PostService implements SearchRepository {
	@Autowired
	MongoClient mongoClient;
	@Autowired
	MongoConverter mongoConverter;
	@Autowired
	PostRepository postRepository;
	public List<Post> getAllPost() {
		return  postRepository.findAll();
	}
	public Post addPost(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

	@Override
	public List<Post> findByName(String name) {
		// TODO Auto-generated method stub
		String lowerCaseSearchTerm =name.toLowerCase();
//		Post post=new Post();
//		List<Post> posts=new ArrayList<>();
//		if((post.getDescription().toLowerCase().contains(name))|| 
//				(post.getProfile().toLowerCase().contains(name))|| (post.getTechnology().stream()
//                .anyMatch(tech -> tech.toLowerCase().contains(searchTerm.toLowerCase()))))
//		{
//			posts.add(post);
//		}
//		return posts;
		List<Post> allPosts=new ArrayList<>(); 
        return allPosts.stream()
                .filter(post -> 
                    (post.getDescription() != null && post.getDescription().toLowerCase().contains(lowerCaseSearchTerm)) ||
                    (post.getProfile() != null && post.getProfile().toLowerCase().contains(lowerCaseSearchTerm)) ||
                    (post.getTechnology() != null && 
                     Arrays.stream(post.getTechnology()).anyMatch(tech -> tech.toLowerCase().contains(lowerCaseSearchTerm)))
                )
                .collect(Collectors.toList());
        }
	public List<Post> findByNameUsingMongo(String name) {
		List<Post> allPosts=new ArrayList<>(); 
		MongoDatabase database = mongoClient.getDatabase("JobListing");
		MongoCollection<Document> collection = database.getCollection("JobPost");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("text", 
		    new Document("query", name)//Arrays.asList("java", "UI/UX"))
		                .append("path", Arrays.asList("technology", "description", "profile")))), 
		    new Document("$limit", 4L)));
		result.forEach(doc -> allPosts.add(mongoConverter.read(Post.class, doc)));
		return allPosts;
	}
	public List<Post> updatePostsByDescription(String description, Post updatedPost) {
        List<Post> posts = postRepository.findByDescription(description);
        if (!posts.isEmpty()) {
            for (Post post : posts) {
            	post.setDescription(updatedPost.getDescription());
                post.setExperience(updatedPost.getExperience());
                post.setProfile(updatedPost.getProfile());
                post.setTechnology(updatedPost.getTechnology());
            }
            return postRepository.saveAll(posts); // Save all updated posts
        } else {
            throw new RuntimeException("No posts found with description: " + description);
        }
	}
	//If your app is single word so only single data comes
	public Post updatePostByDescriptionSingleWord(String description, Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findByProfile(description);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setExperience(updatedPost.getExperience());
            post.setProfile(updatedPost.getProfile());
            post.setTechnology(updatedPost.getTechnology());
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with description: " + description);
        }
    }
}

