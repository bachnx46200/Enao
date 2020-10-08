package dev.hunghh.springsecurityjwtmysql.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hunghh.springsecurityjwtmysql.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

	@Query(value = "select * from t_user", nativeQuery = true)
	Page<User> findAccounts(Pageable pageable);

	

	@Query(value = "Select userName,created_at,id_class from t_user", nativeQuery = true)
	List<Object[]> listAccount();

	// Tim kiem theo ten
	

	// select theo id_class=1
	@Query(value = "select i.fullname,i.birthday,i.gender,i.address,i.phone from infor as i join t_user as ac on i.id=ac.id_infor and ac.id_role='3' and ac.id_class='1'", nativeQuery = true)
	List<?> findInforByClass();

	// selectByIdClass
	@Query(value = "select i.fullname,i.birthday,i.gender,i.address,i.phone,ac.userName,cl.class_name from infor as i join t_user as ac on i.id=ac.id_infor join class as cl on ac.id_class=cl.id and ac.id_role='5' and ac.id_class=?1", nativeQuery = true)
	List<Tuple> findInforByClass2(Long id_class);

	@Query(value = "select i.fullname,i.birthday,i.gender,i.address,i.phone,ac.userName,cl.class_name from infor as i join t_user as ac on i.id=ac.id_infor join class as cl on ac.id_class=cl.id and ac.id_role='5' and ac.id_class=?1", nativeQuery = true)
	List<Object[]> findInforByClass3(Long id_class);
}
