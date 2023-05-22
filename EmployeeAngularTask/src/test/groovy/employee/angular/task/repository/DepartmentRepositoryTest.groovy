package employee.angular.task.repository;


import static org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

import employee.angular.task.entity.Department

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository

    @Test
     void testSaveDepartment() {
        Department department = new Department()
        department.setName("IT")

        Department savedDepartment = departmentRepository.save(department)

        assertNotNull(savedDepartment.getId())

        assertEquals("IT", savedDepartment.getName())
    }

    @Test
    void testFindDepartmentById() {
        Department department = new Department()
        department.setName("IT")
        Department savedDepartment = departmentRepository.save(department)

        Optional<Department> optionalDepartment = departmentRepository.findById(savedDepartment.getId())

        assertTrue(optionalDepartment.isPresent())

        Department foundDepartment = optionalDepartment.get()
        assertEquals("IT", foundDepartment.getName())
    }

    @Test
    void testDeleteDepartment() {
        Department department = new Department()
        department.setName("IT")
        Department savedDepartment = departmentRepository.save(department)

        departmentRepository.deleteById(savedDepartment.getId())

        assertFalse(departmentRepository.existsById(savedDepartment.getId()))
    }
}
