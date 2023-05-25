package employee.angular.task.controller;

import javax.validation.Valid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

import employee.angular.task.entity.Department;
import employee.angular.task.entity.Employee
import employee.angular.task.service.DepartmentTaskService
@CrossOrigin('${crossorigin}')
@RestController
@RequestMapping('/departments')
 class DepartmentTaskController {
	@Autowired
	 DepartmentTaskService departmentService


	@GetMapping('/getAllDepts')
	List<Department> getAllDepartments() {
		departmentService.getAllDepartments()
	}
	
	@GetMapping(value="/getDeptById/{id}", produces="application/json")
	ResponseEntity<Department> getDepartmentById(@PathVariable(value = 'id') Integer departmentId) {
		Department dept=  departmentService.getDepartmentById(departmentId)
		return ResponseEntity.ok().body(dept)
	}

	@PostMapping('/createDept')
	ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
		Department dept= departmentService.createDepartment(department)
		return ResponseEntity.ok().body(dept)
		
	}

	@DeleteMapping('/deleteDeptById/{id}')
	Map<String, Boolean> deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartment(id)
		Map<String, Boolean> response = new HashMap<>()
		response.put('deleted', Boolean.TRUE)
		return response
	}
	
	@PutMapping('/updateDept/{id}')
	ResponseEntity<Department> updateDepartment(@PathVariable(value = 'id') Integer departmentId, @Valid @RequestBody Department departmentDetails) {
		Department dept= departmentService.updateDepartment(departmentId, departmentDetails)
		return ResponseEntity.ok().body(dept)
	}
	

}
