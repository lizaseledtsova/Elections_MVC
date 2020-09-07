package dlugolecki.pawel.exceptions;

import java.text.MessageFormat;

public class MyException extends RuntimeException {
    private ExceptionInfo exceptionInfo;

    public MyException(String message, String className, String methodName) {
        exceptionInfo = new ExceptionInfo(message, className, methodName);
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("[{3} {0}.{1} {2}",
                exceptionInfo.getClassName(),
                exceptionInfo.getMethodName(),
                exceptionInfo.getMessage(),
                exceptionInfo.getDateTime()
        );
    }
}
