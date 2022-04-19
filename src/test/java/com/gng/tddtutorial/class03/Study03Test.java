package com.gng.tddtutorial.class03;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * Tagging/Filtering
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study03 테스트")
public class Study03Test {

	// 태그에 따라 실행할 테스트를 지정할 수 있음
	// Run configurations > Include and exclude tags > Configure
	@Tag("fast") 
	@Test
	@DisplayName("생성 테스트(빠름)")
	void createFast() {
		log.info("Execute createFast()");
		
		Study03 study03 = new Study03(10);
		assertThat(study03.getLimit()).isGreaterThan(0);
	}

	@Tag("slow")
	@Test
	@DisplayName("생성 테스트(느림)")
	void createSlow() {
		log.info("Execute createSlow()");
		
		Study03 study03 = new Study03(10);
		assertThat(study03.getLimit()).isGreaterThan(0);
	}
	
}