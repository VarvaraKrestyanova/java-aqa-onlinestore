package com.issoft.auto.domain.abilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reader {
    File xmlFile = new File("domain/src/main/resources/configSort.xml");
    private Map<String, String> xmlData = new HashMap<>();

    public Map<String, String> readXmlConfigSort() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++){
            if (nodeList.item(i) instanceof Element) {
                xmlData.put(((Element) nodeList.item(i)).getTagName(), nodeList.item(i).getTextContent());
            }
        }

        return xmlData;
    }
}
