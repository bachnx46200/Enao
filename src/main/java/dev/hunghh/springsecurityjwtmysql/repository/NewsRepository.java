package dev.hunghh.springsecurityjwtmysql.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hunghh.springsecurityjwtmysql.entity.News;
@Repository
public interface NewsRepository extends JpaRepository<News, Long>,JpaSpecificationExecutor<News> {
	@Query("SELECT n FROM News n")
	Page<News> findNews(Pageable pageable);
	@Query("SELECT n FROM News n where n.new_desc like %?1%")
	List<News> findNewDesc(String newdesc);
}
