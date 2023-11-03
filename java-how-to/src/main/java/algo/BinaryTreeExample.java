package algo;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BinaryTreeExample {

    public static void main(String[] args) {
        Tree t1 = TreeProcessor.update("key5", 234, null);
        Tree t2 = TreeProcessor.update("key7", 334, t1);
        Tree t3 = TreeProcessor.update("key4", 325, t2);
        Tree t4 = TreeProcessor.update("key6", 387, t3);

        int result = TreeProcessor.lookup("key4", -1, t4);

        System.out.println(result);
    }
}

@AllArgsConstructor
@Getter
class Tree {

    private String key;

    private int value;

    private Tree left;

    private Tree right;
}

class TreeProcessor {

    public static int lookup(String k, int defaultValue, Tree t) {
        if (t == null) return defaultValue;

        if (k.equals(t.getKey())) return t.getValue();

        return lookup(k, defaultValue, k.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
    }

    public static Tree update(String k, int newValue, Tree t) {
        return (t == null)
                ? new Tree(k, newValue, null, null)
                : k.equals(t.getKey())
                    ? new Tree(k, newValue, t.getLeft(), t.getRight())
                    : k.compareTo(t.getKey()) < 0
                        ? new Tree(t.getKey(), t.getValue(), update(k, newValue, t.getLeft()), t.getRight())
                        : new Tree(t.getKey(), t.getValue(), t.getLeft(), update(k, newValue, t.getRight()));
    }
}
