package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entities.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

	
}
