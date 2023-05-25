package employee.angular.task.service;

import java.util.List;

import employee.angular.task.entity.Department;
import employee.angular.task.entity.Employee

 interface DepartmentTaskService {
	


		Department getDepartmentById(Integer departmentId)
		
		boolean deleteDepartment(Integer departmentId)

		Department createDepartment(Department department)

		List<Department> getAllDepartments()

		Department updateDepartment(Integer departmentId, Department departmentDetails)
		
	}

