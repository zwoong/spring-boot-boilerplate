package com.farukgenc.boilerplate.springboot.repository.user;

import com.farukgenc.boilerplate.springboot.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 사용자 데이터 접근 리포지토리
 * 
 * <p>사용자(User) 엔티티에 대한 데이터베이스 접근을 담당하는 리포지토리 인터페이스입니다.
 * Spring Data JPA의 JpaRepository를 상속받아 기본적인 CRUD 작업을 자동으로 제공받습니다.
 * 
 * <p>Spring Data JPA의 메서드 네이밍 컨벤션을 사용하여 쿼리 메서드를 정의합니다:
 * <ul>
 *   <li>findBy{필드명}: 특정 필드로 엔티티 조회</li>
 *   <li>existsBy{필드명}: 특정 필드 값의 존재 여부 확인</li>
 * </ul>
 * 
 * <p>이 인터페이스는 Spring Data JPA에 의해 자동으로 구현되며,
 * 런타임에 프록시 객체가 생성되어 실제 데이터베이스 작업을 수행합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 사용자명으로 사용자 조회
	 * 
	 * <p>데이터베이스에서 사용자명(username)을 기준으로 사용자를 조회합니다.
	 * Spring Data JPA가 메서드 이름을 분석하여 자동으로 쿼리를 생성합니다.
	 * 
	 * <p>생성되는 쿼리 (예시):
	 * <pre>SELECT u FROM User u WHERE u.username = ?1</pre>
	 * 
	 * @param username 조회할 사용자명
	 * @return 사용자 엔티티를 포함한 Optional (없으면 Optional.empty())
	 */
	Optional<User> findByUsername(String username);

	/**
	 * 이메일 존재 여부 확인
	 * 
	 * <p>데이터베이스에서 해당 이메일을 가진 사용자가 존재하는지 확인합니다.
	 * 주로 회원가입 시 이메일 중복 검증에 사용됩니다.
	 * 
	 * <p>생성되는 쿼리 (예시):
	 * <pre>SELECT COUNT(u) > 0 FROM User u WHERE u.email = ?1</pre>
	 * 
	 * @param email 확인할 이메일 주소
	 * @return 이메일이 존재하면 true, 없으면 false
	 */
	boolean existsByEmail(String email);

	/**
	 * 사용자명 존재 여부 확인
	 * 
	 * <p>데이터베이스에서 해당 사용자명을 가진 사용자가 존재하는지 확인합니다.
	 * 주로 회원가입 시 사용자명 중복 검증에 사용됩니다.
	 * 
	 * <p>생성되는 쿼리 (예시):
	 * <pre>SELECT COUNT(u) > 0 FROM User u WHERE u.username = ?1</pre>
	 * 
	 * @param username 확인할 사용자명
	 * @return 사용자명이 존재하면 true, 없으면 false
	 */
	boolean existsByUsername(String username);

}
