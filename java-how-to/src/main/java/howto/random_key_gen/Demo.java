package howto.random_key_gen;

public class Demo {

    public static void main(String[] args) {
        RandomKeyGenerator rkg = new RandomKeyGenerator();

        System.out.println(rkg.generateKey());
        System.out.println(rkg.generateKey());
        System.out.println(rkg.generateKey());

    }
}
