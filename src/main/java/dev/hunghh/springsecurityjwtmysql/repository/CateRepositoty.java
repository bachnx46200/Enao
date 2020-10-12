 package dev.hunghh.springsecurityjwtmysql.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hunghh.springsecurityjwtmysql.entity.Category;

@Repository
public interface CateRepositoty extends JpaRepository<Category, Long>,JpaSpecificationExecutor<Category>  {
	@Query("SELECT c FROM Category c")
	Page<Category> findCategory(Pageable pageable);

	// tim full 
	@Query(value = "select * from category where unaccent(concat(id,' ',cate_name,' ',cate_desc)) ilike %?1% or concat(cate_name,' ',cate_desc) ilike %?1% ", nativeQuery = true)
	List<Category> search(String keyword);

	
}
