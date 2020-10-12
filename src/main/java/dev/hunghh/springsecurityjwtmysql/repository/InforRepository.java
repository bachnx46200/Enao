package dev.hunghh.springsecurityjwtmysql.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hunghh.springsecurityjwtmysql.entity.Infor;

@Repository
public interface InforRepository extends JpaRepository<Infor, Long>, JpaSpecificationExecutor<Infor>  {

	@Query(value = "select * from infor where active=true", nativeQuery = true)
	Page<Infor> findInfors(Pageable pageable);

	@Query(value = "select * from infor  where unaccent(concat(id,' ',fullname,' ',birthday,' ',gender,' ',address,' ',phone)) ILIKE %?1% or "
			+ "concat(id,' ',fullname,' ',birthday,' ',gender,' ',address,' ',phone) ILIKE %?1%", nativeQuery = true)
	public List<Infor> search(String keyword);

	@Query(value = "select * from infor  where unaccent(concat(id,' ',fullname,' ',birthday,' ',gender,' ',address,' ',phone)) ILIKE %?1% or "
			+ "concat(id,' ',fullname,' ',birthday,' ',gender,' ',address,' ',phone) ILIKE %?1%", nativeQuery = true)
	public Page<Infor> search2(Pageable pageable, String keyword);

	@Query(value = "select fullname,birthday,gender,address,phone,class_name from infor join t_user on infor.id=t_user.id_infor join class on t_user.id_class=class.id and id_role='5' and t_user.id_class=?1", nativeQuery = true)
	List<Tuple> repo(Long id_class);

}