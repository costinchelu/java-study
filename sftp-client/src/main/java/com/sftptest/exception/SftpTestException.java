package com.sftptest.exception;

public class SftpTestException extends Exception {

    public SftpTestException(String message) {
        super(message);
    }

    public SftpTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SftpTestException(Throwable cause) {
        super(cause);
    }
}
