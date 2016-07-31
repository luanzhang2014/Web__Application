package edu.ucla.cs.cs144;
import java.io.*;
import java.text.*;
import java.util.*;

public class Item {

public Item(String anItemID, String aName, String aCurrently, String aFirst_Bid, 
	String aNumber_of_Bids, String aLocation, String aLatitude, String aLongitude, 
	String aCountry, String aStarted, String anEnds, String aSellerID,String aDescription, 
	ArrayList<String> Item_Categories, String aBuy_Price, String aRating, ArrayList<Bid> Bids){
	this.anItemID = anItemID;
	this.aName = aName;
	this.aCurrently = aCurrently;
	this.aFirst_Bid = aFirst_Bid;
	this.aNumber_of_Bids = aNumber_of_Bids;
	this.aLocation = aLocation;
	this.aLatitude = aLatitude;
	this.aLongitude = aLongitude;
	this.aCountry = aCountry;
	this.aStarted = aStarted;
	this.anEnds = anEnds;
	this.aSellerID = aSellerID;
	this.aDescription = aDescription;
	this.Item_Categories = Item_Categories;
	this.aBuy_Price = aBuy_Price;
	this.aRating = aRating;
	this.Bids = Bids;
}

public String getItemID(){
	return anItemID;
}

public String getName(){
	return aName;
}

public String getCurrently(){
	return aCurrently;
}

public String getFirstBid(){
	return aFirst_Bid;
}

public String getNumberOfBids(){
	return aNumber_of_Bids;
}

public String getLocation(){
	return aLocation;
}

public String getLatitude(){
	return aLatitude;
}

public String getLongitude(){
	return aLongitude;
}

public String getCountry(){
	return aCountry;
}

public String getStarted(){
	return aStarted;
}

public String getEnds(){
	return anEnds;
}

public String getSellerID(){
	return aSellerID;
}

public String getDescription(){
	return aDescription;
}

public ArrayList<String> getItemCategories(){
	return Item_Categories;
}

public String getBuyPrice(){
	return aBuy_Price;
}

public String getRating(){
	return aRating;
}

public ArrayList<Bid> getBids(){
	return Bids;
}

private String anItemID; 
private String aName;
private String aCurrently;
private String aFirst_Bid; 
private String aNumber_of_Bids; 
private String aLocation;
private String aLatitude; 
private String aLongitude; 
private String aCountry;
private String aStarted; 
private String anEnds;
private String aSellerID;
private String aDescription;	
private ArrayList<String> Item_Categories;
private String aBuy_Price;
private String aRating;
private ArrayList<Bid> Bids;
}


