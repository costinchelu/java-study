package dp_behavioral.chainofresponsibility.client;


import dp_behavioral.chainofresponsibility.concrete_handlers.CEO;
import dp_behavioral.chainofresponsibility.concrete_handlers.Director;
import dp_behavioral.chainofresponsibility.concrete_handlers.VP;
import dp_behavioral.chainofresponsibility.handler_base.Request;
import dp_behavioral.chainofresponsibility.handler_base.RequestType;

public class Client {

    public static void main(String[] args) {

        Director bryan = new Director();
        VP crystal = new VP();
        CEO jeff = new CEO();

        bryan.setSuccessor(crystal);
        crystal.setSuccessor(jeff);

        Request request = new Request(RequestType.CONFERENCE, 500);
        bryan.handleRequest(request);

        request = new Request(RequestType.PURCHASE, 1000);
        bryan.handleRequest(request);

        request = new Request(RequestType.PURCHASE, 2000);
        bryan.handleRequest(request);
    }
}
/*
if a request cannot be approved by the directr, it will be passed to his superior in the chain of command
 */