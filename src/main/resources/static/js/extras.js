$(function() {
	var $messageTemplate = $('<li class="list-group-item"><div class="row"><div class="col-md-2 date"></div><div class="col-md-2 name"></div><div class="col-md-8 message"></div></div></li>');
	
	$('#saveGuestbookEntryBtn').on('click', function() {
		if ($('#protection').val() === '') {
			$.ajax({
				method: 'GET',
				url: '/extras/saveGuestbookMessage',
				data: {
					name: $('#name').val(),
					message: $('#message').val()
				}
			}).done(function(guestbookMessage) {
				var $guestbookMessages = $('#guestbookMessages');
				var $message = $messageTemplate.clone()
				$message
					.find('.date').text(guestbookMessage.dateCreatedWithoutTime).end()
					.find('.name').text(guestbookMessage.name).end()
					.find('.message').text(guestbookMessage.message).end();
				
				$guestbookMessages.find('ul').prepend($message);
				
				if ($guestbookMessages.hasClass('d-none')) {
					$guestbookMessages.removeClass('d-none');
				}
				
				console.log(guestbookMessage);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			});
		}
	});
	
	baguetteBox.run('.photo-gallery');
});