package dlugolecki.pawel.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionInfo {
    private String message;
    private String className;
    private String methodName;
    private LocalDateTime dateTime;

    public ExceptionInfo(String message, String className, String methodName) {
        this.message = message;
        this.className = className;
        this.methodName = methodName;
        dateTime = LocalDateTime.now();
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
    
    


}
