package com.gng.tddtutorial.class06;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Target(ElementType.METHOD) // �޼ҵ忡 ��� ������ ������̼�
@Retention(RetentionPolicy.RUNTIME) // ������̼� ������ ��Ÿ�� �������� ����
@Test // �׽�Ʈ �뵵�� ������̼�
@Tag("slow") // �±� ����
public @interface SlowTest {
	
}
