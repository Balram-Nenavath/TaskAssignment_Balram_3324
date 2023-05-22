package employee.angular.task.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
@Entity
@Table(name="department")
public class Department{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id
	
	private String name
	@JsonIgnore
	//@JsonBackReference
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY, mappedBy='department')
	List<EmployeeTask> employees= new ArrayList<>()

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

	public List<EmployeeTask> getEmployees() {
		return employees
	}

	public void setEmployees(List<EmployeeTask> employees) {
		this.employees = employees
	}

	@Override
	public int hashCode() {
		return Objects.hash(employees, id, name)
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		Department other = (Department) obj
		return Objects.equals(employees, other.employees) && Objects.equals(id, other.id)&& Objects.equals(name, other.name)
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employees=" + employees + "]"
	}


	public Department() {
		super()
		// TODO Auto-generated constructor stub
	}

	public Department(Integer id, String name, List<EmployeeTask> employees) {
		super()
		this.id = id
		this.name = name
		this.employees = employees
	}
	
}
