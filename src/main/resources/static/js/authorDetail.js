$(document).ready(function() {
	
	var y = document.getElementsByName("eBtn")[0];
	y.addEventListener("click", function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(author, status){
			$('.myForm #id').val(author.authorId);
			$('.myForm #authorName').val(author.authorName);
			$('.myForm #authorDescription').val(author.authorDescription);
		});
		
		$('.myForm #authorModal').modal();
	});
});