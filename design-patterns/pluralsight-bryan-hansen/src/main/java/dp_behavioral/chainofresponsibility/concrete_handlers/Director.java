package dp_behavioral.chainofresponsibility.concrete_handlers;


import dp_behavioral.chainofresponsibility.handler_base.Handler;
import dp_behavioral.chainofresponsibility.handler_base.Request;
import dp_behavioral.chainofresponsibility.handler_base.RequestType;

public class Director extends Handler {

    @Override
    public void handleRequest(Request request) {
        if(request.getRequestType() == RequestType.CONFERENCE) {
            System.out.println("Directors can approve conferences");
        } else {
            successor.handleRequest(request);
        }
    }
}
