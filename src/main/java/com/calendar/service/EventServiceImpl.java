package com.calendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

	@Override
	public List<Map<String, Object>> getEventsByProf(int rut_med) {
		return eventRepository.getEventsByIdProf(rut_med);
	}

	@Override
	public Events2 findEvents2ById(Long id) {
		return eventRepository.findEvents2ById(id);
	}
	
}
