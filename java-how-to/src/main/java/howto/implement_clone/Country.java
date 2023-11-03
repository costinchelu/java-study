package howto.implement_clone;



public class Country {

    private String countryName;


    public Country(String countryName) {
        this.countryName = countryName;
    }

    // implement copy contructor
    public Country(Country original) {
        this.countryName = original.countryName;
    }





    public String getCountryName() {
        return countryName;
    }
}
