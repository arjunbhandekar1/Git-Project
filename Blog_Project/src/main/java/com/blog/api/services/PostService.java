package com.blog.api.services;

import java.util.List;

import com.blog.api.payloads.PostResponse;
import com.blog.api.payloads.Postdto;

public interface PostService {

	// post
	Postdto createPost(Postdto dto, Integer userId, Integer categoryId);

	// update
	Postdto updatePost(Postdto postdto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// get all posts
	List<Postdto> getAllPosts();

	// get single post
	Postdto getPostById(Integer postId);

	// get all post by category
	List<Postdto> getPostByCategory(Integer catId);

	// get all post by user :
	List<Postdto> getPostByUser(Integer userId);

	// search posts by keyword :
	List<Postdto> searchPosts(String keyword);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	List<Postdto> searchPost(String keywords);

}
