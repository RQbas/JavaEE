/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.operations;

import java.util.ArrayList;
import java.util.List;
import pl.kubas.rafał.execution.MainMenu;
import static pl.kubas.rafał.execution.menu.MenuDictionary.CREATE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DELETE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DELETE_LETTER;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DELETE_POSTMAN;
import static pl.kubas.rafał.execution.menu.MenuDictionary.SUCCESSFUL;
import static pl.kubas.rafał.execution.menu.MenuDictionary.TRY_AGAIN;
import pl.kubas.rafał.execution.menu.views.DeleteMenuView;
import pl.kubas.rafał.execution.menu.views.MenuView;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * 2017-10-23 09:24:44
 *
 * @author rkubas //class representing delete operation
 */
public class DeleteOperation extends Operation {
    private DeleteMenuView deleteMenuView;
    
    
    /**
     * Detailed operation - delete, gets main menu, main menu's view and it's own view
     * @param menu
     * @param menuView
     * @param deleteMenuView
     */
    public DeleteOperation(MainMenu menu,  MenuView menuView, DeleteMenuView deleteMenuView) {
        super(menu, menuView);
        this.deleteMenuView = deleteMenuView;
        handleUserInteraction(DELETE);

    }

    /*
   Perform postman deletion
     */
    @Override
    public void performOperationPostman() {
        deleteMenuView.displayDeletePostmanStatement();
        Postman postman = getMenu().getDB().findPostman(getUserInputNumber());
        if (postman == null) {
            deleteMenuView.displayTryAgainStatement();
            performOperationPostman();
        } else {
            getMenu().getDB().removeObject(postman);
        }

        deleteMenuView.displaySuccessfulStatement();
        getMenu().handleUserInteraction();
    }

    /*
   Perform letter deletion
     */
    @Override
    public void performOperationLetter() {
        deleteMenuView.displayDeleteLetterStatement();

        Letter letter = getMenu().getDB().findLetter(getUserInputNumber());
        if (letter == null) {
            deleteMenuView.displayTryAgainStatement();
            performOperationLetter();
        } else {
            getMenu().getDB().removeObject(letter);
        }

        deleteMenuView.displaySuccessfulStatement();
        getMenu().handleUserInteraction();
    }
    
    
}
