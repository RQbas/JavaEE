package pl.kubas.rafal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kubas.rafal.model.Letter;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-01T15:21:32")
@StaticMetamodel(Postman.class)
public class Postman_ { 

    public static volatile SingularAttribute<Postman, String> surname;
    public static volatile SingularAttribute<Postman, String> name;
    public static volatile SingularAttribute<Postman, Integer> id;
    public static volatile ListAttribute<Postman, Letter> letters;

}