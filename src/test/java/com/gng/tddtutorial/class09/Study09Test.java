package com.gng.tddtutorial.class09;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.gng.tddtutorial.class04.SlowTest;

import lombok.extern.slf4j.Slf4j;

/**
 * Extension model
 * @author gchyoo
 *
 */
@Slf4j
//@ExtendWith(value = FindSlowTestExtension.class) // 선언으로 extension을 등록(동적으로 설정 불가)
@DisplayName("Study09 테스트")
public class Study09Test {
	// 유스케이스나 시나리오, 통합테스트 등을 진행할 때는 테스트 순서가 필요해짐

	/*
	 * 프로그래밍적으로 extension을 등록(동적으로 설정 가능)
	 * junit properties를 사용하여 등록할 수도 있지만
	 * 명시적으로 코드에 등록해주는 것이 나음
	 */
	@RegisterExtension
	private static FindSlowTestExtension findSlowTestExteension = new FindSlowTestExtension(1000L);
	
	@Test
	@DisplayName("SlowTest 어노테이션 미적용")
	void createNewStudyWithoutSlowTest() throws InterruptedException {
		Study09 study09 = new Study09(10);
		Thread.sleep(2000);
		log.info("createNewStudy01 value : {}", study09.getLimit());
	}

	@SlowTest
	@DisplayName("SlowTest 어노테이션 적용")
	void createNewStudyWithSlowTest() throws InterruptedException {
		Study09 study09 = new Study09(10);
		Thread.sleep(2000);
		log.info("createNewStudy01 value : {}", study09.getLimit());
	}
}
