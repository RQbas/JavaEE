package pl.polsl.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.kubas.rafal.operations.CreateEntityOperationLocal;
import pl.kubas.rafal.operations.DeleteEntityOperationLocal;
import pl.kubas.rafal.operations.ReadLetterOperationLocal;
import pl.kubas.rafal.operations.ReadPostmanOperationLocal;
import pl.kubas.rafal.operations.UpdateLetterOperationLocal;
import pl.kubas.rafal.operations.UpdatePostmanOperationLocal;
import pl.kubas.rafal.model.Letter;
import pl.kubas.rafal.model.Postman;

/**
 * @author rkubas
 * @date 2018-01-01
 * @version 1.0
 */
public class BeansTest {
    /*
     *Properties for connections
     */
    private static Properties properties;
    /*
     *Bean with creation operations
     */
    private static CreateEntityOperationLocal ceo;
    /*
     *Bean with finding postman operations
     */
    private static ReadPostmanOperationLocal rpo;
    /*
     *Bean with finding letter operations
     */
    private static ReadLetterOperationLocal rlo;
    /*
     *Bean with postman update operations
     */
    private static UpdatePostmanOperationLocal upo;
    /*
     *Bean with letter update operations
     */
    private static UpdateLetterOperationLocal ulo;
    /*
     * Bean with delete operations
     */
    private static DeleteEntityOperationLocal deo;

    /**
     * Method used to initialize database connection, before any other.
     * @throws NamingException
     */
    @BeforeClass
    public static void prepare() throws NamingException {
        properties = new Properties();
        properties.put("lab5PU", "new://Resource?type=DataSource");
        properties.put("lab5PU.UserName", "root");
        properties.put("lab5PU.Password", "root");
        properties.put("lab5PU.JtaManaged", "true");
        properties.put("lab5PU.JdbcDriver",
                "com.mysql.jdbc.Driver");
        properties.put("lab5PU.JdbcUrl", "jdbc:mysql://localhost:3306/lab?zeroDateTimeBehavior=convertToNull");

        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.openejb.client.LocalInitialContextFactory");

        ceo = (CreateEntityOperationLocal) new InitialContext(properties).lookup("CreateEntityOperationLocal");
        rpo = (ReadPostmanOperationLocal) new InitialContext(properties).lookup("ReadPostmanOperationLocal");
        rlo = (ReadLetterOperationLocal) new InitialContext(properties).lookup("ReadLetterOperationLocal");
        upo = (UpdatePostmanOperationLocal) new InitialContext(properties).lookup("UpdatePostmanOperationLocal");
        ulo = (UpdateLetterOperationLocal) new InitialContext(properties).lookup("UpdateLetterOperationLocal");
        deo = (DeleteEntityOperationLocal) new InitialContext(properties).lookup("DeleteEntityOperationLocal");
    }

    /**
     *Method for setting data sources for tests
     */
    @Before
    public void setup() {
        for (int i = 0; i < 10; i++) {
            ceo.createPostman("Name" + i, "Surname" + i);
        }

        List<Postman> list = rpo.findAllPostmans();

        for (int i = 0; i < 10; i++) {
            ceo.createLetter("Content" + i, "addresseeName" + i, "addresseeAddress" + i, "senderName", "senderAddress" + i, list.get(i).getId());
        }

    }

    /**
     * Method for checking if postmans haven been created correctly
     */
    @Test
    public void arePostmansCreated() {
        List<Postman> list = rpo.findAllPostmans();
        Assert.assertNotNull("Should be not null", list);
        Assert.assertEquals(10, list.size());
    }

    /**
     * Method for checking if letters have been created correctly
     */
    @Test
    public void areLettersCreated() {
        List<Letter> list = rlo.findAllLetters();
        Assert.assertNotNull("Should be not null", list);
        Assert.assertEquals(10, list.size());
    }

    /**
     * Method for checking if postmans can be found by name
     */
    @Test
    public void isEveryPostmanReadByName() {
        for (int i = 0; i < 10; i++) {
            List<Postman> list = rpo.findPostmanByName("Name" + i);
            Assert.assertNotNull("Should be not null", list);
            Assert.assertEquals("Name" + i, list.get(0).getName());
        }
    }

    /**
     * Method for checking if letters can be found by addressee address
     */
    @Test
    public void isEveryLetterReadByAddresseeAddress() {
        for (int i = 0; i < 10; i++) {
            List<Letter> list = rlo.findLetterByAddresseeAddress("addresseeAddress" + i);
            Assert.assertNotNull("Should be not null", list);
            Assert.assertEquals("addresseeAddress" + i, list.get(0).getAddresseeAddress());
        }
    }

    /**
     * Method for checking if postman can be found by surname
     */
    @Test
    public void isEveryPostmanReadBySurname() {
        for (int i = 0; i < 10; i++) {
            List<Postman> list = rpo.findPostmanBySurname("Surname" + i);
            Assert.assertNotNull("Should be not null", list);
            Assert.assertEquals("Surname" + i, list.get(0).getSurname());
        }
    }

    /**
     * Method for checking if postman can be found by id
     */
    @Test
    public void isEveryPostmanFoundByID() {
        List<Postman> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(rpo.findPostman(i));
        }
        Assert.assertNotNull("Should be not null", list);
        Assert.assertEquals(10, list.size());
    }

    /**
     * Method for checking if postman can be found by id
     */
    @Test
    public void isEveryLetterFoundByID() {
        List<Letter> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(rlo.findLetter(i));
        }
        Assert.assertNotNull("Should be not null", list);
        Assert.assertEquals(10, list.size());
    }

    /**
     * Metod for checking if postman can be updated
     */
    @Test
    public void canPostmansBeUpdated() {
        List<Postman> list = rpo.findAllPostmans();

        for (Postman postman : list) {
            Assert.assertEquals("Name" + list.indexOf(postman), postman.getName());
            upo.updatePostmanName(postman.getId(), "NAME" + list.indexOf(postman));
        }

        list = rpo.findAllPostmans();
        for (Postman postman : list) {
            Assert.assertEquals("NAME" + list.indexOf(postman), postman.getName());
        }
    }

    /**
     * Method for checking if letters can be updated
     */
    @Test
    public void canLettersBeUpdated() {
        List<Letter> list = rlo.findAllLetters();

        for (Letter letter : list) {
            Assert.assertEquals("Content" + list.indexOf(letter), letter.getLetterContent());
            ulo.updateLetterContent(letter.getId(), "CONTENT" + list.indexOf(letter));
        }

        list = rlo.findAllLetters();

        for (Letter letter : list) {
            Assert.assertEquals("CONTENT" + list.indexOf(letter), letter.getLetterContent());
        }
    }

    /**
     * Method for checking if postman can be deleted
     */
    @Test
    public void canPostmansBeDeleted() {
        List<Postman> list = rpo.findAllPostmans();
        Assert.assertNotNull("Should be not null", list);
        for (Postman postman : list) {
            int id = postman.getId();
            deo.deletePostman(id);
        }
        list = rpo.findAllPostmans();
        Assert.assertEquals(0, list.size());
    }

    /**
     * Method for destroying data entities in database
     */
    @After
    public void destroy() {
        List<Postman> listPostman = rpo.findAllPostmans();

        for (Postman postman : listPostman) {
            deo.deletePostman(postman.getId());
        }

        List<Letter> listLetter = rlo.findAllLetters();

        for (Letter letter : listLetter) {
            deo.deleteLetter(letter.getId());
        }
    }

}
