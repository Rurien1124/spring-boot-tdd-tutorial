package com.gng.tddtutorial.class05;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom tag
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study05 테스트")
public class Study05Test {
	
	@BeforeAll
	public static void beforeAll() {
		log.info("Execute beforeAll()");
	}
	
	@AfterAll
	public static void afterAll() {
		log.info("Execute afterAll()");
	}
	
	
	@BeforeEach
	public void beforeEach() {
		log.info("Execute beforeEach()");
	}
	
	
	@AfterEach
	public void afterEach() {
		log.info("Execute afterEach()");
	}
	
	@RepeatedTest(value = 10, // 테스트 반복
			name = "{displayName}, {currentRepetition}/{totalRepetitions}") // 반복 시 표시할 명칭
	@DisplayName("반복 테스트") // 테스트 명칭(RepeatedTest의 상위 레벨)
	public void repeatTest(RepetitionInfo repetitionInfo) {
		System.out.println("Test : " + repetitionInfo.getCurrentRepetition());
	}
	
	@ParameterizedTest(name = "{index} {displayName} message={0}") // 테스트 반복(파라미터 이용)
	@ValueSource(strings = {"테스트", "파라미터", "데이터"}) // 반복 시 사용할 파라미터
	@DisplayName("반복 테스트(파라미터 이용)")
	public void parameterizedTest(String message) {
		System.out.println("Test : " + message);
	}
	
	@FastTest
	@Test
	@DisplayName("생성 테스트(빠름)")
	public void createFast() {
		log.info("Execute createFast()");
		
		Study05 study05 = new Study05(10);
		assertThat(study05.getLimit()).isGreaterThan(0);
	}

	@SlowTest
	@Test
	@DisplayName("생성 테스트(느림)")
	public void createSlow() {
		log.info("Execute createSlow()");
		
		Study05 study05 = new Study05(10);
		assertThat(study05.getLimit()).isGreaterThan(0);
	}
	
}