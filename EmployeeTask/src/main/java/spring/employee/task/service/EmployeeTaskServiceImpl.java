package spring.employee.task.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.employee.task.entity.Department;
import spring.employee.task.entity.EmployeeTask;
import spring.employee.task.exception.EmployeeNotFoundException;
import spring.employee.task.repository.DepartmentRepository;
import spring.employee.task.repository.EmployeeTaskRepository;
@Service
public class EmployeeTaskServiceImpl implements EmployeeTaskService{

	@Autowired
	private EmployeeTaskRepository employeeTaskRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public EmployeeTask findById(Integer employeeId) {
	EmployeeTask task = employeeTaskRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("For id " + employeeId));
	    return task;
	}

	@Override
	public boolean delete(Integer employeeId) {
		EmployeeTask employee = employeeTaskRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		employeeTaskRepository.delete(employee);
		return true;	
	}

	@Override
	public EmployeeTask save(EmployeeTask employee) {
		return employeeTaskRepository.save(employee);
	}

	@Override
	public List<EmployeeTask> findAll() {
		List<EmployeeTask> emps = employeeTaskRepository.findAll();
        return emps.stream().collect(toList());
	}

	@Override
	public EmployeeTask findByName(String employeeName) {
		EmployeeTask emp = employeeTaskRepository.findByName(employeeName).orElseThrow(() -> new EmployeeNotFoundException("For name " + employeeName));
        return emp;
	}

	@Override
	public EmployeeTask update(Integer employeeId, EmployeeTask employeeDetails) {
		EmployeeTask employee = employeeTaskRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setName(employeeDetails.getName());
		employee.setMobile(employeeDetails.getMobile());
		employee.setSalary(employeeDetails.getSalary());
		Department existingDepartment = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
		existingDepartment.setName(employeeDetails.getDepartment().getName());
		existingDepartment.setdesignation(employeeDetails.getDepartment().getdesignation());
		if(existingDepartment != null) {
		employee.setDepartment(existingDepartment);
		}
		final EmployeeTask updatedEmployee = employeeTaskRepository.save(employee);
		return updatedEmployee;
	}

}
