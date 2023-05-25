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


@Entity
@Table
class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer id
	@NotNull
	 String name
	@NotNull
	@Column(name = 'email_id')
	 String emailId
	@NotNull
	 String designation
	@NotNull
	 double salary
	@NotNull
	@Column(name='date_of_joining')
	 LocalDate doj

	//@JsonManagedReference
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinTable(name="employee_department", joinColumns=@JoinColumn(name='employee_id'),
		inverseJoinColumns = @JoinColumn(name='department_id'))
	 List<Department> department= new ArrayList<>()



	

}
