package structural.Template;

import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract class TravelPackage {

    protected int packageCode;


    public abstract void searchForAccommodation(int packageCode);

    public abstract void searchForTransportation(int packageCode);

    public abstract void bookPackage(int packageCode);

    public abstract void payPackage(int packageCode);

    public final void sellPackage() {
        searchForAccommodation(this.packageCode);
        searchForTransportation(this.packageCode);
        bookPackage(this.packageCode);
        payPackage(this.packageCode);
    }
}

class TransportService extends TravelPackage {

    public TransportService(int packageCode) {
        super(packageCode);
    }

    @Override
    public void searchForAccommodation(int packageCode) {
        // not implemented
    }

    @Override
    public void searchForTransportation(int packageCode) {
        System.out.println("Transport found for package " + packageCode);
    }

    @Override
    public void bookPackage(int packageCode) {
        System.out.println("Package " + packageCode + " is now booked");
    }

    @Override
    public void payPackage(int packageCode) {
        System.out.println("Package " + packageCode + " is now payed");
    }
}

class AccommodationService extends TravelPackage {

    public AccommodationService(int packageCode) {
        super(packageCode);
    }

    @Override
    public void searchForAccommodation(int packageCode) {
        System.out.println("Accommodation found for package " + packageCode);
    }

    @Override
    public void searchForTransportation(int packageCode) {
        // not implemented
    }

    @Override
    public void bookPackage(int packageCode) {
        System.out.println("Package " + packageCode + " is now booked");
    }

    @Override
    public void payPackage(int packageCode) {
        System.out.println("Package " + packageCode + " is now payed");
    }
}

class AllInclusiveService extends TravelPackage {

    public AllInclusiveService(int packageCode) {
        super(packageCode);
    }

    @Override
    public void searchForAccommodation(int packageCode) {
        System.out.println("Accommodation found for package " + packageCode);
    }

    @Override
    public void searchForTransportation(int packageCode) {
        System.out.println("Transport found for package " + packageCode);
    }

    @Override
    public void bookPackage(int packageCode) {
        System.out.println("Package " + packageCode + " is now booked");
    }

    @Override
    public void payPackage(int packageCode) {
        System.out.println("Package " + packageCode + " is now payed");
    }
}


public class Template {

    public static void main(String[] args) {
        TravelPackage tp1 = new AccommodationService(58);
        TravelPackage tp2 = new AllInclusiveService(65);
        tp1.sellPackage();
        tp2.sellPackage();
    }
}
