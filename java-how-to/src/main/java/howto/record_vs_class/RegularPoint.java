package howto.record_vs_class;

public final class RegularPoint implements Point {

    private final int x;
    private final int y;

    public RegularPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    // Implementations of toString(), hashCode() and equals()
    // omitted for brevity

}
