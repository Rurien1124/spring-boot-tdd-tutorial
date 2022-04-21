package com.gng.tddtutorial.class10.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.gng.tddtutorial.class01.StudyStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Study10 {
	private String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT = "참석 인원은 0보다 커야합니다.";
	
	@Id
	@GeneratedValue
	private Long id;
	private StudyStatus status = StudyStatus.DRAFT;
	private int limitCount;
	private String name;
	private LocalDateTime openedDateTime;
	@ManyToOne
	private Member owner;

	public Study10(int limitCount) {
		this.limitCount = limitCount;
		
		if(limitCount < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT);
		}
	}
	
	public Study10(int limitCount, String name) {
		if(limitCount < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_MIMIT);
		}
		
		this.limitCount = limitCount;
		this.name = name;
	}
	
	public void publish() {
		this.openedDateTime = LocalDateTime.now();
		this.status = StudyStatus.OPENED;
	}

	public void open() {
		this.openedDateTime = LocalDateTime.now();
		this.status = StudyStatus.OPENED;
	}
}
