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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title")
	@NotNull
	@Size(min = 2, max = 50, message = "can't smaller 2 and bigger 50")
	private String title;
	@Column(name = "new_desc")
	@NotNull
	@Size(min = 2, max = 100, message = "can't smaller 2 and bigger 50")
	private String new_desc;
	@Column(name = "content")
	@NotNull
	@Size(min = 2, max = 200, message = "can't smaller 2 and bigger 50")
	private String content;
	@Column(name = "id_user")
	@NotNull
	private Long id_user;
	@Column(name = "created_at")
	@NotNull
	private Date created_at;
	@Column(name = "updated_at")
	private Date update_at;
	@Column(name = "id_cate")
	private Long id_cate;
}
