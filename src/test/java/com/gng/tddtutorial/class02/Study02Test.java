package com.gng.tddtutorial.class02;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import lombok.extern.slf4j.Slf4j;

/**
 * Condition annotations
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study02 �׽�Ʈ") // �׽�Ʈ ��Ī ����
public class Study02Test {
	@Test // �׽�Ʈ ����
	@DisplayName("���� �׽�Ʈ") // �׽�Ʈ ��Ī ����
	@EnabledOnOs(value = {OS.WINDOWS, OS.MAC, OS.LINUX}) // OS�� ���� �����ϵ��� ����
//	@EnabledOnJre(value = {JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11}) // �ڹ� ������ ���� �����ϵ��� ����
	@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL", disabledReason = "TEST_ENV ���� LOCAL�� �ƴմϴ�.") // ȯ�溯���� ���� �����ϵ��� ����
	public void create() {
		log.info("Execute create()");
		
		// ȯ�溯��
		String testEnv = System.getenv("TEST_ENV");
		log.info("Test env : {}", testEnv);
		
		// ȯ�溯�� TEST_ENV�� ���� LOCAL�̸� ����
		assumeTrue("LOCAL".equalsIgnoreCase(testEnv), "TEST_ENV ���� LOCAL�� �ƴմϴ�.");
		
		Study02 study02 = new Study02(10);
		
		// AssertJ
		assertThat(study02.getLimit()).isGreaterThan(0);
	}
}