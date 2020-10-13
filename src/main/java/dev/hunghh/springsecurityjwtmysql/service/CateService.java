package dev.hunghh.springsecurityjwtmysql.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.hunghh.springsecurityjwtmysql.dto.CategoryDTO;

public interface CateService {

List<CategoryDTO> getCate(String cate_name , String cate_desc,Pageable pageable);
}
