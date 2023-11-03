package dp_creational.factory;


import dp_creational.factory.differentpages.CartPage;
import dp_creational.factory.differentpages.ItemPage;
import dp_creational.factory.differentpages.SearchPage;

public class Shop extends Website {

    // the createWebsite method is implemeted from parent abstract class and
    // it will be called exactly when we are creating a Website type object
    // that means whenever we are creating a Shop() we will call createWebsite adding pages to the pages list
    @Override
    protected void createWebsite() {
        pages.add(new CartPage());
        pages.add(new ItemPage());
        pages.add(new SearchPage());
    }
}

/*

ConcreteProduct

 */