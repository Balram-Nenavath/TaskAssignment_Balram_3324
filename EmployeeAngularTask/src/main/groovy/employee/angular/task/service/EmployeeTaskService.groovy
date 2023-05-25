package employee.angular.task.service

import java.util.List
import java.util.Map
import java.util.Optional

import javax.validation.Valid

import org.springframework.http.ResponseEntity

import employee.angular.task.entity.Employee
import employee.angular.task.exception.EmployeeNotFoundException

 interface EmployeeTaskService {

	Employee findEmployeeById(Integer employeeId)
	
	Employee findEmployeeByName(String employeeName)

	boolean deleteEmployee(Integer employeeId)

	Employee saveEmployee(Employee employee, Integer departmentId)

	List<Employee> findAllEmployees()

	Employee updateEmployee(Integer employeeId, Employee employeeDetails)
	
}
