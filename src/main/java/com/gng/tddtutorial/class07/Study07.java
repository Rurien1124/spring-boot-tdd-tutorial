package com.gng.tddtutorial.class07;

import com.gng.tddtutorial.class01.StudyStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Study07 {
	private String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT = "참석 인원은 0보다 커야합니다.";
	private StudyStatus status = StudyStatus.DRAFT;
	
	private int limit = 100;
	private String name;
	
	public Study07(int limit) {
		this.limit = limit;
		
		if(limit < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT);
		}
	}
	
	public Study07(int limit, String name) {
		this.limit = limit;
		this.name = name;
		
		if(limit < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT);
		}
	}
}
