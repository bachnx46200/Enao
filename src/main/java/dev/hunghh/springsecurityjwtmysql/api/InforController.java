package dev.hunghh.springsecurityjwtmysql.api;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.sipios.springsearch.anotation.SearchSpec;

import dev.hunghh.springsecurityjwtmysql.dto.InforDTO;
import dev.hunghh.springsecurityjwtmysql.entity.Infor;
import dev.hunghh.springsecurityjwtmysql.entity.StudentInfor;
import dev.hunghh.springsecurityjwtmysql.repository.InforRepository;
import dev.hunghh.springsecurityjwtmysql.service.InforService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "*")
public class InforController {

	@Autowired
	private InforRepository inforRepository;

    private final InforService inforService;
    

	public InforController(InforService inforService) {
		
		this.inforService = inforService;
	}


	@Autowired
	ApplicationContext context;
	
	private static final String DATE_PATTERN = "yyyy/MM/dd";

	// Get Infor
	@GetMapping("/infor")
	public Page<Infor> listInfors(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("fullname").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("fullname").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		return inforRepository.findInfors(pageable);
	}
	
	@GetMapping("/infor2/{keyword}")
	public Page<Infor> listInfors2(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,@RequestParam(name = "keyword") String keyword) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("fullname").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("fullname").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		return inforRepository.search2(pageable, keyword);
	}

	// Get InforById
	@GetMapping("/infor{id}")
	public ResponseEntity<Infor> getInforById(@PathVariable(value = "id") Long inforId)
			throws ResourceNotFoundException {
		Infor i = inforRepository.findById(inforId)
				.orElseThrow(() -> new ResourceNotFoundException("Infor not found for this id :: " + inforId));
		return ResponseEntity.ok().body(i);
	}

	// Save Infor
	@PostMapping("/infor")
	public Infor createInfor(@Valid @RequestBody Infor i) {

		return this.inforRepository.save(i);
	}

	// update Infor
	@PutMapping("/infor/{id}")
	public ResponseEntity<Infor> updateInfor(@PathVariable(value = "id") Long inforId, @Valid @RequestBody Infor i1)
			throws ResourceNotFoundException {
		Infor i = inforRepository.findById(inforId)
				.orElseThrow(() -> new ResourceNotFoundException("Infor not found for this id :: " + inforId));
		i.setFullname(i1.getFullname());
		i.setBirthday(i1.getBirthday());
		i.setGender(i1.getGender());
		i.setAddress(i1.getAddress());
		i.setPhone(i1.getPhone());
		final Infor updateInfor = inforRepository.save(i);
		return ResponseEntity.ok(updateInfor);
	}

	// delete Infor
	@PutMapping("/delete-infor/{id}")
	public ResponseEntity<Infor> deleteInfor(@PathVariable(value = "id") Long inforId, @Valid @RequestBody Infor i1)
			throws ResourceNotFoundException {
		Infor i = inforRepository.findById(inforId)
				.orElseThrow(() -> new ResourceNotFoundException("Infor not found for this id :: " + inforId));
	     i.setActive(i1.isActive()==false);
		final Infor deleteInfor = inforRepository.save(i);
		return ResponseEntity.ok(deleteInfor);
	}

	// Search
	@GetMapping("/infor/{keyword}")
	public List<Infor> searchInfor(@PathVariable(name = "keyword") String keyword) {

		if (keyword != null) {
			return this.inforRepository.search(keyword);
		}
		return this.inforRepository.findAll();
	}

//	@GetMapping("/report/{format}")
//	public String generateReport(@PathVariable String format)
//			throws FileNotFoundException, JRException, ParseException {
//		return service.exportReport(format);
//	}

	@GetMapping("/pdf/{id}")
	@ResponseBody
	public void getPdf(HttpServletResponse response,@PathVariable(value = "id") Long id_class) throws Exception {
		//Get JRXML template from resources folder
		Resource resource = context.getResource("classpath:abc.jrxml");
		//Compile to jasperReport
		InputStream inputStream = resource.getInputStream();
		JasperReport report = JasperCompileManager.compileReport(inputStream);
		//Parameters Set
		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("Cot1", "FULLNAME");
//		parameters.put("createdBy", "ENAO TEAM_ONE");
		List<StudentInfor> students = new ArrayList<>();
		List<Tuple> infor = inforRepository.repo(id_class);
		for (Tuple item : infor) {
			StudentInfor student = new StudentInfor();
			student.setFullname(item.get("fullname").toString());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(item.get("birthday").toString());
			student.setBirthday(date);
			student.setGender(item.get("gender").toString());
			student.setAddress(item.get("address").toString());
			student.setPhone(item.get("phone").toString());
			student.setClass_name(item.get("class_name").toString());
			students.add(student);
		}
		 //Data source Set
		JRDataSource dataSource = new JRBeanCollectionDataSource(students);
	     //Make jasperPrint
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
		 //Media Type
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		 //Export PDF Stream
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}
	
	
	@GetMapping("/pdff")
	@ResponseBody
	public void getPdf2(HttpServletResponse response) throws Exception {
		//Get JRXML template from resources folder
		Resource resource = context.getResource("classpath:abc.jrxml");
		//Compile to jasperReport
		InputStream inputStream = resource.getInputStream();
		JasperReport report = JasperCompileManager.compileReport(inputStream);
		//Parameters Set
		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("Cot1", "FULLNAME");
//		parameters.put("createdBy", "ENAO TEAM_ONE");
		List<StudentInfor> students = new ArrayList<>();
		List<Tuple> infor = inforRepository.repo2();
		for (Tuple item : infor) {
			StudentInfor student = new StudentInfor();
			student.setFullname(item.get("fullname").toString());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(item.get("birthday").toString());
			student.setBirthday(date);
			student.setGender(item.get("gender").toString());
			student.setAddress(item.get("address").toString());
			student.setPhone(item.get("phone").toString());
			
			students.add(student);
		}
		 //Data source Set
		JRDataSource dataSource = new JRBeanCollectionDataSource(students);
	     //Make jasperPrint
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
		 //Media Type
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		 //Export PDF Stream
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}
	
    @GetMapping("/tt")
    public List<InforDTO> getStudents(@RequestParam(required = false)
                                        @DateTimeFormat(pattern = DATE_PATTERN) Date fromDate,
                                        @RequestParam(required = false)
                                        @DateTimeFormat(pattern = DATE_PATTERN) Date toDate,
                                        @RequestParam(required = false) String fullname,
                                        @RequestParam(required = false) String gender,
                                        @RequestParam(required = false) String address,
                                        @RequestParam(required = false) String phone,
                                        Pageable pageable){
        return inforService.getInfors(fromDate, toDate, fullname, gender, address, phone, pageable);
    }
    
    
    @GetMapping("/cars")
    public ResponseEntity<List<Infor>> searchForCars(@SearchSpec Specification<Infor> infor) {
        return new ResponseEntity<>(inforRepository.findAll(Specification.where(infor)), HttpStatus.OK);
    }
    
    


}
