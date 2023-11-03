package dp_creational.factory;

public class WebsiteFactoryV1 {

    public static Website getWebsite(String siteType) {

        // this will receive a string as a parameter and will create a website type object
        // which in turn will load in the pages list all the particular pages of the different website types
        // as set in the implemetation on createWebsite() method
        switch (siteType) {
            case "blog" : {
                return new Blog();
            }
            case "shop" : {
                return new Shop();
            }
            default: {
                return null;
            }
        }
    }
}
