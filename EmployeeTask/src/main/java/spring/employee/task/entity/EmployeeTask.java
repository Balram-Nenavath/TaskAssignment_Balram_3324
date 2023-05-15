package spring.employee.task.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@Column(name = "email_id")
	private String emailId;
	@NotNull
	private String mobile;
	@NotNull
	private double salary;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private Department department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeTask [id=" + id + ", name=" + name + ", emailId=" + emailId + ", mobile=" + mobile + ", salary="
				+ salary + ", department=" + department + "]";
	}

	public EmployeeTask(Integer id, @NotNull @NotBlank String name, @NotNull String emailId, @NotNull String mobile,
			@NotNull double salary, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.mobile = mobile;
		this.salary = salary;
		this.department = department;
	}

	public EmployeeTask() {
		super();
		// TODO Auto-generated constructor stub
	}

}
