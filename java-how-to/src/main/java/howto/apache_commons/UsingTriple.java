package howto.apache_commons;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Arrays;
import java.util.Objects;

public class UsingTriple {

    private static Triple<String, String[], String[]> getEmailContacts() {
        String emailSender = "";
        String emailReceiver = "emailReceiver1;emailReceiver2;emailReceiver3";
        String emailCC = "emailCcObject1;emailCcObject1";
        if (Objects.nonNull(emailSender) && StringUtils.isNotBlank(emailSender)
                && Objects.nonNull(emailReceiver) && StringUtils.isNotBlank(emailReceiver)) {
            String[] receivers = emailReceiver.split(";");
            String[] cc = Objects.nonNull(emailCC) && StringUtils.isNotBlank(emailCC)
                    ? emailCC.split(";")
                    : new String[]{};
            return Triple.of(emailSender, receivers, cc);
        } else {
            System.out.println("Receivers missing");
            return null;
        }
    }

    public static void main(String[] args) {
        Triple<String, String[], String[]> emailContacts = getEmailContacts();
        if (Objects.nonNull(emailContacts)) {
            System.out.println(emailContacts.getLeft());
            System.out.println(Arrays.toString(emailContacts.getMiddle()));
            System.out.println(Arrays.toString(emailContacts.getRight()));
        }
    }
}
