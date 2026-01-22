package com.farukgenc.boilerplate.springboot.utils;

import java.util.Locale;

/**
 * 프로젝트 상수 클래스
 * 
 * <p>애플리케이션 전역에서 사용하는 상수들을 정의합니다.
 * 유틸리티 클래스이므로 인스턴스 생성을 방지하기 위해 private 생성자를 사용합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
public final class ProjectConstants {

	/** 기본 문자 인코딩 (UTF-8) */
	public static final String DEFAULT_ENCODING = "UTF-8";

	/** 터키어 로케일 */
	public static final Locale TURKISH_LOCALE = new Locale.Builder().setLanguage("tr").setRegion("TR").build();

	/** 인스턴스 생성 방지 */
	private ProjectConstants() {

		throw new UnsupportedOperationException();
	}

}
