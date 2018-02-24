/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pl.kubas.rafal.operations.CreateEntityOperationLocal;
import pl.kubas.rafal.operations.DeleteEntityOperationLocal;
import pl.kubas.rafal.operations.ReadLetterOperationLocal;
import pl.kubas.rafal.operations.UpdateLetterOperationLocal;
import pl.kubas.rafal.model.Letter;
import pl.kubas.rafal.model.Postman;

/**
 * @author rkubas
 * @date 2017-12-29
 * @version 1.0
 */
@ManagedBean(name = "letterController", eager = true)
@ViewScoped
public class LetterController implements Serializable {

    /*
    * Bean for entity creation
     */
    @EJB
    private CreateEntityOperationLocal ceo;
    /*
    * Bean for entity removal
     */
    @EJB
    private DeleteEntityOperationLocal deo;

    /*
    * Bean for finding letter
     */
    @EJB
    private ReadLetterOperationLocal rlo;
    /*
    * Bean for updating letter
     */
    @EJB
    private UpdateLetterOperationLocal ulo;
    /*
    * Letter holder
     */
    private List<Letter> letters;

    /*
    * Initializes letter holder
     */
    @PostConstruct
    protected void init() {
        letters = new ArrayList<>();
    }
    /*
    * Letter's id
     */
    private Integer letterID;
    /*
    * Letter's content
     */
    private String letterContent;
    /*
    * Letter's addressee address
     */
    private String addresseeAddress;

    /*
    * Letter's addressee name
     */
    private String addresseeName;
    /*
    * Letter's sender address
     */
    private String senderAddress;
    /*
    * Letter's sender name
     */
    private String senderName;
    /*
    * Letter's postman id
     */
    private Integer postmanID;
    /*
    * Result message displayed on the page
     */
    private String resultMessage;

    /*
    * Method for updating content of the letter
     */
    public void updateContent() {
        try {
            if (rlo.findLetter(letterID) != null) {
                ulo.updateLetterContent(letterID, letterContent);
                resultMessage = "Content changed successfully";
            } else {
                resultMessage = "Letter with given ID doesn't exist";
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with connection - database error";
        }
    }

    /*
    * Method for updating addressee address
     */
    public void updateAddresseeAddress() {
        try {
            if (rlo.findLetter(letterID) != null) {
                ulo.updateAddresseAddress(letterID, addresseeAddress);
                resultMessage = "Address changed successfully";
            } else {
                resultMessage = "Letter with given ID doesn't exist";
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with connection - database error";
        }
    }

    /*
    * Method for updating sender address
     */
    public void updateSenderAddress() {
        try {
            if (rlo.findLetter(letterID) != null) {
                ulo.updateSenderAddress(letterID, senderAddress);
                resultMessage = "Address changed successfully";
            } else {
                resultMessage = "Letter with given ID doesn't exist";
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with connection - database error";
        }
    }

    /*
    * Method for udpating addressee name
     */
    public void updateAddresseeName() {
        try {
            if (rlo.findLetter(letterID) != null) {
                ulo.updateAddresseeName(letterID, addresseeName);
                resultMessage = "Address changed successfully";
            } else {
                resultMessage = "Letter with given ID doesn't exist";
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with connection - database error";
        }
    }

    /*
    * Method for updating sender name
     */
    public void updateSenderName() {
        try {
            if (rlo.findLetter(letterID) != null) {
                ulo.updateSenderName(letterID, senderName);
                resultMessage = "Address changed successfully";
            } else {
                resultMessage = "Letter with given ID doesn't exist";
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with connection - database error";
        }
    }

    /*
    * Method for finding letter by id
     */
    public void readByID() {
        try {
            letters.clear();
            if (rlo.findLetter(letterID) != null) {
                letters.add(rlo.findLetter(letterID));
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for finding all existing letters
     */
    public void readAll() {
        try {
            letters = rlo.findAllLetters();
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for finding letters by addressee address
     */
    public void readByAddresseeAddress() {
        try {
            letters = rlo.findLetterByAddresseeAddress(addresseeAddress);
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for finding letters by sender address
     */
    public void readBySenderAddress() {
        try {
            letters = rlo.findLetterBySender(senderAddress);
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for finding letters carrying by the postman with given ID
     */
    public void readByPostmanID() {
        try {
            letters = rlo.findLetterByPostman(postmanID);
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for letter creation
     */
    public void createLetter() {
        try {
            ceo.createLetter(letterContent, addresseeName, addresseeAddress, senderName, senderAddress, postmanID);
            resultMessage = "Letter created successfully";
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - check if postman with given ID exists";
        }
    }

    /*
    * Method for letter removal
     */
    public void deleteLetter() {
        if (rlo.findLetter(letterID) == null) {
            resultMessage = "Problem with removal - check if letter with given ID exists";
        } else {
            try {
                resultMessage = "Letter deleted successfully";
                deo.deleteLetter(letterID);
            } catch (Exception dbException) {
                resultMessage = "Problem with removal - check database connection";
            }
        }

    }

    public Integer getLetterID() {
        return letterID;
    }

    public void setLetterID(Integer letterID) {
        this.letterID = letterID;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public String getAddresseeAddress() {
        return addresseeAddress;
    }

    public void setAddresseeAddress(String addresseeAddress) {
        this.addresseeAddress = addresseeAddress;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getPostmanID() {
        return postmanID;
    }

    public void setPostmanID(Integer postmanID) {
        this.postmanID = postmanID;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

}
