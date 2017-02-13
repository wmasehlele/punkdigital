$(function(){
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
			success: function( result ) {
				alert("Your signature is ready for download");				
				$('#download').show();
			},error: function( result ) {
				alert("Sorry an error occured. Please try again");	
			}
		});    	    	
    });
});