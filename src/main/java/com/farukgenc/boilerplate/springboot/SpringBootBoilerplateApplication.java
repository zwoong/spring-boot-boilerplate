package com.farukgenc.boilerplate.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 애플리케이션 메인 클래스
 * 
 * <p>애플리케이션의 진입점입니다.
 * @SpringBootApplication 어노테이션은 다음을 포함합니다:
 * <ul>
 *   <li>@Configuration: 설정 클래스로 지정</li>
 *   <li>@EnableAutoConfiguration: Spring Boot 자동 설정 활성화</li>
 *   <li>@ComponentScan: 컴포넌트 스캔 활성화</li>
 * </ul>
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@SpringBootApplication
public class SpringBootBoilerplateApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootBoilerplateApplication.class, args);
	}

}
