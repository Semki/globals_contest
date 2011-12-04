package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;
import support.LogWriter;

import java.lang.reflect.Field;
import java.util.*;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import core.ClickStreamFilter;
import core.DataFinder;
import core.DataFinder.ConditionTypes;
import core.DataWorker;

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
		ClickStreamFilter filter = new Gson().fromJson(body, ClickStreamFilter.class);

		ArrayList<ClickStreamEvent> array = new ArrayList<ClickStreamEvent>();
		DataFinder finder = new DataFinder(ClickStreamEvent.class).OrderByIdDesc();
		try 
		{
			if (!filter.elementType.isEmpty())
			{
				finder.Where("elementType", ConditionTypes.Equals, filter.elementType);
			}
			
			if (!filter.elementId.isEmpty())
			{
				finder.Where("elementId", ConditionTypes.Equals, filter.elementId);
			}
			
			if (!filter.elementClass.isEmpty())
			{
				finder.Where("elementClass", ConditionTypes.Equals, filter.elementClass);
			}
			
			if (!filter.createdAtStart.isEmpty())
			{
				finder.Where("CreatedOn", ConditionTypes.GreaterOrEqual, filter.createdAtStart.substring(0, filter.createdAtStart.length()-1).concat(":00"));
			}
			
			if (!filter.createdAtFinish.isEmpty())
			{
				finder.Where("CreatedOn", ConditionTypes.LessOrEqual, filter.createdAtFinish.substring(0, filter.createdAtFinish.length()-1).concat(":59"));
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
	
	public static void getAddedEvents(JsonObject body) 
	{
		String sid = body.getAsJsonPrimitive("Id").getAsString();  
		long id = Long.parseLong(sid);
		DataFinder finder = new DataFinder(ClickStreamEvent.class);
		ArrayList<Long> ids = finder.getAddedIdsSinceId(id);
		
		ArrayList<ClickStreamEvent> results = new ArrayList<ClickStreamEvent>();
		
		for (int i = 0; i<ids.size(); i++)
		{
			try 
			{
				ClickStreamEvent event = new ClickStreamEvent();
				event = (ClickStreamEvent) DataWorker.Instance().LoadObjectById(ids.get(i), event);
				results.add(event);
			} 
			catch (IllegalAccessException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		if (results.size() == 0)
		{
		
			renderJSON("[]");
			return;
		}
		renderJSON(results);
	}
	
	
	public static void getTopTenElements() 
	{
		DataFinder finder = new DataFinder(ClickStreamEvent.class);
		
		List<ElementCount> results = finder.getIndexValueCounts("elementIdIndex", 10);
		
		if (results.size() == 0)
		{
		
			renderJSON("[]");
			return;
		}
		renderJSON(results);
	}
	
}
