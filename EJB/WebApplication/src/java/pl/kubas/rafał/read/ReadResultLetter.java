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
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;
import pl.kubas.rafał.operations.ReadLetterOperationLocal;
import pl.kubas.rafał.operations.ReadPostmanOperationLocal;
import pl.kubas.rafał.pages.PageView;

/**
 * Servlet handling input during finding letters
 * @author rkubas
 * @date 2017-11-26
 * @version 1.0
 */
public class ReadResultLetter extends HttpServlet {

    /**
     * Bean delivering functionallity for reading letter entities
     */
    @EJB
    ReadLetterOperationLocal rlo;
    /**
     * Bean delivering functionallity for reading postman entities
     */
    @EJB
    ReadPostmanOperationLocal rpo;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Handles input and type of query to display letters
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
            String letterID = request.getParameter("letterID");
            String addresseeAddress = request.getParameter("addresseeAddress");
            String senderAddress = request.getParameter("senderAddress");
            String postmanID = request.getParameter("postmanID");

            if (type.equals("byID")) {
                if (letterID == null || letterID.isEmpty()) {
                    handleEmptyInput(request, response);
                } else {
                    List<Letter> list = new ArrayList<>();
                    int letterIDInt = Integer.MAX_VALUE;
                    try {
                        letterIDInt = Integer.parseInt(letterID);
                    } catch (NumberFormatException e) {
                        handleTypeInput(request, response);
                    }
                    list.add(rlo.findLetter(letterIDInt));
                    displayLetterList(out, list, request);
                }

            }

            if (type.equals("byPostmanID")) {
                if (postmanID == null || postmanID.isEmpty()) {
                    handleEmptyInput(request, response);
                } else {
                    List<Letter> list = new ArrayList<>();
                    int postmanIDInt = Integer.MAX_VALUE;
                    try {
                        postmanIDInt = Integer.parseInt(postmanID);
                    } catch (NumberFormatException e) {
                        handleTypeInput(request, response);
                    }
                    displayLetterList(out, rlo.findLetterByPostman(postmanIDInt), request);
                }

            }

            if (type.equals("byAll")) {
                displayLetterList(out, rlo.findAllLetters(), request);
            }

            if (type.equals("bySenderAddress")) {
                if (senderAddress == null || senderAddress.isEmpty()) {
                    handleEmptyInput(request, response);
                } else {
                    displayLetterList(out, rlo.findLetterBySender(senderAddress), request);
                }
            }

            if (type.equals("byAddresseeAddress")) {
                if (addresseeAddress == null || addresseeAddress.isEmpty()) {
                    handleEmptyInput(request, response);
                } else {
                    displayLetterList(out, rlo.findLetterByAddresseeAddress(addresseeAddress), request);
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
        request.setAttribute("resultURL", "ReadLetterPanelServlet");
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
        request.setAttribute("resultURL", "ReadLetterPanelServlet");
        request.getRequestDispatcher("ResultServlet").forward(request, response);
    }

    /**
     * Method which refers to the view to create html page with listed letters
     *
     * @param out PrintWriter from servlet
     * @param list list of leters which will be displayed on html page
     * @param request servlet request
     */
    private void displayLetterList(PrintWriter out, List<Letter> list, HttpServletRequest request) {
        PageView letterPage = new PageView();
        OperationCounter.increaseCounterInSession("readCounter", request);
        letterPage.generateReadLetterPage(out, list);
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
