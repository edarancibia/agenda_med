package com.calendar.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calendar.entities.Event;
import com.calendar.entities.Events2;
import com.calendar.repository.EventJpaRepository;

@Service("eventServiceImpl")
public class EventServiceImpl implements EventService {

	@Autowired
	@Qualifier("eventJpaRepository")
	private EventJpaRepository eventRepository;

	@Override
	public Events2 addEvent(Events2 event) {
		Events2 event_ = eventRepository.save(event);
		return event_;
	}

	@Override
	public List<Map<String, Object>> getAllEvents() {
		return eventRepository.getAllEvents();
	}
	
}
