package dev.hunghh.springsecurityjwtmysql.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "class")
public class Classes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 30, message = " ClassName must be equal to or greater than 2 characters and less than 30 characters")
	@Column(name = "class_name")
	private String class_name;

}
