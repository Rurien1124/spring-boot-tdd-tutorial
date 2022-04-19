package com.gng.tddtutorial.class04;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom tag
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study04 테스트")
public class Study04Test {
	
	// 태그에 따라 실행할 테스트를 지정할 수 있음
	// Run configurations > Include and exclude tags > Configure
	@FastTest // 커스텀 어노테이션 사용
	@Test
	@DisplayName("생성 테스트(빠름)")
	void createFast() {
		log.info("Execute createFast()");
		
		Study04 study04 = new Study04(10);
		assertThat(study04.getLimit()).isGreaterThan(0);
	}

	@SlowTest // 커스텀 어노테이션 사용
	@Test
	@DisplayName("생성 테스트(느림)")
	void createSlow() {
		log.info("Execute createSlow()");
		
		Study04 study04 = new Study04(10);
		assertThat(study04.getLimit()).isGreaterThan(0);
	}
	
}