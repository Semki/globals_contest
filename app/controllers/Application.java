package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void addEvent(JsonObject body) {
    	
    	ClickStreamEvent e = handleJsonAsObject(body);
    	if (e== null)
    	{
    		//render("Empty!!!");
    		render(body);
    		// add error message
    		return;
    	}
    	e.SessionId = Session.current().getId();
   
    	e.Save(); 
    } 
  
    public static void handleJson(JsonObject body) 
    {        
    	renderText(body.getAsJsonPrimitive("name").getAsString());    
    }        
    
    public static ClickStreamEvent handleJsonAsObject(JsonObject body) 
    {        
    	ClickStreamEvent e = new Gson().fromJson(body, ClickStreamEvent.class);        
    	return e; 
    }

}