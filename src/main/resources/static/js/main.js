function scrollToAnchor(url) {
	var splitUrl = url.split('/');
	var anchorId = splitUrl[splitUrl.length - 1];
	var offsetFromTop = $('#nav')[0].clientHeight + 30;
	
	// Make sure the mobile menu is collapsed
	$('#navbarSupportedContent').collapse('hide');
	
	$('html,body').animate({
		scrollTop: $(anchorId).offset().top - offsetFromTop
    }, 'slow');
	
	$('.dropdown-item').removeClass('active');
	$('.dropdown-item[href="' + url + '"]').addClass('active');
}

function handleNavBarHittingTop() {
	var isMobileView = $('body').css('background-image') == 'none';
	if (!isMobileView) {
		var $nav = $('#nav');
		var navBarTopPosition = 200;
		if ($nav.offset().top >= navBarTopPosition) {
			if ($nav.hasClass('bg-transparent')) {
				$nav.removeClass('bg-transparent');
				$nav.css('box-shadow', '0px 10px 20px 0px #FCFCFC');
			}
		} else {
			if (!$nav.hasClass('bg-transparent')) {
				$nav.addClass('bg-transparent');
				$nav.css('box-shadow', 'none');
			}
		}
	}
}

// Document ready function
$(function() {
	$("#nav a").click(function(e) {
		var $this = $(this);
		if ($this.attr('href').indexOf('#') > 0) {
			scrollToAnchor($this.attr('href'));
			e.preventDefault();
		}
	});
	
	$(window).scroll(handleNavBarHittingTop);
	
	if (window.location.href.indexOf('#') > 0) {
		scrollToAnchor(window.location.pathname + window.location.hash);
	}
})