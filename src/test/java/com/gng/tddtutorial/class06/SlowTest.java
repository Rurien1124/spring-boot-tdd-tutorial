package com.gng.tddtutorial.class06;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Target(ElementType.METHOD) // 메소드에 사용 가능한 어노테이션
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보를 런타임 시점까지 유지
@Test // 테스트 용도의 어노테이션
@Tag("slow") // 태그 설정
public @interface SlowTest {
	
}
