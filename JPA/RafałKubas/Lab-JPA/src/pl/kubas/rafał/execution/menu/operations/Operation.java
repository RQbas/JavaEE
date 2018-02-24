/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.operations;

import pl.kubas.rafał.execution.MainMenu;
import static pl.kubas.rafał.execution.menu.MenuDictionary.*;
import pl.kubas.rafał.execution.menu.views.MenuView;
import pl.kubas.rafał.model.PostOffice;

/**
 * 2017-10-23 09:47:39
 *
 * @author rkubas
 */
public abstract class Operation {

    private MainMenu menu;
    private MenuView menuView;

    /*
    Getting reference to main menu
     */
    public Operation(MainMenu menu, MenuView menuView) {
        this.menu = menu;
        this.menuView = menuView;
    }

    /*
    Init UI
     */
    public void handleUserInteraction(String operationName) {
        displayMenu(operationName);
        interpretUserInput();

    }

    /*
    Display menu for operations - 2 types: for letter and for postman
     */
    private void displayMenu(String operationName) {
       menuView.displayOperationMenu(operationName);
    }

    /*
    Get user response to recognise entity type which will be procceed
     */
    private void interpretUserInput() {
        switch (menu.getUserInputNumber()) {
            case 1:
                performOperationPostman();
                break;
            case 2:
                performOperationLetter();
                break;
            case 3:
                menu.handleUserInteraction();
                break;
            default:
                menuView.displayWrongInputNumber();
                interpretUserInput();
                break;
        }
    }

    /*
    Abstract methods which will be defining specific operation
     */
    public abstract void performOperationPostman();

    public abstract void performOperationLetter();

    public MainMenu getMenu() {
        return menu;
    }

    public int getUserInputNumber() {
        return menu.getUserInputNumber();
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    /*
    Display and interact with user to get post office
     */

    public PostOffice getPostOfficeFromUser() {
        int counter = 1;

        for (PostOffice office : PostOffice.values()) {
            menuView.displayPostOffice(counter, office);
            counter++;
        }
        counter = 1;
        int input = getUserInputNumber();
        for (PostOffice office : PostOffice.values()) {
            if (counter == input) {
                return office;
            }
            counter++;
        }
        return null;

    }

}
