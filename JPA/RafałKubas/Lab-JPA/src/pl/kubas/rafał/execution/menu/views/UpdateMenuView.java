/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.views;

import static pl.kubas.rafał.execution.menu.MenuDictionary.BACK;
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
import static pl.kubas.rafał.execution.menu.MenuDictionary.UPDATE_LETTER;
import static pl.kubas.rafał.execution.menu.MenuDictionary.UPDATE_POSTMAN;

/**
 *
 * @author rkubas
 */
public class UpdateMenuView {

    /**
     *View for update operations
     */
    public UpdateMenuView(){
        
    }
    
    /**
     * asks for postman's id to update
     */
    public void displayUpdatePostmanStatement(){
         System.out.println(UPDATE_POSTMAN);
    }
    
    /**
     * asks for letter's id to update
     */
     public void displayUpdateLetterStatement(){
         System.out.println(UPDATE_LETTER);
    }
     
    /**
     *asks for new value
     */
    public void displayNewValuesStatement(){
        System.out.println(NEW_VALUES);
    }
    
    /**
     * informs about second chance to correct data
     */
    public void displayTryAgainStatement(){
        System.out.println(TRY_AGAIN);
    }
    
    /**
     * asks for new value of id
     */
    public void displayNewIDStatement(){
         System.out.println(NEW_VALUES_ID);
    }
    
    /**
     *display all possible update properties on postman
     */
    public void  displayPostmanProperties(){
        System.out.println("1. " + POSTMAN_NAME);
        System.out.println("2. " + POSTMAN_SURNAME);
        System.out.println("3. " + POSTMAN_OFFICE);
        System.out.println("4. " + BACK);       
    }
     /**
     *display all possible update properties on letter
     */
    public void displayLetterProperties(){
        System.out.println("1. " + LETTER_ADDRESSEE_NAME);
        System.out.println("2. " + LETTER_ADDRESSEE_ADDRESS);
        System.out.println("3. " + LETTER_SENDER_NAME);
        System.out.println("4. " + LETTER_SENDER_ADDRESS);
        System.out.println("5. " + LETTER_CONTENT);
        System.out.println("6. " + LETTER_POSTMAN);
        System.out.println("7. " + BACK);
    }
}
