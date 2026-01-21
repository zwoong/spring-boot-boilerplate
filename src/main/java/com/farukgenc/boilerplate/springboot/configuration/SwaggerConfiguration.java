package com.farukgenc.boilerplate.springboot.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

/**
 * Swagger/OpenAPI 설정 클래스
 * 
 * <p>API 문서화를 위한 Swagger UI 설정 및 JWT 인증 스키마를 구성합니다.
 * application.yml의 swagger 설정을 주입받아 사용합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "swagger")  // application.yml의 swagger 설정 바인딩
public class SwaggerConfiguration {

	// application.yml에서 주입받는 Swagger 설정 값들
	private String appName;           // 애플리케이션 이름
	private String appDescription;    // 애플리케이션 설명
	private String appVersion;       // 애플리케이션 버전
	private String appLicense;        // 라이선스 이름
	private String appLicenseUrl;     // 라이선스 URL
	private String contactName;      // 연락처 이름
	private String contactUrl;       // 연락처 URL
	private String contactMail;       // 연락처 이메일

	/**
	 * OpenAPI Bean 생성
	 * 
	 * <p>Swagger UI에 표시될 API 문서 정보와 JWT 인증 스키마를 설정합니다.
	 * 
	 * @return OpenAPI 인스턴스
	 */
	@Bean
	public OpenAPI openAPI() {
		final Info apiInformation = getApiInformation();
		final Components components = new Components();

		// JWT Bearer 토큰 인증 스키마 설정
		final String schemeName = "bearerAuth";
		components.addSecuritySchemes(schemeName, 
			new SecurityScheme()
				.name(schemeName)
				.type(HTTP)
				.scheme("Bearer")
				.bearerFormat("JWT"));

		final OpenAPI openAPI = new OpenAPI();
		openAPI.setInfo(apiInformation);           // API 정보 설정
		openAPI.setComponents(components);         // 보안 스키마 설정
		openAPI.addSecurityItem(new SecurityRequirement().addList(schemeName));  // 기본 보안 요구사항 추가

		return openAPI;
	}

	/**
	 * API 정보 구성
	 * 
	 * <p>애플리케이션 이름, 버전, 설명, 라이선스, 연락처 정보를 설정합니다.
	 * 
	 * @return Info 인스턴스
	 */
	private Info getApiInformation() {
		final License license = new License();
		license.setName(appLicense);
		license.setUrl(appLicenseUrl);

		final Contact contact = new Contact();
		contact.setName(contactName);
		contact.setUrl(contactUrl);
		contact.setEmail(contactMail);

		final Info info = new Info();
		info.setTitle(appName);
		info.setVersion(appVersion);
		info.setDescription(appDescription);
		info.setLicense(license);
		info.setContact(contact);

		return info;
	}

	/**
	 * Actuator API 그룹 생성
	 * 
	 * <p>Swagger UI에서 Actuator 엔드포인트를 별도 그룹으로 표시합니다.
	 * 
	 * @return GroupedOpenApi 인스턴스
	 */
	@Bean
	GroupedOpenApi managementApi() {
		return GroupedOpenApi.builder()
			.pathsToMatch("/actuator/**")  // Actuator 경로만 포함
			.group("Management Api")       // 그룹 이름
			.build();
	}

	/**
	 * 기본 API 그룹 생성
	 * 
	 * <p>Swagger UI에서 Actuator를 제외한 모든 API를 기본 그룹으로 표시합니다.
	 * 
	 * @return GroupedOpenApi 인스턴스
	 */
	@Bean
	GroupedOpenApi defaultApi() {
		return GroupedOpenApi.builder()
			.pathsToExclude("/actuator/**")  // Actuator 경로 제외
			.group("Default Api")             // 그룹 이름
			.build();
	}

}
