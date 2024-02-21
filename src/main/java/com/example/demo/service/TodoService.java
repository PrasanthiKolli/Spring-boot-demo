package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	private static int todocount = 0;

	public List<Todo> findByUsername(String string) {
		// TODO Auto-generated method stub
		return todos;
	}

	public void addTodo(String name, String description, LocalDate date, boolean done) {

		Todo todo = new Todo(++todocount, name, description, date, done);
		todos.add(todo);
	}

}
