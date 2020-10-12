package dev.hunghh.springsecurityjwtmysql.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;

import dev.hunghh.springsecurityjwtmysql.dto.ClassesDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Classes;
import dev.hunghh.springsecurityjwtmysql.repository.ClassRepository;
import dev.hunghh.springsecurityjwtmysql.service.ClassesService;

@RestController
@RequestMapping("/api/v2")
public class ClassController {

	@Autowired
	private ClassRepository classRepository;

	private final ClassesService classesService;

	public ClassController(ClassesService classesService) {

		this.classesService = classesService;
	}

	// Get Class
	@GetMapping("/class")
	public Page<Classes> listClases(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		return classRepository.findClasses(pageable);
	}
	
	@GetMapping("/lop")
	public List<ClassesDTO> getLop(@RequestParam(required = false) String name,Pageable pageable){
		return classesService.getClasses(name, pageable);
	}

	// Get Class By Id
	@GetMapping("/class{id}")
	public ResponseEntity<Classes> getClassesById(@PathVariable(value = "id") Long classId)
			throws ResourceNotFoundException {
		Classes c = classRepository.findById(classId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id::" + classId));
		return ResponseEntity.ok().body(c);
	}

	// Save Classes
	@PostMapping("/class")
	public void addClasses(@RequestBody ClassesDTO classes) {
       classesService.addClasses(classes);
	}

	// update classes
	@PutMapping("/class/{id}")
	public ResponseEntity<Classes> updateClass(@PathVariable(value = "id") Long classId, @Valid @RequestBody Classes c1)
			throws ResourceNotFoundException {

		Classes c = classRepository.findById(classId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id:" + classId));
		c.setClass_name(c1.getClass_name());
		final Classes updateClasses = classRepository.save(c);
		return ResponseEntity.ok(updateClasses);

	}

	// delete class
	@DeleteMapping("/class/{id}")
	public Map<String, Boolean> deleteClasses(@PathVariable(value = "id") Long classId)
			throws ResourceNotFoundException {
		Classes c = classRepository.findById(classId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id::" + classId));

		classRepository.delete(c);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
