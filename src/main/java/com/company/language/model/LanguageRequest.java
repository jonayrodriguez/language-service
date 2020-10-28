package com.company.language.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class LanguageRequest {
	
	@NotEmpty
	private List<LanguageDTO> languages;
	
	public LanguageRequest() {
		super();
	}
	
	public List<LanguageDTO> getLanguages() {
		return languages;
	}

	public void setLanguages(List<LanguageDTO> languages) {
		this.languages = languages;
	}

}
