package com.gng.tddtutorial.class05;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom tag
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study05 �׽�Ʈ")
public class Study05Test {
	
	@RepeatedTest(value = 10, // �׽�Ʈ �ݺ�
			name = "{displayName}, {currentRepetition}/{totalRepetitions}") // �ݺ� �� ǥ���� ��Ī
	@DisplayName("�ݺ� �׽�Ʈ") // �׽�Ʈ ��Ī(RepeatedTest�� ���� ����)
	public void repeatTest(RepetitionInfo repetitionInfo) {
		log.info("Test : {}", repetitionInfo.getCurrentRepetition());
	}
	
	@ParameterizedTest(name = "{index} {displayName} message={0}") // �׽�Ʈ �ݺ�(�Ķ���� �̿�)
	@ValueSource(strings = {"�׽�Ʈ", "�Ķ����", "������"}) // �ݺ� �� ����� �Ķ����
	@DisplayName("�ݺ� �׽�Ʈ(�Ķ���� �̿�)")
	public void parameterizedTest(String message) {
		log.info("Test {}", message);
	}
}