package dev.hunghh.springsecurityjwtmysql.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hunghh.springsecurityjwtmysql.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value = "select * from t_role",nativeQuery = true)
	Page<Role> listRoles (Pageable pageable);
	
	

}
