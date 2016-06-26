package com.enghack.aRIve.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

/**
 * Created by 37264 on 6/26/16.
 */
@Service
public class XMLParser {




        public XMLParser()
        {

        }


    public String parser (String xmlString, String id) throws Exception{
        String xmlRecords = "<data><employee><name>A</name>"
                + "<title>Manager</title></employee></data>";

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlString));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("id");

        String hey = "";
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            NodeList name = element.getElementsByTagName("lat");
            Element line1 = (Element) name.item(0);
            NodeList title = element.getElementsByTagName("lon");
            Element line2 = (Element) title.item(0);
            hey += ("lon: " + getCharacterDataFromElement(line2) + " lat: " + getCharacterDataFromElement(line1));
        }

        return hey;
    }
//        public double[] parseIt(String url, String userId)
//        {
//            double[] coordinates = new double[2];
//            try
//            {
//                // Create a new Document Builder Factory object to parse the URL
//                DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
//                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//                InputStream stream = new ByteArrayInputStream(url.getBytes(Charset.forName("UTF-8")));
//
//                Document doc = dBuilder.parse(stream);
//
//
//                doc.getDocumentElement().normalize();
//                Element root = doc.getDocumentElement();
//                NodeList vehicleList = doc.getElementsByTagName("lat");
//                // Loop through the list of elements and find the one with the desired Id
//                Boolean found = false;
//                int i = 0;
//                while (i < vehicleList.getLength() && !found)
//                {
//                    //Create a node object and store the one with our desired ID
//                    Node node = vehicleList.item(i);
//                    Element vehicle = (Element) node;
//                    // Check if the id of this node matches ours
//                    if (vehicle.getAttribute("id") == userId )
//                    {
//                        coordinates[0] = Double.parseDouble(vehicle.getAttribute("lat"));
//                        coordinates[1] = Double.parseDouble(vehicle.getAttribute("lon"));
//                        found = true;
//                    }
//                    i++;
//                }
//
//            }
//            catch (Exception e){
//                return null;
//            }
//            return coordinates;
//        }
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }


    }

