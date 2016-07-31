/* CS144
 *
 * Parser skeleton for processing item-???.xml files. Must be compiled in
 * JDK 1.5 or above.
 *
 * Instructions:
 *
 * This program processes all files passed on the command line (to parse
 * an entire diectory, type "java MyParser myFiles/*.xml" at the shell).
 *
 * At the point noted below, an individual XML file has been parsed into a
 * DOM Document node. You should fill in code to process the node. Java's
 * interface for the Document Object Model (DOM) is in package
 * org.w3c.dom. The documentation is available online at
 *
 * http://java.sun.com/j2se/1.5.0/docs/api/index.html
 *
 * A tutorial of Java's XML Parsing can be found at:
 *
 * http://java.sun.com/webservices/jaxp/
 *
 * Some auxiliary methods have been written for you. You may find them
 * useful.
 */

package edu.ucla.cs.cs144;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;


class MyParser {
        
    static final String[] typeName = {
	"none",
	"Element",
	"Attr",
	"Text",
	"CDATA",
	"EntityRef",
	"Entity",
	"ProcInstr",
	"Comment",
	"Document",
	"DocType",
	"DocFragment",
	"Notation",
    };
    
    static class MyErrorHandler implements ErrorHandler {
        
        public void warning(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void error(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void fatalError(SAXParseException exception)
        throws SAXException {
            exception.printStackTrace();
            System.out.println("There should be no errors " +
                               "in the supplied XML files.");
            System.exit(3);
        }
        
    }
    
    /* Non-recursive (NR) version of Node.getElementsByTagName(...)
     */
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
        Vector< Element > elements = new Vector< Element >();
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
            {
                elements.add( (Element)child );
            }
            child = child.getNextSibling();
        }
        Element[] result = new Element[elements.size()];
        elements.copyInto(result);
        return result;
    }
    
    /* Returns the first subelement of e matching the given tagName, or
     * null if one does not exist. NR means Non-Recursive.
     */
    static Element getElementByTagNameNR(Element e, String tagName) {
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
                return (Element) child;
            child = child.getNextSibling();
        }
        return null;
    }
    
    /* Returns the text associated with the given element (which must have
     * type #PCDATA) as child, or "" if it contains no text.
     */
    static String getElementText(Element e) {
        if (e.getChildNodes().getLength() == 1) {
            Text elementText = (Text) e.getFirstChild();
            return elementText.getNodeValue();
        }
        else
            return "";
    }
    
    /* Returns the text (#PCDATA) associated with the first subelement X
     * of e with the given tagName. If no such X exists or X contains no
     * text, "" is returned. NR means Non-Recursive.
     */
    static String getElementTextByTagNameNR(Element e, String tagName) {
        Element elem = getElementByTagNameNR(e, tagName);
        if (elem != null)
            return getElementText(elem);
        else
            return "";
    }
    
    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) {
        if (money.equals(""))
            return money;
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                                   "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }

    /* Change the format of time from "MMM-dd-yy HH:mm:ss" to "yyyy-MM-dd HH:mm:ss"
     */

    static String changeTimeFormat(String aStarted){
        SimpleDateFormat inputFormat =
            new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
        SimpleDateFormat outputFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parsed = inputFormat.parse(aStarted);
            aStarted = outputFormat.format(parsed);
        }
        catch(ParseException pe) {
            System.out.println("ERROR: Cannot parse \"" + aStarted + "\"");
        }
        return aStarted;
    }

    
    /* Process one items-???.xml file.
     */
    static Item processFile(Document doc) {
        ArrayList<String> Item_Categories = new ArrayList<String>();
        ArrayList<Bid> Bids = new ArrayList<Bid>();
        
        /* Fill in code here (you will probably need to write auxiliary
            methods). */
        
        Element item = doc.getDocumentElement();

            String anItemID = item.getAttribute("ItemID");
            String aName = getElementTextByTagNameNR(item, "Name");
            String aCurrently = strip(getElementTextByTagNameNR(item, "Currently"));
            String aFirst_Bid = strip(getElementTextByTagNameNR(item, "First_Bid"));
            String aNumber_of_Bids = getElementTextByTagNameNR(item, "Number_of_Bids");
            String aLocation = getElementTextByTagNameNR(item, "Location");
            String aLatitude = getElementByTagNameNR(item, "Location").getAttribute("Latitude");
            String aLongitude = getElementByTagNameNR(item, "Location").getAttribute("Longitude");
            String aCountry = getElementTextByTagNameNR(item, "Country");
            String aStarted = getElementTextByTagNameNR(item, "Started");
            aStarted = changeTimeFormat(aStarted);
            String anEnds = getElementTextByTagNameNR(item, "Ends");
            anEnds = changeTimeFormat(anEnds);

            String aSellerID = getElementByTagNameNR(item, "Seller").getAttribute("UserID");
            String aDescription = getElementTextByTagNameNR(item, "Description");
            if(aDescription.length() > 4000)
                aDescription = aDescription.substring(0, 4000);

            Element[] categories = getElementsByTagNameNR(item, "Category");
            for(Element category:categories) {
                Item_Categories.add(category.getTextContent());                
            }

            String aBuy_Price = strip(getElementTextByTagNameNR(item, "Buy_Price"));

            String aRating = getElementByTagNameNR(item, "Seller").getAttribute("Rating");

            Element[] bids = getElementsByTagNameNR(getElementByTagNameNR(item, "Bids"), "Bid");

            for(Element bid:bids) {
                Element bidder = getElementByTagNameNR(bid, "Bidder");
                String aBidderID = bidder.getAttribute("UserID");
                String aBidRating = bidder.getAttribute("Rating");
                String aBidLocation = getElementTextByTagNameNR(bidder, "Location");
                String aBidCountry = getElementTextByTagNameNR(bidder, "Country");
                String aTime = getElementTextByTagNameNR(bid, "Time");
                aTime = changeTimeFormat(aTime);
                String anAmount = strip(getElementTextByTagNameNR(bid, "Amount"));
                Bid cur = new Bid(aBidderID, aBidRating, aBidLocation, aBidCountry, aTime,anAmount);
                Bids.add(cur);
            }
        Item curitem = new Item(anItemID, aName, aCurrently, aFirst_Bid, aNumber_of_Bids,
            aLocation, aLatitude, aLongitude, aCountry, aStarted, anEnds, aSellerID,
            aDescription, Item_Categories, aBuy_Price, aRating, Bids);
        return curitem;
        
    }
    
}
