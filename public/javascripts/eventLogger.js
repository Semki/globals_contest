$(document).ready(function() {
	$('body').delegate('*', 'click', function (e){
		e.stopPropagation();
		
		var c = e.currentTarget;
		
		// Don't fetch elements without class name and id
		if ((c.id == "") && (c.className == ""))
		{
			return;
		}
		var eventObject = {"eventType": "click", 
				           "elementType": c.tagName, 
				           "mouseX": e.pageX, 
				           "mouseY": e.pageY, 
				           "elementId": c.id, 
				           "elementClass": c.className };
		
		$.post("/addEvent", JSON.stringify(eventObject), function(data) {
	        
	    }, "json");
	});
});

JSON.stringify = JSON.stringify || function (obj) {
    var t = typeof (obj);
    if (t != "object" || obj === null) {
        // simple data type
        if (t == "string") obj = '"'+obj+'"';
        return String(obj);
    }
    else {
        // recurse array or object
        var n, v, json = [], arr = (obj && obj.constructor == Array);
        for (n in obj) {
            v = obj[n]; t = typeof(v);
            if (t == "string") v = '"'+v+'"';
            else if (t == "object" && v !== null) v = JSON.stringify(v);
            json.push((arr ? "" : '"' + n + '":') + String(v));
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    }
};