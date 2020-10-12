package dev.hunghh.springsecurityjwtmysql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.hunghh.springsecurityjwtmysql.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
	@Query("SELECT n FROM News n")
	Page<News> findNews(Pageable pageable);

}
