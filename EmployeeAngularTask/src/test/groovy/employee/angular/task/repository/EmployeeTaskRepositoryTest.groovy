package employee.angular.task.repository

import static org.junit.jupiter.api.Assertions.*

import java.time.LocalDate

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension

import employee.angular.task.entity.EmployeeTask

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeTaskRepositoryTest {

	@Autowired
	private EmployeeTaskRepository employeeTaskRepository

	@Autowired
	private DepartmentRepository departmentRepository
	@Autowired
	private TestEntityManager entityManager


	@Test
	void testFindByName() {
		EmployeeTask employee = new EmployeeTask()
		employee.setName("Ram")
		employee.setDesignation("Manager")
		employee.setEmailId("ram@gmail.com")
		employee.setDoj(LocalDate.now())
		entityManager.persist(employee)
		entityManager.flush()
		Optional<EmployeeTask> optionalEmployee = employeeTaskRepository.findByName("Ram")
		assertTrue(optionalEmployee.isPresent())
		assertEquals("Ram", optionalEmployee.get().getName())
	}

	@Test
	void testSaveEmployeeTask() {
		EmployeeTask employeeTask = new EmployeeTask()
		employeeTask.setName("Ram")
		employeeTask.setEmailId("ram@example.com")
		employeeTask.setDesignation("Developer")
		employeeTask.setDoj(LocalDate.now())
		EmployeeTask savedTask = employeeTaskRepository.save(employeeTask)
		Assertions.assertNotNull(savedTask.getId())
	}

	@Test
	void testFindById() {
		EmployeeTask employeeTask = new EmployeeTask()
		employeeTask.setName("Ram")
		employeeTask.setEmailId("ram@example.com")
		employeeTask.setDesignation("Developer")
		employeeTask.setDoj(LocalDate.now())
		entityManager.persistAndFlush(employeeTask)
		Optional<EmployeeTask> foundTask = employeeTaskRepository.findById(employeeTask.getId())
		Assertions.assertTrue(foundTask.isPresent())
		Assertions.assertEquals(employeeTask.getId(), foundTask.get().getId())
		Assertions.assertEquals(employeeTask.getName(), foundTask.get().getName())
	}

	@Test
	public void testDeleteEmployeeTask() {
		EmployeeTask employeeTask = new EmployeeTask()
		employeeTask.setName("Ram")
		employeeTask.setEmailId("ram@example.com")
		employeeTask.setDesignation("Developer")
		employeeTask.setDoj(LocalDate.now())
		entityManager.persistAndFlush(employeeTask)
		employeeTaskRepository.delete(employeeTask)
		Optional<EmployeeTask> deletedTask = employeeTaskRepository.findById(employeeTask.getId())
		Assertions.assertFalse(deletedTask.isPresent())
	}

	@Test
	public void testFindAll() {
		employeeTaskRepository.deleteAll()
		employeeTaskRepository.flush()
		EmployeeTask task1 = new EmployeeTask()
		task1.setName("Ram")
		task1.setEmailId("ram@example.com")
		task1.setDesignation("Developer")
		task1.setDoj(LocalDate.now())
		entityManager.persistAndFlush(task1)

		EmployeeTask task2 = new EmployeeTask()
		task2.setName("Krishna")
		task2.setEmailId("krishna@example.com")
		task2.setDesignation("Manager")
		task2.setDoj(LocalDate.now())
		entityManager.persistAndFlush(task2)

		List<EmployeeTask> tasks = employeeTaskRepository.findAll()
		Assertions.assertEquals(2, tasks.size())
	}
}