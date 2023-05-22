package employee.angular.task.exception;

public class DepartmentNotFoundException extends RuntimeException {

private String message
	
	public DepartmentNotFoundException() {}
	
	public DepartmentNotFoundException(String msg)
	{
		super(msg)
		this.message = msg
	}
}
