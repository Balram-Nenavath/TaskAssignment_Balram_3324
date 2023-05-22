package employee.angular.task.service

import static java.util.stream.Collectors.toList

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import employee.angular.task.entity.Department
import employee.angular.task.entity.EmployeeTask
import employee.angular.task.exception.DepartmentNotFoundException
import employee.angular.task.exception.EmployeeNotFoundException
import employee.angular.task.repository.DepartmentRepository
import employee.angular.task.repository.EmployeeTaskRepository
@Service
class EmployeeTaskServiceImpl implements EmployeeTaskService{

	@Autowired
	private EmployeeTaskRepository employeeTaskRepository
	
	@Autowired
	private DepartmentTaskService departmentTaskService
	@Autowired
	private DepartmentRepository departmentRepository
	
	public EmployeeTaskServiceImpl(EmployeeTaskRepository employeeTaskRepository, DepartmentTaskService departmentTaskService) {
		this.employeeTaskRepository = employeeTaskRepository
		this.departmentTaskService = departmentTaskService
	}

	private static final Logger logger = LoggerFactory.getLogger(EmployeeTaskServiceImpl.class)


	@Override
	EmployeeTask findEmployeeById(Integer employeeId) {
		logger.info("Fetching task with id: $employeeId")
		Optional<EmployeeTask> employee = employeeTaskRepository.findById(employeeId)
	
		return employee.orElseThrow { new EmployeeNotFoundException("Employee not found for Id: $employeeId") }
	}
	

	
	@Override
	boolean deleteEmployee(Integer employeeId) {
		def existingTask = employeeTaskRepository.findById(employeeId)
	
		return existingTask?.with {
			employeeTaskRepository.deleteById(employeeId)
			logger.info("Employee deleted with id: $employeeId")
			true
		} ?: ( new EmployeeNotFoundException("Employee not found for this id: $employeeId"))
	}
	
	
	
	@Override
	EmployeeTask saveEmployee(EmployeeTask employee, Integer departmentId) {
		Optional<Department> optDepartment = departmentRepository.findById(departmentId)
	
		return optDepartment.map { department ->
			employee.department.add(department)
			logger.info("Employee details Saving is in progress ...")
			employeeTaskRepository.save(employee)
		}.orElseThrow { new DepartmentNotFoundException("Department Not found for this departmentId: $departmentId") }
	}
	
	
	
	@Override
	List<EmployeeTask> findAllEmployees() {
		logger.info("Fetching all Tasks!")
		List<EmployeeTask> emps = employeeTaskRepository.findAll()
		return emps?.collect() ?: (new EmployeeNotFoundException("No records found in DB"))
		
	}


	
	@Override
	EmployeeTask findEmployeeByName(String employeeName) {
		logger.info("Fetching task with id: ${employeeTaskRepository.findByName(employeeName)}")
		Optional<EmployeeTask> employee = employeeTaskRepository.findByName(employeeName)
		return employee.orElseThrow { new EmployeeNotFoundException("Employee not found for Name: $employeeName") }
	}
	
	
	@Override
	EmployeeTask updateEmployee(Integer employeeId, EmployeeTask employeeDetails) {
		Optional<EmployeeTask> employee = employeeTaskRepository.findById(employeeId)
		if (employee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found for this id :: $employeeId")
		}
	
		if (employeeDetails) {
			employee.get().emailId = employeeDetails.emailId
			employee.get().name = employeeDetails.name
			employee.get().designation = employeeDetails.designation
			employee.get().salary = employeeDetails.salary
			employee.get().doj = employeeDetails.doj
	
			if (employeeDetails.department && !employeeDetails.department.empty) {
				Integer departmentId = employeeDetails.department[0].id
				Department department = departmentRepository.findById(departmentId).orElse(null)
	
				if (department) {
					employee.get().department = employeeDetails.department
				} else {
					throw new DepartmentNotFoundException("Department not found")
				}
			}
		}
	
		final EmployeeTask updatedEmployee = employeeTaskRepository.save(employee.get())
		return updatedEmployee
	}
	

}
