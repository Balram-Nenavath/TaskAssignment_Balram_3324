package employee.angular.task.service
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension

import static org.mockito.Mockito.*
import static org.junit.jupiter.api.Assertions.*

import java.util.ArrayList
import java.util.List
import java.util.Optional

import employee.angular.task.entity.Department
import employee.angular.task.entity.EmployeeTask
import employee.angular.task.exception.DepartmentNotFoundException
import employee.angular.task.exception.EmployeeNotFoundException
import employee.angular.task.repository.DepartmentRepository
import employee.angular.task.repository.EmployeeTaskRepository
@ExtendWith(MockitoExtension.class)
class EmployeeTaskServiceImplTest {

	@Mock
	private EmployeeTaskRepository employeeTaskRepository

	@Mock
	private DepartmentTaskService departmentTaskService

	@Mock
	private DepartmentRepository departmentRepository

	@InjectMocks
	private EmployeeTaskServiceImpl employeeTaskService

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this)
	}

	@Test
	void testFindEmployeeById() {
		Integer employeeId = 1
		EmployeeTask employee = new EmployeeTask()
		when(employeeTaskRepository.findById(employeeId)).thenReturn(Optional.of(employee))

		EmployeeTask result = employeeTaskService.findEmployeeById(employeeId)

		assertEquals(employee, result)
	}

	@Test
	void testFindEmployeeById_EmployeeNotFoundException() {
		Integer employeeId = 1
		when(employeeTaskRepository.findById(employeeId)).thenReturn(Optional.empty())
		assertThrows(EmployeeNotFoundException) {
			employeeTaskService.findEmployeeById(employeeId)}
	}

	@Test
	void testDeleteEmployee() {
		Integer employeeId = 1
		EmployeeTask existingTask = new EmployeeTask()
		when(employeeTaskRepository.findById(employeeId)).thenReturn(Optional.of(existingTask))

		boolean result = employeeTaskService.deleteEmployee(employeeId)

		assertTrue(result)
		verify(employeeTaskRepository, times(1)).deleteById(employeeId)
	}


	@Test
	void testSaveEmployee() {
		EmployeeTask employee = new EmployeeTask()
		Integer departmentId = 1
		Department department = new Department()
		department.setId(departmentId)
		Optional<Department> optDepartment = Optional.of(department)
		when(departmentRepository.findById(departmentId)).thenReturn(optDepartment)
		when(employeeTaskRepository.save(employee)).thenReturn(employee)

		EmployeeTask result = employeeTaskService.saveEmployee(employee, departmentId)

		assertEquals(employee, result)
		assertTrue(employee.getDepartment().contains(department))
		verify(employeeTaskRepository, times(1)).save(employee)
	}


	@Test
	void testFindAllEmployees() {
		List<EmployeeTask> employees = new ArrayList<>()
		employees.add(new EmployeeTask())
		employees.add(new EmployeeTask())
		when(employeeTaskRepository.findAll()).thenReturn(employees)

		List<EmployeeTask> result = employeeTaskService.findAllEmployees()

		assertEquals(employees, result)
	}

	@Test
	void testFindEmployeeByName() {
		String employeeName = "Ram"
		EmployeeTask employee = new EmployeeTask()
		when(employeeTaskRepository.findByName(employeeName)).thenReturn(Optional.of(employee))

		EmployeeTask result = employeeTaskService.findEmployeeByName(employeeName)

		assertEquals(employee, result)
	}

	@Test
	void testFindEmployeeByName_EmployeeNotFoundException() {
		String employeeName = "Ram"
		when(employeeTaskRepository.findByName(employeeName)).thenReturn(Optional.empty())
		assertThrows(EmployeeNotFoundException) {
			employeeTaskService.findEmployeeByName(employeeName)}
	}

	@Test
	void testUpdateEmployee() {
		Integer employeeId = 1
		EmployeeTask existingEmployee = new EmployeeTask()
		existingEmployee.setEmailId("ram@example.com")
		EmployeeTask updatedEmployeeDetails = new EmployeeTask()
		updatedEmployeeDetails.setEmailId("krish@example.com")
		when(employeeTaskRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee))
		when(employeeTaskRepository.save(existingEmployee)).thenReturn(existingEmployee)

		EmployeeTask result = employeeTaskService.updateEmployee(employeeId, updatedEmployeeDetails)

		assertEquals(existingEmployee, result)
		assertEquals("krish@example.com", existingEmployee.getEmailId())
	}

	@Test
	void testUpdateEmployee_EmployeeNotFoundException() {
		Integer employeeId = 1
		when(employeeTaskRepository.findById(employeeId)).thenReturn(Optional.empty())
		EmployeeTask employeeDetails = new EmployeeTask()
		assertThrows(EmployeeNotFoundException) {
			employeeTaskService.updateEmployee(employeeId,employeeDetails)}
	}
}
