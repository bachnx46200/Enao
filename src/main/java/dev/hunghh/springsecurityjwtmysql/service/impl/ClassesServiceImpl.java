package dev.hunghh.springsecurityjwtmysql.service.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.hunghh.springsecurityjwtmysql.converter.ClassesConverter;
import dev.hunghh.springsecurityjwtmysql.dto.ClassesDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Classes;
import dev.hunghh.springsecurityjwtmysql.repository.ClassRepository;
import dev.hunghh.springsecurityjwtmysql.service.ClassesService;

@Service
public class ClassesServiceImpl implements ClassesService {

	private final ClassRepository classRepository;

	public ClassesServiceImpl(ClassRepository classRepository) {

		this.classRepository = classRepository;
	}

	@Override
	public void addClasses(ClassesDTO classes) {
		classRepository.save(ClassesConverter.convertToClasses(classes));

	}

	@Override
	public List<ClassesDTO> getClasses(String name, Pageable pageable) {
		List<Classes> classes = classRepository.findAll((Specification<Classes>) (root, cq, cb) -> {
			Predicate p = cb.conjunction();

			if (!StringUtils.isEmpty(name)) {
				p = cb.and(p, cb.like(root.get("class_name"), "%" + name + "%"));
			}

			cq.orderBy(cb.asc(root.get("class_name")), cb.asc(root.get("id")));
			return p;
		}, pageable).getContent();
		return ClassesConverter.convertToClassesDTO(classes);
	}

}
