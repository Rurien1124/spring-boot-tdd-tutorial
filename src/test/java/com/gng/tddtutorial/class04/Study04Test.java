package com.gng.tddtutorial.class04;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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