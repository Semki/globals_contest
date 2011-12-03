$(document).ready(function () {
	

	
	//$.datepicker.setDefaults({ dateFormat: 'yy-mm-dd' });
	$("#datepicker1").datetimepicker({dateFormat: 'yy-mm-dd'});
	$("#datepicker2").datetimepicker({dateFormat: 'yy-mm-dd'});
	
	
	requestData({});
	
	$("#apply_filter").click(function () {
		var filter = {"elementType": $('#element_type').val(), "elementId": $('#element_id').val(), "elementClass": $('#element_class').val(),
				"created_at_start":$('#datepicker1').val(),
				"created_at_finish":$('#datepicker2').val()};
		
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