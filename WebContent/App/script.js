
$(function() {
	$('#generate').click('click', function(event) {
    	event.preventDefault();
    	var postdata = {};
    	postdata.names = $('#names').val();
    	postdata.position = $('#position').val();
    	postdata.tell = $('#tell').val();
    	postdata.email = $('#email').val();
    	postdata.logoUrl = $('#logoUrl').val();
    	$.ajax({ 
			type: "POST",
			url: "SignatureServlet",
			data: postdata,
			dataType: 'application/json'
    	}).success(function(respdata){
console.log(respdata);    		
    	}).error(function(respdata){
    	});
    });
});

$(function() {
    $('#download').click('click', function(event) {
    	event.preventDefault();
    	$.ajax({
			type: "GET",
			url: "SignatureServlet",
    	}).success(function(respdata){
    	}).error(function(respdata){
    	});
    });
});