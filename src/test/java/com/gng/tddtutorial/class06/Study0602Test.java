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
@DisplayName("Study06 �׽�Ʈ")
public class Study0602Test {
	
	/**
	 * <pre>
	 * CSV ���� Primitive ������ �޾�
	 * Study06 Ŭ������ �����ڷ� ��ü�� �����ϴ� ��� 
	 * </pre>
	 * @param limit
	 * @param name
	 */
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, 'TDD Study'", "20, 'Spring boot'"}) // CSV ���� �Ķ���ͷ� �Է�, ������ ���� ���ڿ��� ''�� ��� �Է�
	@DisplayName("�ݺ� �׽�Ʈ(CSV �̿�)")
	void parameterizedTestWithPrimitiveType(
			Integer limit, String name // @CsvSource���� �Է��� ���� �޸��� �����Ͽ� �Ķ���ͷ� �Է¹���
			) {
		Study06 study06 = new Study06(limit, name);
		log.info("[{}] {}", study06.getName(), study06.getLimit());
	}
	
	/**
	 * <pre>
	 * ArgumentsAccessor�� ����Ͽ� CSV���� �޾�
	 * Study06 Ŭ������ �����ڷ� ��ü�� �����ϴ� ���
	 * </pre>
	 * @param argumentsAccessor
	 */
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, 'TDD Study'", "20, 'Spring boot'"}) // CSV ���� �Ķ���ͷ� �Է�, ������ ���� ���ڿ��� ''�� ��� �Է�
	@DisplayName("�ݺ� �׽�Ʈ(ArgumentsAccessor �̿�)")
	void parameterizedTestWithArgumentsAccessor(ArgumentsAccessor argumentsAccessor) {
		Study06 study06 = new Study06(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		log.info("[{}] {}", study06.getName(), study06.getLimit());
	}
	
	/**
	 * <pre>
	 * ArgumentsAggregator�� ����Ͽ� CSV���� �޾�
	 * Study06 Ŭ������ �Ķ���͸� �޴� ���
	 * </pre>
	 * @param study06
	 */
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, 'TDD Study'", "20, 'Spring boot'"}) // CSV ���� �Ķ���ͷ� �Է�, ������ ���� ���ڿ��� ''�� ��� �Է�
	@DisplayName("�ݺ� �׽�Ʈ(ArgumentsAggregator �̿�)")
	void parameterizedTestWithArgumentsAggregator(
			@AggregateWith(value = Study06Aggregator.class) Study06 study06
			) {
		log.info("[{}] {}", study06.getName(), study06.getLimit());
	}
	
	/**
	 * @CsvSource�� �Էµ� �Ķ���͸� Study06 Ŭ������ �����ڿ� �°� �����ϱ� ���� Ŭ����
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