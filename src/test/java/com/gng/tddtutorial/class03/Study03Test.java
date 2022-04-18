package com.gng.tddtutorial.class03;


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
 * Tagging/Filtering
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study03 �׽�Ʈ")
public class Study03Test {
	
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
	@Tag("fast") 
	@Test
	@DisplayName("���� �׽�Ʈ(����)")
	public void createFast() {
		log.info("Execute createFast()");
		
		Study03 study03 = new Study03(10);
		assertThat(study03.getLimit()).isGreaterThan(0);
	}

	@Tag("slow")
	@Test
	@DisplayName("���� �׽�Ʈ(����)")
	public void createSlow() {
		log.info("Execute createSlow()");
		
		Study03 study03 = new Study03(10);
		assertThat(study03.getLimit()).isGreaterThan(0);
	}
	
}