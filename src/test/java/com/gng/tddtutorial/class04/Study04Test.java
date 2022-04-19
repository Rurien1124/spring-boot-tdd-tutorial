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
@DisplayName("Study04 �׽�Ʈ")
public class Study04Test {
	
	// �±׿� ���� ������ �׽�Ʈ�� ������ �� ����
	// Run configurations > Include and exclude tags > Configure
	@FastTest // Ŀ���� ������̼� ���
	@Test
	@DisplayName("���� �׽�Ʈ(����)")
	public void createFast() {
		log.info("Execute createFast()");
		
		Study04 study04 = new Study04(10);
		assertThat(study04.getLimit()).isGreaterThan(0);
	}

	@SlowTest // Ŀ���� ������̼� ���
	@Test
	@DisplayName("���� �׽�Ʈ(����)")
	public void createSlow() {
		log.info("Execute createSlow()");
		
		Study04 study04 = new Study04(10);
		assertThat(study04.getLimit()).isGreaterThan(0);
	}
	
}