package dp_creational.factory;

public class WebsiteFactoryV2 {

    public static Website getWebsite(WebsiteTypeEnum siteType) {

        // this version of factory will use an enum that contains the list of available website types
        // to be constructed and is a refactored version of the String type
        switch (siteType) {
            case BLOG: {
                return new Blog();
            }
            case SHOP: {
                return new Shop();
            }
            default: {
                return null;
            }
        }
    }
}
