package com.sandipan.service;

import org.springframework.stereotype.Service;

import com.sandipan.model.TodoData;
import com.sandipan.model.TodoItem;

import lombok.Getter;


@Service
public class TodoItemServiceImpl implements TodoItemService {
	
	// == Fields ==
	@Getter
	private final TodoData data = new TodoData();
		
	@Override
	public void addItem(TodoItem toAdd) {
		data.addItem(toAdd);
	}

	@Override
	public void removeItem(int id) {
		data.removeItem(id);

	}

	@Override
	public TodoItem getItem(int id) {
		return data.geItem(id);
	}

	@Override
	public void updateItem(TodoItem toUpdate) {
		data.updateItem(toUpdate);

	}

}
