/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.operations;

import pl.kubas.rafał.execution.MainMenu;
import static pl.kubas.rafał.execution.menu.MenuDictionary.BACK;
import static pl.kubas.rafał.execution.menu.MenuDictionary.CREATE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_ADDRESSEE_ADDRESS;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_ADDRESSEE_NAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_CONTENT;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_POSTMAN;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_SENDER_ADDRESS;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_SENDER_NAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.NEW_VALUES;
import static pl.kubas.rafał.execution.menu.MenuDictionary.NEW_VALUES_ID;
import static pl.kubas.rafał.execution.menu.MenuDictionary.POSTMAN_NAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.POSTMAN_OFFICE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.POSTMAN_SURNAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.TRY_AGAIN;
import static pl.kubas.rafał.execution.menu.MenuDictionary.UPDATE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.UPDATE_LETTER;
import static pl.kubas.rafał.execution.menu.MenuDictionary.UPDATE_POSTMAN;
import pl.kubas.rafał.execution.menu.views.MenuView;
import pl.kubas.rafał.execution.menu.views.UpdateMenuView;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * 2017-10-23 09:23:54
 *
 * @author rkubas //Class representing update operation
 */
public class UpdateOperation extends Operation {

    private Letter letter;
    private Postman postman;
    private UpdateMenuView updateMenuView;
/**
     * Detailed operation - update, gets main menu, main menu's view and it's own view
     * @param menu
     * @param menuView
     * @param updateMenuView
     */
    public UpdateOperation(MainMenu menu, MenuView menuView, UpdateMenuView updateMenuView) {
        super(menu, menuView);
        this.updateMenuView = updateMenuView;
        handleUserInteraction(UPDATE);
    }

    /*
    Perform update operation on postman entity
     */
    @Override
    public void performOperationPostman() {
       updateMenuView.displayUpdatePostmanStatement();

        postman = getMenu().getDB().findPostman(getUserInputNumber());
        if (postman == null) {
            updateMenuView.displayTryAgainStatement();
            performOperationPostman();
        } else {
            updatePostmanProperties();
        }

        getMenu().getDB().updateObject(postman);

        getMenu().handleUserInteraction();
    }

    /*
    Perform update operation on letter entity
     */
    @Override
    public void performOperationLetter() {
        updateMenuView.displayUpdateLetterStatement();
        letter = getMenu().getDB().findLetter(getUserInputNumber());
        if (letter == null) {
            updateMenuView.displayTryAgainStatement();
            performOperationLetter();
        } else {
            updateLetterProperties();
        }

        getMenu().getDB().updateObject(letter);
        getMenu().handleUserInteraction();
    }

    /*
    Display menu with possibilities of change for letter
     */
    private void updateLetterProperties() {
        updateMenuView.displayLetterProperties();
        handleNewLetterProperties();
    }

    /*
   Handle letter changing from user
     */
    private void handleNewLetterProperties() {
        switch (getUserInputNumber()) {
            case 1:
                updateMenuView.displayNewValuesStatement();
                letter.setAddresseeName(getMenu().getUserInputString());
                updateLetterProperties();
                break;
            case 2:
                updateMenuView.displayNewValuesStatement();
                letter.setAddresseeAddress(getMenu().getUserInputString());
                updateLetterProperties();
                break;
            case 3:
                updateMenuView.displayNewValuesStatement();
                letter.setSenderName(getMenu().getUserInputString());
                updateLetterProperties();
                break;
            case 4:
                updateMenuView.displayNewValuesStatement();
                letter.setSenderAddress(getMenu().getUserInputString());
                updateLetterProperties();
                break;
            case 5:
                updateMenuView.displayNewValuesStatement();
                letter.setLetterContent(getMenu().getUserInputString());
                updateLetterProperties();
                break;
            case 6:
                updateMenuView.displayNewIDStatement();
                Postman postman = getMenu().getDB().findPostman(getUserInputNumber());
                letter.setPostman(postman);
                updateLetterProperties();
                break;
            default:
                break;

        }
    }

    /*
    Display menu with possibilities of change for postman
     */
    private void updatePostmanProperties() {
        updateMenuView.displayPostmanProperties();
        handleNewPostmanProperties();
    }

    /*
   Handle postman changing from user
     */
    private void handleNewPostmanProperties() {
        switch (getUserInputNumber()) {
            case 1:
                updateMenuView.displayNewValuesStatement();
                postman.setName(getMenu().getUserInputString());
                updatePostmanProperties();
                break;
            case 2:
                updateMenuView.displayNewValuesStatement();
                postman.setSurname(getMenu().getUserInputString());
                updatePostmanProperties();
                break;
            case 3:
                updateMenuView.displayNewValuesStatement();
                postman.setPostOffice(getPostOfficeFromUser());
                updatePostmanProperties();
                break;
            default:
                break;
        }
    }

}
