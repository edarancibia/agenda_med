package com.calendar.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calendar.entities.Event;
import com.calendar.entities.Events2;
import com.calendar.entities.Paciente;

public interface EventJpaRepository extends JpaRepository<Events2, Long> {

	@Query(value="select e.id as id,p.dni,e.rut_num, e.start as start,e.end as end,e.description,p.nombre as title from events e,paciente p where e.rut_num=p.dni",nativeQuery=true)
	List<Map<String, Object>> getAllEvents();
}
