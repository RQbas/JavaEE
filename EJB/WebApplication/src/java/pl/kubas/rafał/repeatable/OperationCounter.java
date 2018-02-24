/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.repeatable;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class with only static method to increment CRUD counter
 * @author rkubas
 * @date 2017-11-27
 * @version 1.0
 */
public class OperationCounter {

    
    /**
     * Static method to increase specified by string counter. It takes map which had to be obtained from session
     * @param operation String which specify which type of operation has been proceed
     * @param request servlet request
     */
    public static void increaseCounterInSession(String operation, HttpServletRequest request) {
        Map<String, Integer> map;
        HttpSession session = request.getSession();
        map = (Map<String, Integer>) session.getAttribute("counterMap");
        if (map == null) {
            map = new HashMap<>();
            map.put("createCounter", 0);
            map.put("readCounter", 0);
            map.put("updateCounter", 0);
            map.put("deleteCounter", 0);
        }
        if (map.containsKey(operation)) {
            int value = map.get(operation);
            value++;
            map.put(operation, value);
            request.getSession(true).setAttribute("counterMap", map);
        }

    }
}
