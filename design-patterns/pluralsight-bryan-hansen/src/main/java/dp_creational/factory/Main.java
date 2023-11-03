package dp_creational.factory;

public class Main {

    public static void main(String[] args) {

        Website websiteV1 = WebsiteFactoryV1.getWebsite("blog");
        System.out.println(websiteV1.getPages());

        websiteV1 = WebsiteFactoryV1.getWebsite("shop");
        System.out.println(websiteV1.getPages());

        Website websiteV2 = WebsiteFactoryV2.getWebsite(WebsiteTypeEnum.BLOG);
        System.out.println(websiteV2.getPages());

        websiteV2 = WebsiteFactoryV2.getWebsite(WebsiteTypeEnum.SHOP);
        System.out.println(websiteV2.getPages());

        // we will get 4 instances of the 2 wesite types constructed by the two factory versions
    }
}
/*
- doesn't expose instantiation logic
- defer to subclasses
- common interface
- specified by architecture, implemented by user
- parameter driven
- solves complex creation
- complex to write

Examples:
Calendar
ResourceBundle
NumberFormat
 */