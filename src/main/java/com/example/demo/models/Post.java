package com.example.demo.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
public class Post {
	@Id
	private String id;
	private String subject;
	private String description;
	@DBRef
	private User owner;
	private boolean isApproved = false;
	@DBRef
	private User approvedBy;

	private long likeCounts;

	private long commentsCounts;

	private String[] mediaPath;

	private Date createdAt = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public long getLikeCounts() {
		return likeCounts;
	}

	public void setLikeCounts(long likeCounts) {
		this.likeCounts = likeCounts;
	}

	public long getCommentsCounts() {
		return commentsCounts;
	}

	public void setCommentsCounts(long commentsCounts) {
		this.commentsCounts = commentsCounts;
	}

	public String[] getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String[] mediaPath) {
		this.mediaPath = mediaPath;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	}
