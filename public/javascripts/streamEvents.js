$(document).ready(function () {
	
	
	$.get("streamevents/find", null,function (data) {
		iterateData(data);
	}, "json");
	
});


iterateData = function (data) {
	for (var i in data)
	{
		$("#events_table").append('<tr></tr>');
		$("#events_table tr:last-child").append('<td>' + data[i].Name + '</td');
		$("#events_table tr:last-child").append('<td>' + data[i].elementType + '</td');
		$("#events_table tr:last-child").append('<td>' + data[i].CreatedOn + '</td');
	}
};