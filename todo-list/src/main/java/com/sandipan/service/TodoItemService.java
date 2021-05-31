package com.sandipan.service;

import com.sandipan.model.TodoData;
import com.sandipan.model.TodoItem;

public interface TodoItemService {
	
	void addItem(TodoItem toAdd);
	
	void removeItem(int id);
	
	TodoItem getItem(int id);
	
	void updateItem(TodoItem toUpdate);
	
	TodoData getData();
}
