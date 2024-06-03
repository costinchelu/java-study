package module01.question02.after;

import module01.question02.common.renderer.color.RedColorRenderer;
import module01.question02.common.renderer.weight.BoldFontWeightRenderer;

public class Runner {
    public static void main(String... args) {
        TextRenderer textRenderer = new TextRenderer.TextRendererBuilder().build();
        textRenderer.render("Default Rendering");

        TextRenderer redRenderer = new TextRenderer.TextRendererBuilder()
                .withFontColorRenderer(new RedColorRenderer())
                .build();

        redRenderer.render("Red Color Rendering");

        TextRenderer boldRenderer = new TextRenderer.TextRendererBuilder()
                .withFontWeightRenderer(new BoldFontWeightRenderer())
                .build();

        boldRenderer.render("Bold Rendering");
    }
}
