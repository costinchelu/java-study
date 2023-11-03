package sec8b_arraylist.challengepersonalcode;

import java.util.ArrayList;

public class MobilePhone {
    private int idPhone;
    private ArrayList<Contacts> contactsList;

    public MobilePhone(int idPhone) {
        this.idPhone = idPhone;
        this.contactsList = new ArrayList<Contacts>();
    }

    public void addToList(String name, String phoneNo) {
        if(!existsPosition(name)) {
            contactsList.add(new Contacts(name, phoneNo));
            System.out.println("New contact added(Name: " + name + ". phone number: " + phoneNo);
        }
        else {
            System.out.println("Contact name is already existing");
        }
    }

    private boolean existsPosition(String name) {
        return getPosition(name) >= 0;
    }

    public void printContactList() {
        for(int i = 0; i < contactsList.size(); i++) {
            System.out.println((i + 1) + ". Name: " +
                    contactsList.get(i).getName() + ", Telephone: " +
                    contactsList.get(i).getPhoneNumber());
        }
    }

    private void modifyPosition(int position, String name, String phoneNo) {
        Contacts temp = contactsList.get(position);
        if (phoneNo.equals("0")){
            phoneNo = temp.getPhoneNumber();
        }
        if(name.equals("0")) {
            name = temp.getName();
        }
        Contacts c = new Contacts(name, phoneNo);
        contactsList.set(position, c);
    }

    private int getPosition(String name) {
        for (int i = 0; i < contactsList.size(); i++){
            if(contactsList.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public void modifyEntry(String oldName, String newName, String newNo) {
        if(!existsPosition(newName)) {
            int position = getPosition(oldName);
            if (position >= 0) {
                modifyPosition(position, newName, newNo);
                System.out.println("Entry with name " + oldName + " has been modified.");
            }
            else {
                System.out.println("Entry was not found.");
            }
        }
        else {
            System.out.println("New name already exists in phone book. Entry will not be updated.");
        }
    }

    public void removeEntry(String name) {
        int position = getPosition(name);
        if(position >= 0) {
            contactsList.remove(position);
            System.out.println("Position with index " + (position + 1) + " was removed.");
        }
        else {
            System.out.println("Name not coresponding to a valid entry.");
        }
    }

    public void findContact(String name) {
        int position = getPosition(name);
        if(position >= 0) {
            System.out.println("Name " + name + " coresponds to index " + (position + 1) +
                    " and has an associated phone number: " + contactsList.get(position).getPhoneNumber());
        }
        else {
            System.out.println("Name not coresponding to a valid entry.");
        }
    }
}
