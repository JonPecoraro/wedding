package com.wedding.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	private String suffix;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String rsvpResponse;
	private String foodChoice;
	private String plansToUseRoomBlock;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSuffix() {
		return suffix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRsvpResponse() {
		return rsvpResponse;
	}

	public void setRsvpResponse(String rsvpResponse) {
		this.rsvpResponse = rsvpResponse;
	}
	
	public String getFoodChoice() {
		return foodChoice;
	}

	public void setFoodChoice(String foodChoice) {
		this.foodChoice = foodChoice;
	}
	
	public String getPlansToUseRoomBlock() {
		return plansToUseRoomBlock;
	}

	public void setPlansToUseRoomBlock(String plansToUseRoomBlock) {
		this.plansToUseRoomBlock = plansToUseRoomBlock;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", suffix=" + suffix
				+ ", email=" + email + ", rsvpResponse=" + rsvpResponse + ", foodChoice=" + foodChoice
				+ ", plansToUseRoomBlock=" + plansToUseRoomBlock + "]";
	}
}
