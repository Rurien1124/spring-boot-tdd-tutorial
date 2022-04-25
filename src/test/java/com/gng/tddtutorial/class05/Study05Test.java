package com.gng.tddtutorial.class05;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Repeated test
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study05 테스트")
public class Study05Test {
	
	@RepeatedTest(value = 10, // 테스트 반복
			name = "{displayName}, {currentRepetition}/{totalRepetitions}") // 반복 시 표시할 명칭
	@DisplayName("반복 테스트") // 테스트 명칭(RepeatedTest의 상위 레벨)
	void repeatTest(RepetitionInfo repetitionInfo) {
		log.info("Test : {}", repetitionInfo.getCurrentRepetition());
	}
	
	@ParameterizedTest(name = "{index} {displayName} message={0}") // 테스트 반복(파라미터 이용)
	@ValueSource(strings = {"테스트", "파라미터", "데이터"}) // String 값을 파라미터로 추가(ints, booleans 등이 있음)
	@CsvSource({"java, 8"}) // CSV 값을 파라미터로 추가
	@EmptySource // 비어있는 문자열을 파라미터로 추가(@ValueSource와 별개로 추가됨)
	@NullSource // Null을 파라미터로 추가
	@NullAndEmptySource // 비어있는 문자열과 Null을 파라미터로 추가(@EmptySource, @NullSource는 덮어씌워짐)
	@DisplayName("반복 테스트(파라미터 이용)")
	void parameterizedTest(String message) {
		log.info("{}", message);
	}
}