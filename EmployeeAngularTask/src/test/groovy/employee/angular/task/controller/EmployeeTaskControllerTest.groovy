package employee.angular.task.controller

import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import java.time.LocalDate

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

import com.fasterxml.jackson.databind.ObjectMapper

import employee.angular.task.entity.EmployeeTask
import employee.angular.task.service.EmployeeTaskService

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeTaskController.class)
public class EmployeeTaskControllerTest {

	@Autowired
	private MockMvc mockMvc

	@MockBean
	private EmployeeTaskService employeeTaskService
	
	@Autowired
	private EmployeeTaskController employeeController

	
	@BeforeEach
	void setup() {
		System.setProperty("crossorigin", "http://localhost:4200")
	}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		List<EmployeeTask> employeeList = new ArrayList<>()

		when(employeeTaskService.findAllEmployees()).thenReturn(employeeList)

		mockMvc.perform(get("/employees/all"))
				.andExpect(status().isOk())
		verify(employeeTaskService, times(1)).findAllEmployees()
	}

	@Test
	public void testGetEmployeeById() throws Exception {
		int employeeId = 1
		EmployeeTask employee = new EmployeeTask()

		when(employeeTaskService.findEmployeeById(employeeId)).thenReturn(employee)

		mockMvc.perform(get("/employees/getById/{id}", employeeId))
				.andExpect(status().isOk())

		verify(employeeTaskService, times(1)).findEmployeeById(employeeId)
	}

	@Test
	public void testGetEmployeeByName() throws Exception {
		String employeeName = "Balram"
		EmployeeTask employee = new EmployeeTask()

		when(employeeTaskService.findEmployeeByName(employeeName)).thenReturn(employee)

		mockMvc.perform(get("/employees/getByName/{name}", employeeName))
				.andExpect(status().isOk())

		verify(employeeTaskService, times(1)).findEmployeeByName(employeeName)
	}


	@Test
	void testAddEmployee() throws Exception {
		EmployeeTask savedEmployee = new EmployeeTask()
		savedEmployee.setId(1)
		savedEmployee.setName("Ram")
		when(employeeTaskService.saveEmployee(any(EmployeeTask.class), anyInt())).thenReturn(savedEmployee)

		String requestContent = "{\"name\":\"Ram\",\"emailId\":\"ram@gmail.com\",\"designation\":\"TA\",\"salary\":\"25000.00\",\"doj\":\"2022-06-02\",\"employees\":\"[1]\"}"

		mockMvc.perform(post("/employees/addEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestContent)
				.param("departmentId", "123"))
				.andExpect(status().isOk())

		verify(employeeTaskService).saveEmployee(any(EmployeeTask.class), eq(123))
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		int employeeId = 1


		mockMvc.perform(delete("/employees/deleteById/{id}", employeeId))
				.andExpect(status().isOk())

		verify(employeeTaskService, times(1)).deleteEmployee(employeeId)
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		int employeeId = 1
		EmployeeTask employee = new EmployeeTask()

		when(employeeTaskService.updateEmployee(eq(employeeId), any(EmployeeTask.class))).thenReturn(employee)
		String requestContent = "{\"name\":\"Ram\",\"emailId\":\"ram@gmail.com\",\"designation\":\"TA\",\"salary\":\"25000.00\",\"doj\":\"2022-06-02\",\"employees\":\"[1]\"}"
		
		mockMvc.perform(put("/employees/updateEmployee/{id}", employeeId)
				.content(requestContent)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())

		verify(employeeTaskService, times(1)).updateEmployee(eq(employeeId), any(EmployeeTask.class))
	}
}
