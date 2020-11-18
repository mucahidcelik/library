$(document).ready(function() {
	console.log("asd");
	var y = document.getElementsByName("eBtn")[0];
	y.addEventListener("click", function(e) {
		console.log("asd1");
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(publisher, status){
			$('.myForm #id').val(publisher.publisherId);
			$('.myForm #publisherName').val(publisher.publisherName);
			$('.myForm #publisherDescription').val(publisher.publisherDescription);
		});
		
		$('.myForm #publisherModal').modal();
	});
});