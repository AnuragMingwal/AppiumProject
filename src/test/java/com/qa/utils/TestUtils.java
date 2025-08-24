package com.qa.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;



public class TestUtils {
	public static final long Wait_TimeOut = 10; 
	
	public HashMap<String, String> parseStringXML(InputStream file) {
        HashMap<String, String> map = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("string");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String key = element.getAttribute("name");
                    String value = element.getTextContent().trim();
                    map.put(key, value);
                }
            }

            file.close(); // ✅ close InputStream
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;  // ✅ return HashMap
    }

}
