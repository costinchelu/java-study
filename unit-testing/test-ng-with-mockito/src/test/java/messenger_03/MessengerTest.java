package messenger_03;

import org.testng.annotations.Test;

import static org.mockito.Mockito.*;


public class MessengerTest {

    // Some static value to be used within the test code.
    private static final String SENDER_ADD = "some@email.com";
    private static final String MSG_CONTENT = "Dear John! You are fired.";

    @Test
    public void shouldSendEmail() {
        // Creation of test doubles using static mock() method. At this point they do not differ from each other
        // (except for the type they are pretending to be).
        Template template = mock(Template.class);
        Client client = mock(Client.class);
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = mock(TemplateEngine.class);

        // Creation of the SUT. No mock() method here,
        // we want to test the real one! Dependency injection of DOCs into the SUT.
        Messenger sut = new Messenger(mailServer, templateEngine);

        // Stubbing of DOCs in order to satisfy the SUT
        when(client.getEmail()).thenReturn(SENDER_ADD);
        when(templateEngine.prepareMessage(template, client)).thenReturn(MSG_CONTENT);

        // Execution of the SUT method.
        sut.sendMessage(client, template);

        //Verification of the behaviour of the SUT: "was the send() method invoked on
        // mailServer DOC with the same SENDER_ADD and MSG_CONTENT that were obtained
        // from other collaborators?".
        verify(mailServer).send(SENDER_ADD, MSG_CONTENT);
    }

}