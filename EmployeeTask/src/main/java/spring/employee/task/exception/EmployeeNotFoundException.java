package spring.employee.task.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmployeeNotFoundException extends RuntimeException {

private String message;
	
	public EmployeeNotFoundException() {}
	
	public EmployeeNotFoundException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}
