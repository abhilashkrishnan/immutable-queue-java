package jp.paypay.immutable.exception;

public class EmptyQueueException extends EmptyStackException {

    public EmptyQueueException() {
        super();
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}
