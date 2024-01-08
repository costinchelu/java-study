package behavioral.adapter;






class Accommodation implements ITarget {

    @Override
    public void description() {
        System.out.println("This service includes only accommodation.");
    }

    @Override
    public void bookPackage() {
        System.out.println("Accommodation is reserved!");
    }
}
































public class Client {
}
