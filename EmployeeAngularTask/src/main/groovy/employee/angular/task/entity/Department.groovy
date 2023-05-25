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
 class Department{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 Integer id
	
	 String name
	@JsonIgnore
	//@JsonBackReference
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY, mappedBy='department')
	List<Employee> employees= new ArrayList<>()

	
	
}
