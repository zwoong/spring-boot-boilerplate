package com.farukgenc.boilerplate.springboot.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

/**
 * 예외 메시지 접근 유틸리티
 * 
 * <p>예외 메시지(ExceptionMessages)를 가져오는 서비스입니다.
 * 다국어 메시지 파일에서 메시지를 조회하며, 로케일이 지정되지 않으면 기본값(터키어)을 사용합니다.
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
	 * @param locale 로케일 (null이면 기본값 사용)
	 * @param key 메시지 키
	 * @param parameter 메시지 파라미터
	 * @return 메시지 문자열
	 */
	public String getMessage(Locale locale, String key, Object... parameter) {

		if (Objects.isNull(locale)) {
			return messageSource.getMessage(key, parameter, ProjectConstants.TURKISH_LOCALE);
		}

		return messageSource.getMessage(key, parameter, locale);
	}

}
