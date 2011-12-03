$(document).ready(function() {
	$('body').delegate('*', 'click', function (e){
		e.stopPropagation();
		
		var c = e.currentTarget;
		var eventObject = {"eventType": "click", 
				           "elementType": c.tagName, 
				           "mouseX": e.pageX, 
				           "mouseY": e.pageY, 
				           "elementId": c.id, 
				           "elementClass": c.className };
		
		$.post("/Application/addEvent", eventObject, function(data) {
	        
	    });
	});
});