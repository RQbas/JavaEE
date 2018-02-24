/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.operations;

import javax.ejb.Local;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Local
public interface UpdateLetterOperationLocal {
    
    /**
     * Method updating content of the letter
     * @param id ID of the letter which will be changed
     * @param letterContent New content of the letter
     */
    void updateLetterContent(int id, String letterContent);
    
    /**
     * Method updating addressee name 
     * @param id ID of the letter which will be changed
     * @param name New addressee name
     */
    void updateAddresseeName(int id, String name);
    
    /**
     * Method updating addressee address
     * @param id ID of the letter which will be changed
     * @param address New addressee address
     */
    void updateAddresseAddress(int id, String address);
    
    /**
     * Method updating addressee name
     * @param id ID of the letter which will be changed
     * @param name New sender name
     */
    void updateSenderName(int id, String name);
    
    /**
     * Method updating sender address
     * @param id ID of the letter which will be changed
     * @param address New sendeer address
     */
    void updateSenderAddress(int id, String address);
    
    
}
