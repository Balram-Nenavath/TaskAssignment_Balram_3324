package employee.angular.task.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import employee.angular.task.entity.Department
import employee.angular.task.entity.Employee

@Repository
interface EmployeeTaskRepository extends JpaRepository<Employee,Integer>{

	Optional<Employee> findByName(String employeeName)
	
	@Query("SELECT e FROM Employee e JOIN e.department d WHERE d=:department")
	List<Employee> findAllByDepartments(@Param("department")Department department)

}
