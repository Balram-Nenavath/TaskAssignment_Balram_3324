package employee.angular.task.controller

import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Optional

import javax.validation.Valid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import employee.angular.task.entity.Employee
import employee.angular.task.exception.EmployeeNotFoundException
import employee.angular.task.service.EmployeeTaskService

@CrossOrigin('${crossorigin}')
@RestController
@RequestMapping('/employees')
public class EmployeeTaskController {
	
	
	@Autowired
	EmployeeTaskService employeeTaskService

	@GetMapping('/all')
	List<Employee> getAllEmployees() {
		return employeeTaskService.findAllEmployees()
	}

	@GetMapping('/getById/{id}')
	ResponseEntity<Employee> getEmployeeById(@PathVariable(value = 'id') Integer employeeId)
			 {
		Employee employee = employeeTaskService.findEmployeeById(employeeId)
		return ResponseEntity.ok().body(employee)
	}
	
	@GetMapping('getByName/{name}')
	ResponseEntity<Employee> getEmployeeByName(@PathVariable(value = 'name') String employeeName)
			{
		Employee employee = employeeTaskService.findEmployeeByName(employeeName)
		return ResponseEntity.ok().body(employee)
	}

	@PostMapping('/addEmployee')
	ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee, @RequestParam Integer departmentId) {
		Employee empl = employeeTaskService.saveEmployee(employee,departmentId)
		return ResponseEntity.ok().body(empl)
	}

	@DeleteMapping('/deleteById/{id}')
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = 'id') Integer employeeId)  {
		
		employeeTaskService.deleteEmployee(employeeId)
		Map<String, Boolean> response = new HashMap<>()
		response.put('deleted', Boolean.TRUE)
		return response
	}

	@PutMapping('/updateEmployee/{id}')
	ResponseEntity<Employee> updateEmployee(@PathVariable(value = 'id') Integer employeeId, @Valid @RequestBody Employee employeeDetails) {
		Employee employee = employeeTaskService.updateEmployee(employeeId, employeeDetails)
		return ResponseEntity.ok().body(employee)
	}
	
}
