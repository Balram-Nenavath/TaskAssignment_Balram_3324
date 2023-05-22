package employee.angular.task.repository

import java.util.Optional

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import employee.angular.task.entity.Department
import employee.angular.task.entity.EmployeeTask

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{

}
