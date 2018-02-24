/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kubas.rafał.execution.menu.views;

import static pl.kubas.rafał.execution.menu.MenuDictionary.BACK;
import static pl.kubas.rafał.execution.menu.MenuDictionary.CREATE_OPERATION;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DELETE_OPERATION;
import static pl.kubas.rafał.execution.menu.MenuDictionary.END;
import static pl.kubas.rafał.execution.menu.MenuDictionary.HEADER;
import static pl.kubas.rafał.execution.menu.MenuDictionary.LETTER;
import static pl.kubas.rafał.execution.menu.MenuDictionary.OPERATION;
import static pl.kubas.rafał.execution.menu.MenuDictionary.POSTMAN;
import static pl.kubas.rafał.execution.menu.MenuDictionary.READ_OPERATION;
import static pl.kubas.rafał.execution.menu.MenuDictionary.UPDATE_OPERATION;
import static pl.kubas.rafał.execution.menu.MenuDictionary.WRONG_INPUT_NUMBER;
import static pl.kubas.rafał.execution.menu.MenuDictionary.WRONG_INPUT_TYPE;
import pl.kubas.rafał.model.PostOffice;

/**
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author rkubas
 */
public class MenuView {
    
    /**
     *represents view for main menu
     */
    public MenuView(){
        
    }
    
    /**
     *presents main menu
     */
    public void displayMenu() {
        System.out.println(HEADER);
        System.out.println(OPERATION);
        System.out.println(CREATE_OPERATION);
        System.out.println(READ_OPERATION);
        System.out.println(UPDATE_OPERATION);
        System.out.println(DELETE_OPERATION);
        System.out.println(END);
    }

    /**
     * display typical operations for entities
     * @param operationName
     */
    public void displayOperationMenu(String operationName){
 System.out.println("1. " + operationName + POSTMAN);
        System.out.println("2. " + operationName + LETTER);
        System.out.println("3. " + BACK);
}
    
    /**
     * informs about wrong type
     */
    public void displayWrongInputType(){
    System.out.println(WRONG_INPUT_TYPE);
}

    /**
     * informs abut wrong input range
     */
    public void displayWrongInputNumber(){
    System.out.println(WRONG_INPUT_NUMBER);
}

    /**
     *display possible post offices
     * @param counter
     * @param office
     */
    public void displayPostOffice(int counter, PostOffice office){
    System.out.println(counter + ". " + office.name());
}

}
