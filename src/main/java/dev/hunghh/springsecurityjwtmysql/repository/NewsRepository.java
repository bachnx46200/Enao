package dev.hunghh.springsecurityjwtmysql.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hunghh.springsecurityjwtmysql.entity.News;
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	@Query("SELECT c FROM Category c")
	Page<News> findNews(Pageable pageable);
	@Query(value = "select new_desc,title,content,created_at,updated_at from news join category on news.id_cate = category.id and id_cate=?1", nativeQuery = true)
	List<Object[]> findByCate_id(Long id);
	@Query(value = "select * from news where unaccent(concat(id,' ',title,' ',new_desc,' ',content,' ',id_user,' ',created_at,' ',updated_at,' ',id_cate)) ilike %?1% or concat(id,' ',title,' ',new_desc,' ',content,' ',id_user,' ',created_at,' ',updated_at,' ',id_cate) ilike %?1% ", nativeQuery = true)
	List<News> findunaccent(String keyword);
}
