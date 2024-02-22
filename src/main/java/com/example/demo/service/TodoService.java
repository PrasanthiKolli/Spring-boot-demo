package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;

import jakarta.validation.Valid;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int todocount = 0;
	
	static {
		todos.add(new Todo(++todocount, "in28minutes","Learn AWS", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++todocount, "in28minutes","Learn DevOps", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(++todocount, "in28minutes","Learn Full Stack Development", 
				LocalDate.now().plusYears(3), false ));
	}

	

	public List<Todo> findByUsername(String string) {
		// TODO Auto-generated method stub
		return todos;
	}

	public void addTodo(String name, String description, LocalDate date, boolean done) {

		Todo todo = new Todo(++todocount, name, description, date, done);
		todos.add(todo);
	}

	public void deleteTodo(int id) {

		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);

	}

	public Todo findById(int id) {

		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;

	}

	public void updateTodo(@Valid Todo todo) {
		
		deleteTodo(todo.getId());
		todos.add(todo);
	}

}
