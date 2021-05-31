package com.sandipan.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandipan.model.TodoData;
import com.sandipan.model.TodoItem;
import com.sandipan.service.TodoItemService;
import com.sandipan.util.AttributeNames;
import com.sandipan.util.Mappings;
import com.sandipan.util.ViewNames;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TodoItemController {

	private final TodoItemService service;

	@Autowired
	public TodoItemController(TodoItemService service) {
		this.service = service;
	}

	// == model attributes ==
	@ModelAttribute
	public TodoData todoData() {
		return service.getData();
	}

	// == handler methods ==

	// http://localhost:8080/todo-list/items
	@GetMapping(Mappings.ITEMS)
	public String items() {
		return ViewNames.ITEMS_LIST;
	}

	@GetMapping(Mappings.ADD_ITEMS)
	public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {

		log.info("editing by id = {}", id);
		TodoItem todoItem = service.getItem(id);
		log.info("todoItem = {}",todoItem);

		if (todoItem == null) {
			todoItem = new TodoItem("", "", LocalDate.now());
		}

		model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
		return ViewNames.ADD_ITEM;
	}

	@PostMapping(Mappings.ADD_ITEMS)
	public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
		log.info("todoItem from from = {}", todoItem);
		
		if (todoItem.getId() == 0) {
			service.addItem(todoItem);
		} else {
			service.updateItem(todoItem);
		}
		
		return ViewNames.REDIRECT + Mappings.ITEMS;
	}

	@GetMapping(Mappings.DELETE_ITEM)
	public String deleteItem(@RequestParam int id) {
		log.info("deleting by id = {}", id);
		service.removeItem(id);
		return ViewNames.REDIRECT + Mappings.ITEMS;
	}
	
	@GetMapping(Mappings.VIEW_ITEM)
	public String viewItem(@RequestParam int id, Model model) {
		TodoItem todoItem = service.getItem(id);
		model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
		return ViewNames.VIEW_ITEM;
	}

}
