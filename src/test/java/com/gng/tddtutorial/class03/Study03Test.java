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
@DisplayName("Study03 �׽�Ʈ")
public class Study03Test {

	// �±׿� ���� ������ �׽�Ʈ�� ������ �� ����
	// Run configurations > Include and exclude tags > Configure
	@Tag("fast") 
	@Test
	@DisplayName("���� �׽�Ʈ(����)")
	void createFast() {
		log.info("Execute createFast()");
		
		Study03 study03 = new Study03(10);
		assertThat(study03.getLimit()).isGreaterThan(0);
	}

	@Tag("slow")
	@Test
	@DisplayName("���� �׽�Ʈ(����)")
	void createSlow() {
		log.info("Execute createSlow()");
		
		Study03 study03 = new Study03(10);
		assertThat(study03.getLimit()).isGreaterThan(0);
	}
	
}