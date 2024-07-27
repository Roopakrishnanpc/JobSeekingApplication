package com.jobseeking.DAO;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobseeking.Model.Post;

@Repository
//public interface PostRepository extends MongoRepository<Post,Integer> 
public interface PostRepository extends MongoRepository<Post,ObjectId>{
	@Query("{ 'description': { $regex: ?0, $options: 'i' } }")
	List<Post> findByDescription(String description);

	Optional<Post> findByProfile(String description);

}
