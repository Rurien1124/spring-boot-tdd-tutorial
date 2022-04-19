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
@DisplayName("Study05 테스트")
public class Study05Test {
	
	@RepeatedTest(value = 10, // 테스트 반복
			name = "{displayName}, {currentRepetition}/{totalRepetitions}") // 반복 시 표시할 명칭
	@DisplayName("반복 테스트") // 테스트 명칭(RepeatedTest의 상위 레벨)
	public void repeatTest(RepetitionInfo repetitionInfo) {
		log.info("Test : {}", repetitionInfo.getCurrentRepetition());
	}
	
	@ParameterizedTest(name = "{index} {displayName} message={0}") // 테스트 반복(파라미터 이용)
	@ValueSource(strings = {"테스트", "파라미터", "데이터"}) // 반복 시 사용할 파라미터
	@DisplayName("반복 테스트(파라미터 이용)")
	public void parameterizedTest(String message) {
		log.info("Test {}", message);
	}
}