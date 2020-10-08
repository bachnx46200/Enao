package dev.hunghh.springsecurityjwtmysql.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 10, max = 30, message = " Fullname must be equal to or greater than 2 characters and less than 30 characters")
	@Column(name = "title")
	private String title;
	@NotNull
	@Size(min = 10, max = 30, message = " Fullname must be equal to or greater than 2 characters and less than 30 characters")
	@Column(name = "new_desc")
	private String news_desc;
	@NotNull
	@Size(min = 10, max = 200, message = " Fullname must be equal to or greater than 2 characters and less than 200 characters")
	@Column(name = "content")
	private String content;
	@NotNull
	@Column(name = "id_user")
	private Long id_user;
	@Column(name = "created_at")
	private Date created_at;
	@NotNull
	private Date updated_at;
	@NotNull
	private Long id_cate;

}
