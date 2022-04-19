package com.gng.tddtutorial.class07;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lombok.extern.slf4j.Slf4j;

/**
 * Test instance(Per class)
 * @author gchyoo
 *
 */
@Slf4j
@TestInstance(Lifecycle.PER_CLASS) // 테스트 인스턴스를 테스트 클래스로 지정
@DisplayName("Study07 테스트")
public class Study0702Test {
	// 테스트 인스턴스 확인을 위한 멤버 변수
	private int value = 1;
	
	/*
	 * @TestInstance의 Lifecycle을 PER_CLASS로 지정하였으므로
	 * createNewStudy01, createNewStudy02 모두 동일한 value를 사용
	 */
	
	@Test
	@DisplayName("테스트 인스턴스 01")
	void createNewStudy01() {
		Study07 study07 = new Study07(value++);
		log.info("createNewStudy01 value : {}", study07.getLimit());
	}
	
	@Test
	@DisplayName("테스트 인스턴스 02")
	void createNewStudy02() {
		Study07 study07 = new Study07(value++);
		log.info("createNewStudy02 value : {}", study07.getLimit());
	}
}