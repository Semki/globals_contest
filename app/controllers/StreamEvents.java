package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.*;

public class StreamEvents extends Controller {
	
	public static void index() {
		render();
	}
}
