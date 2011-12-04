$(document).ready(function () {
	
	initFilter();
	
	requestData(getFilterObject());
	
	$("#apply_filter").click(function () {
		requestData(getFilterObject());	
	});
	
});

initFilter = function () {
	$("#datepicker1").datetimepicker({dateFormat: 'yy-mm-dd'});
	$("#datepicker2").datetimepicker({dateFormat: 'yy-mm-dd'});
};

requestData = function (filter) {
	$.post("streamevents/find", JSON.stringify(filter),function (data) {
		iterateData(data);
	}, "json");
};

getFilterObject = function () {
	return {"elementType": $('#element_type').val(), 
		    "elementId": $('#element_id').val(), 
		    "elementClass": $('#element_class').val(),
		    "created_at_start":$('#datepicker1').val(),
		    "created_at_finish":$('#datepicker2').val()};
};


iterateData = function (data) {
	$('#events_table').find("tr:gt(0)").remove();
	for (var i in data)
	{
		$("#events_table").append('<tr></tr>');
		$("#events_table tr:last-child").append('<td>' + data[i].elementId + '</td');
		$("#events_table tr:last-child").append('<td>' + data[i].elementClass + '</td');
		$("#events_table tr:last-child").append('<td><span class="gray">&lt;</span>' + data[i].elementType + '<span class="gray">&gt;</span></td');
		$("#events_table tr:last-child").append('<td>' + data[i].CreatedOn + '</td');
	}
};