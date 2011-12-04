package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;
import support.LogWriter;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
	
	public static void Find(JsonObject body)
	{
		
		JsonObject obj = body;
		JsonElement element = null;
		//System.out.println("body = "+ body + "obj " + obj);
		ArrayList<ClickStreamEvent> array = new ArrayList<ClickStreamEvent>();
		DataFinder finder = new DataFinder(ClickStreamEvent.class).OrderByIdDesc();
		try 
		{
			//System.out.println("obi is arr = "+ obj.isJsonArray()+ " and is obj " + obj.isJsonObject());
			
			if (!obj.isJsonObject())
				return;
			
			element = obj.get("elementType");
			if (element != null && !element.getAsString().isEmpty())
			{
					
				finder = finder.Where("elementType", ConditionTypes.Equals, element.getAsString());
				System.out.println("obj.get('elementType').toString() "+ obj.get("elementType").toString());
			}
			
			element = obj.get("elementId");
			if (element != null && !element.getAsString().isEmpty())
			{
					
				finder = finder.Where("elementId", ConditionTypes.Equals, element.getAsString());
				System.out.println("obj.get('elementType').toString() "+ obj.get("elementType").toString());
			}
			
			element = obj.get("elementClass");
			if (element != null && !element.getAsString().isEmpty())
			{
					
				finder = finder.Where("elementClass", ConditionTypes.Equals, element.getAsString());
				System.out.println("obj.get('elementClass').toString() "+ obj.get("elementClass").toString());
			}
			
			element = obj.get("created_at_start");
			if (element != null && !element.getAsString().isEmpty())
			{
					
				finder = finder.Where("CreatedOn", ConditionTypes.Equals, element.getAsString());
				System.out.println("obj.get('created_at_start').toString() "+ obj.get("created_at_start").toString());
			}
			
			element = obj.get("creted_at_finish");
			if (element != null && !element.getAsString().isEmpty())
			{
				finder = finder.Where("CreatedOn", ConditionTypes.Equals, element.getAsString());
				System.out.println("obj.get('creted_at_finish').toString() "+ obj.get("creted_at_finish").toString());
			}
			

			ClickStreamEvent event = null;
			while (true) 
			{
				event = (ClickStreamEvent) finder.Next();
				if (event == null)
					break;
				array.add(event);
			} 
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if (array.size() == 0)
		{
		
			renderJSON("[]");
			return;
		}
		renderJSON(array);
		
	}
}
