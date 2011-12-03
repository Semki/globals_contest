package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void addEvent(String json) {
    	
    	
    	ClickStreamEvent event = getObject(json);
    	event.Save();
    	
        //render();
    }
    
 
    public static ClickStreamEvent getObject(String json_string)
    {
    	Gson gson = new Gson();
    	ClickStreamEvent obj = gson.fromJson(json_string, ClickStreamEvent.class);
     	return obj;
    	
    }

}