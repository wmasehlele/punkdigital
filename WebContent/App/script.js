$(function() {
	$('#badnews').hide();
	$('#goodnews').hide();
	$('#download').hide();
});
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
    		if (respdata.status){
    			$('#goodnews').show();
    			$('#download').show();
    		} else {
    			$('#badnews').show();
    		}
    	}).error(function(respdata){
    		$('#badnews').show();
    	});
    });
});

$(function() {
    $('#download').click('click', function(event) {
    	event.preventDefault();
    	$.ajax({
			type: "GET",
			url: "/webcontacts/api-contact-yeeperk",
			data: postdata,
			dataType: 'application/json'
    	}).success(function(respdata){

    	}).error(function(respdata){

    	});
    });
});