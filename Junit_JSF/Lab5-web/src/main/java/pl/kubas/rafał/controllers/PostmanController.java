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
import pl.kubas.rafal.operations.ReadPostmanOperationLocal;
import pl.kubas.rafal.operations.UpdatePostmanOperationLocal;
import pl.kubas.rafal.model.Postman;

/**
 * @author rkubas
 * @date 2017-12-29
 * @version 1.0
 */
@ManagedBean(name = "postmanController", eager = true)
@ViewScoped
public class PostmanController implements Serializable {

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
    * Bean for finding postmans
     */
    @EJB
    private ReadPostmanOperationLocal rpo;
    /*
    * Bean for updating postmans
     */
    @EJB
    private UpdatePostmanOperationLocal upo;

    /*
    * Postman id
     */
    private Integer postmanID;

    /*
    *Postman name 
     */
    private String postmanName;

    /*
    * Postman surname
     */
    private String postmanSurname;

    /*
    * Result message for the page display
     */
    private String resultMessage;

    /*
    * Postman holder
     */
    private List<Postman> postmans;

    /*
    * Initializes psotman holder
     */
    @PostConstruct
    protected void init() {
        postmans = new ArrayList<>();
    }

    /*
    * Method for updating postman's surname
     */
    public void updateSurname() {
        try {
            if (rpo.findPostman(postmanID) != null) {
                upo.updatePostmanSurname(postmanID, postmanSurname);
                resultMessage = "Surname changed successfully";
            } else {
                resultMessage = "Postman with given ID doesn't exist";
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with connection - database error";
        }
    }

    /*
    *  Method for updating postman's name
     */
    public void updateName() {
        try {
            if (rpo.findPostman(postmanID) != null) {
                upo.updatePostmanName(postmanID, postmanName);
                resultMessage = "Name changed successfully";
            } else {
                resultMessage = "Postman with given ID doesn't exist";
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with connection - database error";
        }
    }

    /*
    * Method for finding postman by ID
     */
    public void readByID() {
        try {
            postmans.clear();
            if (rpo.findPostman(postmanID) != null) {
                postmans.add(rpo.findPostman(postmanID));
            }
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for finding all existing postmans
     */
    public void readAll() {
        try {
            postmans = rpo.findAllPostmans();
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for finding postmans by name 
     */
    public void readByName() {
        try {
            postmans = rpo.findPostmanByName(postmanName);
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for finding postmans by surname
     */
    public void readBySurname() {
        try {
            postmans = rpo.findPostmanBySurname(postmanSurname);
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for postman creation
     */
    public void createPostman() {
        try {
            ceo.createPostman(postmanName, postmanSurname);
            resultMessage = "Postman created";
        } catch (Exception dbException) {
            resultMessage = "Problem with creation - database error";
        }
    }

    /*
    * Method for postman removal
     */
    public void deletePostman() {
        if (rpo.findPostman(postmanID) == null) {
            resultMessage = "Problem with removal - check if postman with given ID exists";
        } else {
            try {
                resultMessage = "Postman deleted successfully";
                deo.deletePostman(postmanID);
            } catch (Exception dbException) {
                resultMessage = "Problem with removal - check database connection";
            }
        }

    }

    public List<Postman> getPostmans() {
        return postmans;
    }

    public void setPostmans(List<Postman> postmans) {
        this.postmans = postmans;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getPostmanName() {
        return postmanName;
    }

    public void setPostmanName(String postmanName) {
        this.postmanName = postmanName;
    }

    public String getPostmanSurname() {
        return postmanSurname;
    }

    public void setPostmanSurname(String postmanSurname) {
        this.postmanSurname = postmanSurname;
    }

    public Integer getPostmanID() {
        return postmanID;
    }

    public void setPostmanID(Integer postmanID) {
        this.postmanID = postmanID;
    }

}
