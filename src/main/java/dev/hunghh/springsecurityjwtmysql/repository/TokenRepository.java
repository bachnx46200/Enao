package dev.hunghh.springsecurityjwtmysql.repository;

import dev.hunghh.springsecurityjwtmysql.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {


    @Query(value="SELECT * FROM t_token u WHERE u.active = TRUE and u.token=?1",nativeQuery = true)
    Token findByToken(String token);

    @Query(value="SELECT * FROM t_token u WHERE u.id_user =?1",nativeQuery = true)
    Token getToken(long token);
}
