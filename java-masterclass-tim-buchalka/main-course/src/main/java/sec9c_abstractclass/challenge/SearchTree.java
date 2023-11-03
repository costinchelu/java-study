package sec9c_abstractclass.challenge;

public class SearchTree implements NodeList {

    private ListItem root = null;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null) {
            //tree was empty, so we introduce our item as the head of the tree(root)
            this.root = newItem;
            return true;
        }

        //if tree is not empty, we start comparing from the head of the tree
        ListItem currentItem = this.root;
        while(currentItem != null) {
            if(currentItem.compareTo(newItem) < 0) {
                //newItem is greater, so we move to right
                if(currentItem.next() != null) {
                    currentItem = currentItem.next();
                }
                else {
                    //in this case there is no node to the right(next), so we add there
                    currentItem.setNext(newItem);
                    return true;
                    //actually we are always looking to the right branch until we find an open spot
                }
            }
            else if(currentItem.compareTo(newItem) > 0) {
                //new item is smaller, so we move to the left
                if(currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                }
                else {
                    //there is no node to the left(previous), so we can add newItem there
                    currentItem.setPrevious(newItem);
                    return true;
                }
            }
            else {
                //equal(same object) we are not adding
                System.out.println(newItem.getValue() + " is already present");
                return false;
            }
        }
        //we can't get here(so no case possible) but java complains if there is no return
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        //just determining what to remove:
        if(item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;

        while(currentItem != null) {
            if(currentItem.compareTo(item) < 0) {
                parentItem = currentItem;
                currentItem = currentItem.next();
            }
            else if(currentItem.compareTo(item) > 0) {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            }
            else {
                //equal means that we found the item so we can remove it
                performRemoval(currentItem, parentItem);
                return true;
            }
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        //the actual removal operation
        if(item.next() == null) {
            //in this case we have no right tree, so parent point to the left tree (which may be null)
            if(parent.next() == item) {
                //item is right child of its parent
                parent.setNext(item.previous());
            }
            else if (parent.previous() == item) {
                //item is left child of its parent
                parent.setPrevious(item.previous());
            }
            else {
                //parent must be item, which means we are looking at the root of the tree
                this.root = item.previous();
            }
        }
        else if(item.previous() == null) {
            //no left tree, so make parent point to right tree (which may be null)
            if(parent.next() == item) {
                //item is right child of its parent
                parent.setNext(item.next());
            }
            else if(parent.previous() == item) {
                //item is left child of its parent
                parent.setPrevious(item.next());
            }
            else {
                //same ase before, parent is item
                this.root = item.next();
            }
        }
        else {
            //neither left nor right are null, deletion is now a lot trickier!
            //from the right subtree, find the smallest value(i.e., the leftmost)
            ListItem current = item.next();
            ListItem leftMostParent = item;

            while(current.previous() != null) {
                leftMostParent = current;
                current = current.previous();
            }
            //now put the smallest value into our node to be deleted
            item.setValue(current.getValue());
            //and delete the smallest
            if(leftMostParent == item) {
                // there was no leftmost node, so 'current' points to the smallest
                // node (the one that must now be deleted).
                item.setNext(current.next());
            } else {
                // set the smallest node's parent to point to
                // the smallest node's right child (which may be null).
                leftMostParent.setPrevious(current.next());
            }
        }
    }

    @Override
    public void traverse(ListItem root) {
        //recursive
        if(root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }
    }
}
