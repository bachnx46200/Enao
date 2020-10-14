package dev.hunghh.springsecurityjwtmysql.converter;

import java.util.List;
import java.util.stream.Collectors;

import dev.hunghh.springsecurityjwtmysql.dto.NewsDTO;
import dev.hunghh.springsecurityjwtmysql.entity.News;

public class NewsConverter {
public static List<NewsDTO> convertToNewsDTO(List<News> news){
	return news.stream().map(n ->
	NewsDTO.builder()
	.id(n.getId())
	.title(n.getTitle())
	.new_desc(n.getNew_desc())
	.content(n.getContent())
	.id_user(n.getId_user())
	.created_at(n.getCreated_at())
	.update_at(n.getUpdate_at())
	.id_cate(n.getId_cate())
	.build()).collect(Collectors.toList());
}

public static News convertToNews(NewsDTO newsDTO) {
	News news = new News();
	news.setId(newsDTO.getId());
	news.setTitle(newsDTO.getTitle());
	news.setNew_desc(newsDTO.getNew_desc());
	news.setContent(newsDTO.getContent());
	news.setId_user(news.getId_user());
	news.setCreated_at(newsDTO.getCreated_at());
	news.setUpdate_at(newsDTO.getUpdate_at());
	news.setId_cate(news.getId_user());
	return news;
}
}
