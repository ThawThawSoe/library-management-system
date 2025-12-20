package com.example.library.dto;



public class AuthorDTO {
	
	private Long author_id;
	private String author_name;
	
	public AuthorDTO(Long authorid,String authorname) {
		this.author_id = authorid;
		this.author_name = authorname;
	}

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	
	

}
