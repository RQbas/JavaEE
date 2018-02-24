/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.views;

import static pl.kubas.rafał.execution.menu.MenuDictionary.BACK;
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
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 *
 * @author rkubas
 */
public class ReadMenuView {
    
    public void displayLetterOperations(){
        System.out.println("1. " + DISPLAY_ALL);
        System.out.println("2. " + DISPLAY_BY_ID);
        System.out.println("3. " + DISPLAY_BY_POSTMAN_ID);
        System.out.println("4. " + DISPLAY_BY_ADDRESSEE_ADDRESS);
        System.out.println("5. " + DISPLAY_BY_SENDER_ADDRESS);
        System.out.println("6. " + BACK);
    }
    
    public void displayPostmanOperations(){
        System.out.println("1. " + DISPLAY_ALL);
        System.out.println("2. " + DISPLAY_BY_ID);
        System.out.println("3. " + DISPLAY_BY_NAME);
        System.out.println("4. " + DISPLAY_BY_SURNAME);
        System.out.println("5. " + DISPLAY_BY_OFFICE);
        System.out.println("6. " + BACK);
    }
    
    public void displayLetter(Letter letter){
        System.out.println(letter.getId() + " " + letter.toString());
    }
    
    public void displayPostman(Postman postman){
        System.out.println(postman.getId() + " " + postman.toString());
    }
    
    public void displayIDStatement(){
        System.out.println(PROVIDE_ID);
    }
    public void displayValueStatement(){
        System.out.println(PROVIDE_VALUE);
    }
}
