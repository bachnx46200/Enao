package dev.hunghh.springsecurityjwtmysql.repository;

import dev.hunghh.springsecurityjwtmysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="SELECT * FROM t_user u WHERE u.active = TRUE and u.username=?1",nativeQuery = true)
    User findByUsername(String username);

}
