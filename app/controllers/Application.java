package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	ClickStreamEvent event = new ClickStreamEvent();
    	event.EventType = "Say Hello!";
    	event.CreatedOn = new Date();
    	event.ElementType = "HTMLDate";
    	event.Save();
        render();
    }

}