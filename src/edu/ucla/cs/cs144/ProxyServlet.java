package edu.ucla.cs.cs144;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.*;
import java.io.*;
public class ProxyServlet extends HttpServlet implements Servlet {
       
    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
        String query = request.getParameter("q");
        if(query!=null)
        {
        URL googleurl = new URL("http://google.com/complete/search?output=toolbar&q="+URLEncoder.encode(query, "UTF-8"));
		HttpURLConnection connection = (HttpURLConnection) googleurl.openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8"); 
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                response.setContentType("text/xml"); 
        				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer buf = new StringBuffer();
			 
				while ((inputLine = in.readLine()) != null) 
				{
					buf.append(inputLine);
				}
				
				PrintWriter out = response.getWriter();
                out.println(buf.toString());
        }
    }
    }
}
