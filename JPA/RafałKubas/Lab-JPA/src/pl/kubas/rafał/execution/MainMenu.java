/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution;

import pl.kubas.rafał.execution.menu.views.CreateMenuView;
import java.util.Scanner;
import pl.kubas.rafał.db.DatabaseExecutor;
import static pl.kubas.rafał.execution.menu.MenuDictionary.*;
import pl.kubas.rafał.execution.menu.operations.CreateOperation;
import pl.kubas.rafał.execution.menu.operations.DeleteOperation;
import pl.kubas.rafał.execution.menu.operations.ReadOperation;
import pl.kubas.rafał.execution.menu.operations.UpdateOperation;
import pl.kubas.rafał.execution.menu.views.DeleteMenuView;
import pl.kubas.rafał.execution.menu.views.MenuView;
import pl.kubas.rafał.execution.menu.views.ReadMenuView;
import pl.kubas.rafał.execution.menu.views.UpdateMenuView;

/**
 * 2017-10-23 09:01:39
 *
 * @author rkubas
 */
public class MainMenu {
    private MenuView menuView;
    private DatabaseExecutor dbExecutor;

    public MainMenu() {
        dbExecutor = new DatabaseExecutor();
        menuView = new MenuView();
        handleUserInteraction();
    }

    /**
     * creates main menu view for user and get his input
     */
    public void handleUserInteraction() {
        menuView.displayMenu();
        interpretUserInput();

    }

    /*
    Get input number from user
     */
    public int getUserInputNumber() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        int userInput = 0;
        try {
            userInput = Integer.parseInt(input);
        } catch (Exception e) {
            menuView.displayWrongInputType();
            return getUserInputNumber();

        }
        return userInput;
    }

    /*
    Get input string from user
     */
    public String getUserInputString() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    /*
    interpret user input
     */
    private void interpretUserInput() {
        switch (getUserInputNumber()) {
            case 1:
                new CreateOperation(this, menuView, new CreateMenuView());
                break;
            case 2:
                new ReadOperation(this, menuView, new ReadMenuView());
                break;
            case 3:
                new UpdateOperation(this, menuView, new UpdateMenuView());
                break;
            case 4:
                new DeleteOperation(this, menuView, new DeleteMenuView());
                break;
            case 5:
                System.exit(0);
                break;
            default:
                menuView.displayWrongInputNumber();
                interpretUserInput();
                break;
        }
    }

    public DatabaseExecutor getDB() {
        return dbExecutor;
    }

}
