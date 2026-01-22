package com.farukgenc.boilerplate.springboot.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 엔티티
 * 
 * <p>시스템 사용자 정보를 나타내는 JPA 엔티티입니다.
 * USERS 테이블과 매핑됩니다.
 * 
 * <p>실무 권장 스타일:
 * <ul>
 *   <li>@Builder: 가독성 좋은 객체 생성</li>
 *   <li>@NoArgsConstructor(access = PROTECTED): JPA 요구사항 충족 및 보안 강화</li>
 *   <li>@Getter만 사용: 무분별한 setter 사용 방지</li>
 * </ul>
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "USERS")
public class User {

	/** 사용자 ID (Primary Key) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 이름 */
	private String name;

	/** 사용자명 (고유값) */
	@Column(unique = true)
	private String username;

	/** 비밀번호 (암호화됨) */
	private String password;

	/** 이메일 주소 */
	private String email;

	/** 사용자 역할 */
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

}
