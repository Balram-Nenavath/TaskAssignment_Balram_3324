package spring.employee.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.employee.task.entity.EmployeeTask;
import spring.employee.task.exception.EmployeeNotFoundException;
import spring.employee.task.service.EmployeeTaskService;

@CrossOrigin(value="${crossorigin}")
@RestController
@RequestMapping("/employees")
public class EmployeeTaskController {
	
	
	@Autowired
	private EmployeeTaskService employeeTaskService;

	@GetMapping("/all")
	public List<EmployeeTask> getAllEmployees() {
		return employeeTaskService.findAll();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<EmployeeTask> getEmployeeById(@PathVariable(value = "id") Integer employeeId)
			 {
		EmployeeTask employee = employeeTaskService.findById(employeeId);
		return ResponseEntity.ok().body(employee);
	}
	
	@GetMapping("getByName/{name}")
	public ResponseEntity<EmployeeTask> getEmployeeByName(@PathVariable(value = "name") String employeeName)
			{
		EmployeeTask employee = employeeTaskService.findByName(employeeName);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/addEmployee")
	public EmployeeTask createEmployee(@Valid @RequestBody EmployeeTask employee) {
		return employeeTaskService.save(employee);
	}

	@DeleteMapping("/deleteById/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId)  {
		
		employeeTaskService.delete(employeeId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/updateEmployee/{id}")
	public EmployeeTask updateEmployee(@PathVariable(value = "id") Integer employeeId,
			@Valid @RequestBody EmployeeTask employeeDetails) {
		return employeeTaskService.update(employeeId, employeeDetails);
	}
	
}
