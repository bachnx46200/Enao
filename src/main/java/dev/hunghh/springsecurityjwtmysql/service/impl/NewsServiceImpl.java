package dev.hunghh.springsecurityjwtmysql.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import dev.hunghh.springsecurityjwtmysql.converter.NewsConverter;
import dev.hunghh.springsecurityjwtmysql.dto.NewsDTO;
import dev.hunghh.springsecurityjwtmysql.entity.News;
import dev.hunghh.springsecurityjwtmysql.repository.NewsRepository;
import dev.hunghh.springsecurityjwtmysql.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
private final NewsRepository newsRepository;

public NewsServiceImpl(NewsRepository newsRepository) {
	this.newsRepository= newsRepository;
}
	
	@Override
	public List<NewsDTO> getNews(String title, String new_desc, String content, Date create_at,
			Date update_at,Pageable pageable) {
		List<News> news = newsRepository.findAll((Specification<News>) (root,cq,cb) ->{
		Predicate p = cb.conjunction();
		if(Objects.nonNull(create_at)&&Objects.nonNull(update_at)&&create_at.before(update_at)) {
			p=cb.and(p,cb.between(root.get("create_at"), create_at,update_at));
		}
		if(!StringUtils.isEmpty(title)) {
			 p = cb.and(p, cb.like(root.get("title"), "%" + title + "%"));
		}
		if(!StringUtils.isEmpty(new_desc)) {
			 p = cb.and(p, cb.like(root.get("new_desc"), "%" + new_desc + "%"));
		}
		if(!StringUtils.isEmpty(content)) {
			 p = cb.and(p, cb.like(root.get("content"), "%" + content + "%"));
		}
			return p;
		},pageable).getContent();
		return NewsConverter.convertToNewsDTO(news);
	}

}
