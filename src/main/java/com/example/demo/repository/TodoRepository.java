package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	public List<Todo> findByUsername(String username);
}
