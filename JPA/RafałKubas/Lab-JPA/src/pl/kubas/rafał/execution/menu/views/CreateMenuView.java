/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kubas.rafał.execution.menu.views;

import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_ADDRESSEE_ADDRESS;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_ADDRESSEE_NAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_CONTENT;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_POSTMAN;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_SENDER_ADDRESS;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER_SENDER_NAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.POSTMAN_NAME;
import static pl.kubas.rafał.execution.menu.MenuDictionary.POSTMAN_OFFICE;
import static pl.kubas.rafał.execution.menu.MenuDictionary.POSTMAN_SURNAME;
import pl.kubas.rafał.model.Postman;

/**
 * @author rkubas
 */
public class CreateMenuView {

    /**
     * View for create operations
     */
    public CreateMenuView(){
        
    }
    
    /**
     *asks for postman name
     */
    public void displayPostmanNameStatement(){
        System.out.println(POSTMAN_NAME);
    }

    /**
     *asks for postman surname
     */
    public void displayPostmanSurnameStatement(){
        System.out.println(POSTMAN_SURNAME);
    }

    /**
     *asks for postman office
     */
    public void displayPostmanOfficeStatement(){
        System.out.println(POSTMAN_OFFICE);
    }

    /**
     *informs about created postman
     * @param postman
     */
    public void displayPostmanCreatedStatement(Postman postman) {
        System.out.println("Created postman: " + postman.toString());
    }
    
    /**
     *asks for letter's content
     */
    public void displayLetterContentStatement(){
        System.out.println(LETTER_CONTENT);
    }
    
    /**
     *asks for addressee name
     */
    public void displayAddresseeNameStatement(){
         System.out.println(LETTER_ADDRESSEE_NAME);
    }
    
    /**
     *asks for addressee address
     */
    public void displayAddresseeAddressStatement(){
        System.out.println(LETTER_ADDRESSEE_ADDRESS);
    }
    
    /**
     *asks for sender name
     */
    public void displaySenderNameStatement(){
        System.out.println(LETTER_SENDER_NAME);
    }
    
    /**
     *asks for postman carrying letter
     */
    public void displayPostmanCarryingLetters(){
        System.out.println(LETTER_POSTMAN);

    }
    
    /**
     *asks for sender address
     */
    public void displaySenderAddressStatement(){
    System.out.println(LETTER_SENDER_ADDRESS);
    }
    
    /**
     * display possible postmans
     * @param postman
     */
    public void displayPossiblePostman(Postman postman){
        System.out.println(postman.getId() + " " + postman.toString());
    }
    
}
