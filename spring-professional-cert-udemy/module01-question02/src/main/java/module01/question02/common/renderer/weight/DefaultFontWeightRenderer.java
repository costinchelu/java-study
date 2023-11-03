package module01.question02.common.renderer.weight;

public class DefaultFontWeightRenderer implements FontWeightRenderer {
    @Override
    public String render(String text) {
        return "<default-weight>" + text + "</default-weight>";
    }
}
