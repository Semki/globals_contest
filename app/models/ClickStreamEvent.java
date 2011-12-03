package models;

import core.Persistent;

public class ClickStreamEvent extends Persistent {
	public String eventType;
	public String elementType;
	public String mouseX;
	public String mouseY;
	public String elementId;
	public String elementClass;
	public String timeStamp;
	
	public String sessionId;
	
}
