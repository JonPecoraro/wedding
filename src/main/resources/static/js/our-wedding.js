$(function() {
	var $weddingPartySection = $('#party');
	var $weddingPartyTemplate =  $('#party .template');
	
	function buildWeddingPartyPair(photoMap, personKey1, personKey2, fullName1, fullName2, role1, role2, blurb1, blurb2) {
		var key1 = 'img/wedding-party/' + personKey1 + '-scaled.jpg';
		var key2 = 'img/wedding-party/' + personKey2 + '-scaled.jpg';
		
		var $weddingPartyPair = $weddingPartyTemplate.clone();
		$weddingPartyPair
			.find('.card:first')
			.find('img').attr('src', decodeURIComponent(photoMap[key1])).attr('alt', fullName1 + ' - ' + role1).end()
			.find('h3.card-title').text(fullName1).end()
			.find('h4.card-text').text(role1).end()
			.find('p.card-text').text(blurb1).end()
			.end()
			.find('.card:last')
			.find('img').attr('src',decodeURIComponent(photoMap[key2])).attr('alt', fullName2 + ' - ' + role2).end()
			.find('h3.card-title').text(fullName2).end()
			.find('h4.card-text').text(role2).end()
			.find('p.card-text').text(blurb2).end()
			.end()
			.removeClass('template d-none');
		
		$weddingPartySection.append($weddingPartyPair);
	}
	
	// Get wedding party photos
	$.ajax({
		method: 'GET',
		url: '/our-wedding/getWeddingParty'
	}).done(function(photoMap) {
		buildWeddingPartyPair(photoMap, 'vince', 'alyssa', 'Vince Pecoraro', 'Alyssa Will Zegers', 'Best Man', 'Matron of Honor', 'Vince is Jonathan\'s brother.', 'Alyssa is Ashley\'s sister.');
		buildWeddingPartyPair(photoMap, 'jason', 'alex', 'Jason Klemka', 'Alex Hays', 'Groomsman', 'Bridesmaid', 'Jason is Jonathan\'s long time friend.', 'Alex is Ashley\'s college friend and college room mate.');
		buildWeddingPartyPair(photoMap, 'russ', 'rainey', 'Russ Anderson', 'Rainey Anderson', 'Groomsman', 'Bridesmaid', 'Russ is Jonathan\'s long time friend.', 'Rainey is a good friend of Ashley.');
		buildWeddingPartyPair(photoMap, 'murray', 'robin', 'Murray White IV', 'Robin White', 'Groomsman', 'Bridesmaid', 'Murray is Jonathan\'s brother-in-law.', 'Robin is Jonathan\'s sister.');
		buildWeddingPartyPair(photoMap, 'devin', 'amber', 'Devin Holmes', 'Amber Pecoraro', 'Groomsman', 'Bridesmaid', 'Devin is Jonathan\'s long time friend.', 'Amber is Jonathan\'s sister-in-law.');
		buildWeddingPartyPair(photoMap, 'jonathan', 'kate', 'Jonathan Zegers', 'Katelyn Crush', 'Groomsman', 'Bridesmaid', 'Jonathan is Ashley\'s brother-in-law.', 'Katelyn is Ashley\'s high school friend.');
		buildWeddingPartyPair(photoMap, 'matt', 'stephanie', 'Matt Gaines', 'Stephanie Sickbert', 'Groomsman', 'Bridesmaid', 'Matt is Jonathan\'s long time friend.', 'Stephanie is Ashley\'s cousin.');
		buildWeddingPartyPair(photoMap, 'murray5', 'fiona', 'Murray White V', 'Fiona Pecoraro', 'Ring Bearer', 'Flower Girl', 'Murray is Jonathan\'s nephew.', 'Fiona is Jonathan\'s oldest niece.');
		buildWeddingPartyPair(photoMap, 'vinny', 'kaylin', 'Vincent Pecoraro', 'Cailyn Gonzalez', 'Ring Bearer', 'Flower Girl', 'Vincent is Jonathan\'s nephew.', 'Cailyn is Ashley\'s cousin.');
		buildWeddingPartyPair(photoMap, 'hudson', 'hannah', 'Hudson White', 'Hannah Gonzalez', 'Ring Bearer', 'Flower Girl', 'Hudson is Jonathan\'s nephew.', 'Hannah is Ashley\'s cousin.');
		buildWeddingPartyPair(photoMap, 'tony', 'sadie', 'Anthony Pecoraro', 'Sadie White', 'Ring Bearer', 'Flower Girl', 'Tony is Jonathan\'s nephew.', 'Sadie is Jonathan\'s niece.');
		$weddingPartyTemplate.remove();
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log(jqXHR);
		console.log(textStatus);
		console.log(errorThrown);
	});
	
	common.getImageFromS3('piggy-back-scaled.jpg', $('#engagement-photo'), 'Engagement photo', 'img-fluid');
	common.getImageFromS3('corpus-christi.jpg', $('#corpus-christi'), 'Corpus Christi Catholic Church', 'img-fluid');
	common.getImageFromS3('caribe-royale.jpg', $('#caribe-royale'), 'Caribe Royale', 'img-fluid');
	common.getImageFromS3('days-hotel-conference-center.jpg', $('#days-hotel'), 'Days Hotel by Wyndham Celebration - Conference Center', 'img-fluid');
	common.getImageFromS3('animal-kingdom-scaled.jpg', $('#story-p1'), 'Animal Kingdom', 'float-left p-2', true);
	common.getImageFromS3('silhouette-proposal.png', $('#story-p2'), 'Proposal Silhouette', 'float-left p-2', true);
});