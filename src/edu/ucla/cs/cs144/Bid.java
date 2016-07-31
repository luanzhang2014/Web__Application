package edu.ucla.cs.cs144;
public class Bid {

public Bid(String aBidderID, String aRating, String aLocation, String aCountry, String aTime, String anAmount){
	this.aBidderID = aBidderID;
	this.aRating = aRating;
	this.aLocation = aLocation;
	this.aCountry = aCountry;
	this.aTime = aTime;
	this.anAmount = anAmount;
}

public String getBidderID(){
	return aBidderID;
}

public String getRating(){
	return aRating;
}

public String getLocation(){
	return aLocation;
}

public String getCountry(){
	return aCountry;
}

public String getTime(){
	return aTime;
}

public String getAmount(){
	return anAmount;
}
private String aBidderID;
private String aRating;
private String aLocation;
private String aCountry;
private String aTime;
private String anAmount;
}