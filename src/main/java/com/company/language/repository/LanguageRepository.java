package com.company.language.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.language.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
	
	List<Language> findByApplicationAndLocaleAndKeyLike(String app,String locale, String key);
	
	Language findByApplicationAndLocaleAndKey(String app,String locale, String key);
	
	@Query("SELECT COUNT(l)>0 FROM Language l WHERE l.application=:app AND l.locale=:locale AND l.key=:key")
	boolean existByApplicationAndLocaleAndKey(String app,String locale, String key);

	@Modifying
	@Query("delete Language l WHERE l.application=:app AND l.locale=:locale AND l.key LIKE :key")
	void deleteByApplicationAndLocaleAndKeyLike(String app,String locale, String key);
	
	@Modifying
	@Query("delete Language l WHERE l.application=:app AND l.locale=:locale AND l.key=:key")
	void deleteByApplicationAndLocaleAndKey(String app,String locale, String key);
}
