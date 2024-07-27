package com.jobseeking.DAO;

import java.util.List;

import com.jobseeking.Model.Post;


public interface SearchRepository {
List<Post> findByName(String name);
List<Post> findByNameUsingMongo(String name);
}
