package dev.hunghh.springsecurityjwtmysql.repository;

import dev.hunghh.springsecurityjwtmysql.entity.userRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository  extends JpaRepository<userRole, Long> {
}
