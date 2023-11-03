package venkats;

import java.util.function.Consumer;

class MailService {

    public static void print(String message) {
        System.out.println(message);
    }

    public MailService from(String addr) {
        print("from " + addr);
        return this;
    }
    public MailService to(String addr) {
        print("to " + addr);
        return this;
    }
    public MailService subject(String line) {
        print("subject: " + line);
        return this;
    }
    public MailService body(String msg) {
        print("body:\t" + msg);
        return this;
    }
    public static void send(Consumer<MailService> block) {
        MailService mailService = new MailService();
        block.accept(mailService);
        print("sending ...");
    }
}

public class Builder {

    public static void main(String[] args) {
        MailService.send(mailService ->
                mailService
                    .from("builder@a.com")
                    .to("dest@b.com")
                    .subject("Builder")
                    .body("Body of the mail"));
    }
}
