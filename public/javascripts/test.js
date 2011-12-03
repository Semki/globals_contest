$(document).ready(function() {
	$('body').delegate('*', 'click', function (e){
		e.stopPropagation();
		alert("id:" + e.currentTarget.id + " class:" + e.currentTarget.className + " content:" + e.currentTarget.innerText)
	});
});