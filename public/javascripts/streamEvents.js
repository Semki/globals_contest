$(document).ready(function () {
	
	initFilter();
	
	requestData(getFilterObject());
	
	$("#apply_filter").click(function () {
		requestData(getFilterObject());	
	});
	
	$("#clear_filter").click(function () {
		clearFilter();
	});
	
	$("#real_time_mode_checkbox").removeAttr("checked");
	
	$("#real_time_mode_checkbox").click(function () {
		if (this.checked)
		{
			$("#filter_tr").hide();
			$('#buttons_cell').hide();
		}
		else
		{
			$('#filter_tr').show();
			$('#buttons_cell').show();
		}
		
	});
	
});

clearFilter = function () {
	$('#element_type').val('');
    $('#element_id').val('');
    $('#element_class').val('');
    $('#datepicker1').val('');
    $('#datepicker2').val('');
};

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
		    "createdAtStart":$('#datepicker1').val(),
		    "createdAtFinish":$('#datepicker2').val()};
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