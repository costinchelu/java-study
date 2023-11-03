package messenger_03;

// example of a "manager" class. Transforms and pass messages.
public class Messenger {

    private TemplateEngine templateEngine;
    private MailServer mailServer;

    public Messenger(MailServer mailServer, TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    /**
    * The testing of this sendMessage() method is problematic.
    * It does not return anything, so the results of its work cannot be directly observed.
    * It does not even change the state of the client or template object,
    * so we cannot use these for verification.
    * */
    public void sendMessage(Client client, Template template) {
        String msgContent = templateEngine.prepareMessage(template, client);
        mailServer.send(client.getEmail(), msgContent);
    }
}
