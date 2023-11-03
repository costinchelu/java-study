package howto.implement_clone;



public class City implements Cloneable {

    private String cityName;


    public City(String cityName) {
        this.cityName = cityName;
    }

    // implementing a copy constructor
    public City(City original) {
        this.cityName = original.cityName;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }




    public String getCityName() {
        return cityName;
    }
}
