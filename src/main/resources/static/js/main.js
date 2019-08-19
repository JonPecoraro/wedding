function scrollToAnchor(url) {
	var splitUrl = url.split('/');
	var anchorId = splitUrl[splitUrl.length - 1];
	var $contentHider = $('#content-hider');
	var offsetFromTop = $contentHider.is(':visible') ? $contentHider[0].clientHeight : 0;
	
	$('html,body').animate({
		scrollTop: $(anchorId).offset().top - offsetFromTop
    }, 'slow');
}

function handleNavBarHittingTop() {
	var $nav = $('#nav');
	var navBarTopPosition = 200;
	if ($nav.offset().top >= navBarTopPosition) {
		if ($nav.hasClass('bg-transparent')) {
			$nav.removeClass('bg-transparent');
		}
	} else {
		if (!$nav.hasClass('bg-transparent')) {
			$nav.addClass('bg-transparent');
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
		scrollToAnchor(window.location.href);
	}
})