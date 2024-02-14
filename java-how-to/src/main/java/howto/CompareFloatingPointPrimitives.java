package howto;

public class CompareFloatingPointPrimitives {

    public static void main(String[] args) {
        System.out.println(compareFloatingPointPrimitives(1.5436567F, 1.5436568F));
        System.out.println(compareFloatingPointPrimitives(1.5436567F, 1.5436567F));
    }

    private static boolean compareFloatingPointPrimitives(float a, float b) {
        return Math.abs(a - b) < 1e-7;
    }
}
