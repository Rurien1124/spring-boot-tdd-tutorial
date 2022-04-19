package com.gng.tddtutorial.class02;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import lombok.extern.slf4j.Slf4j;

/**
 * Condition annotations
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study02 테스트") // 테스트 명칭 지정
public class Study02Test {
	@Test // 테스트 지정
	@DisplayName("생성 테스트") // 테스트 명칭 지정
	@EnabledOnOs(value = {OS.WINDOWS, OS.MAC, OS.LINUX}) // OS에 따라 실행하도록 설정
//	@EnabledOnJre(value = {JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11}) // 자바 버전에 따라 실행하도록 설정
	@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL", disabledReason = "TEST_ENV 값이 LOCAL이 아닙니다.") // 환경변수에 따라 실행하도록 설정
	public void create() {
		log.info("Execute create()");
		
		// 환경변수
		String testEnv = System.getenv("TEST_ENV");
		log.info("Test env : {}", testEnv);
		
		// 환경변수 TEST_ENV의 값이 LOCAL이면 실행
		assumeTrue("LOCAL".equalsIgnoreCase(testEnv), "TEST_ENV 값이 LOCAL이 아닙니다.");
		
		Study02 study02 = new Study02(10);
		
		// AssertJ
		assertThat(study02.getLimit()).isGreaterThan(0);
	}
}