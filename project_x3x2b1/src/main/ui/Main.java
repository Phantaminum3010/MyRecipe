package ui;


import model.Ingredient;
import model.Recipe;

import java.io.FileNotFoundException;

public class Main {
    private static MenuGUI menuGUI;

    public static void main(String[] args) {
//        MenuApp menuApp = new MenuApp();
//        menuApp.runMenu();
//    }
        try {
            menuGUI = new MenuGUI();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found!!!");
        }

    }
}
