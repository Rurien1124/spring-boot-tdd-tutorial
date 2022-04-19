package com.gng.tddtutorial.class06;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

import lombok.extern.slf4j.Slf4j;

/**
 * CSV
 * @author gchyoo
 *
 */
@Slf4j
@DisplayName("Study06 테스트")
public class Study0602Test {
	
	/**
	 * <pre>
	 * CSV 값을 Primitive 값으로 받아
	 * Study06 클래스의 생성자로 객체를 생성하는 방식 
	 * </pre>
	 * @param limit
	 * @param name
	 */
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, 'TDD Study'", "20, 'Spring boot'"}) // CSV 값을 파라미터로 입력, 공백이 들어가는 문자열은 ''로 묶어서 입력
	@DisplayName("반복 테스트(CSV 이용)")
	void parameterizedTestWithPrimitiveType(
			Integer limit, String name // @CsvSource에서 입력한 값을 콤마로 구분하여 파라미터로 입력받음
			) {
		Study06 study06 = new Study06(limit, name);
		log.info("[{}] {}", study06.getName(), study06.getLimit());
	}
	
	/**
	 * <pre>
	 * ArgumentsAccessor를 사용하여 CSV값을 받아
	 * Study06 클래스의 생성자로 객체를 생성하는 방식
	 * </pre>
	 * @param argumentsAccessor
	 */
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, 'TDD Study'", "20, 'Spring boot'"}) // CSV 값을 파라미터로 입력, 공백이 들어가는 문자열은 ''로 묶어서 입력
	@DisplayName("반복 테스트(ArgumentsAccessor 이용)")
	void parameterizedTestWithArgumentsAccessor(ArgumentsAccessor argumentsAccessor) {
		Study06 study06 = new Study06(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		log.info("[{}] {}", study06.getName(), study06.getLimit());
	}
	
	/**
	 * <pre>
	 * ArgumentsAggregator를 사용하여 CSV값을 받아
	 * Study06 클래스로 파라미터를 받는 방식
	 * </pre>
	 * @param study06
	 */
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, 'TDD Study'", "20, 'Spring boot'"}) // CSV 값을 파라미터로 입력, 공백이 들어가는 문자열은 ''로 묶어서 입력
	@DisplayName("반복 테스트(ArgumentsAggregator 이용)")
	void parameterizedTestWithArgumentsAggregator(
			@AggregateWith(value = Study06Aggregator.class) Study06 study06
			) {
		log.info("[{}] {}", study06.getName(), study06.getLimit());
	}
	
	/**
	 * @CsvSource에 입력된 파라미터를 Study06 클래스의 생성자에 맞게 변경하기 위한 클래스
	 * @author gchyoo
	 *
	 */
	private static class Study06Aggregator implements ArgumentsAggregator {

		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
				throws ArgumentsAggregationException {
			return new Study06(accessor.getInteger(0), accessor.getString(1));
		}		
		
	}
}