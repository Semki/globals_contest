$(document).ready(function () {
	
	$("#time2").timePicker({
		  startTime: "02.00", // Using string. Can take string or Date object.
		  endTime: new Date(0, 0, 0, 15, 30, 0), // Using Date object here.
		  show24Hours: false,
		  separator: '.',
		  step: 15});
	
	
	$( "#datepicker" ).datepicker();
	
	
	requestData(null);
	
	$("#apply_filter").click(function () {
		var filter = {"elementType": $('#element_type').val(), "elementId": $('#element_id').val(), "elementClass": $('#element_class').val()};
		
		requestData(filter);
		
	});
	
});

requestData = function (filter) {
	$.post("streamevents/find", JSON.stringify(filter),function (data) {
		iterateData(data);
	}, "json");
};


iterateData = function (data) {
	$('#events_table').html('');
	for (var i in data)
	{
		$("#events_table").append('<tr></tr>');
		$("#events_table tr:last-child").append('<td>' + data[i].Name + '</td');
		$("#events_table tr:last-child").append('<td>&lt;' + data[i].elementType + '&gt;</td');
		$("#events_table tr:last-child").append('<td>' + data[i].CreatedOn + '</td');
	}
};