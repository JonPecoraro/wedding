$(function() {
	$('.display').DataTable({
		'order': [[ 0, 'desc' ]]
    });
	
	// Guest admin page functions	
	$(document).on('click', '.guest-link', function(e) {
		e.preventDefault();
		var $this = $(this);
		$('#updateGuestForm').attr('action', '/admin/edit-guest/' + $this.attr('id'));
		$('#guestId').val($this.attr('id'));
		$('#firstName').val($this.attr('first-name'));
		$('#lastName').val($this.attr('last-name'));
		$('#suffix').val($this.attr('suffix'));
		$('#email').val($this.attr('email'));
		$('#rsvpResponse').val($this.attr('rsvp-response'));
		$('#foodChoice').val($this.attr('food-choice'));
		$('#plansToUseRoomBlock').val($this.attr('plans-to-use-room-block'));
		$('#editGuestModal').modal();
	});
	
	$('#updateGuestBtn').on('click', function() {
		$('#updateGuestForm').submit();
	});
	
	$('#deleteGuestBtn').on('click', function() {
		var deleteConfirmation = confirm("Are you sure you want to delete this guest?");
		if (deleteConfirmation == true) {
			$('#updateGuestForm')
				.attr('action', '/admin/delete-guest/' + $('#guestId').val())
				.submit();
		} else {
			$('#editGuestModal').modal('hide');
		}
	});
	// End Guest admin page functions
	
	// Guestbook admin page function
	$(document).on('click', '.guestbook-entry-link', function(e) {
		e.preventDefault();
		var $this = $(this);
		$('#updateEntryForm').attr('action', '/admin/edit-guestbook-entry/' + $this.attr('id'));
		$('#entryId').val($this.attr('id'));
		$('#name').val($this.attr('name'));
		$('#message').val($this.attr('message'));
		$('#privateMessage').val($this.attr('private-message'));
		$('#ediEntrytModal').modal();
	});
	
	$('#updateEntryBtn').on('click', function() {
		$('#updateEntryForm').submit();
	});
	
	$('#deleteEntryBtn').on('click', function() {
		var deleteConfirmation = confirm("Are you sure you want to delete this guestbook entry?");
		if (deleteConfirmation == true) {
			$('#updateEntryForm')
				.attr('action', '/admin/delete-guestbook-entry/' + $('#entryId').val())
				.submit();
		} else {
			$('#ediEntrytModal').modal('hide');
		}
	});
	// End Guestbook admin page function
});
