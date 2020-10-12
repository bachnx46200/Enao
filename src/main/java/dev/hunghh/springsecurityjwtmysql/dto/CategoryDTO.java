package dev.hunghh.springsecurityjwtmysql.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDTO {
 private Long id;
 private String cate_name;
 private String cate_desc;
 
}
