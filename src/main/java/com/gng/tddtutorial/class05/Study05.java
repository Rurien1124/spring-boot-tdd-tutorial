package com.gng.tddtutorial.class05;

import com.gng.tddtutorial.class01.StudyStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Study05 {
	private StudyStatus status = StudyStatus.DRAFT;
	
	private int limit = 100;
	
	public Study05(int limit) {
		this.limit = limit;
		
		if(limit < 0) {
			throw new IllegalArgumentException("참석 인원은 0보다 커야합니다.");
		}
	}
}
