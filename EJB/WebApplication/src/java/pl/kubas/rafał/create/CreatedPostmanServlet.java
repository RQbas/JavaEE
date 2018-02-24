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
 * Servlet handling input provided during postman creating
 * @author rkubas
 * @date 2017-11-25
 * @version 1.0
 */
public class CreatedPostmanServlet extends HttpServlet {

    /**
     * Fields for user input read from the form
     */
    private String postmanName;
    private String postmanSurname;
    /**
     * Bean delivering functionallity for creating entities
     */
    @EJB
    CreateEntityOperationLocal ceo;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Handles input for creating postman entity. Check if input is
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
            postmanName = request.getParameter("postmanName");
            postmanSurname = request.getParameter("postmanSurname");
            if (postmanName == null || postmanName.isEmpty() || postmanSurname == null || postmanSurname.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                if (postmanName.matches(".*\\d+.*") || postmanSurname.matches(".*\\d+.*")) {
                    handleNumericInput(request, response);
                } else {
                    handleCorrectInput(request, response);
                }

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
            ceo.createPostman(postmanName, postmanSurname);
        } catch (Exception dbException) {
            handleDatabaseProblem(request, response);
        }
        OperationCounter.increaseCounterInSession("createCounter", request);
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
        request.setAttribute("resultHeader", "Saving to database failed");
        request.setAttribute("resultMessage", "You will be returned to the main page");
        request.setAttribute("resultURL", "index.html");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
    }

    /**
     * Method to redirect to result page whenever numeric value was forbidden
     * and it appeared
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void handleNumericInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resultHeader", "Error with performing operation");
        request.setAttribute("resultMessage", "Problem with input data - numeric input not allowed");
        request.setAttribute("resultURL", "CreatePostmanPanelServlet");
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
        request.setAttribute("resultURL", "CreatePostmanPanelServlet");
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
