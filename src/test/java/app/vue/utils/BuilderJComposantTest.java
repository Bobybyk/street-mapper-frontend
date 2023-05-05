package app.vue.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import vue.composant.FlatJButton;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;

class BuilderJComposantTest {

    private static final int TIMEOUT_SECONDS = 2000;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCreateJButtonWithName() {
        String buttonName = "Click me!";
        FlatJButton button = BuilderJComposant.createJButton(buttonName);
        assertNotNull(button);
        assertEquals(buttonName, button.getText());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCreateJButtonWithNameAndPath() {
        String buttonName = "Save";
        String path = "/path/to/image.png";
        FlatJButton button = BuilderJComposant.createJButton(buttonName, path);
        assertNotNull(button);
        assertEquals(buttonName, button.getText());
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCreateFlatJTextFieldWithPlaceholderAndDimension() {
        String placeholder = "Enter your email";
        Dimension dimension = new Dimension(300, 20);
        FlatJTextField textField = BuilderJComposant.createFlatJTextField(placeholder, dimension);
        assertNotNull(textField);
        assertEquals(dimension, textField.getPreferredSize());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCreatePanelBoxLayoutVertical() {
        JPanel panel = BuilderJComposant.createPanelBoxLayoutVertical();
        assertNotNull(panel);
        assertSame(BoxLayout.class, panel.getLayout().getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCreatePanelBoxLayoutVerticalWithName() {
        String name = "Vertical Panel";
        JPanel panel = BuilderJComposant.createPanelBoxLayoutVertical(name);
        assertNotNull(panel);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCreatePanelBoxLayoutHorizontal() {
        JPanel panel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        assertNotNull(panel);
        assertSame(BoxLayout.class, panel.getLayout().getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCreatePanelBoxLayoutHorizontalWithName() {
        String name = "Horizontal Panel";
        JPanel panel = BuilderJComposant.createPanelBoxLayoutHorizontal(name);
        assertNotNull(panel);
    }
}
