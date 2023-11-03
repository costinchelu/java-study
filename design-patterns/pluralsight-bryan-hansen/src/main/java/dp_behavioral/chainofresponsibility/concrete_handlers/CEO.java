package dp_behavioral.chainofresponsibility.concrete_handlers;


import dp_behavioral.chainofresponsibility.handler_base.Handler;
import dp_behavioral.chainofresponsibility.handler_base.Request;

public class CEO extends Handler {

    @Override
    public void handleRequest(Request request) {
        System.out.println("ConcreteHandlers.CEO can approve anything they want");
    }
}
