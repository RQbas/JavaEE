/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.create;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.kubas.rafał.repeatable.OperationCounter;
import pl.kubas.rafał.operations.CreateEntityOperation;
import pl.kubas.rafał.operations.CreateEntityOperationLocal;

/**
 * Servlet handling input provided during letter creating
 * @author rkubas
 * @date 2017-11-25
 * @version 1.0
 */
public class CreatedLetterServlet extends HttpServlet {

    /**
     * Fields for user input read from the form
     */
    private String letterContent;
    private String addresseeAddress;
    private String addresseeName;
    private String senderAddress;
    private String senderName;
    private String postmanID;
    private int postmanIdInt;

    /**
     * Bean delivering functionallity for creating entities
     */
    @EJB
    CreateEntityOperationLocal ceo;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Handles input for creating letter entity. Check if input is
     * null, if the the type is correct (number in case of ID)
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            letterContent = request.getParameter("letterContent");
            addresseeAddress = request.getParameter("addresseeAddress");
            addresseeName = request.getParameter("addresseeName");
            senderAddress = request.getParameter("senderAddress");
            senderName = request.getParameter("senderName");
            postmanID = request.getParameter("postmanID");
            if (letterContent == null || letterContent.isEmpty()
                    || addresseeAddress == null || addresseeAddress.isEmpty()
                    || addresseeName == null || addresseeName.isEmpty()
                    || senderAddress == null || senderAddress.isEmpty()
                    || senderName == null || senderName.isEmpty()
                    | postmanID == null || postmanID.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    postmanIdInt = Integer.valueOf(postmanID);
                } catch (NumberFormatException typeMismatch) {
                    handleIDInput(request, response);
                }
                handleCorrectInput(request, response);
            }
        }
    }

    /**
     * Method to perform last operation on the input. We've got here valid input
     * with its type. It checks if database operation is possible and if it's
     * possible, method redirect to the Result Servlet with information about
     * successful operation
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void handleCorrectInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resultHeader", "Operation performed successfully");
        request.setAttribute("resultMessage", "You will be returned to the main page");
        request.setAttribute("resultURL", "index.html");

        try {
            ceo.createLetter(letterContent, addresseeName, addresseeAddress, senderName, senderAddress, postmanIdInt);
            OperationCounter.increaseCounterInSession("createCounter", request);
            request.getRequestDispatcher("ResultServlet").forward(request, response);
        } catch (IOException | ServletException dbException) {
            handleDatabaseProblem(request, response);
        }

    }

    /**
     * Method redirecting to the result page with information about failure. It
     * is used when exception is caught in case of problem with database
     * operation
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void handleDatabaseProblem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resultHeader", "Saving to database failed");
        request.setAttribute("resultMessage", "Db error or postman's id doesn't exist");
        request.setAttribute("resultURL", "CreateLetterPanelServlet");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
    }

    /**
     * Method to redirect to result page whenever input was empty
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void handleEmptyInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resultHeader", "Error with performing operation");
        request.setAttribute("resultMessage", "Problem with input data - empty data");
        request.setAttribute("resultURL", "CreateLetterPanelServlet");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Method to redirect to result page whenever numeric value of ID was
     * necessary and the text one was provided
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void handleIDInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resultHeader", "Error with data input");
        request.setAttribute("resultMessage", "Integer id of postman carrying letter required");
        request.setAttribute("resultURL", "CreateLetterPanelServlet");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
    }

}
