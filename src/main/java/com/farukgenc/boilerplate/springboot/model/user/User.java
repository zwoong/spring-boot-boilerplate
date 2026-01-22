package com.farukgenc.boilerplate.springboot.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 엔티티
 * 
 * <p>시스템 사용자 정보를 나타내는 JPA 엔티티입니다.
 * USERS 테이블과 매핑됩니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
