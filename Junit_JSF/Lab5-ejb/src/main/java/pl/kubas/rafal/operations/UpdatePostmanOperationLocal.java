/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafal.operations;

import javax.ejb.Local;

/**
 * @author rkubas
 * @date 2017-11-21
 * @version 1.0
 */
@Local
public interface UpdatePostmanOperationLocal {
    
    /**
     * Method for updating postman name
     * @param id ID of postman which will be changed
     * @param name New name of postman
     */
    void updatePostmanName(int id, String name);
    
    /**
     * Method for updating postman surname
     * @param id ID of postman which will be changed
     * @param surname New surname of postman
     */
    void updatePostmanSurname(int id, String surname);
    
}
