package com.wedding.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guestbook {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String message;
	private boolean privateMessage;
	private Timestamp dateCreated;
	
	public Guestbook() {}
	
	public Guestbook(String name, String message) {
		this.name = name;
		this.message = message;
		this.dateCreated = new Timestamp(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	
	public boolean isPrivateMessage() {
		return privateMessage;
	}

	public void setPrivateMessage(boolean privateMessage) {
		this.privateMessage = privateMessage;
	}
	
	public Date getDateCreatedWithoutTime() {
		return new Date(dateCreated.getTime());
	}
	
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Guestbook [id=" + id + ", name=" + name + ", message=" + message + ", privateMessage=" + privateMessage
				+ ", dateCreated=" + dateCreated + "]";
	}
}
