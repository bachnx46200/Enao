package dev.hunghh.springsecurityjwtmysql.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.ResourceNotFoundException;

import dev.hunghh.springsecurityjwtmysql.dto.NewsDTO;
import dev.hunghh.springsecurityjwtmysql.entity.News;
import dev.hunghh.springsecurityjwtmysql.repository.NewsRepository;
import dev.hunghh.springsecurityjwtmysql.service.NewsService;

@RestController
@RequestMapping("api/v6")
@CrossOrigin(origins = "*")
public class NewsController {
	@Autowired
	private NewsRepository newsrepository;
	private static final String DATE_PATTERN = "yyyy/MM/dd";
	@Autowired
	private NewsService newsService;
	@GetMapping("/newlist")
	public List<News> newsList() {
		return this.newsrepository.findAll();
	}

	@GetMapping("/title")
	public Page<News> title(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("title").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("title").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

//	model.addAttribute("listNews", newsrepository.findNews(pageable));
		return newsrepository.findNews(pageable);

	}

	@GetMapping("/new_desc")
	public Page<News> new_desc(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("new_desc").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("new_desc").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

//	model.addAttribute("listNews", newsrepository.findNews(pageable));
		return newsrepository.findNews(pageable);

	}

	@GetMapping("/content")
	public Page<News> content(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("content").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("content").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

//	model.addAttribute("listNews", newsrepository.findNews(pageable));
		return newsrepository.findNews(pageable);

	}

	@GetMapping("/id_user")
	public Page<News> id_user(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id_user").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id_user").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

//	model.addAttribute("listNews", newsrepository.findNews(pageable));
		return newsrepository.findNews(pageable);

	}

	@GetMapping("/created_at")
	public Page<News> created_at(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("created_at").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("created_at").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

//	model.addAttribute("listNews", newsrepository.findNews(pageable));
		return newsrepository.findNews(pageable);

	}

	@GetMapping("/update_at")
	public Page<News> update_at(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("update_at").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("update_at").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

//	model.addAttribute("listNews", newsrepository.findNews(pageable));
		return newsrepository.findNews(pageable);

	}

	@GetMapping("/id_cate")
	public Page<News> id_cate(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id_cate").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id_cate").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

//	model.addAttribute("listNews", newsrepository.findNews(pageable));
		return newsrepository.findNews(pageable);

	}

	// tim theo news_desc
	@GetMapping("/newsdesc/{newdesc}")
	public List<News> getDesc(@PathVariable(value = "newdesc") String newdesc) {
		return this.newsrepository.findNewDesc(newdesc);
	}

	@PostMapping("/news")
	@PreAuthorize("hasAnyAuthority('HT')")
	public News createCate(@Valid @RequestBody News news) {
		return this.newsrepository.save(news);
	}

	@PutMapping("/news/{id}")
	@PreAuthorize("hasAnyAuthority('HT')")
	public ResponseEntity<News> updateNews(@PathVariable(value = "id") Long id, @Valid @RequestBody News n1)
			throws ResourceNotFoundException {
		News n = newsrepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id:" + id));
		n.setTitle(n1.getTitle());
		n.setNew_desc(n1.getNew_desc());
		n.setContent(n1.getContent());
		n.setId_user(n1.getId_user());
		n.setCreated_at(n1.getCreated_at());
		n.setUpdate_at(n1.getUpdate_at());
		// n.setId_cate(n1.getId());
		News Updatenews = newsrepository.save(n);
		return ResponseEntity.ok(Updatenews);
	}

	@DeleteMapping("/news/{id}")
	@PreAuthorize("hasAnyAuthority('HT')")
	public Map<String, Boolean> delete1(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		News c = newsrepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("not found this" + id));
		this.newsrepository.delete(c);
		Map<String, Boolean> respone = new HashMap<>();
		respone.put("delete", Boolean.TRUE);
		return respone;
	}

	@GetMapping("/newstt")
	public List<NewsDTO> getNews(@RequestParam(required = false)
								@DateTimeFormat(pattern = DATE_PATTERN)Date create_at,
								@DateTimeFormat(pattern = DATE_PATTERN)Date update_at,
								 @RequestParam(required = false) String title, 
								 @RequestParam(required = false) String new_desc,
								 @RequestParam(required = false) String content,
								 Pageable pageable
								){
		return newsService.getNews(title, new_desc, content , create_at, update_at, pageable);
}
}
