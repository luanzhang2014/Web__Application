
/**
 * Provides suggestions from Google suggest.
 * @class
 * @scope public
 */
function GoogleSuggestions() {

}

/**
 * Request suggestions for the given autosuggest control. 
 * @scope protected
 * @param oAutoSuggestControl The autosuggest control to provide suggestions for.
 */
GoogleSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/) {
    var aSuggestions = [];
    var sTextboxValue = oAutoSuggestControl.textbox.value;
    
    if (sTextboxValue != ""){
        var xHttp = new XMLHttpRequest();

        var request = "/eBay/suggest?q="+encodeURI(sTextboxValue);
    
        xHttp.open("GET", request, true); 
        xHttp.onreadystatechange=function()
        {
            if (xHttp.readyState == 4 && xHttp.status == 200) 
         {
                  // get the CompleteSuggestion elements from the response
                  if(xHttp.responseXML!=null)
                  {
                   var s = xHttp.responseXML.getElementsByTagName('CompleteSuggestion');
                 
                  // construct a suggestion array
                  for (var i = 0; i < s.length; i++) 
                  {
                    var text = s[i].childNodes[0].getAttribute("data");
                
                    aSuggestions.push(text);
                  }
                  //provide suggestions to the control

                  oAutoSuggestControl.autosuggest(aSuggestions);
              }
        }
       }
       xHttp.send();

    }
    else
    {
      oAutoSuggestControl.hideSuggestions();
    }
        


};