package com.example.demo.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
		
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String showNewTodo() {
		return "todo";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addNewTodo(@RequestParam String description,ModelMap model) {
		
		String username=(String) model.get("name");
		todoService.addTodo(username, description, LocalDate.now().plusYears(1), false);
		return "redirect:list-todos";
	}
	
	

}
