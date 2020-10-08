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
import org.springframework.ui.Model;
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

import dev.hunghh.springsecurityjwtmysql.entity.News;
import dev.hunghh.springsecurityjwtmysql.repository.NewsRepository;

@RestController
@RequestMapping("/api/v6")
public class NewsController {
	@Autowired
	private NewsRepository newsRepository;

	@GetMapping("/news")
	public List<News> getNew() {
		return this.newsRepository.findAll();
	}

//phan trang
	@GetMapping("/news/")
	public Page<News> News(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
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

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}
	//sap xep theo title
	@GetMapping("/title/")
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

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}
	//sap xep theo new desc
	@GetMapping("/news_desc/")
	public Page<News> news_desc(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("news_desc").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("news_desc").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}
	//sap xep theo noi dung
	@GetMapping("/content/")
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

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}
	//sap xep theo id usser
	@GetMapping("/id_user/")
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

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}
	//sap xep theo create at
	@GetMapping("/created_at/")
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

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}
	//sap xep theo update at
	@GetMapping("/updated_at/")
	public Page<News> updated_at(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("updated_at").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("updated_at").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}
	@GetMapping("/news/")
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

		model.addAttribute("listNews", newsRepository.findNews(pageable));
		return newsRepository.findNews(pageable);

	}

// thêm
	@PostMapping("/news")
	public News createCate(@Valid @RequestBody News news) {
		return this.newsRepository.save(news);
	}

//tìm theo id
	@GetMapping("/news/{id}")
	public ResponseEntity<News> getCateById(@PathVariable(value = "id") Long newsId) throws ResourceNotFoundException {
		News n = newsRepository.findById(newsId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id::" + newsId));
		return ResponseEntity.ok().body(n);
	}

//update
	@PutMapping("/news/{id}")
	public ResponseEntity<News> UpdateById(@PathVariable(value = "id") Long newsId, @Valid @RequestBody News news)
			throws ResourceNotFoundException {
		News n = newsRepository.findById(newsId)
				.orElseThrow(() -> new ResourceNotFoundException("Class not found for this id::" + newsId));
		n.setTitle(news.getTitle());
		n.setNews_desc(news.getNews_desc());
		n.setContent(news.getContent());
		n.setCreated_at(news.getCreated_at());
		n.setUpdated_at(news.getUpdated_at());
		final News Updatenews = newsRepository.save(n);
		return ResponseEntity.ok().body(Updatenews);
	}

//delete
	@DeleteMapping("/news/{id}")
	public Map<String, Boolean> delete1(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		News n = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found this" + id));
		this.newsRepository.delete(n);
		Map<String, Boolean> respone = new HashMap<>();
		respone.put("delete", Boolean.TRUE);
		return respone;
	}

	// get id theo the loai
	@GetMapping("/search/{id}")
	public List<Object[]> gettitle(@PathVariable(value = "id") Long id) {
		return this.newsRepository.findByCate_id(id);
	}
	@GetMapping("/cate/{keyword}")
	public List<News> getUpper(@PathVariable(value = "keyword") String keyword) {
		if (keyword != null) {
			return this.newsRepository.findunaccent(keyword);
		}
		return this.newsRepository.findAll();
	}
}
