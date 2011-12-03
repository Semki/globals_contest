package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;
import support.LogWriter;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import core.DataFinder;
import core.DataFinder.ConditionTypes;

import models.*;

public class StreamEvents extends Controller {
	
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
	
		ArrayList<ClickStreamEvent> array = new ArrayList<ClickStreamEvent>();
		
		
			DataFinder finder;
			try 
			{
				
				//finder = new DataFinder(ClickStreamEvent.class).Where(obj.get("field").toString(), ConditionTypes.Equals, obj.get("fieldValue").toString());
				
				finder = new DataFinder(ClickStreamEvent.class);
				
				boolean notEnd;
				ClickStreamEvent event = null; 
				JsonObject element = null;
				LogWriter log = new LogWriter();
				
				while (true) 
				{
					event = new ClickStreamEvent();
					event.Name = "Test1";
					event.CreatedOn = new Date();
					event.elementType = "Button";
					array.add(event);
					
					
					event = new ClickStreamEvent();
					event.Name = "Test2";
					event.CreatedOn = new Date();
					event.elementType = "Div";
					array.add(event);
					
					break;
					
					//event = (ClickStreamEvent) finder.Next();
					//log.WriteToFile("Test", true);
					//if (event == null)
					//	break;
					//array.add(event);
				} 
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
		renderJSON(array);
		
	}
}
