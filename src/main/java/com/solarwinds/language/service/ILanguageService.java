package com.solarwinds.language.service;

import java.util.List;
import java.util.Locale;

import com.solarwinds.language.model.LanguageDTO;
import com.solarwinds.language.model.LanguageRequest;

public interface ILanguageService {
	
	List<LanguageDTO> findLocateLanguages(final String appName, final Locale locale, final String key) ;
	List<LanguageDTO> addLocaleLanguages(final String appName, final Locale locale, final LanguageRequest addRequest);
	List<LanguageDTO> updateLocaleLanguages (final String appName, final Locale locale, final LanguageRequest updateRequest);
	void deleteLocaleLanguage (final String appName, final Locale locale, final String key) ;	

}
