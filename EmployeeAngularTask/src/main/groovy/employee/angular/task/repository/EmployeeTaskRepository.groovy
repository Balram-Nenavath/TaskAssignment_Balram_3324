package employee.angular.task.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import employee.angular.task.entity.Department
import employee.angular.task.entity.EmployeeTask

@Repository
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask,Integer>{

	Optional<EmployeeTask> findByName(String employeeName)
	
	@Query("SELECT e FROM EmployeeTask e JOIN e.department d WHERE d=:department")
	List<EmployeeTask> findAllByDepartments(@Param("department")Department department)

}
