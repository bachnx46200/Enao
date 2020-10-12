package dev.hunghh.springsecurityjwtmysql.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.hunghh.springsecurityjwtmysql.dto.NewsDTO;

public interface NewsService {
List<NewsDTO> getNews(String title,String new_desc,String content,
		Date crate_at,Date update_at,Pageable pageable );
}
