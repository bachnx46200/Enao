package dev.hunghh.springsecurityjwtmysql.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.hunghh.springsecurityjwtmysql.dto.ClassesDTO;

public interface ClassesService {
	
	void addClasses(ClassesDTO classes);
	
	List<ClassesDTO> getClasses(String name, Pageable pageable);
	
	

}
