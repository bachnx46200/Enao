package dev.hunghh.springsecurityjwtmysql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hunghh.springsecurityjwtmysql.entity.Classes;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long>, JpaSpecificationExecutor<Classes> {
	@Query(value = "select * from Class", nativeQuery = true)
	Page<Classes> findClasses(Pageable pageable);

}
