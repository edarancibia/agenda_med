package com.calendar.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calendar.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	@Query(value = "SELECT 	p.idpaciente,TIMESTAMPDIFF(YEAR ,p.fecha_nac ,now()) as edad, p.dni,UPPER(p.a_pat) as a_pat,UPPER(p.a_mat) as a_mat,UPPER(p.nombre) as nombre,  \n" + 
			"			p.email,p.telefono,p.direccion, DATE_FORMAT(p.fecha_nac,'%d-%m-%Y') as fecha_nac ,\n" + 
			"			case \n" + 
			"				when p.sexo=1 then 'F'\n" + 
			"				when p.sexo=0 then 'M'\n" + 
			"			end as sexo \n"+
			"			from paciente p  where p.dni = :rutnum", nativeQuery = true)
	List<Map<String, Object>> obtienePorRut(int rutnum);
	
	@Query(value="select concat(p.a_pat,' ',p.a_mat,' ',p.nombre) as nombre, p.dni,p.telefono,\n" + 
			"		case  \n" + 
			"							when p.sexo=1 then 'F' \n" + 
			"							when p.sexo=0 then 'M' \n" + 
			"						end as sexo \n" + 
			"from ficha f, paciente p\n" + 
			"where f.rut_pac = p.dni and f.idficha=:idficha", nativeQuery = true)
	Map<String, Object> getPacienteByFicha(int idficha);

}
