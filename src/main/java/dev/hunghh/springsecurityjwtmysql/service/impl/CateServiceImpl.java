package dev.hunghh.springsecurityjwtmysql.service.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.hunghh.springsecurityjwtmysql.converter.CateConvert;
import dev.hunghh.springsecurityjwtmysql.dto.CategoryDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Category;
import dev.hunghh.springsecurityjwtmysql.repository.CateRepositoty;
import dev.hunghh.springsecurityjwtmysql.service.CateService;
@Service
public class CateServiceImpl implements CateService {
	
	private final CateRepositoty caterepository;
	public CateServiceImpl(CateRepositoty caterepository) {
		this.caterepository = caterepository;
	}

	@Override
	public List<CategoryDTO> getCate(String cate_name, String cate_desc, Pageable pageable) {
		List<Category> cate = caterepository.findAll((Specification<Category>) (root, cq, cb) -> {
			Predicate p = cb.conjunction();			
			if (!StringUtils.isEmpty(cate_name)) {
				p = cb.and(p, cb.like(root.get("cate_name"), "%" + cate_name + "%"));
			}
			if (!StringUtils.isEmpty(cate_desc)) {
				p = cb.and(p, cb.like(root.get("cate_desc"), "%" + cate_desc + "%"));
			}
			return p;
		},pageable).getContent();
		return CateConvert.convertToCateDTO(cate);
		

	}

}