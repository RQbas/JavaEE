/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.operations;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import pl.kubas.rafał.execution.menu.views.CreateMenuView;
import pl.kubas.rafał.execution.MainMenu;
import static pl.kubas.rafał.execution.menu.MenuDictionary.*;
import pl.kubas.rafał.execution.menu.views.MenuView;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * 2017-10-23 09:23:38
 *
 * @author rkubas //Class representing create operation for entities
 */
public class CreateOperation extends Operation {
    private CreateMenuView createMenuView;
    
    /**
     * Detailed operation - create, gets main menu, main menu's view and it's own view
     * @param menu
     * @param menuView
     * @param createMenuView
     */
    public CreateOperation(MainMenu menu, MenuView menuView, CreateMenuView createMenuView) {
        super(menu, menuView);
        this.createMenuView = createMenuView;
        handleUserInteraction(CREATE);
    }

    /*
   Create postman
     */
    @Override
    public void performOperationPostman() {

        Postman postman = new Postman();
        createMenuView.displayPostmanNameStatement();
        postman.setName(getMenu().getUserInputString());
        createMenuView.displayPostmanSurnameStatement();
        postman.setSurname(getMenu().getUserInputString());
        createMenuView.displayPostmanOfficeStatement();
        postman.setPostOffice(getPostOfficeFromUser());
        createMenuView.displayPostmanCreatedStatement(postman);
        getMenu().getDB().storeObject(postman);
        getMenu().handleUserInteraction();
    }

    /*
  Create new letter
     */
    @Override
    public void performOperationLetter() {

        Letter letter = new Letter();
        letter.setSendingDate(new Date());
        createMenuView.displayLetterContentStatement();
        letter.setLetterContent(getMenu().getUserInputString());
        createMenuView.displayAddresseeNameStatement();
        letter.setAddresseeName(getMenu().getUserInputString());
        createMenuView.displayAddresseeAddressStatement();
        letter.setAddresseeAddress(getMenu().getUserInputString());
        createMenuView.displaySenderNameStatement();
        letter.setSenderName(getMenu().getUserInputString());
        createMenuView.displayAddresseeAddressStatement();
        letter.setSenderAddress(getMenu().getUserInputString());
        handlePostmanForLetter(letter);

        getMenu().getDB().storeObject(letter);

        getMenu().handleUserInteraction();
    }

    /*
       List all possible postmans to pick for letter
     */
    private void handlePostmanForLetter(Letter letter) {
        Set<Integer> set = new HashSet();

        letter.setPostman(null);
        if (!getMenu().getDB().findAllPostmans().isEmpty()) {
            createMenuView.displayPostmanCarryingLetters();
            for (Postman postman : getMenu().getDB().findAllPostmans()) {
                createMenuView.displayPossiblePostman(postman);
                set.add(postman.getId());
            }

            int input = getUserInputNumber();
            if (set.contains(input)) {
                Postman postman = getMenu().getDB().findPostman(input);
                letter.setPostman(postman);
                postman.getLetters().add(letter);
            } else {
                handlePostmanForLetter(letter);
            }

        }

    }

}
