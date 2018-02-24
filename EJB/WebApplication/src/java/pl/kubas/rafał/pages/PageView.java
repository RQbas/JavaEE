/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.pages;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import pl.kubas.rafał.model.Letter;
import pl.kubas.rafał.model.Postman;

/**
 * View part of the program, responsible for generating html
 * @author rkubas
 * @date 2017-11-27
 * @version 1.0
 */
public class PageView {

    /**
     * Method printing out counter page in form of html page. 
     * @param out PrintWriter from the servlet
     * @param map Map storing number of CRUD operations in a session. Obtained in servlet.
     */
    public void generateCounterPage(PrintWriter out, Map<String, Integer> map) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Post Manager</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<body style=\"background-color:#cfcfc4\">");
        out.println("<center>");
        out.println("<table>");
        out.println("<table style=\"width:300px\" bgcolor=\"#adad9a\">");
        out.print("<tr><th style=\"height:50px\"><b><center>Create operations: " + map.get("createCounter") + "</center></b></th></tr>");
        out.print("<tr><th style=\"height:50px\"><b><center>Read operations: " + map.get("readCounter") + "</center></b></th></tr>");
        out.print("<tr><th style=\"height:50px\"><b><center>Update operations: " + map.get("updateCounter") + "</center></b></th></tr>");
        out.print("<tr><th style=\"height:50px\"><b><center>Delete operations: " + map.get("deleteCounter") + "</center></b></th></tr>");
        out.println("<tr><td style=\"height:50px\"><b><center><form action=\"BackServlet\" method=\"POST\"><input type=\"submit\" value=\"Back\" /> </form><center></b></td></tr>");
        out.println("</table>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Method for dynamic creationg of result page in form of html page.
     * @param out PrintWriter from the servlet
     * @param resultURL Navigate to this url after 2 seconds
     * @param resultMessage Display message of the result in a page
     * @param resultHeader Display header of the result in a page
     */
    public void generateResultPage(PrintWriter out, String resultURL, String resultMessage, String resultHeader) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Post Manager</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<meta http-equiv=\"Refresh\" content=\"2;url=" + resultURL + "\">");
        out.println("</head>");
        out.println("<body style=\"background-color:#cfcfc4\">");
        out.println("<center>");
        out.println("<table>");
        out.println("<table style=\"width:300px\" bgcolor=\"#adad9a\">");
        out.print("<tr><th style=\"height:50px\"><b><center>" + resultHeader + "</center></b></th></tr>");
        out.print("<tr><td style=\"height:50px\"><b><center>" + resultMessage + "<center></b></td></tr>");
        out.println("</table>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Method creating page in form of a html page displaying result of find queries with postman type
     * @param out PrintWriter from the servlet to generate html.
     * @param list List of postman which will be displayed
     */
    public void generateReadPostmanPage(PrintWriter out, List<Postman> list) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Post Manager</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("</head>");
        out.println("<body style=\"background-color:#cfcfc4\">");
        out.println("<center>");
        out.println("<table>");
        out.println("<table style=\"width:300px\" bgcolor=\"#adad9a\">");
        if (list.isEmpty() || list.get(0) == null) {
            out.println("<tr><th style=\"height:50px\"><b><center>" + "Records not found" + "</center></b></th></tr>");
        } else {
            for (Postman postman : list) {
                out.println("<tr><th style=\"height:50px\"><b><center>" + postman.getId() + " " + postman.toString() + "</center></b></th></tr>");
            }
        }
        out.println("<tr><td style=\"height:50px\"><b><center><form action=\"ReadPostmanPanelServlet\" method=\"POST\"><input type=\"submit\" value=\"Back\" /> </form><center></b></td></tr>");
        out.println("</table>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }
  /**
     * Method creating page in form of a html page displaying result of find queries with letter type
     * @param out PrintWriter from the servlet to generate html.
     * @param list List of letters which will be displayed
     */
    public void generateReadLetterPage(PrintWriter out, List<Letter> list) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Post Manager</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("</head>");
        out.println("<body style=\"background-color:#cfcfc4\">");
        out.println("<center>");
        out.println("<table>");
        out.println("<table style=\"width:300px\" bgcolor=\"#adad9a\">");
        if (list.isEmpty() || list.get(0) == null) {
            out.println("<tr><th style=\"height:50px\"><b><center>" + "Records not found" + "</center></b></th></tr>");
        } else {
            for (Letter letter : list) {
                out.println("<tr><th style=\"height:50px\"><b><center>" + letter.getId() + " " + letter.toString() + "</center></b></th></tr>");
            }
        }
        out.println("<tr><td style=\"height:50px\"><b><center><form action=\"ReadLetterPanelServlet\" method=\"POST\"><input type=\"submit\" value=\"Back\" /> </form><center></b></td></tr>");
        out.println("</table>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }
}
