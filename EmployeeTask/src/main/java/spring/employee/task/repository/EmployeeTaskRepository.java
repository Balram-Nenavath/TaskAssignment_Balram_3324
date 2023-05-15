package spring.employee.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.employee.task.entity.EmployeeTask;

@Repository
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask,Integer>{

	Optional<EmployeeTask> findByName(String employeeName);

}
