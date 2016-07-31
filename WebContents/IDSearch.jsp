<%@ page import="edu.ucla.cs.cs144.SearchResult" %>
<%@ page import="edu.ucla.cs.cs144.Item" %>
<%@ page import="edu.ucla.cs.cs144.Bid" %>
<%@ page import="java.util.*" %>
<% 
	String q = request.getParameter("id");
	Item item = (Item)request.getAttribute("item");
	String itemID, name, currently, first_Bid, number_of_Bids, location, latitude, longitude, country, started, ends, sellerID, description, buy_Price, rating;
	ArrayList<String> item_Categories;
	ArrayList<Bid> bids;
	if(item != null){
		itemID = item.getItemID();
		name = item.getName();
		currently= item.getCurrently();
		first_Bid = item.getFirstBid();
		number_of_Bids = item.getNumberOfBids();
		location = item.getLocation();
		latitude = item.getLatitude();
		longitude = item.getLongitude();
		country = item.getCountry();
		started = item.getStarted();
		ends = item.getEnds();
		sellerID = item.getSellerID();
		description = item.getDescription();
		buy_Price = item.getBuyPrice();
		rating = item.getRating();
		item_Categories = item.getItemCategories();
		bids = item.getBids();
	}
	else{
		itemID = null;
		name = null;
		currently= null;
		first_Bid = null;
		number_of_Bids = null;
		location = null;
		latitude = null;
		longitude = null;
		country = null;
		started = null;
		ends = null;
		sellerID = null;
		description = null;
		buy_Price = null;
		rating = null;
		item_Categories = null;
		bids = null;	
	}
%>
<html>
<head>
    <title>Item Information</title>
    <link rel="stylesheet" type="text/css" href="./layout/searchPage.css" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script src="./layout/jquery-3.1.0.min.js"></script>
    <script src ="./layout/blur.js"></script>
	<script>

		$(document).ready(function(){
		    $("#switchMap").click(function(){
		        $("#map").toggle(function(){
		        	if($("#switchMap").html() == "Hide Map")
		        		$("#switchMap").html("Show Me the Map");
		        	else
		        		$("#switchMap").html("Hide Map");
		        });
		    });
		});

	</script>

    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?callback=initMap"
    async defer></script>
    <script type="text/javascript">
	var geocoder;
	var map;
	function initMap() {
		    
	    	geocoder = new google.maps.Geocoder();
			var address = "<%= location %>";
			var country = "<%= country %>";
			address = address + ", " + country;					
	    	geocoder.geocode( { 'address': address}, function(results, status) {
	      	if (status == google.maps.GeocoderStatus.OK) {
		    var mapOptions = {
	    	  zoom: 8,
	    	  center: results[0].geometry.location
	    	};
		    map = new google.maps.Map(document.getElementById("map"), mapOptions);
	        map.setCenter(results[0].geometry.location);
	        var marker = new google.maps.Marker({
	            map: map,
	            position: results[0].geometry.location
	        });
	      } 
	      else{
	      	var latlng = new google.maps.LatLng(34.063509,-118.44541);
		    var mapOptions = {
	    	  zoom: 1,
	    	  center: latlng
	    	};
		    map = new google.maps.Map(document.getElementById("map"), mapOptions);
	        map.setCenter(latlng);
	        var marker = new google.maps.Marker({
	            map: map,
	            position: latlng
	        });	      	
	      }
	    });
  	}



    </script>
    
</head>
<body>
    <div class = "background">
	<h1> Search Item by ID </h1>
	<div>
		<form  action = "/eBay/item" method = "GET">
			Enter ID: <input type = "text" name = "id"/>
			<input type = "submit" value = "Submit"/>
		</form>
	</div>
	<%if((item == null) &&(q == null || !("".equals(q)))) {%>
    <h2>No result found.</h2>
	<%} else if(item == null){ %>
    <h2>Please type in the above block to get item information.</h2>
    <%}%>
	<div>
		<% String id = request.getParameter("id"); %>

        <%if(item != null){ %>
		<h2> Item Information:</h2>
		<p>Item ID: <%= id %></p>

		<p>Item Name: <%= name %></p>
		<p>Current Highest Bid: <%= currently %></p>
		<p>Minimum Qualifying First Bid Amount: <%= first_Bid %></p>
		<p>Location: <%= location %></p>
		<%if(latitude != null && !("".equals(latitude))){%>
		<p>Latitude: <%= latitude %></p>
		<%}
		if(latitude != null && !("".equals(latitude))){%>
		<p>Longitude: <%= longitude %></p>
		<%}%>
		<p>Country: <%= country %></p>
		<button id="switchMap" style = "background-color:#D1F3C0">Hide Map</button>
		<div id = "map"></div>
		<br>
		<p>Auction Start Time: <%= started %></p>
		<p>Auction End Time: <%= ends %></p>
		<p>Seller ID: <%= sellerID %></p>
		<p>Seller's Rating: <%= rating %></p>
		<p>Description of Item: <%= description %></p>
		<p>Number of Bids: <%= number_of_Bids %></p>
		<p>Buy Price: <%= buy_Price %></p>
		<%if(item_Categories.size() != 0 && !("".equals(item_Categories.get(0)))){%>
		<p> Item Categories:
		<%
		for(int i = 0; i < item_Categories.size()-1; i++){
			 String cur = item_Categories.get(i); 
		%>
			<%= cur %>,
			<% }
			int i = item_Categories.size()-1;
			String cur = item_Categories.get(i); 
			%>
			<%= cur %>
			<%} %></p>
		<%if(bids != null && bids.size() != 0){ %>
		<p> Bid Information:</p>
		<table id="bid" border = "1">
			<tr>
				<th>Bidder ID</th>
				<th>Bidder Rating</th>
				<th>Bid Time</th>
				<th>Bid Amount</th>
				<th>Bidder Location</th>
				<th>Bidder Country</th>
			</tr>
		<%
		for(int i = bids.size()-1; i >= 0; i--){
			String bidderID = bids.get(i).getBidderID(); 
			String bidderRating = bids.get(i).getRating(); 
			String bidderLocation = bids.get(i).getLocation(); 
			String bidderCountry = bids.get(i).getCountry(); 
			String bidTime = bids.get(i).getTime(); 
			String bidAmount = bids.get(i).getAmount(); 
		%>
		<tr>
			<td><%= bidderID %></td>
			<td><%= bidderRating %></td>
			<td><%= bidTime %></td>
			<td><%= bidAmount %></td>

			<%
			if(bidderLocation != null && !("".equals(bidderLocation))){%>
			<td><%= bidderLocation %></td>
			<td><%= bidderCountry %></td>
			<%}%>	
		</tr>	
		<% }} %>
		</table>
		<br><br>
	<%}%>
	</div>
	</div>
</body>
</html>