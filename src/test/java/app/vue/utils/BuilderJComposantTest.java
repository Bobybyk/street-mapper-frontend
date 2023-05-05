package app.vue.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import vue.composant.FlatJButton;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BuilderJComposantTest {


    private static final int TIMEOUT_SECONDS = 2;


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testCreateJButtonWithName() {
        String buttonName = "Click me!";
        FlatJButton button = BuilderJComposant.createJButton(buttonName);
        assertNotNull(button);
        assertEquals(buttonName, button.getText());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testCreateJButtonWithNameAndPath() {
        String buttonName = "Save";
        String path = "/path/to/image.png";
        FlatJButton button = BuilderJComposant.createJButton(buttonName, path);
        assertNotNull(button);
        assertEquals(buttonName, button.getText());
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testCreateFlatJTextFieldWithPlaceholderAndDimension() {
        String placeholder = "Enter your email";
        Dimension dimension = new Dimension(300, 20);
        FlatJTextField textField = BuilderJComposant.createFlatJTextField(placeholder, dimension);
        assertNotNull(textField);
        assertEquals(dimension, textField.getPreferredSize());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testCreatePanelBoxLayoutVertical() {
        JPanel panel = BuilderJComposant.createPanelBoxLayoutVertical();
        assertNotNull(panel);
        assertSame(BoxLayout.class, panel.getLayout().getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testCreatePanelBoxLayoutVerticalWithName() {
        String name = "Vertical Panel";
        JPanel panel = BuilderJComposant.createPanelBoxLayoutVertical(name);
        assertNotNull(panel);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testCreatePanelBoxLayoutHorizontal() {
        JPanel panel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        assertNotNull(panel);
        assertSame(BoxLayout.class, panel.getLayout().getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testCreatePanelBoxLayoutHorizontalWithName() {
        String name = "Horizontal Panel";
        JPanel panel = BuilderJComposant.createPanelBoxLayoutHorizontal(name);
        assertNotNull(panel);
    }


}
