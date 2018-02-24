/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.delete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.kubas.rafał.repeatable.OperationCounter;
import pl.kubas.rafał.operations.DeleteEntityOperationLocal;

/**
 * Servlet handling input provided during postman delete operation
 * @author rkubas
 * @date 2017-11-25
 * @version 1.0
 */
public class DeletedPostmanServlet extends HttpServlet {

    /**
     * Fields for user input read from the form
     */
    private String postmanID;
    private int postmanIdInt;

    /**
     * Bean delivering functionallity for deleting entities
     */
    @EJB
    private DeleteEntityOperationLocal deo;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            /* TODO output your page here. You may use following sample code. */
            postmanID = request.getParameter("postmanID");
            if (postmanID == null || postmanID.isEmpty()) {
                handleEmptyInput(request, response);
            } else {
                try {
                    postmanIdInt = Integer.valueOf(postmanID);

                } catch (NumberFormatException typeMismatch) {
                    handleTypeInput(request, response);
                }
                handleCorrectInput(request, response);

            }

        }
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
        request.setAttribute("resultURL", "DeletePostmanPanelServlet");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
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
            deo.deletePostman(postmanIdInt);
            OperationCounter.increaseCounterInSession("deleteCounter", request);
            request.getRequestDispatcher("ResultServlet").forward(request, response);
        } catch (IOException | ServletException dbException) {
            handleDatabaseProblem(request, response);
        }

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
        request.setAttribute("resultURL", "DeletePostmanPanelServlet");
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
        request.setAttribute("resultHeader", "Operation failed");
        request.setAttribute("resultMessage", "Db error or postman's id doesn't exist");
        request.setAttribute("resultURL", "DeletePostmanPanelServlet");
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
