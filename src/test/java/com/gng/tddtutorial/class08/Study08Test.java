package com.gng.tddtutorial.class08;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import lombok.extern.slf4j.Slf4j;

/**
 * Test ordering
 * @author gchyoo
 *
 */
@Slf4j
@TestMethodOrder(value = OrderAnnotation.class) // 순서 지정, Alphanumeric/Annotation/OrderAnnotation
@DisplayName("Study08 테스트")
public class Study08Test {
	// 유스케이스나 시나리오, 통합테스트 등을 진행할 때는 테스트 순서가 필요해짐
	
	// 테스트 인스턴스 확인을 위한 멤버 변수
	private int value = 1;

	@Order(1)
	@Test
	@DisplayName("테스트 인스턴스 01")
	void createNewStudy01() {
		Study08 study08 = new Study08(value++);
		log.info("createNewStudy01 value : {}", study08.getLimit());
	}

	@Order(3)
	@Test
	@DisplayName("테스트 인스턴스 02")
	void createNewStudy02() {
		Study08 study08 = new Study08(value++);
		log.info("createNewStudy02 value : {}", study08.getLimit());
	}

	@Order(2)
	@Test
	@DisplayName("테스트 인스턴스 03")
	void createNewStudy03() {
		Study08 study08 = new Study08(value++);
		log.info("createNewStudy03 value : {}", study08.getLimit());
	}
}