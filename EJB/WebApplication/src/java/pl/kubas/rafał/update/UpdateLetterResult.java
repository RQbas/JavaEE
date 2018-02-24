/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.update;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.kubas.rafał.repeatable.OperationCounter;
import pl.kubas.rafał.operations.UpdateLetterOperationLocal;

/**
 * Servlet handling input for letter update
 * @author rkubas
 * @date 2017-11-26
 * @version 1.0
 */
public class UpdateLetterResult extends HttpServlet {

    /**
     * Letter entity input fields
     */
    private String type;
    private String letterID;
    private String letterContent;
    private String addresseeAddress;
    private String addresseeName;
    private String senderAddress;
    private String senderName;
    private int letterIdInt;
    /**
     * Bean delivering functionallity for updating entities
     */
    @EJB
    private UpdateLetterOperationLocal ulo;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Handles updated for specified attribute of the letter class
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
            type = request.getParameter("operationType");
            letterID = request.getParameter("letterID");
            letterContent = request.getParameter("letterContent");
            addresseeAddress = request.getParameter("addresseeAddress");
            addresseeName = request.getParameter("addresseeName");
            senderAddress = request.getParameter("senderAddress");
            senderName = request.getParameter("senderName");

            if (letterID == null || letterID.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    letterIdInt = Integer.valueOf(letterID);
                } catch (NumberFormatException e) {
                    handleTypeInput(request, response);
                }

                checkCaseUpdateContent(type, request, response);
                checkCaseUpdateAddresseeAddress(type, request, response);
                checkCaseUpdateAddresseeName(type, request, response);
                checkCaseUpdateSenderAddress(type, request, response);
                checkCaseUpdateSenderName(type, request, response);

            }

        }
    }

    /**
     * Method for handling updating letter's content
     *
     * @param type type of the operation which will change entity's attribute
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException
     */
    private void checkCaseUpdateContent(String type, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (type.equals("updateContent")) {
            if (letterContent == null || letterContent.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    ulo.updateLetterContent(letterIdInt, letterContent);
                } catch (Exception e) {
                    handleDatabaseProblem(request, response);
                }
            }
            handleCorrectInput(request, response);
        }
    }

    /**
     * Method for handling updating letter's addressee address
     *
     * @param type type of the operation which will change entity's attribute
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException
     */
    private void checkCaseUpdateAddresseeAddress(String type, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (type.equals("updateAddresseeAddress")) {
            if (addresseeAddress == null || addresseeAddress.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    ulo.updateAddresseAddress(letterIdInt, addresseeAddress);
                } catch (Exception e) {
                    handleDatabaseProblem(request, response);
                }
                handleCorrectInput(request, response);
            }
        }
    }

    /**
     * Method for handling updating addressee name
     *
     * @param type type of the operation which will change entity's attribute
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException
     */
    private void checkCaseUpdateAddresseeName(String type, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (type.equals("updateAddresseeName")) {
            if (addresseeName == null || addresseeName.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    ulo.updateAddresseeName(letterIdInt, addresseeName);
                } catch (Exception e) {
                    handleDatabaseProblem(request, response);
                }
                handleCorrectInput(request, response);

            }
        }
    }

    /**
     * Method for handling updating sender address
     *
     * @param type type of the operation which will change entity's attribute
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException
     */
    private void checkCaseUpdateSenderAddress(String type, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (type.equals("updateSenderAddress")) {
            if (senderAddress == null || senderAddress.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    ulo.updateSenderAddress(letterIdInt, senderAddress);
                } catch (Exception e) {
                    handleDatabaseProblem(request, response);
                }
                handleCorrectInput(request, response);
            }
        }

    }

    /**
     * Method for handling updating sender name
     *
     * @param type type of the operation which will change entity's attribute
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException
     */
    private void checkCaseUpdateSenderName(String type, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (type.equals("updateSenderName")) {
            if (senderName == null || senderName.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    ulo.updateSenderName(letterIdInt, senderName);
                } catch (Exception e) {
                    handleDatabaseProblem(request, response);
                }
                handleCorrectInput(request, response);
            }
        }
    }

    /**
     * Method to perform last operation on the input. We've got here valid input
     * with its type. Method redirect to the Result Servlet with information
     * about successful operation
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
        OperationCounter.increaseCounterInSession("updateCounter", request);
        request.getRequestDispatcher("ResultServlet").forward(request, response);
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
        request.setAttribute("resultHeader", "Database operation failed");
        request.setAttribute("resultMessage", "Entity with given ID doesn't exist");
        request.setAttribute("resultURL", "UpdateLetterPanelServlet");
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
    private void handleEmptyInput(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("resultHeader", "Error with performing operation");
        request.setAttribute("resultMessage", "Problem with input data - empty data");
        request.setAttribute("resultURL", "UpdateLetterPanelServlet");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
    }

    /**
     * Method to redirect to result page whenever numeric value of ID was
     * necessary and the text one was provided
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void handleTypeInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resultHeader", "Error with data input");
        request.setAttribute("resultMessage", "Integer value of ID is required");
        request.setAttribute("resultURL", "UpdateLetterPanelServlet");
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

}
