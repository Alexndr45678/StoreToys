package exceptions;

import view.Msg;

public class EmptyExceptions extends Exception{
    public EmptyExceptions() {
        super(Msg.emptyQueueMsg);
    }
}
