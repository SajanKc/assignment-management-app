package com.kcsajan.main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assignments")
public class Assignment {

	@Id
	private String id;
	private String title;
	private String description;
	private String submissionDate;
	private String status;

	public Assignment() {

	}

	public Assignment(String title, String description, String submissionDate, String status) {
		this.title = title;
		this.description = description;
		this.submissionDate = submissionDate;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Assignment [id=" + id + ", title=" + title + ", description=" + description + ", submissionDate="
				+ submissionDate + ", status=" + status + "]";
	}

}
