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
@DisplayName("Study06 �׽�Ʈ")
public class Study0601Test {
	
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(ints = {10, 20, 40}) // Integer���� �Ķ���ͷ� ����, �޼����� �Ķ���� Ÿ�Ե� �����ؾ���
	@DisplayName("�ݺ� �׽�Ʈ(�Ķ���� �̿�)")
	void parameterizedTest(
			@ConvertWith(Study06Converter.class) Study06 study06
			) {
		log.info("Test : {}", study06.getLimit());
	}
	
	/**
	 * @ValueSource�� �Էµ� �Ķ���͸� Study06 Ŭ������ �����ڿ� �°� �����ϱ� ���� Ŭ����
	 * @author gchyoo
	 *
	 */
	private static class Study06Converter extends SimpleArgumentConverter {

		/**
		 * @param source @ValueSource�� �Էµ� �Ķ����
		 * @param targetType �Ķ���͸� ��ȯ�� Ŭ���� Ÿ��
		 */
		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study06.class, targetType, "Study06 Ŭ������ �ƴմϴ�.");
			
			return new Study06(Integer.parseInt(source.toString()));
		}		
		
	}
}