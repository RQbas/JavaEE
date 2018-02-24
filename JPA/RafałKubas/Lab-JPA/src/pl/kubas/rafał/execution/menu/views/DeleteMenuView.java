/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.execution.menu.views;

import static pl.kubas.rafał.execution.menu.MenuDictionary.DELETE_LETTER;
import static pl.kubas.rafał.execution.menu.MenuDictionary.DELETE_POSTMAN;
import static pl.kubas.rafał.execution.menu.MenuDictionary.SUCCESSFUL;
import static pl.kubas.rafał.execution.menu.MenuDictionary.TRY_AGAIN;

/**
 *
 * @author rkubas
 */
public class DeleteMenuView {

    /**
     *View for delete operations
     */
    public DeleteMenuView(){
        
    }
    
    /**
     *asks for postman's id to delete
     */
    public void displayDeletePostmanStatement(){
        System.out.println(DELETE_POSTMAN);
    }
    
    /**
     *informs about wrong input
     */
    public void displayTryAgainStatement(){
        System.out.println(TRY_AGAIN);
    }
    
    /**
     *informs about successful result
     */
    public void displaySuccessfulStatement(){
        System.out.println(SUCCESSFUL);
    }
    
    /**
     *asks for letter's id to delete
     */
    public void displayDeleteLetterStatement(){
       System.out.println(DELETE_LETTER);
    }
}
