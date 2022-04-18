package com.gng.tddtutorial.class01;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisplayName("Study01 테스트") // 테스트 명칭 지정
public class Study01Test {
	
	@BeforeAll // 모든 메소드 시작 전 실행, static으로 사용
	public static void beforeAll() {
		log.info("Execute beforeAll()");
	}
	
	@AfterAll // 모든 메소드 종료 후 실행, static으로 사용
	public static void afterAll() {
		log.info("Execute afterAll()");
	}
	
	
	@BeforeEach // 각각의 메소드 시작 전 실행
	public void beforeEach() {
		log.info("Execute beforeEach()");
	}
	
	
	@AfterEach // 각각의 메소드 종료 후 실행
	public void afterEach() {
		log.info("Execute afterEach()");
	}
	
	@Test // 테스트 지정
	@DisplayName("생성 테스트") // 테스트 명칭 지정
	public void create() {
		log.info("Execute create()");
		Study01 study01 = new Study01(10);
		
		assertNotNull(study01);
		
		// 최초 실행 시 DRAFT 상태인지 확인
		// Expected, Actual, Failure message 순서대로 적어야 함
		assertEquals(StudyStatus.DRAFT, study01.getStatus(),
				() -> "스터디의 초기 값이 " + StudyStatus.DRAFT + "가 아닙니다."); // 람다식 사용 시 실패하는 경우가 아니면 연산하지 않음
		
		// True를 반환하는지 확인
		assertTrue(study01.getLimit() > 0, "최대 참석 가능 인원은 1명 이상이어야 합니다.");
		
		// 예외를 반환하는지 확인
		assertThrows(IllegalArgumentException.class, () -> new Study01(-10));
		
		// 특정 시간 안에 끝나는지 확인
		assertTimeout(Duration.ofMillis(1000), () -> {
			new Study01(10);
			Thread.sleep(100);
		});

		// 특정 시간 안에 끝나지 않으면 테스트를 중단함
		// Transaction 롤백이 안될 수 있으므로 주의해야 함
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			new Study01(10);
			Thread.sleep(100);
		});
		
		// 람다식을 이용하여 모든 assert 실행
		assertAll(
				() -> assertEquals(StudyStatus.DRAFT, study01.getStatus(),
						() -> "스터디의 초기 값이 " + StudyStatus.DRAFT + "가 아닙니다."), // 람다식 사용 시 실패하는 경우가 아니면 연산하지 않음
				() -> assertTrue(study01.getLimit() > 0, "최대 참석 가능 인원은 1명 이상이어야 합니다.")
		);
	}
	
	@Test
	@Disabled // 테스트 비활성화
	public void create01() {
		log.info("Execute create01()");
	}
}