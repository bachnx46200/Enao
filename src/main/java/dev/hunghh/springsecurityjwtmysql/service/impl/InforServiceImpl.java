package dev.hunghh.springsecurityjwtmysql.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import dev.hunghh.springsecurityjwtmysql.converter.InforConverter;
import dev.hunghh.springsecurityjwtmysql.dto.InforDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Infor;
import dev.hunghh.springsecurityjwtmysql.repository.InforRepository;
import dev.hunghh.springsecurityjwtmysql.service.InforService;

@Service
public class InforServiceImpl implements InforService {

	private final InforRepository inforRepository;

	public InforServiceImpl(InforRepository inforRepository) {
		this.inforRepository = inforRepository;
	}

	@Override
	public List<InforDTO> getInfors(Date fromDate, Date toDate, String fullname, String gender, String address,
			String phone,Pageable pageable) {
		
		   List<Infor> infor = inforRepository.findAll((Specification<Infor>) (root, cq, cb) -> {
	            Predicate p = cb.conjunction();
	            if (Objects.nonNull(fromDate) && Objects.nonNull(toDate) && fromDate.before(toDate)) {
	                p = cb.and(p, cb.between(root.get("birthday"), fromDate, toDate));
	            }
	            if (!StringUtils.isEmpty(fullname)) {
	                p = cb.and(p, cb.like(root.get("fullname"), "%" + fullname + "%"));
	            }
	            if (!StringUtils.isEmpty(gender)) {
	                p = cb.and(p, cb.like(root.get("gender"), "%" + gender + "%"));
	            }
	            if (!StringUtils.isEmpty(address)) {
	                p = cb.and(p, cb.like(root.get("address"), "%" + address + "%"));
	            }
	            if (!StringUtils.isEmpty(phone)) {
	                p = cb.and(p, cb.like(root.get("phone"), "%" + phone + "%"));
	            }
//	            cq.orderBy(cb.desc(root.get("name")), cb.asc(root.get("id")));
	            return p;
	        },pageable).getContent();
	        return InforConverter.convertToInforDTO(infor);
	}
}
