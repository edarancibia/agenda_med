package com.calendar.service;

import java.util.List;
import java.util.Map;

import com.calendar.entities.Events2;

public interface EventService {

	public abstract Events2 addEvent(Events2 event);
	public abstract List<Map<String, Object>> getAllEvents();
}
