package com.calendar.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendar.entities.Events2;

public interface EventJpaRepository extends JpaRepository<Events2, Long> {

	@Query(value="select e.id as id,p.dni,e.rut_num, e.start as start,e.end as end,e.description,p.nombre as title from events e,paciente p where e.rut_num=p.dni",nativeQuery=true)
	List<Map<String, Object>> getAllEvents();
	
	@Query(value="select e.id as id,p.dni,e.rut_num, e.start as start,e.end as end,e.description,UPPER(CONCAT(p.nombre,' ',p.a_pat,' ',p.a_mat)) as title,estado,e.rut_num as rut from events e,paciente p where e.rut_num=p.dni and e.rut_med = :rut_med and e.estado in(1,2)",nativeQuery=true)
	List<Map<String, Object>> getEventsByIdProf(@Param("rut_med") int rut_med);
	
	public abstract Events2 findEvents2ById(Long id);
}
