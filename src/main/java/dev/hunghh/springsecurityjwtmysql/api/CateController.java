package dev.hunghh.springsecurityjwtmysql.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import dev.hunghh.springsecurityjwtmysql.dto.CategoryDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Category;
import dev.hunghh.springsecurityjwtmysql.repository.CateRepositoty;
import dev.hunghh.springsecurityjwtmysql.service.CateService;

@RestController
@RequestMapping("/api/v5")
@CrossOrigin(origins = "*")
public class CateController {
	@Autowired
    ApplicationContext context;
	private final CateService cateService;
	public CateController(CateService cateService) {
		
		this.cateService = cateService;
	}

	@Autowired
	private CateRepositoty cateRepository;
	@GetMapping("/catee")
	public List<Category> getCate() {
		return this.cateRepository.findAll();
	}
	// phÃ¢n trang
	// http://localhost:8081/jpa/v1/cate/?page=2&size=10
	@GetMapping("/cate")
	public Page<Category> Category(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("cate_name").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("cate_name").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		model.addAttribute("listCategory", cateRepository.findCategory(pageable));
		return cateRepository.findCategory(pageable);

	}
	@GetMapping("/cated")
	public Page<Category> CateDesc(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("cate_desc").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("cate_desc").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		model.addAttribute("listCategory", cateRepository.findCategory(pageable));
		return cateRepository.findCategory(pageable);

	}

	// them moi
	@PostMapping("/cate")
	@PreAuthorize("hasAnyAuthority('HT')")
	public Category createCate(@Valid @RequestBody Category cate) {
		return this.cateRepository.save(cate);
	}

	// tim theo id
	@GetMapping("/cate/{id}")
	public ResponseEntity<Category> getCateById(@PathVariable(value = "id") Long cateId)
			throws ResourceNotFoundException {
		Category c = cateRepository.findById(cateId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id::" + cateId));

		return ResponseEntity.ok().body(c);
	}

	// update
	@PutMapping("/cate/{id}")
	@PreAuthorize("hasAnyAuthority('HT')")
	public ResponseEntity<Category> updateCate(@PathVariable(value = "id") Long cateId, @Valid @RequestBody Category c1)
			throws ResourceNotFoundException {

		Category c = cateRepository.findById(cateId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id:" + cateId));
		c.setCate_name(c1.getCate_name());
		c.setCate_desc(c1.getCate_desc());
		final Category updateCate = cateRepository.save(c);
		return ResponseEntity.ok(updateCate);

	}

	// delete
	@DeleteMapping("/cate/{id}")
	@PreAuthorize("hasAnyAuthority('HT')")
	public Map<String, Boolean> delete1(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Category c = cateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("not found this" + id));
		this.cateRepository.delete(c);
		Map<String, Boolean> respone = new HashMap<>();
		respone.put("delete", Boolean.TRUE);
		return respone;
	}

	// tìm không dấu
	@GetMapping("/cate/{keyword}")
	public List<Category> getUpper(@PathVariable(value = "keyword") String keyword) {
		if (keyword != null) {
			return this.cateRepository.search(keyword);
		}
		return this.cateRepository.findAll();
	}
	
	@GetMapping("/s")
	public List<CategoryDTO> getCate(@RequestParam(required = false) String cate_name,
								@RequestParam(required = false) String cate_desc,
								Pageable pageable)
	{
		return cateService.getCate(cate_name, cate_desc, pageable);
	}
}
