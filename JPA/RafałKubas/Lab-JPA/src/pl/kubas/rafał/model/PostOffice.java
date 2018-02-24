/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kubas.rafał.model;

/**
 * 2017-10-23 11:47:56
 *
 * @author rkubas Set of possible post offices
 */
public enum PostOffice {

    /**
     *Post office instance in Katowice
     */
    KATOWICE(1, "Katowice", 167),

    /**
     *Post office instance in Gliwice
     */
    GLIWICE(2, "Gliwice", 134),

    /**
     *Post office instance in Zabrze
     */
    ZABRZE(3, "Zabrze", 80),

    /**
     *Post office instance in Kraków
     */
    KRAKOW(4, "Kraków", 327);

    /*
    *Post office properties
    */
    private final Integer ID;
    private final Integer AREA;
    private final String NAME;

    private PostOffice(Integer id, String name, Integer area) {
        this.ID = id;
        this.AREA = area;
        this.NAME = name;
    }

}
