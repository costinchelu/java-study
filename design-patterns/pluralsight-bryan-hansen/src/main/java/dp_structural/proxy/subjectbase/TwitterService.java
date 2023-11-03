package dp_structural.proxy.subjectbase;

public interface TwitterService {

    String getTimeline(String screenName);
    void postToTimeline(String screenName, String message);
}
