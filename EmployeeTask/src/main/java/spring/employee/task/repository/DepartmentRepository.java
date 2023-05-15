package spring.employee.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.employee.task.entity.Department;
import spring.employee.task.entity.EmployeeTask;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{

}
