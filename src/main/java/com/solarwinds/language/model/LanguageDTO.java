package com.solarwinds.language.model;

import javax.validation.constraints.NotBlank;

public class LanguageDTO {
	
	@NotBlank
	private String key;
	@NotBlank
	private String content;
	
	public LanguageDTO() {
		super();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
