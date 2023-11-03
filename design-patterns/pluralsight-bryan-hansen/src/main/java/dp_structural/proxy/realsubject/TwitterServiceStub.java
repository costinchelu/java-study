package dp_structural.proxy.realsubject;


import dp_structural.proxy.subjectbase.TwitterService;

public class TwitterServiceStub implements TwitterService {

    @Override
    public String getTimeline(String screenName) {
        return "My timeline";
    }

    @Override
    public void postToTimeline(String screenName, String message) {

    }
}
