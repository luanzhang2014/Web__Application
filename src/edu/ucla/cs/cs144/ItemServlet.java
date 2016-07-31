package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucla.cs.cs144.AuctionSearchClient;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //your codes here
        try{
	        String id = request.getParameter("id");
	        String xmlStr = AuctionSearchClient.getXMLDataForItemId(id);
			StringReader sr = new StringReader(xmlStr); 
			InputSource is = new InputSource(sr); 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			Document doc = builder.parse(is);
			Item item = MyParser.processFile(doc);

			request.setAttribute("id", id);
			request.setAttribute("item", item);
		}
		catch (Exception e){
		}
		request.getRequestDispatcher("/IDSearch.jsp").forward(request, response);
    }
}
