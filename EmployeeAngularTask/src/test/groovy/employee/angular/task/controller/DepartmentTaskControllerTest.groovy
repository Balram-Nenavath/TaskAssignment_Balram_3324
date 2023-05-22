package employee.angular.task.controller
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import static org.mockito.Mockito.*
import employee.angular.task.entity.Department
import employee.angular.task.service.DepartmentTaskService
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.contentType

@WebMvcTest(DepartmentTaskController.class)
class DepartmentTaskControllerTest {

	@Autowired
	private MockMvc mockMvc

	@MockBean
	private DepartmentTaskService departmentService

	@BeforeEach
	void setup() {
		System.setProperty("crossorigin", "http://localhost:4200")
	}

	@Test
	void testGetAllDepartments() throws Exception {
		List<Department> departments = Arrays.asList(
			 new Department(1,"Updated department 1",[]),
			 new Department(2,"Updated department 2",[])
		)
		when(departmentService.getAllDepartments()).thenReturn(departments)

		mockMvc.perform(get("/departments/getAllDepts"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		verify(departmentService).getAllDepartments()
	}

	@Test
	void testGetDepartmentById() throws Exception {
		Department department =  new Department(1,"Updated department",[])
		when(departmentService.getDepartmentById(1)).thenReturn(department)

		mockMvc.perform(get("/departments/getDeptById/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		verify(departmentService).getDepartmentById(1)
	}

	@Test
	void testCreateDepartment() throws Exception {
		Department department = new Department(1, "Department 1",[])
		when(departmentService.createDepartment(any(Department.class))).thenReturn(department)

		mockMvc.perform(post("/departments/createDept")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"id\": 1, \"name\": \"Department 1\"}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		verify(departmentService).createDepartment(any(Department.class))
	}

	@Test
	void testDeleteDepartment() throws Exception {
		mockMvc.perform(delete("/departments/deleteDeptById/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		verify(departmentService).deleteDepartment(1)
	}

	@Test
	void testUpdateDepartment() throws Exception {
		Department updatedDepartment = new Department(1,"Updated department",[])
		when(departmentService.updateDepartment(eq(1), any(Department.class))).thenReturn(updatedDepartment)

		mockMvc.perform(put("/departments/updateDept/{id}", 1)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"id\": 1, \"name\": \"Updated Department\"}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		verify(departmentService).updateDepartment(eq(1), any(Department.class))
	}

}
