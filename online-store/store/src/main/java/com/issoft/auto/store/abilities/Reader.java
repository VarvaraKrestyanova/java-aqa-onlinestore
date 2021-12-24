package com.issoft.auto.store.abilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Reader {

    public Map<String, String> readXmlConfigSort(String packageName, String fileName) throws ParserConfigurationException, IOException, SAXException {
        String xmlPath = packageName + "/src/main/resources/" + fileName + ".xml";
        File xmlFile = new File(xmlPath);

        Map<String, String> xmlData = new LinkedHashMap<>();

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
