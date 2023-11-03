package dp_structural.proxy.client;


import dp_structural.proxy.proxy.SecurityProxy;
import dp_structural.proxy.realsubject.TwitterServiceStub;
import dp_structural.proxy.subjectbase.TwitterService;

public class Main {

    public static void main(String[] args) {

        TwitterService service = (TwitterService) SecurityProxy.newInstance(new TwitterServiceStub());
        System.out.println(service.getTimeline("bh5k"));


    }
}
/*
- acts as an interface by wrapping
- can add functionality
- proxy is called to access real object
- we only get to use one instance
- utilities built into Java API

java.lang.reflect.Proxy
java.rmi.*
 */