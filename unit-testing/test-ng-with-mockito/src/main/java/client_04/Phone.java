package client_04;

public class Phone {
    private final boolean mobile;
    private final String number;

    public Phone(String number, boolean mobile) {
        this.number = number;
        this.mobile = mobile;
    }

    public Phone(String number) {
        this.number = number;
        this.mobile = number.startsWith("+") && number.endsWith("9");
    }

    public boolean isMobile() {
        return mobile;
    }
}
