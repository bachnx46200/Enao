package dev.hunghh.springsecurityjwtmysql.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "class")
public class Classes  {
	

	 
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     
 	@NotNull
 	@Size(min=2, max=30, message=" ClassName must be equal to or greater than 2 characters and less than 30 characters")
     @Column(name = "class_name")
     private String class_name;

	public Classes() {
		super();
		
	}

	public Classes(String class_name) {
		super();
		this.class_name = class_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
 
     
}
