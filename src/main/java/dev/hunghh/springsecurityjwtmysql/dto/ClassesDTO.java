package dev.hunghh.springsecurityjwtmysql.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClassesDTO {
   
	private Long id;
	private String class_name;
}
