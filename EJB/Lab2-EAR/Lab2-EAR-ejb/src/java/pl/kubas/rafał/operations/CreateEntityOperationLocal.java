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
public interface CreateEntityOperationLocal {
    
    /**
     *Declaration of method creating postman instance
     * @param name delivers name of the postman
     * @param surname delivers surname of the postman
     */
    void createPostman(String name, String surname);

    /**
     * Declaration of method creating letter instance
     * @param letterContent delivers content of the letter
     * @param addresseeName delivers name of the addressee
     * @param addresseeAddress delivers address of the addressee
     * @param senderName delivers name of the sender
     * @param senderAddress delivers address of the sender
     */
    void createLetter(String letterContent, String addresseeName, String addresseeAddress, String senderName, String senderAddress, int postmanID);
}
