package com.issoft.auto.domain.abilities;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class HandleReader {

    Reader reader = new Reader();

    public boolean isSortAsc(String attributeName) throws IOException, SAXException, ParserConfigurationException {
        Map<String, String> xmlData = reader.readXmlConfigSort();
        return xmlData.get(attributeName).equals("asc");
    }

    public List<String> attributeNames() {
        Map<String, String> xmlData = null;
        try {
            xmlData = reader.readXmlConfigSort();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        List<String> attributes = new ArrayList<>();

        for ( String key : xmlData.keySet() ) {
            attributes.add(key);
        }

        return attributes;
    }
}