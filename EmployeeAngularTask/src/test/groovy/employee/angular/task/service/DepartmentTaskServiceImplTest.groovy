package employee.angular.task.service
import static org.junit.jupiter.api.Assertions.*
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import employee.angular.task.entity.Department
import employee.angular.task.entity.Employee
import employee.angular.task.exception.DepartmentNotFoundException
import employee.angular.task.repository.DepartmentRepository
import employee.angular.task.repository.EmployeeTaskRepository

class DepartmentTaskServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository

    @Mock
    private EmployeeTaskRepository employeeTaskRepository

    @InjectMocks
    private DepartmentTaskServiceImpl departmentTaskService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testGetDepartmentById() {
        Integer departmentId = 1
        Department department = new Department()
        department.setId(departmentId)
        List<Employee> employees = new ArrayList<>()
        employees.add(new Employee())
        department.setEmployees(employees)

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department))
        when(employeeTaskRepository.findAllByDepartments(department)).thenReturn(employees)

        Department result = departmentTaskService.getDepartmentById(departmentId)

        assertEquals(departmentId, result.getId())
        assertEquals(employees, result.getEmployees())

        verify(departmentRepository, times(1)).findById(departmentId)
        verify(employeeTaskRepository, times(1)).findAllByDepartments(department)
    }

    @Test
    void testGetDepartmentByIdDepartmentNotFound() {
        Integer departmentId = 1

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty())

		assertThrows(DepartmentNotFoundException) {
			departmentTaskService.getDepartmentById(departmentId)}

        verify(departmentRepository, times(1)).findById(departmentId)
    }

    @Test
    void testDeleteDepartment() {
        Integer departmentId = 1
        Department department = new Department()
        department.setId(departmentId)

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department))

        boolean result = departmentTaskService.deleteDepartment(departmentId)

        assertTrue(result)

        verify(departmentRepository, times(1)).findById(departmentId)
        verify(departmentRepository, times(1)).deleteById(departmentId)
    }



    @Test
    void testCreateDepartment() {
        Department department = new Department()
        department.setId(1)

        when(departmentRepository.save(department)).thenReturn(department)

        Department result = departmentTaskService.createDepartment(department)

        assertEquals(department, result)

        verify(departmentRepository, times(1)).save(department)
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = new ArrayList<>()
        departments.add(new Department())
        departments.add(new Department())

        when(departmentRepository.findAll()).thenReturn(departments)

        List<Department> result = departmentTaskService.getAllDepartments()

        assertEquals(departments, result)

        verify(departmentRepository, times(1)).findAll()
    }


    @Test
    void testUpdateDepartment() {
        Integer departmentId = 1
        Department existingDepartment = new Department()
        existingDepartment.setId(departmentId)
        existingDepartment.setName("Existing Department")

        Department updatedDepartment = new Department()
        updatedDepartment.setId(departmentId)
        updatedDepartment.setName("Updated Department")

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(existingDepartment))
        when(departmentRepository.save(existingDepartment)).thenReturn(updatedDepartment)

        Department result = departmentTaskService.updateDepartment(departmentId, updatedDepartment)

        assertEquals(departmentId, result.getId())
        assertEquals("Updated Department", result.getName())

        verify(departmentRepository, times(1)).findById(departmentId)
        verify(departmentRepository, times(1)).save(existingDepartment)
    }

    @Test
    void testUpdateDepartmentDepartmentNotFound() {
        Integer departmentId = 1

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty())

			assertThrows(DepartmentNotFoundException) {
			departmentTaskService.updateDepartment(departmentId,new Department())}

        verify(departmentRepository, times(1)).findById(departmentId)
    }
}
