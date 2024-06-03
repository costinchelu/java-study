package module01.question02.before;

import module01.question02.common.renderer.color.DefaultColorRenderer;
import module01.question02.common.renderer.color.RedColorRenderer;
import module01.question02.common.renderer.style.DefaultFontStyleRenderer;
import module01.question02.common.renderer.weight.BoldFontWeightRenderer;
import module01.question02.common.renderer.weight.DefaultFontWeightRenderer;

public class Runner {
    public static void main(String... args) {

        TextRenderer textRenderer = new TextRenderer(
                new DefaultFontStyleRenderer(),
                new DefaultColorRenderer(),
                new DefaultFontWeightRenderer());

        textRenderer.render("Default Rendering");

        TextRenderer redRenderer = new TextRenderer(
                new DefaultFontStyleRenderer(),
                new RedColorRenderer(),
                new DefaultFontWeightRenderer());

        redRenderer.render("Red Color Rendering");

        TextRenderer boldRenderer = new TextRenderer(
                new DefaultFontStyleRenderer(),
                new DefaultColorRenderer(),
                new BoldFontWeightRenderer());

        boldRenderer.render("Bold Rendering");
    }
}
