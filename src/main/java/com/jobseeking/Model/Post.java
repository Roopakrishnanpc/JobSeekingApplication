package com.jobseeking.Model;

import java.util.Arrays;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="JobPost")
public class Post {
@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
private ObjectId id;
private String description;
private String experience;
private String profile;
private String[] technology;
public ObjectId getId() {
	return id;
}
public void setId(ObjectId id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
public String getProfile() {
	return profile;
}
public void setProfile(String profile) {
	this.profile = profile;
}
public String[] getTechnology() {
	return technology;
}
public void setTechnology(String[] technology) {
	this.technology = technology;
}
@Override
public String toString() {
	return "Post [id=" + id + ", description=" + description + ", experience=" + experience + ", profile=" + profile
			+ ", technology=" + Arrays.toString(technology) + "]";
}
public Post() {
	super();
	// TODO Auto-generated constructor stub
}


}
