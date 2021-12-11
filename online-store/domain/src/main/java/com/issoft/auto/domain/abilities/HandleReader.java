package com.issoft.auto.domain.abilities;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class HandleReader {

    Reader reader = new Reader();

    public boolean isSortAsc(String attributeName) throws IOException, SAXException, ParserConfigurationException {
        Map<String, String> xmlData = reader.readXmlConfigSort();
        return xmlData.get(attributeName).equals("asc");
    }

}