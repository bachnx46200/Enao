package dev.hunghh.springsecurityjwtmysql.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewsDTO {
	private Long id;
	private String title;
	private String new_desc;
	private String content;
	private Long id_user;
	private Date created_at;
	private Date update_at;
	private Long id_cate;
}
