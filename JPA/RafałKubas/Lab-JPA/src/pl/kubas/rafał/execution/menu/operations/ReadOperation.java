/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.operations;

import java.util.List;
import pl.kubas.rafał.execution.MainMenu;
import static pl.kubas.rafał.execution.menu.MenuDictionary.BACK;
import static pl.kubas.rafał.execution.menu.MenuDictionary.CREATE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_ALL;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_BY_ADDRESSEE_ADDRESS;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_BY_ID;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_BY_NAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_BY_OFFICE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_BY_POSTMAN_ID;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_BY_SENDER_ADDRESS;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DISPLAY_BY_SURNAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.PROVIDE_ID;
import static pl.kubas.rafał.execution.menu.MenuDictionary.PROVIDE_VALUE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.READ;
import pl.kubas.rafał.execution.menu.views.MenuView;
import pl.kubas.rafał.execution.menu.views.ReadMenuView;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * 2017-10-23 09:24:53
 *
 * @author rkubas //Class representing read operation from database
 */
public class ReadOperation extends Operation {
    private ReadMenuView readMenuView;
/**
     * Detailed operation - read, gets main menu, main menu's view and it's own view
     * @param menu
     * @param menuView
     * @param readMenuView
     */
    public ReadOperation(MainMenu menu, MenuView menuView, ReadMenuView readMenuView) {
        super(menu, menuView);
        this.readMenuView = readMenuView;
        handleUserInteraction(READ);

    }

    /*
    Display menu with possibilities of display for postman
     */
    @Override
    public void performOperationPostman() {
       readMenuView.displayPostmanOperations();

        handleReadPostman();
        getMenu().handleUserInteraction();
    }

    /*
    Display menu with possibilities of display for letter
     */
    @Override
    public void performOperationLetter() {
        readMenuView.displayLetterOperations();
        handleReadLetter();
        getMenu().handleUserInteraction();
    }

    /*
   Handle letter reading from user
     */
    private void handleReadLetter() {
        switch (getUserInputNumber()) {
            case 1:
                for (Letter letter : getMenu().getDB().findAllLetters()) {
                    readMenuView.displayLetter(letter);
                }
                break;
            case 2:
             readMenuView.displayIDStatement();
                Letter letter = getMenu().getDB().findLetter(getUserInputNumber());
                if (letter != null) {
                    readMenuView.displayLetter(letter);
                }
                break;
            case 3:
                readMenuView.displayIDStatement();
                Postman postman = getMenu().getDB().findPostman(getUserInputNumber());
                List<Letter> listPostmanLetters = getMenu().getDB().findLetterByPostman(postman);
                for (Letter letterPostmans : listPostmanLetters) {
                    readMenuView.displayLetter(letterPostmans);
                }
                break;
            case 4:
                readMenuView.displayValueStatement();
                List<Letter> listAddressee = getMenu().getDB().findLetterByAddressee(getMenu().getUserInputString());
                for (Letter letterAddressee : listAddressee) {
                    readMenuView.displayLetter(letterAddressee);
                }
                break;
            case 5:
                readMenuView.displayValueStatement();
                List<Letter> listSender = getMenu().getDB().findLetterBySender(getMenu().getUserInputString());
                for (Letter letterSender : listSender) {
                    readMenuView.displayLetter(letterSender);
                }
                break;

            default:
                break;
        }

    }

    /*
   Handle postman reading from user
     */
    private void handleReadPostman() {
        switch (getUserInputNumber()) {
            case 1:
                for (Postman postman : getMenu().getDB().findAllPostmans()) {
                    readMenuView.displayPostman(postman);
                }
                break;
            case 2:
                readMenuView.displayIDStatement();
                Postman postman = getMenu().getDB().findPostman(getUserInputNumber());
                if (postman != null) {
                   readMenuView.displayPostman(postman);
                }
                break;
            case 3:
                readMenuView.displayValueStatement();
                List<Postman> listName = getMenu().getDB().findPostmanByName(getMenu().getUserInputString());
                for (Postman postmanName : listName) {
                   readMenuView.displayPostman(postmanName);
                }
                break;

            case 4:
                readMenuView.displayValueStatement();
                List<Postman> listSurname = getMenu().getDB().findPostmanBySurname(getMenu().getUserInputString());
                for (Postman postmanSurname : listSurname) {
                    readMenuView.displayPostman(postmanSurname);
                }
                break;

            case 5:
                readMenuView.displayValueStatement();
                List<Postman> listOffice = getMenu().getDB().findPostmanByOffice(getPostOfficeFromUser());
                for (Postman postmanOffice : listOffice) {
                    readMenuView.displayPostman(postmanOffice);
                }
                break;
            default:
                break;
        }
    }
}
