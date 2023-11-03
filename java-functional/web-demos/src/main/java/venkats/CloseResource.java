package venkats;

import java.util.function.Consumer;

public class CloseResource {

    public static void main(String[] args) {
//        try(Resource resource = new Resource()) {
//            resource.op1();
//            resource.op2();
//        }

        Resource.use(resource -> resource.op1().op2());
    }
}

class Resource implements AutoCloseable {
    public Resource() {
        System.out.println("Created...");
    }

    public Resource op1() {
        System.out.println("op1");
        return this;
    }

    public Resource op2() {
        System.out.println("op2");
        return this;
    }

    @Override
    public void close() {
        System.out.println("Cleanup...");
    }

    public static void use(Consumer<Resource> block) {
        try (Resource resource = new Resource()) {
            block.accept(resource);
        }
    }
}
