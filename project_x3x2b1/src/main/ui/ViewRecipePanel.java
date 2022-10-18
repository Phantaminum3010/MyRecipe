package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//represents a panels with a list of buttons for which we can perform several actions regarding viewing all recipes
public class ViewRecipePanel extends JPanel {
    private JLabel message;
    private JButton backToMenu;

    public ViewRecipePanel() {
        message = new JLabel();
        this.add(message);
        addButtons();
        this.addLabel("                           ", this);
    }

    //MODIFIES: this
    //EFFECTS: adds the buttons backToMenu to the container
    public void addButtons() {
        backToMenu = MainMenuPanel.addAButton("Back", "Back to Menu", this);
    }

    //EFFECTS: returns a list of buttons in ViewRecipePanel
    public List<JButton> getButtons() {
        List<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(backToMenu);
        return buttonList;
    }

    //EFFECTS: adds a label to the container
    public void addLabel(String text, Container container) {
        JLabel label = new JLabel(text);
        container.add(label);
    }

    public JLabel getMessage() {
        return this.message;
    }


}
