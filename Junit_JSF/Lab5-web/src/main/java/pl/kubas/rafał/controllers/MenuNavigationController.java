/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.kubas.rafał.controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean; 
import javax.faces.bean.ManagedProperty; 
import javax.faces.bean.RequestScoped;  

/**
 * @author rkubas
 * @date 2017-12-29
 * @version 1.0
 */

@ManagedBean(name = "navigationController", eager = true) 
@RequestScoped 
public class MenuNavigationController implements Serializable {  
    
   private static final long serialVersionUID = 1L;  
     /*
     * Id of the page
     */
   @ManagedProperty(value = "#{param.pageId}")    
   private String pageId;  
   
    /**
     * Returns name of page to creating postman
     * @return String
     */
    public String navigateToCreatePostman() {      
      return "pageCreatePostman";    
   }  
   
    /**
     * Returns name of page to creating letter
     * @return String
     */
    public String navigateToCreateLetter() {       
      return "pageCreateLetter";    
   }  
   
    /**
     * Returns name of page to find postman
     * @return String
     */
    public String navigateToReadPostman() {      
      return "pageReadPostman";    
   }  
   
    /**
     *Returns name of page to find letter
     * @return String
     */
    public String navigateToReadLetter() {       
      return "pageReadLetter";    
   }  
   
    /**
     * Returns name of page to update postman
     * @return String
     */
    public String navigateToUpdatePostman() {       
      return "pageUpdatePostman";    
   } 
   
    /**
     *Returns name of page to update letter
     * @return String
     */
    public String navigateToUpdateLetter() {       
      return "pageUpdateLetter";    
   }
   
    /**
     *Returns name of page to delete postman
     * @return String
     */
    public String navigateToDeletePostman() {       
      return "pageDeletePostman";    
   }    
       
    /**
     *Returns name of page to delete letter
     * @return String
     */
    public String navigateToDeleteLetter() {       
      return "pageDeleteLetter";    
   } 
   
    /**
     * Returns name of the menu page
     * @return String
     */
    public String navigateToMenu() {       
      return "pageMenu";    
   } 
   
    /**
     * Handles page selection based on the page Id
     * @return
     */
    public String showPage() {       
      if(pageId == null) {          
         return navigateToMenu();       
      }       
      if(pageId.equals("1")) {          
         return navigateToCreatePostman();     
         
      }else if(pageId.equals("2")) {      
         return navigateToCreateLetter();  
         
       }else if(pageId.equals("3")) {          
         return navigateToReadPostman(); 
         
       }else if(pageId.equals("4")) {          
         return navigateToReadLetter();
         
       }else if(pageId.equals("5")) {          
         return navigateToUpdatePostman(); 
         
       }else if(pageId.equals("6")) {          
         return navigateToUpdateLetter();  
         
       }else if(pageId.equals("7")) {          
         return navigateToDeletePostman(); 
         
       }else if(pageId.equals("8")) {          
         return navigateToDeleteLetter();         
         
      }else {          
         return navigateToMenu();       
      }    
   }  
   
    /**
     * Returns Id of the page
     * @return String
     */
    public String getPageId() {       
      return pageId;    
   }  
   
    /**
     * Sets Id of the page
     * @param pageId
     */
    public void setPageId(String pageId) {       
      this.pageId = pageId;   
   } 
} 
   

