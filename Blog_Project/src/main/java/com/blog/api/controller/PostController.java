package com.blog.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.config.AppConstants;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.PostResponse;
import com.blog.api.payloads.Postdto;
import com.blog.api.services.FileService;
import com.blog.api.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<Postdto> createPost(@RequestBody Postdto postdto, @PathVariable Integer userId,
			@PathVariable Integer catId) {

		Postdto createPost = this.postService.createPost(postdto, userId, catId);

		return new ResponseEntity<Postdto>(createPost, HttpStatus.CREATED);
	}

	// get post by User :
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<Postdto>> getPostByUser(@PathVariable Integer userId) {

		List<Postdto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<Postdto>>(posts, HttpStatus.OK);
	}

	// get post by category
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<Postdto>> getPostByCategory(@PathVariable Integer catId) {
		List<Postdto> posts = this.postService.getPostByCategory(catId);
		return new ResponseEntity<List<Postdto>>(posts, HttpStatus.OK);
	}

	// get all posts :
	/*
	 * @GetMapping("/posts") public ResponseEntity<List<Postdto>> getAllPost() {
	 * List<Postdto> allPosts = this.postService.getAllPosts(); return new
	 * ResponseEntity<List<Postdto>>(allPosts, HttpStatus.OK); }
	 */

	// get post details by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Postdto> getPostById(@PathVariable Integer postId) {
		Postdto post = this.postService.getPostById(postId);
		return new ResponseEntity<Postdto>(post, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post is deleted", true), HttpStatus.OK);

	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<Postdto> updatePost(@RequestBody Postdto postdto, @PathVariable("postId") Integer postId) {
		Postdto updatedPost = this.postService.updatePost(postdto, postId);
		return new ResponseEntity<Postdto>(updatedPost, HttpStatus.OK);

	}

	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)

	{

		PostResponse allPosts = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}

	@GetMapping("posts/search/{keywords}")
	public ResponseEntity<List<Postdto>> searchPostByTitle(@PathVariable("keywords") String keywords) {

		List<Postdto> searchPosts = this.postService.searchPosts(keywords);

		return new ResponseEntity<List<Postdto>>(searchPosts, HttpStatus.OK);

	}

	@GetMapping("posts/search/content/{keywords}")
	public ResponseEntity<List<Postdto>> searchPostByContent(@PathVariable("keywords") String keywords) {

		List<Postdto> searchPosts = this.postService.searchPost(keywords);

		return new ResponseEntity<List<Postdto>>(searchPosts, HttpStatus.OK);

	}

	// post image upload:
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<Postdto> uploadPostImage(@PathVariable("postId") Integer postId,
			@RequestParam("image") MultipartFile image) throws IOException {
		Postdto postdto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postdto.setImageName(fileName);
		Postdto updatePost = this.postService.updatePost(postdto, postId);
		return new ResponseEntity<Postdto>(updatePost, HttpStatus.OK);

	}

}
