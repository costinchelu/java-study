package dp_structural.composite.menu;


import dp_structural.composite.menu.composite.Menu;
import dp_structural.composite.menu.leaf.MenuItem;

public class Main {

    public static void main(String[] args) {

        Menu mainMenu = new Menu("JavaIOExample.JavaNetURLExample.WrapperExample.TwitterExample.Client.Main", "/main");

        MenuItem safetyMenuItem = new MenuItem("Safety", "/safety");
        mainMenu.addChildren(safetyMenuItem);

        Menu claimsSubMenu = new Menu("Claims", "/claims");
        mainMenu.addChildren(claimsSubMenu);

        MenuItem personalClaimsMenu = new MenuItem("Personal claims", "/personalClaims");
        claimsSubMenu.addChildren(personalClaimsMenu);

        MenuItem otherClaimsMenu = new MenuItem("Other claims", "/otherClaims");
        claimsSubMenu.addChildren(otherClaimsMenu);

        System.out.println(mainMenu.toString());

        claimsSubMenu.removeChildren(otherClaimsMenu);
        System.out.println(mainMenu.toString());
    }
}

/*
Tree structure: leaf and composite have same interface
Provides unity between objects

 */