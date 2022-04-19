package com.gng.tddtutorial.class09;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.gng.tddtutorial.class04.SlowTest;

import lombok.extern.slf4j.Slf4j;

/**
 * Extension for find slow tests
 * @author gchyoo
 *
 */
@Slf4j
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
	
	private long threshold = 1000L;
	
	public FindSlowTestExtension(long threshold) {
		this.threshold = threshold;
	}
	
	/*
	 * context에는 store가 있고, 값을 저장하고 조회할 수 있음
	 */

	/**
	 * 테스트 메서드 실행 전 인터셉트
	 */
	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		Store store = getStore(context);
		
		store.put("START_TIME", System.currentTimeMillis());
	}

	/**
	 * 테스트 메서드 실행 후 인터셉트
	 */
	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		Method requiredTestMethod = context.getRequiredTestMethod();
		SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
		
		if(annotation == null) {
			Store store = getStore(context);
			
			long startTime = store.remove("START_TIME", long.class);
			long duration = System.currentTimeMillis() - startTime;
			
			if(duration > threshold) {
				log.warn("Please consider mark method [{}()] with @SlowTest.", context.getRequiredTestMethod().getName());
			}
		}
	}

	private Store getStore(ExtensionContext context) {
		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		
		// store를 가져와서 값을 조회
		return context.getStore(Namespace.create(testClassName, testMethodName));
	}
	
}
