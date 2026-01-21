package com.farukgenc.boilerplate.springboot.configuration;

import com.farukgenc.boilerplate.springboot.utils.ProjectConstants;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 다국어(i18n) 메시지 설정 클래스
 * 
 * <p>이 클래스는 Spring의 MessageSource를 사용하여 애플리케이션의 다국어 메시지를 관리합니다.
 * 메시지는 용도별로 3가지로 분리되어 있습니다:
 * <ul>
 *   <li>일반 메시지 (GeneralMessages): 비즈니스 로직에서 사용하는 일반적인 메시지</li>
 *   <li>예외 메시지 (ExceptionMessages): 예외 발생 시 사용자에게 표시할 메시지</li>
 *   <li>유효성 검증 메시지 (ValidationMessages): Bean Validation에서 사용하는 메시지</li>
 * </ul>
 * 
 * <p>ReloadableResourceBundleMessageSource를 사용하여 런타임에 메시지 파일을 다시 로드할 수 있습니다.
 * 이는 서버 재시작 없이 메시지 파일을 수정할 수 있게 해줍니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Configuration
public class MessageConfiguration {

	/**
	 * 일반 메시지용 MessageSource Bean 생성
	 * 
	 * <p>비즈니스 로직에서 사용하는 일반적인 메시지를 관리합니다.
	 * 예: 회원가입 성공 메시지, 작업 완료 메시지 등
	 * 
	 * <p>메시지 파일 위치: classpath:/messages/general/GeneralMessages.properties
	 * 다국어 지원: GeneralMessages_ko.properties (한국어), GeneralMessages_tr.properties (터키어) 등
	 * 
	 * @return 일반 메시지를 관리하는 MessageSource 인스턴스
	 */
	@Bean
	MessageSource generalMessageSource() {
		// ReloadableResourceBundleMessageSource: 런타임에 메시지 파일을 다시 로드할 수 있는 MessageSource 구현체
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		// 메시지 파일의 기본 이름 설정 (확장자와 언어 코드는 자동으로 추가됨)
		// 예: GeneralMessages.properties, GeneralMessages_ko.properties 등
		messageSource.setBasename("classpath:/messages/general/GeneralMessages");
		
		// 메시지 파일의 기본 인코딩 설정 (UTF-8)
		messageSource.setDefaultEncoding(ProjectConstants.DEFAULT_ENCODING);

		return messageSource;
	}

	/**
	 * 예외 메시지용 MessageSource Bean 생성
	 * 
	 * <p>예외 발생 시 사용자에게 표시할 메시지를 관리합니다.
	 * 예: "이 사용자명은 이미 사용 중입니다", "이메일 주소가 중복되었습니다" 등
	 * 
	 * <p>메시지 파일 위치: classpath:/messages/exception/ExceptionMessages.properties
	 * 다국어 지원: ExceptionMessages_ko.properties (한국어), ExceptionMessages_tr.properties (터키어) 등
	 * 
	 * @return 예외 메시지를 관리하는 MessageSource 인스턴스
	 */
	@Bean
	MessageSource exceptionMessageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		// 예외 메시지 파일의 기본 이름 설정
		messageSource.setBasename("classpath:/messages/exception/ExceptionMessages");
		
		// UTF-8 인코딩 설정
		messageSource.setDefaultEncoding(ProjectConstants.DEFAULT_ENCODING);

		return messageSource;
	}

	/**
	 * 유효성 검증 메시지용 MessageSource Bean 생성
	 * 
	 * <p>Bean Validation (@NotNull, @Size, @Email 등)에서 사용하는 메시지를 관리합니다.
	 * 예: "이름은 필수 입력 항목입니다", "유효한 이메일 주소를 입력해주세요" 등
	 * 
	 * <p>메시지 파일 위치: classpath:/messages/validation/ValidationMessages.properties
	 * 다국어 지원: ValidationMessages_ko.properties (한국어), ValidationMessages_tr.properties (터키어) 등
	 * 
	 * <p>이 MessageSource는 LocalValidatorFactoryBean에 주입되어 Bean Validation에서 자동으로 사용됩니다.
	 * 
	 * @return 유효성 검증 메시지를 관리하는 MessageSource 인스턴스
	 */
	@Bean
	public MessageSource validationMessageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		// 유효성 검증 메시지 파일의 기본 이름 설정
		messageSource.setBasename("classpath:/messages/validation/ValidationMessages");
		
		// UTF-8 인코딩 설정
		messageSource.setDefaultEncoding(ProjectConstants.DEFAULT_ENCODING);

		return messageSource;
	}

	/**
	 * Bean Validation을 위한 LocalValidatorFactoryBean 생성
	 * 
	 * <p>Spring의 Bean Validation을 사용하기 위한 Validator Factory Bean입니다.
	 * 이 Bean은 @Valid 어노테이션과 함께 사용되어 DTO의 유효성 검증을 수행합니다.
	 * 
	 * <p>validationMessageSource()를 주입하여 유효성 검증 실패 시 다국어 메시지를 사용할 수 있도록 설정합니다.
	 * 
	 * <p>사용 예시:
	 * <pre>{@code
	 * @PostMapping("/register")
	 * public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
	 *     // 유효성 검증 실패 시 validationMessageSource의 메시지가 사용됨
	 * }
	 * }</pre>
	 * 
	 * @return Bean Validation을 수행하는 LocalValidatorFactoryBean 인스턴스
	 */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		
		// 유효성 검증 메시지를 다국어로 제공하기 위해 validationMessageSource 설정
		// 이 설정으로 @Valid 어노테이션 사용 시 다국어 메시지가 자동으로 적용됨
		bean.setValidationMessageSource(validationMessageSource());

		return bean;
	}

}
