package howto;

import lombok.Setter;

public class TabbedString {

    public static void main(String[] args) {

        SomeString someString = new SomeString();
        someString.setLabel(" : [STOCK] : ");

        someString.setText("some text");
        someString.setQuantity(24.44);
        System.out.println(someString);

        someString.setText("some more    more text. And more text...");
        someString.setQuantity(14.3);
        System.out.println(someString);
    }
}

@Setter
class SomeString {

    private String text;

    private String label;

    private double quantity;

    @Override
    public String toString() {
        if (text.length() < 20) {
            text = text + "                  ";
        }
        if (text.length() > 20) {
            text = text.substring(0, 20);
        }
        return text + label + quantity;
    }
}