package com.gng.tddtutorial.class01;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisplayName("Study01 �׽�Ʈ") // �׽�Ʈ ��Ī ����
public class Study01Test {
	
	@BeforeAll // ��� �޼ҵ� ���� �� ����, static���� ���
	public static void beforeAll() {
		log.info("Execute beforeAll()");
	}
	
	@AfterAll // ��� �޼ҵ� ���� �� ����, static���� ���
	public static void afterAll() {
		log.info("Execute afterAll()");
	}
	
	
	@BeforeEach // ������ �޼ҵ� ���� �� ����
	public void beforeEach() {
		log.info("Execute beforeEach()");
	}
	
	
	@AfterEach // ������ �޼ҵ� ���� �� ����
	public void afterEach() {
		log.info("Execute afterEach()");
	}
	
	@Test // �׽�Ʈ ����
	@DisplayName("���� �׽�Ʈ") // �׽�Ʈ ��Ī ����
	public void create() {
		log.info("Execute create()");
		Study01 study01 = new Study01(10);
		
		assertNotNull(study01);
		
		// ���� ���� �� DRAFT �������� Ȯ��
		// Expected, Actual, Failure message ������� ����� ��
		assertEquals(StudyStatus.DRAFT, study01.getStatus(),
				() -> "���͵��� �ʱ� ���� " + StudyStatus.DRAFT + "�� �ƴմϴ�."); // ���ٽ� ��� �� �����ϴ� ��찡 �ƴϸ� �������� ����
		
		// True�� ��ȯ�ϴ��� Ȯ��
		assertTrue(study01.getLimit() > 0, "�ִ� ���� ���� �ο��� 1�� �̻��̾�� �մϴ�.");
		
		// ���ܸ� ��ȯ�ϴ��� Ȯ��
		assertThrows(IllegalArgumentException.class, () -> new Study01(-10));
		
		// Ư�� �ð� �ȿ� �������� Ȯ��
		assertTimeout(Duration.ofMillis(1000), () -> {
			new Study01(10);
			Thread.sleep(100);
		});

		// Ư�� �ð� �ȿ� ������ ������ �׽�Ʈ�� �ߴ���
		// Transaction �ѹ��� �ȵ� �� �����Ƿ� �����ؾ� ��
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			new Study01(10);
			Thread.sleep(100);
		});
		
		// ���ٽ��� �̿��Ͽ� ��� assert ����
		assertAll(
				() -> assertEquals(StudyStatus.DRAFT, study01.getStatus(),
						() -> "���͵��� �ʱ� ���� " + StudyStatus.DRAFT + "�� �ƴմϴ�."), // ���ٽ� ��� �� �����ϴ� ��찡 �ƴϸ� �������� ����
				() -> assertTrue(study01.getLimit() > 0, "�ִ� ���� ���� �ο��� 1�� �̻��̾�� �մϴ�.")
		);
	}
	
	@Test
	@Disabled // �׽�Ʈ ��Ȱ��ȭ
	public void create01() {
		log.info("Execute create01()");
	}
}