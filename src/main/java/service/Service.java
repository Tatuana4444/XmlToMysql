package service;

import dao.DataBaseDao;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Service {

     private static String getAttrValue(Node node, String attrName)
    {
        if ( ! node.hasAttributes() ) return "";
        NamedNodeMap nmap = node.getAttributes();
        if ( nmap == null ) return "";
        Node n = nmap.getNamedItem(attrName);
        if ( n == null ) return "";
        return n.getNodeValue();
    }

    private static String getTextContent(Node parentNode,String childName)
    {
        NodeList nlist = parentNode.getChildNodes();
        for (int i = 0 ; i < nlist.getLength() ; i++) {
            Node n = nlist.item(i);
            String name = n.getNodeName();
            if ( name != null && name.equals(childName) )
                return n.getTextContent();
        }
        return "";
    }

    public static void parseDocByUsers(Document doc, DataBaseDao dao) {
        NodeList nodeList = doc.getElementsByTagName("ns:user");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                int id =  Integer.parseInt(getAttrValue(node,"id"));
                String name = getTextContent(node, "ns:name");
                String login = getTextContent(node, "ns:login");
                String password = getTextContent(node, "ns:password");
                String role = getTextContent(node, "ns:role");
                boolean isAdmin = role.equals("admin");
                dao.insertUser(id,name,login,password,isAdmin);
            }
        }
    }

    public static void parseDocByProducts(Document doc, DataBaseDao dao){
        NodeList nodeList = doc.getElementsByTagName("ns:product");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                int id =  Integer.parseInt(getAttrValue(node,"id"));
                String name = getTextContent(node, "ns:name");
                String description = getTextContent(node, "ns:description");
                int price = Integer.parseInt(getTextContent(node, "ns:price"));
                dao.insertProduct(id, name, description, price);
            }
        }
    }


    public static void parseDocByOrders(Document doc, DataBaseDao dao) {
        NodeList nodeList = doc.getElementsByTagName("ns:order");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                int id =  Integer.parseInt(getAttrValue(node,"id"));
                int productId = Integer.parseInt(getTextContent(node, "ns:productId"));
                int quantity = Integer.parseInt(getTextContent(node, "ns:quantity"));
                int userId = Integer.parseInt(getTextContent(node, "ns:userId"));
                dao.insertOrder(id, productId, quantity, userId);
            }
        }
    }
}
