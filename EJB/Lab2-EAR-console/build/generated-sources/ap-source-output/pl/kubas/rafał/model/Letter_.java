package pl.kubas.rafał.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kubas.rafał.model.Postman;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T12:24:28")
@StaticMetamodel(Letter.class)
public class Letter_ { 

    public static volatile SingularAttribute<Letter, String> senderName;
    public static volatile SingularAttribute<Letter, String> senderAddress;
    public static volatile SingularAttribute<Letter, Postman> postman;
    public static volatile SingularAttribute<Letter, String> letterContent;
    public static volatile SingularAttribute<Letter, String> addresseeName;
    public static volatile SingularAttribute<Letter, Integer> id;
    public static volatile SingularAttribute<Letter, String> addresseeAddress;

}