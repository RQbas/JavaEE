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
import pl.kubas.rafał.operations.UpdatePostmanOperationLocal;

/**
 * Servlet handling input for updating postman
 * @author rkubas
 * @date 2017-11-26
 * @version 1.0
 */
public class UpdatePostmanResult extends HttpServlet {
 /**
     * Bean delivering functionallity for updating postman entities
     */
    @EJB
    UpdatePostmanOperationLocal upo;

    /**
     * Fields for user input read from the form
     */
    private String postmanID;
    private int postmanIdInt;
    private String postmanName;
    private String postmanSurname;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Handles input in case of updating postman
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
            String type = request.getParameter("operationType");
            postmanID = request.getParameter("postmanID");
            postmanName = request.getParameter("postmanName");
            postmanSurname = request.getParameter("postmanSurname");

            if (postmanID == null || postmanID.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                postmanIdInt = Integer.MAX_VALUE;
                try {
                    postmanIdInt = Integer.valueOf(postmanID);
                } catch (NumberFormatException e) {
                    handleTypeInput(request, response);
                }
                checkCaseUpdateName(type, request, response);
                checkCaseUpdateSurname(type, request, response);

            }

        }
    }
    /**
     * Method for handling updating postman name
     *
     * @param type type of the operation which will change entity's attribute
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException
     */
    private void checkCaseUpdateName(String type, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (type.equals("updateName")) {
            if (postmanName == null || postmanName.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    upo.updatePostmanName(postmanIdInt, postmanName);
                } catch (Exception e) {
                    handleDatabaseProblem(request, response);
                }
                handleCorrectInput(request, response);
            }
        }
    }
    /**
     * Method for handling updating sender surname
     *
     * @param type type of the operation which will change entity's attribute
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException
     */
    private void checkCaseUpdateSurname(String type, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (type.equals("updateSurname")) {
            if (postmanSurname == null || postmanSurname.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    upo.updatePostmanSurname(postmanIdInt, postmanSurname);
                } catch (Exception e) {
                    handleDatabaseProblem(request, response);
                }
                handleCorrectInput(request, response);
            }
        }
    }
  /**
     * Method to perform last operation on the input. We've got here valid input
     * with its type. Method redirect to the Result Servlet with information about
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
        request.setAttribute("resultURL", "UpdatePostmanPanelServlet");
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
        request.setAttribute("resultURL", "UpdatePostmanPanelServlet");
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
        request.setAttribute("resultURL", "UpdatePostmanPanelServlet");
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
