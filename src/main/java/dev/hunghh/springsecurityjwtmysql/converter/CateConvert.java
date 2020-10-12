package dev.hunghh.springsecurityjwtmysql.converter;

import java.util.List;
import java.util.stream.Collectors;

import dev.hunghh.springsecurityjwtmysql.dto.CategoryDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Category;

public class CateConvert {
public static List<CategoryDTO>convertToCateDTO(List<Category>cate){
	return cate.stream().map(c ->
	CategoryDTO.builder()
	.id(c.getId())
	.cate_name(c.getCate_name())
	.cate_desc(c.getCate_desc())
	.build()).collect(Collectors.toList());
}

public static Category convertToCategory(CategoryDTO categoryDTO) {
	Category cate = new Category();
	cate.setId(categoryDTO.getId());
	cate.setCate_name(categoryDTO.getCate_name());
	cate.setCate_desc(categoryDTO.getCate_desc());
	return cate;
}
}
