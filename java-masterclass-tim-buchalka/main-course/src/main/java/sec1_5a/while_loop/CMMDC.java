package sec1_5a.while_loop;

public class CMMDC {

    // CMMDC varianta iterativa
    public static long cmmdcIterativ(long a, long b)
    {
        long r;
        while( b != 0)
        {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    // CMMDC varianta recursiva
    public static long cmmdcRecursiv(long a, long b)
    {
        if (b != 0)
            return cmmdcRecursiv(b, a % b);
        else
            return a;
    }
}
