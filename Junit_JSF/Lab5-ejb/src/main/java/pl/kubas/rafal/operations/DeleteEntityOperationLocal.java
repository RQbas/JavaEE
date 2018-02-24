/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafal.operations;

import java.io.Serializable;
import javax.ejb.Local;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Local
public interface DeleteEntityOperationLocal{
    
    /**
     * Method for deleting leter
     * @param id ID of letter which will be deleted
     */
    void deleteLetter(int id);
    
    /**
     * Method for deleting postman
     * @param id ID of postman which will be deleted
     */
    void deletePostman(int id);
}
