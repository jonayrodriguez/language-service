package com.solarwinds.language.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.solarwinds.language.entity.Language;
import com.solarwinds.language.model.LanguageDTO;
import com.solarwinds.language.model.LanguageRequest;
import com.solarwinds.language.repository.LanguageRepository;

@Component
public class LanguageService implements ILanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final String DEFAULT_LOCALE_CODE = "en";

	@Transactional(readOnly = true)
	@Override
	public List<LanguageDTO> findLocateLanguages(final String appName, final Locale locale, final String key) {
		final String keyToGet = buildLanguageKey(key);
		final String localeToGet = getLanguague(locale);

		List<Language> languages = new ArrayList<>();
		if (keyToGet.contains("%")) {
			languages = languageRepository.findByApplicationAndLocaleAndKeyLike(appName, localeToGet, keyToGet);
		} else {
			Language language = languageRepository.findByApplicationAndLocaleAndKey(appName, localeToGet, keyToGet);
			languages.add(language);
		}
		return languages.stream().map(language -> modelMapper.map(language, LanguageDTO.class))
				.collect(Collectors.toList());

	}

	@Transactional
	@Override
	public List<LanguageDTO> addLocaleLanguages(final String appName, final Locale locale,
			final LanguageRequest addRequest) {
		final String localeToGet = getLanguague(locale);

		// Only save it if it doesn´t exist
		List<Language> languagesToSave = addRequest.getLanguages().stream().filter(l -> {
			return !languageRepository.existByApplicationAndLocaleAndKey(appName, localeToGet, l.getKey());
		}).map(l -> {
			Language lang = modelMapper.map(l, Language.class);
			lang.setApplication(appName);
			lang.setLocale(localeToGet);
			return lang;
		}).collect(Collectors.toList());

		languageRepository.saveAll(languagesToSave);
		return addRequest.getLanguages();
	}

	@Transactional
	@Override
	public List<LanguageDTO> updateLocaleLanguages(final String appName, final Locale locale,
			final LanguageRequest updateRequest) {
		final String localeToGet = getLanguague(locale);

		List<Language> languagesToUpdate = new ArrayList<>();
		// Only update if it doesn´t exist
		updateRequest.getLanguages().stream().forEach(l -> {
			Language language = languageRepository.findByApplicationAndLocaleAndKey(appName, localeToGet, l.getKey());
			if (language != null) {
				// Just updating the content
				language.setContent(l.getContent());
				languagesToUpdate.add(language);
			}
		});
		languageRepository.saveAll(languagesToUpdate);
		return languagesToUpdate.stream().map(language -> modelMapper.map(language, LanguageDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void deleteLocaleLanguage(String appName, Locale locale, String key) {
		final String keyToGet = buildLanguageKey(key);
		final String localeToGet = getLanguague(locale);
		if (keyToGet.contains("%")) {
			languageRepository.deleteByApplicationAndLocaleAndKeyLike(appName, localeToGet, keyToGet);
		} else {
			languageRepository.deleteByApplicationAndLocaleAndKey(appName, localeToGet, keyToGet);
		}
	}

	private String buildLanguageKey(String key) {
		if (StringUtils.isEmpty(key)) {
			return "%";
		} else if (key.contains("*")) {
			return key.replace("*", "%");
		}

		return key;

	}

	private String getLanguague(Locale locale) {
		// Need to valid the locale
		if (locale == null) {
			return DEFAULT_LOCALE_CODE;
		} else {
			return locale.getLanguage();
		}

	}

}
