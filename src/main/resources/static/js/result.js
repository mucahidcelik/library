$(document).ready(function() {
	var x = document.getElementsByName("eBtn");
	Array.prototype.forEach.call(x,element=>element.addEventListener("click", function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(book, status) {
			$('.myForm #id').val(book.id);
			$('.myForm #bookTitle').val(book.bookTitle);
			$('.myForm #bookSubTitle').val(book.bookSubTitle);
			$('.myForm #bookSerialName').val(book.bookSerialName);
			$('.myForm #bookDescription').val(book.bookDescription);
			//$('.myForm #authorId').val(book.author.authorId);
			//$('.myForm #publisherId').val(book.publisher.publisherId);
			$('.myForm #ISBN').val(book.isbn);
		});
		$('.myForm #bookModal').modal();
	}));
});