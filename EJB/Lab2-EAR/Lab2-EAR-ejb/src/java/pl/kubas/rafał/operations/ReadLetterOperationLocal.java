/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.operations;

import java.util.List;
import javax.ejb.Local;
import pl.kubas.rafał.model.Letter;

/**
 * @author rkubas
 * @date 2017-11-22
 * @version 1.0
 */
@Local
public interface ReadLetterOperationLocal {
     /**
     * Method for finding all letters persisted
     * @return List of letter objects
     */
    List<Letter> findAllLetters();

    /**
     * Method for finding letter with given id
     * @param id Id of the wanted letter
     * @return Letter object with declared id
     */
    Letter findLetter(int id);

    /**
     * Method for finding letter with given addressee address
     * @param address  Addressee address of wanted letter
     * @return List of letters with addressee address obtained as a parameter
     */
    List<Letter> findLetterByAddresseeAddress(String address);

    /**
     * Method for finding letter with given sender address
     * @param address  Sender address of wanted letter
     * @return List of letters with sender address obtained as a parameter
     */
    List<Letter> findLetterBySender(String address);

    /**
     * Method for finding letters carried by postman
     * @param id Id of the postman which carries letters
     * @return List of leters carried by postman with given id
     */
    List<Letter> findLetterByPostman(int id);
}
