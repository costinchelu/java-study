package sec9a_interfaces.phone;

public class Main {

    public static void main(String[] args) {

        iTelephone timsPhone;
        // DeskPhone timsPhone = new DeskPhone(21443322); is also valid
        // but, after that, timsPhone would be used only for DeskPhones

        timsPhone = new DeskPhone(21443322);
        timsPhone.powerOn();
        timsPhone.callPhone(21443322);
        timsPhone.answer();

        System.out.println("----------------");

        timsPhone = new MobilePhone(722443322);
        timsPhone.powerOn();
        timsPhone.callPhone(722443322);
        timsPhone.answer();
    }
}
