package com.blog.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import com.blog.api.entities.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.PostResponse;
import com.blog.api.payloads.Postdto;
import com.blog.api.repositories.CategoryRepo;
import com.blog.api.repositories.PostRepo;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Postdto createPost(Postdto dto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		Post post = this.mapper.map(dto, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
//		post.setContent(dto.getContent());
//		post.setTitle(dto.getTitle());

		Post newPost = this.postRepo.save(post);
		Postdto postdto = this.mapper.map(newPost, Postdto.class);
		return postdto;
	}

	@Override
	public Postdto updatePost(Postdto postDto, Integer postId) {
		Post posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		posts.setTitle(postDto.getTitle());
		posts.setContent(postDto.getContent());

		Post updatedPost = this.postRepo.save(posts);
		return this.mapper.map(updatedPost, Postdto.class);

	}

	@Override
	public void deletePost(Integer postId) {

		Post posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepo.delete(posts);
	}

	@Override
	public List<Postdto> getAllPosts() {

		List<Post> allPosts = this.postRepo.findAll();
		List<Postdto> postdtos = allPosts.stream().map((post) -> this.mapper.map(post, Postdto.class))
				.collect(Collectors.toList());

		return postdtos;
	}

	@Override
	public Postdto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		Postdto postdto = this.mapper.map(post, Postdto.class);
		return postdto;
	}

	@Override
	public List<Postdto> getPostByCategory(Integer catId) {

		Category cat = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", catId));

		List<Post> posts = this.postRepo.findByCategory(cat);
		List<Postdto> postDtos = posts.stream().map((post) -> this.mapper.map(posts, Postdto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<Postdto> getPostByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		List<Post> posts = this.postRepo.findByUser(user);
		List<Postdto> postDtos = posts.stream().map((post) -> this.mapper.map(posts, Postdto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	// for pageable:
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//		if (sortDir.equalsIgnoreCase("ASC")) {
//			sort = Sort.by(sortBy).ascending();
//		} else {
//			sort = Sort.by(sortBy).descending();
//		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allposts = pagePost.getContent();
		List<Postdto> postdtos = allposts.stream().map((post) -> this.mapper.map(post, Postdto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postdtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;

	}

	@Override
	public List<Postdto> searchPosts(String keywords) {
		List<Post> posts = this.postRepo.findByTitleContaining(keywords);
		List<Postdto> postdtos = posts.stream().map((post) -> this.mapper.map(post, Postdto.class))
				.collect(Collectors.toList());

		return postdtos;
	}

	public List<Postdto> searchPost(String keywords) {
		List<Post> posts = this.postRepo.findByContentContaining("%"+keywords+"%");
		List<Postdto> postdtos = posts.stream().map((post) -> this.mapper.map(post, Postdto.class))
				.collect(Collectors.toList());

		return postdtos;
	}

}
