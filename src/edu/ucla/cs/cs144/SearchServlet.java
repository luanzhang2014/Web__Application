package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
        try{
            String q = request.getParameter("q");
            String toSkip = request.getParameter("numResultsToSkip");
            String toReturn = request.getParameter("numResultsToReturn");
            int numResultsToSkip = (toSkip != null)? Integer.parseInt(toSkip):0;
            int numResultsToReturn = (toReturn != null)? Integer.parseInt(toReturn):0;
            SearchResult[] result = AuctionSearchClient.basicSearch(q, numResultsToSkip, numResultsToReturn);
            request.setAttribute("result", result);
            request.setAttribute("q", q);
            request.setAttribute("numResultsToSkip", numResultsToSkip);
            request.setAttribute("numResultsToReturn", numResultsToReturn);
        }
    	catch (Exception e)
    	{}
        request.getRequestDispatcher("/keywordSearch.jsp").forward(request, response);
    }
}
