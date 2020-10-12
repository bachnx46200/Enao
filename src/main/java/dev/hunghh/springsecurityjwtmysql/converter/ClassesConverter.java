package dev.hunghh.springsecurityjwtmysql.converter;

import java.util.List;
import java.util.stream.Collectors;

import dev.hunghh.springsecurityjwtmysql.dto.ClassesDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Classes;

public class ClassesConverter {

	public static List<ClassesDTO> convertToClassesDTO(List<Classes> classes) {
       return classes.stream().map(s ->
       ClassesDTO.builder()
       .id(s.getId())
       .class_name(s.getClass_name())
       .build()
       ).collect(Collectors.toList()); 
	}
	
	public static Classes convertToClasses(ClassesDTO classesDTO) {
		Classes classes = new Classes();
		classes.setId(classesDTO.getId());
		classes.setClass_name(classesDTO.getClass_name());
		return classes;
	}

}
