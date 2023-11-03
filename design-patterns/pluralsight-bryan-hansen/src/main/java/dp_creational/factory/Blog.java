package dp_creational.factory;


import dp_creational.factory.differentpages.AboutPage;
import dp_creational.factory.differentpages.CommentPage;
import dp_creational.factory.differentpages.ContactsPage;
import dp_creational.factory.differentpages.PostPage;

public class Blog extends Website {

    // the createWebsite method is implemeted from parent abstract class and
    // it will be called exactly when we are creating a Website type object
    // that means whenever we are creating a Blog() we will call createWebsite adding pages to the pages list
    @Override
    protected void createWebsite() {
        pages.add(new PostPage());
        pages.add(new AboutPage());
        pages.add(new CommentPage());
        pages.add(new ContactsPage());
    }
}
