package com.farukgenc.boilerplate.springboot.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * 예외 메시지 접근 유틸리티
 * 
 * <p>예외 메시지(ExceptionMessages)를 가져오는 서비스입니다.
 * HTTP 요청의 Accept-Language 헤더를 기반으로 로케일을 자동으로 결정합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Service
public class ExceptionMessageAccessor {

	private final MessageSource messageSource;

	ExceptionMessageAccessor(@Qualifier("exceptionMessageSource") MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * 예외 메시지 조회
	 * 
	 * <p>HTTP 요청의 Accept-Language 헤더를 기반으로 로케일을 자동으로 결정합니다.
	 * 헤더가 없거나 지원하지 않는 언어인 경우 기본 로케일(한국어)을 사용합니다.
	 * 
	 * @param key 메시지 키
	 * @param parameter 메시지 파라미터
	 * @return 메시지 문자열
	 */
	public String getMessage(String key, Object... parameter) {

		final Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(key, parameter, locale);
	}

	/**
	 * 예외 메시지 조회 (로케일 명시)
	 * 
	 * <p>특정 로케일을 명시적으로 지정하여 메시지를 조회합니다.
	 * 
	 * @param locale 로케일
	 * @param key 메시지 키
	 * @param parameter 메시지 파라미터
	 * @return 메시지 문자열
	 */
	public String getMessage(Locale locale, String key, Object... parameter) {

		return messageSource.getMessage(key, parameter, locale);
	}

}
