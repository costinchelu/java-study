package behavioral.adapter;



class VehicleRentServiceC extends RentedVehicle implements ITarget {

    public VehicleRentServiceC(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void description() {
        System.out.println(super.toString());
    }

    @Override
    public void bookPackage() {
        super.rentVehicle();
    }
}
