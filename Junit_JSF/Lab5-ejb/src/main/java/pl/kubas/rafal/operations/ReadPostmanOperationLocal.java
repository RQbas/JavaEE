/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafal.operations;

import java.util.List;
import javax.ejb.Local;
import pl.kubas.rafal.model.Postman;

/**
 * @author rkubas
 * @date 2017-11-22
 * @version 1.0
 */
@Local
public interface ReadPostmanOperationLocal {

    /**
     *Method for finding postman with given id
     * @param id Id of the wanted postman
     * @return Postman object with declared id
     */
    Postman findPostman(int id);
    
    /**
     *Method for finding all postmans persisted
     * @return List of postman objects
     */
    List<Postman> findAllPostmans();
    
    /**
     *Method for finding postman with given name
     * @param name Name of wanted postman
     * @return List of postmans with name obtained as a parameter
     */
    List<Postman> findPostmanByName(String name);
    
    /**
     *Method for finding postman with given surname
     * @param surname surname of wanted postman
     * @return List of postmans with surname obtained as a parameter
     */
    List<Postman> findPostmanBySurname(String surname);
}
