package com.company.language.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.language.model.LanguageDTO;
import com.company.language.model.LanguageRequest;
import com.company.language.service.ILanguageService;

@RestController
@RequestMapping("api/v1/languages")
public class LanguageController {
	@Autowired
	private ILanguageService languageService;

	@GetMapping("/{appName}")
	@ResponseStatus(HttpStatus.OK)
	public List<LanguageDTO> findLocaleLanguages(@PathVariable String appName,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			@RequestParam(required = false) String key) {
		return languageService.findLocateLanguages(appName, locale, key);
	}

	@PostMapping("/{appName}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<LanguageDTO> addLocaleLanguages(@PathVariable String appName,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			@Valid @RequestBody LanguageRequest request) {
		return languageService.addLocaleLanguages(appName, locale, request);
	}

	@PutMapping("/{appName}")
	@ResponseStatus(HttpStatus.OK)
	public List<LanguageDTO> updateLocaleLanguages(@PathVariable String appName,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			@Valid @RequestBody LanguageRequest request) {
		return languageService.updateLocaleLanguages(appName, locale, request);
	}

	@DeleteMapping("/{appName}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLocaleLanguage(@PathVariable String appName,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			@RequestParam(required = false) String key) {
		languageService.deleteLocaleLanguage(appName, locale, key);
	}

}
