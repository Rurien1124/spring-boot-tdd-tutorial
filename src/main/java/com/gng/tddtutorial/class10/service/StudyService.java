package com.gng.tddtutorial.class10.service;

import java.util.Optional;

import com.gng.tddtutorial.class10.model.Member;
import com.gng.tddtutorial.class10.model.Study10;
import com.gng.tddtutorial.class10.repository.StudyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudyService {
	private final MemberService memberService;
	private final StudyRepository studyRepository;
	
	public Study10 createNewStudy(Long memberId, Study10 study10) {
		Optional<Member> member = memberService.findById(memberId);
		study10.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist")));
		
		Study10 newStudy10 = studyRepository.save(study10);
		memberService.notify(newStudy10);
		memberService.notify(member.get());
		
		return newStudy10;
	}

	public Study10 openStudy(Study10 study10) {
		study10.open();
		Study10 openedStudy10 = studyRepository.save(study10);
		memberService.notify(openedStudy10);
		
		return openedStudy10;
	}
}
