package spring.employee.task.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import spring.employee.task.entity.EmployeeTask;
import spring.employee.task.exception.EmployeeNotFoundException;

public interface EmployeeTaskService {

	EmployeeTask findById(Integer employeeId);
	
	EmployeeTask findByName(String employeeName);

	boolean delete(Integer employeeId);

	EmployeeTask save(EmployeeTask employee);

	List<EmployeeTask> findAll();

	EmployeeTask update(Integer employeeId,EmployeeTask employeeDetails);
	
}
