/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.read;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.kubas.rafał.repeatable.OperationCounter;
import pl.kubas.rafał.model.Postman;
import pl.kubas.rafał.operations.ReadPostmanOperationLocal;
import pl.kubas.rafał.pages.PageView;

/**
 * Servlet handling input for the finding operation for postman type
 * @author1 rkubas
 * @date 2017-11-26
 * @version 1.0
 */
public class ReadResultPostman extends HttpServlet {

    
    /**
     * Bean delivering functionallity for finding postman
     */
    @EJB
    ReadPostmanOperationLocal rpo;

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
            String type = request.getParameter("operationType");
            String postmanID = request.getParameter("postmanID");
            String postmanName = request.getParameter("postmanName");
            String postmanSurname = request.getParameter("postmanSurname");

            if (type.equals("byID")) {
                if (postmanID == null || postmanID.isEmpty()) {
                    handleEmptyInput(request, response);
                } else {
                    List<Postman> list = new ArrayList<>();
                    int postmanIdInt = Integer.MAX_VALUE;
                    try {
                        postmanIdInt = Integer.parseInt(postmanID);
                    } catch (NumberFormatException e) {
                        handleTypeInput(request, response);
                    }
                    list.add(rpo.findPostman(postmanIdInt));
                    displayPostmanList(out, list, request);
                }

            }
            if (type.equals("byAll")) {
                displayPostmanList(out, rpo.findAllPostmans(), request);
            }
            if (type.equals("byPostmanName")) {
                if (postmanName == null || postmanName.isEmpty()) {
                    handleEmptyInput(request, response);
                } else {
                    displayPostmanList(out, rpo.findPostmanByName(postmanName), request);
                }
            }

            if (type.equals("byPostmanSurname")) {
                if (postmanSurname == null || postmanSurname.isEmpty()) {
                    handleEmptyInput(request, response);
                } else {
                    displayPostmanList(out, rpo.findPostmanBySurname(postmanSurname), request);
                }
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
        request.setAttribute("resultURL", "ReadPostmanPanelServlet");
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
        request.setAttribute("resultURL", "ReadPostmanPanelServlet");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
    }

    /**
     * Method which refers to the view to create html page with listed letters
     *
     * @param out PrintWriter from servlet
     * @param list list of postmans which will be displayed on html page
     * @param request servlet request
     */
    private void displayPostmanList(PrintWriter out, List<Postman> list, HttpServletRequest request) {
        OperationCounter.increaseCounterInSession("readCounter", request);
        PageView postmanListPage = new PageView();
        postmanListPage.generateReadPostmanPage(out, list);
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
