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
	
	public static void Find(JsonObject body)
	{
		
		JsonObject obj = body;
		//System.out.println("body = "+ body + "obj " + obj);
		ArrayList<ClickStreamEvent> array = new ArrayList<ClickStreamEvent>();
		DataFinder finder = new DataFinder(ClickStreamEvent.class).OrderByIdDesc();
		try 
		{
			//System.out.println("obi is arr = "+ obj.isJsonArray()+ " and is obj " + obj.isJsonObject());
			
			if (!obj.isJsonObject())
				return;
			/*
			Boolean found = false;
			if (obj.get("elementType") != null)
			{
					
					finder = finder.Where("elementType", ConditionTypes.Equals, obj.get("elementType").getAsString());
					System.out.println("obj.get('elementType').toString() "+ obj.get("elementType").toString());
			}
			
			if (obj.get("elementId") != null)
			{
					
					finder = finder.Where("elementId", ConditionTypes.Equals, obj.get("elementId").getAsString());
					System.out.println("obj.get('elementId').toString() "+ obj.get("elementId").toString());
			}
			
			if (obj.get("elementClass") != null)
			{
					
					finder = finder.Where("elementClass", ConditionTypes.Equals, obj.get("elementClass").getAsString());
					System.out.println("obj.get('elementType').toString() "+ obj.get("elementType").toString());
			}
			
			if (obj.get("created_at_start") != null)
			{
					
					finder = finder.Where("CreatedOn", ConditionTypes.GreaterOrEqual, obj.get("created_at_start").getAsString());
					System.out.println("obj.get('elementType').toString() "+ obj.get("elementType").toString());
			}
			
			if (obj.get("creted_at_finish") != null)
			{
					
					finder = finder.Where("CreatedOn", ConditionTypes.LessOrEqual, obj.get("created_at_finish").getAsString());
					
			}*/
			
			
			
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
