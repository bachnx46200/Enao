package dev.hunghh.springsecurityjwtmysql.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 50, message = "can't smaller 2 and bigger 50")
	@Column(name = "cate_name")
	private String cate_name;
	@NotNull
	@Size(min = 2, max = 50, message = "can't smaller 2 and bigger 50")
	@Column(name = "cate_desc")
	private String cate_desc;

}
