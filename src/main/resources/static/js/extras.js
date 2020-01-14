$(function() {
	var $messageTemplate = $('<li class="list-group-item"><div class="row"><div class="col-md-2 date"></div><div class="col-md-2 name"></div><div class="col-md-8 message"></div></div></li>');

	$('#saveGuestbookEntryBtn').on('click', function() {
		if ($('#protection').val() === '') {
			$.ajax({
				method: 'GET',
				url: '/extras/saveGuestbookMessage',
				data: {
					name: $('#name').val(),
					message: $('#message').val(),
					privateMessage: $('#privateMessage').is(':checked')
				}
			}).done(function(guestbookMessage) {
				var $guestbookMessages = $('#guestbookMessages');
				var $message = $messageTemplate.clone()
				$message
					.find('.date').text(guestbookMessage.dateCreatedWithoutTime).end()
					.find('.name').text(guestbookMessage.name).end()
					.find('.message').text(guestbookMessage.message).end();
				
				if (guestbookMessage.privateMessage) {
					$message
						.find('.message').parent().prepend($('<div class="col-12">PRIVATE</div>')).end().end()
						.addClass('alert-warning')
						.css('backgroundColor', '#fff3cd');
				}
				
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
	
	common.getImageFromS3('accomodations/caribe-royale.jpg', $('#caribe-royale-image'), 'Caribe Royale', 'card-img-top');
	common.getImageFromS3('accomodations/buena-vista-suites.jpg', $('#buena-vista-suites-image'), 'Buena Vista Suites', 'card-img-top');
	common.getImageFromS3('accomodations/days-hotel.jpg', $('#days-hotel-image'), 'Days Hotel by Wyndham Celebration', 'card-img-top');
	
	common.getImageFromS3('gallery/engagement-photos/engagement-photo.jpg', $('#engagement-photos'), 'Engagement Photos', 'rounded img-thumbnail');
	(function getPhotoGallery() {
		$.ajax({
			method: 'GET',
			url: '/extras/getPhotoGallery'
		}).done(function(photoGallery) {
			var $gallery = $('#photos .photo-gallery .row');
			$.each(photoGallery, function(thumbnailLink, imageLink) {
				var $photoTemplate = $('<div class="col-sm-6 col-md-4"><div class="thumbnail"><a class="lightbox"><img class="rounded img-thumbnail"></a></div></div>');
				$photoTemplate
					.find('a.lightbox').attr('href', imageLink).end()
					.find('img.img-thumbnail').attr('src', thumbnailLink).end();
				$gallery.append($photoTemplate);
			});
			
			baguetteBox.run('.photo-gallery');
		}).fail(function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		});
	}());
	
	common.getImageFromS3('things-to-do/disney-world.jpg', $('#disney'), 'Cinderella\'s Castle at Magic Kingdom', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/the-void.jpg', $('#void'), 'The VOID at Disney Springs', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/hoop-dee-doo.jpg', $('#hoop-dee-doo'), 'Hoop Dee Doo', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/universal.jpg', $('#universal'), 'Universal Studios', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/seaworld.jpg', $('#seaworld'), 'Seaworld', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/discovery-cove.jpg', $('#discovery-cove'), 'Discovery Cove', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/winter-park-boat-tour.jpg', $('#winter-park-boat-tour'), 'Winter Park Boat Tour', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/daytona-beach.jpg', $('#daytona-beach'), 'Daytona Beach', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/cocoa-beach.jpg', $('#cocoa-beach'), 'Cocoa Beach', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/ponce-inlet-lighthouse.jpg', $('#ponce-inlet-lighthouse'), 'Ponce Inlet Lighthouse', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/orlando-city-soccer.jpg', $('#orlando-city-soccer'), 'Orlando City Soccer', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/holy-land-experience.jpg', $('#holy-land-experience'), 'Holy Land Experience', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/legoland.jpg', $('#legoland'), 'Legoland', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/orlando-escape-game.jpg', $('#orlando-escape-game'), 'Orlando Escape Game', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/black-hammock-airboat.jpg', $('#black-hammock-airboat'), 'Black Hammock Airboat', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/margaritaville.jpg', $('#margaritaville'), 'Margaritaville', 'rounded img-thumbnail', true);
	common.getImageFromS3('things-to-do/celebration.jpg', $('#celebration'), 'Celebration Town Center', 'rounded img-thumbnail', true);
	
	$('#private-message-info').tooltip();
});
