package employee.angular.task.entity

import java.time.LocalDate

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id
	@NotNull
	private String name
	@NotNull
	@Column(name = 'email_id')
	private String emailId
	@NotNull
	private String designation
	@NotNull
	private double salary
	@NotNull
	@Column(name='date_of_joining')
	private LocalDate doj

	//@JsonManagedReference
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinTable(name="employee_department", joinColumns=@JoinColumn(name='employee_id'),
		inverseJoinColumns = @JoinColumn(name='department_id'))
	private List<Department> department= new ArrayList<>()


	public Integer getId() {
		return id
	}


	public void setId(Integer id) {
		this.id = id
	}


	public String getName() {
		return name
	}


	public void setName(String name) {
		this.name = name
	}


	public String getEmailId() {
		return emailId
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId
	}


	public String getDesignation() {
		return designation
	}


	public void setDesignation(String designation) {
		this.designation = designation
	}


	public double getSalary() {
		return salary
	}


	public void setSalary(double salary) {
		this.salary = salary
	}


	public LocalDate getDoj() {
		return doj
	}


	public void setDoj(LocalDate doj) {
		this.doj = doj
	}


	public List<Department> getDepartment() {
		return department
	}


	public void setDepartment(List<Department> department) {
		this.department = department
	}


	@Override
	public int hashCode() {
		return Objects.hash(department, designation, doj, emailId, id, name, salary)
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		EmployeeTask other = (EmployeeTask) obj
		return Objects.equals(department, other.department) && Objects.equals(designation, other.designation)&& Objects.equals(doj, other.doj) && Objects.equals(emailId, other.emailId)&& Objects.equals(id, other.id) && Objects.equals(name, other.name)	&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
	}


	@Override
	public String toString() {
		return "EmployeeTask [id=" + id + ", name=" + name + ", emailId=" + emailId + ", designation=" + designation
				+ ", salary=" + salary + ", doj=" + doj + ", department=" + department + "]"
	}


	public EmployeeTask() {
		super()
		// TODO Auto-generated constructor stub
	}


	public EmployeeTask(Integer id, String name, String emailId, String designation, double salary, LocalDate doj,
			List<Department> department) {
		super()
		this.id = id
		this.name = name
		this.emailId = emailId
		this.designation = designation
		this.salary = salary
		this.doj = doj
		this.department = department
	}
	
	
	

}
