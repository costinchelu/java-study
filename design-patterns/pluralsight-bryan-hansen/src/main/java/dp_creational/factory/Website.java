package dp_creational.factory;

import dp_creational.factory.differentpages.Page;

import java.util.ArrayList;
import java.util.List;

public abstract class Website {

    // all sites are a list of page types
    protected List<Page> pages = new ArrayList<>();

    public Website() {
        this.createWebsite();
    }

    public List<Page> getPages() {
        return pages;
    }


    // will be implemented in the children classes by loading different types of pages into the list
    protected abstract void createWebsite();
}

/*

ProductBase

 */