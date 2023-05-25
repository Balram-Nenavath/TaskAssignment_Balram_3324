package employee.angular.task.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import employee.angular.task.entity.Department
import employee.angular.task.entity.Employee
import employee.angular.task.exception.DepartmentNotFoundException
import employee.angular.task.repository.DepartmentRepository
import employee.angular.task.repository.EmployeeTaskRepository
@Service
class DepartmentTaskServiceImpl implements DepartmentTaskService{

	@Autowired
	DepartmentRepository departmentRepository

	@Autowired
	EmployeeTaskRepository employeeTaskRepository

	DepartmentTaskServiceImpl(DepartmentRepository departmentRepository, EmployeeTaskRepository employeeTaskRepository)
	{
		this.departmentRepository= departmentRepository
		this.employeeTaskRepository=employeeTaskRepository
	}


	@Override
	Department getDepartmentById(Integer departmentId) {
		def department = departmentRepository.findById(departmentId).orElse(null)

		if (department) {
			department.employees = employeeTaskRepository.findAllByDepartments(department)
			department
		} else {
			throw new DepartmentNotFoundException("Department not found for this id: $departmentId")
		}
	}

	@Override
	boolean deleteDepartment(Integer departmentId) {

		def existingTask = departmentRepository.findById(departmentId)
		if(existingTask == null)
		{
			throw new DepartmentNotFoundException("Department not found for this id :: " + departmentId)
		}else {
			departmentRepository.deleteById(departmentId)
			return true
		}
	}

	@Override
	Department createDepartment(Department department) {
		return  departmentRepository.save(department)
	}

	@Override
	List<Department> getAllDepartments() {
		List<Department> departments=  departmentRepository.findAll()
		if(departments!=null)
		{
			return departments
		}else
		{
			throw new DepartmentNotFoundException("No Records found in the DB")
		}
	}


	@Override
	Department updateDepartment(Integer departmentId, Department departmentDetails) {
		Department department  = departmentRepository.findById(departmentId).orElseThrow {
			new DepartmentNotFoundException("Department not found for this id :: $departmentId")
		}
		department.setName(departmentDetails.getName())
		final Department updatedDepartment = departmentRepository.save(department)
		return updatedDepartment
	}

}
