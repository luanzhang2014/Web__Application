<html>
<head>
    <title> Search Results</title>
    <link rel="stylesheet" type="text/css" href="./layout/autosuggest.css" />
    <link rel="stylesheet" type="text/css" href="./layout/searchPage.css" />
    <script type="text/javascript" src="./layout/autosuggest.js"></script>
    <script type="text/javascript" src="./layout/suggestions.js"></script>
    <script src="./layout/jquery-3.1.0.min.js"></script>
    <script src ="./layout/blur.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            var oTextbox = new AutoSuggestControl(document.getElementById("txt"),new GoogleSuggestions()); 
        }
    </script>
</head>
<body>
    <div class = "background">
    <h1> Search Item by Keyword </h1>
	<div>
		<form  action = "/eBay/search" method = "GET">
			<input type = "text" id="txt" name = "q" autocomplete = "off"/>
			<input type = "submit" value = "Submit"/>
			<input type = "hidden" name = "numResultsToSkip" value = "0"/>
			<input type = "hidden" name = "numResultsToReturn" value = "20"/>
		</form>
	</div>
	<div>
		<%@ page import="edu.ucla.cs.cs144.SearchResult" %>
		<%
		String q = request.getParameter("q");
        String toSkip = request.getParameter("numResultsToSkip");
        String toReturn = request.getParameter("numResultsToReturn");
        int numResultsToSkip = (toSkip != null)? Integer.parseInt(toSkip):0;
        int numResultsToReturn = (toReturn != null)? Integer.parseInt(toReturn):0;
		SearchResult[] result = (SearchResult[]) request.getAttribute("result");
		%>
        <% if(q == null || ("".equals(q))){ %>
	        <h1>Please type in the above block to search.<h1>
        <% } else { %>
		<% if(result.length==0){ %>
        <h1> There is no search result for <%= q %> </h1>
        <%} else { %>

		<h1> Search results for <%= q %> from <%= numResultsToSkip+1 %> to <%= numResultsToSkip+((result.length == numResultsToReturn)?numResultsToReturn:result.length) %></h1>
		<%
		for(SearchResult tmp:result){
		%>
		<br>
		<a href = "/eBay/item?id=<%= tmp.getItemId() %> " > <%= tmp.getItemId()%> </a> : <%= tmp.getName() %>
		<%}%>
		<br>
        <%
        if(numResultsToSkip == 0){%>
            &lt First Page &gt  &lt Previous Page &gt
        <%}
        else {
        	int previousNumResultsToSkip = numResultsToSkip - numResultsToReturn;
        	if(previousNumResultsToSkip < 0)	previousNumResultsToSkip = 0;
        %>
            <a href="/eBay/search?q=<%= q %>&numResultsToSkip=0&numResultsToReturn=<%=numResultsToReturn %>">&lt First Page &gt</a>
            <a href="/eBay/search?q=<%= q %>&numResultsToSkip=<%= previousNumResultsToSkip %>&numResultsToReturn=<%= numResultsToReturn %>">&lt Previous Page &gt</a>
        <%}%>

        <%
        if(result.length != numResultsToReturn){%>
            &lt Next Page &gt
        <%}
        else{
        %>
        <a href="/eBay/search?q=<%= q %>&numResultsToSkip=<%= numResultsToSkip + numResultsToReturn %>&numResultsToReturn=<%= numResultsToReturn %>">&lt Next Page &gt</a>
        
        <%}}}%>

	</div>
    </div>
</body>
</html>

