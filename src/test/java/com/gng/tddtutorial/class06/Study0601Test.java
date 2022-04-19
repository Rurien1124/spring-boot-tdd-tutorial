package com.gng.tddtutorial.class06;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Parameter
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study06 테스트")
public class Study0601Test {
	
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(ints = {10, 20, 40}) // Integer값을 파라미터로 지정, 메서드의 파라미터 타입도 변경해야함
	@DisplayName("반복 테스트(파라미터 이용)")
	void parameterizedTest(
			@ConvertWith(Study06Converter.class) Study06 study06
			) {
		log.info("Test : {}", study06.getLimit());
	}
	
	/**
	 * @ValueSource에 입력된 파라미터를 Study06 클래스의 생성자에 맞게 변경하기 위한 클래스
	 * @author gchyoo
	 *
	 */
	private static class Study06Converter extends SimpleArgumentConverter {

		/**
		 * @param source @ValueSource에 입력된 파라미터
		 * @param targetType 파라미터를 변환할 클래스 타입
		 */
		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study06.class, targetType, "Study06 클래스가 아닙니다.");
			
			return new Study06(Integer.parseInt(source.toString()));
		}		
		
	}
}