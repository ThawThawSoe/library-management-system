package com.example.library.dto;

public class PublisherDTO {
	private Long publisher_id;
	private String publisher_name;
	
	
	public PublisherDTO(Long publisherid,String publishername) {
		this.publisher_id = publisherid;
		this.publisher_name = publishername;
	}


	public Long getPublisher_id() {
		return publisher_id;
	}


	public void setPublisher_id(Long publisher_id) {
		this.publisher_id = publisher_id;
	}


	public String getPublisher_name() {
		return publisher_name;
	}


	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}
	
	

}
