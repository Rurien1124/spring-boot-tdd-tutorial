package com.gng.tddtutorial.class06;

import com.gng.tddtutorial.class01.StudyStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Study06 {
	private String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT = "���� �ο��� 0���� Ŀ���մϴ�.";
	private StudyStatus status = StudyStatus.DRAFT;
	
	private int limit = 100;
	private String name;
	
	public Study06(int limit) {
		this.limit = limit;
		
		if(limit < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT);
		}
	}
	
	public Study06(int limit, String name) {
		this.limit = limit;
		this.name = name;
		
		if(limit < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT);
		}
	}
}
