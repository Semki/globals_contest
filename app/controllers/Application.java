package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import net.spy.memcached.ops.ConcatenationType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import core.DataFinder;
import core.DataFinder.ConditionTypes;

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
    	e.sessionId = Session.current().getId();
   
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
    
    public static void Find(JsonObject obj)
    {
    
    
    	try {
			DataFinder finder = new DataFinder(ClickStreamEvent.class).Where(obj.get("field").toString(), ConditionTypes.Equals, obj.get("fieldValue").toString());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    }
    

}