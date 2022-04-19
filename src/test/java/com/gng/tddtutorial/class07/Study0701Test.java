package com.gng.tddtutorial.class07;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * Test instance(Separated)
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study07 테스트")
public class Study0701Test {
	// 테스트 인스턴스 확인을 위한 멤버 변수
	private int value = 1;
	
	/*
	 * createNewStudy01, createNewStudy02 모두 value++를 사용하지만
	 * 두 개의 테스트를 동시에 실행하더라도
	 * 인스턴스는 각 테스트마다 별개로 되어있으므로 value의 값은 동일함
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