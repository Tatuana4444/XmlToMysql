import dao.DataBaseDao;
import org.apache.log4j.Logger;

import org.w3c.dom.Document;
import reader.Reader;
import service.Service;
import service.XMLValidator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.SQLException;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){
        Document doc;
        System.out.println("Enter name of xml");
        String xmlName = Reader.nextString();//src/main/resources/shop.xml
        if (!XMLValidator.SAXValidator(xmlName))
            return;
        try {
            File file = new File(xmlName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        }catch (Exception e){
            logger.error(e);
            System.out.println("Parse exception");
            return;
        }

        try {
            DataBaseDao dao = new DataBaseDao();
            Service.parseDocByUsers(doc, dao);
            Service.parseDocByProducts(doc, dao);
            Service.parseDocByOrders(doc, dao);
            dao.destroyConnection();
            System.out.println("Data was imported");
        }catch (SQLException e){
            logger.error("Connection error", e);
            System.out.println("Connection error");
        }


    }


}
