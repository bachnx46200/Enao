package dev.hunghh.springsecurityjwtmysql.api;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import dev.hunghh.springsecurityjwtmysql.entity.Token;
import dev.hunghh.springsecurityjwtmysql.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.excel.AccountExcelExporter;
import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.pdf.AccountPDFExporter;

import com.lowagie.text.DocumentException;

import dev.hunghh.springsecurityjwtmysql.entity.User;
import dev.hunghh.springsecurityjwtmysql.repository.AccountRepository;

@RestController
@RequestMapping("/api/v4")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TokenRepository tokenRepository;

	// Get Account
	@GetMapping("/acc")
	public Page<User> listAccouts(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
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

		return accountRepository.findAccounts(pageable);
	}

	// Get account by id
	@GetMapping("/acc/{id}")
	public ResponseEntity<User> getAccountById(@PathVariable(value = "id") Long accId)
			throws ResourceNotFoundException {
		User ac = accountRepository.findById(accId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found for this id::" + accId));
		return ResponseEntity.ok().body(ac);
	}


	// update Account
	@PutMapping("/acc/{id}")
	@PreAuthorize("hasAnyAuthority('HT')")
	public ResponseEntity<User> updateAccount( @PathVariable(value = "id") Long accId,
			@Valid @RequestBody User acc1) throws ResourceNotFoundException {

		User acc = accountRepository.findById(accId)
				.orElseThrow(() -> new ResourceNotFoundException("account not found for this id:" + accId));
		acc.setUsername(acc1.getUsername());
		acc.setPassword(acc1.getPassword());
		acc.setId_infor(acc1.getId_infor());
		acc.setCreatedAt(acc1.getCreatedAt());
		acc.setUpdatedAt(acc1.getUpdatedAt());
		acc.setRole(acc1.getRole());
		acc.setId_class(acc1.getId_class());

		final User updateAccount = accountRepository.save(acc);
		return ResponseEntity.ok(updateAccount);

	}


	// active=true Account
	@PutMapping("/active-acc/{id}")
	@PreAuthorize("hasAnyAuthority('HT')")
	public ResponseEntity<User> TrueAccount(@PathVariable(value = "id") Long accId
											) throws ResourceNotFoundException {

		User acc = accountRepository.findById(accId)
				.orElseThrow(() -> new ResourceNotFoundException("account not found for this id:" + accId));
		acc.setActive(true);
		Token token = tokenRepository.getToken(accId);
		token.setActive(true);
		tokenRepository.save(token);
		final User updateAccount = accountRepository.save(acc);
		return ResponseEntity.ok(updateAccount);
	}

	// active=false Account
	@PutMapping("/disactive-acc/{id}")
	@PreAuthorize("hasAnyAuthority('HT')")
	public ResponseEntity<User> FalseAccount(@PathVariable(value = "id") Long accId
											) throws ResourceNotFoundException {

		User acc = accountRepository.findById(accId)
				.orElseThrow(() -> new ResourceNotFoundException("account not found for this id:" + accId));
		acc.setActive(false);

		Token token = tokenRepository.getToken(accId);
		token.setActive(false);
		tokenRepository.save(token);
		final User updateAccount = accountRepository.save(acc);
		return ResponseEntity.ok(updateAccount);
	}
//	// delete Account
//	@DeleteMapping("/acc/{id}")
//	public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long accId) throws ResourceNotFoundException {
//		User acc = accountRepository.findById(accId)
//				.orElseThrow(() -> new ResourceNotFoundException("Account not found for this id:" + accId));
//		accountRepository.delete(acc);
//		Map<String, Boolean> respone = new HashMap<>();
//		respone.put("deleted", Boolean.TRUE);
//		return respone;
//
//	}

	// List theo câu query 1
	@GetMapping("/user")
	List<?> getUser() {
		return accountRepository.listAccount();
	}



	// finInforByClaas
	@GetMapping("/inforby/{id}")
	List<Object[]> getUser3(@PathVariable(value = "id") Long id_class) {
		return accountRepository.findInforByClass3(Long.valueOf(id_class));
	}

	/////
	@GetMapping("/acc/export/pdf/{id}")
	public void exportToPDF(HttpServletResponse response, @PathVariable(value = "id") Long id_class)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Account_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue); 
		List<Tuple> listAcc = accountRepository.findInforByClass2(id_class);
		AccountPDFExporter exporter = new AccountPDFExporter(listAcc);
		exporter.export(response);

	}

	///
	@GetMapping("/acc/export/excel/{id}")
	public void exportToExcel(HttpServletResponse response, @PathVariable(value = "id") Long id_class)
			throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Account_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Tuple> listAcc = accountRepository.findInforByClass2(id_class);
		AccountExcelExporter excelExporter = new AccountExcelExporter(listAcc);
		excelExporter.export(response);
	}

}
