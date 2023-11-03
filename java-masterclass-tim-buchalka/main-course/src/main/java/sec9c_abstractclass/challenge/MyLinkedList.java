package sec9c_abstractclass.challenge;

public class MyLinkedList implements NodeList {

    private ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null) {
            // the list was empty,  so this item becomes the head of the list
            this.root = newItem;
            return true;
        }

        ListItem currentItem = this.root;
        //currentItem will be like a pointer to traverse the list. Initially, it will point to the root
        while(currentItem != null) {
            int comparison = currentItem.compareTo(newItem);

            if(comparison < 0) {
                //means that newItem is greater than current. We will move right if possible.
                if(currentItem.next() != null) {
                    currentItem = currentItem.next();
                }
                else {
                    //means that we are at the end of the list
                    currentItem.setNext(newItem);
                    newItem.setPrevious(currentItem);
                    return true;
                }
            }
            else if(comparison > 0) {
                // insert before because newItem is less than current
                if(currentItem.previous() == null) {
                    //we are at the root, there is no previous
                    newItem.setNext(this.root);
                    this.root.setPrevious(newItem);
                    this.root = newItem;                //newItem is the new root
                }
                else {
                    currentItem.previous().setNext(newItem);
                    newItem.setPrevious(currentItem.previous());
                    newItem.setNext(currentItem);
                    currentItem.setPrevious(newItem);
                }
                return true;
            }
            else {
                // = 0 so they are equal. We chose not to add newItem to the list
                System.out.println(newItem.getValue() + " is already present. Will not be added.");
                return false;
            }
        }

        return false;
    }

    /*
    shorter version:

    public boolean addItem(ListItem newItem) {
        if(this.root == null) {
            this.root = newItem;
            return true;
        }
        ListItem currentItem = this.root;
        while(currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if(comparison < 0) {
                if(currentItem.next() != null) {
                    currentItem = currentItem.next();
                }
                else {
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            }
            else if(comparison > 0) {
                if(currentItem.previous() == null) {
                    newItem.setNext(this.root).setPrevious(newItem);
                    this.root = newItem;
                }
                else {
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
                    newItem.setNext(currentItem).setPrevious(newItem);
                }
                return true;
            }
            else {
                System.out.println(newItem.getValue() + " is already present. Will not be added.");
                return false;
            }
        }

        return false;
    }

     */

    @Override
    public boolean removeItem(ListItem item) {
        if(item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem currentItem = this.root;           //like a pointer to the root that can traverse the list
        while(currentItem != null) {
            if(currentItem.compareTo(item) < 0) {
                currentItem = currentItem.next();
            }
            else if(currentItem.compareTo(item) == 0) {
                //means we found the position to delete
                if(currentItem == this.root) {
                    this.root = this.root.next();
                }
                else {
                    currentItem.previous().setNext(currentItem.next());
                    if(currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            }
            else {
                System.out.println("Item " + item.getValue() + " cannot be found in the list");
                return false;
                //equivalent to compareTo > 0
                //we are an item greater than the one to be deleted and since the list is ordered
                //the item is not in the list
            }
        }
        System.out.println("Item " + item.getValue() + " cannot be found in the list");
        return false;
        //if item to delete has a value > greatest value in the list and it's not present, eventually we
        //will reached the end of the list
    }

    @Override
    public void traverse(ListItem root) {
        if(root == null) {
            System.out.printf("List is empty!");
        }
        else {
            while(root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
        /*
        RECURSIVE VERSION
        if(root != null) {
            System.out.println(root.getValue());
            traverse(root.next());
        }
        recursive version cannot be used for large linked lists because it can overflow the stack
        in case of binary search tree, even a huge tree will have at most a few dozens of levels
         */
    }
}
