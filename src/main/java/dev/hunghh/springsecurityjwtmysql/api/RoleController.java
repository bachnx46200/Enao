package dev.hunghh.springsecurityjwtmysql.api;

import java.util.HashMap;
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

import dev.hunghh.springsecurityjwtmysql.repository.RoleRepository;
import dev.hunghh.springsecurityjwtmysql.entity.Role;


@RestController
@RequestMapping("/api/v1")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	// get Role
	@GetMapping("/role")
	public Page<Role> Role(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		return roleRepository.listRoles(pageable);
	}

	// get by id
	@GetMapping("/role{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId) throws ResourceNotFoundException {
		Role r = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
		return ResponseEntity.ok().body(r);
	}

	// save role
	@PostMapping("/role")
	public Role createRole(@Valid @RequestBody Role r) {
		return this.roleRepository.save(r);

	}

	// update role
	@PutMapping("/role{id}")
	public ResponseEntity<Role> updateRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody Role r1)
			throws ResourceNotFoundException {

		Role r = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
		r.setRoleName(r1.getRoleName());
		final Role updateRole = roleRepository.save(r);
		return ResponseEntity.ok(updateRole);

	}

	// delete role
	@DeleteMapping("/role/{id}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "id") Long roleId) throws ResourceNotFoundException {

		Role r = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
        
		this.roleRepository.delete(r);
		Map<String, Boolean> respone = new HashMap<>();
		respone.put("delete", Boolean.TRUE);

		return respone;
	}

}
