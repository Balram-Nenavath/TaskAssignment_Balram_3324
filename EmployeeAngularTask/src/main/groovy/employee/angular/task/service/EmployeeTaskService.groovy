package employee.angular.task.service

import java.util.List
import java.util.Map
import java.util.Optional

import javax.validation.Valid

import org.springframework.http.ResponseEntity

import employee.angular.task.entity.EmployeeTask
import employee.angular.task.exception.EmployeeNotFoundException

public interface EmployeeTaskService {

	EmployeeTask findEmployeeById(Integer employeeId)
	
	EmployeeTask findEmployeeByName(String employeeName)

	boolean deleteEmployee(Integer employeeId)

	EmployeeTask saveEmployee(EmployeeTask employee, Integer departmentId)

	List<EmployeeTask> findAllEmployees()

	EmployeeTask updateEmployee(Integer employeeId, EmployeeTask employeeDetails)
	
}
