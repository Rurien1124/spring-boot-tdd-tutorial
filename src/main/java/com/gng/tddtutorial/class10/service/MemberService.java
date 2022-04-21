package com.gng.tddtutorial.class10.service;

import java.util.Optional;

import com.gng.tddtutorial.class10.exception.MemberNotFoundException;
import com.gng.tddtutorial.class10.model.Member;
import com.gng.tddtutorial.class10.model.Study10;

public interface MemberService {
	public Optional<Member> findById(Long memberId) throws MemberNotFoundException;
	public void validate(Long memberId) throws IllegalArgumentException;
	public void notify(Study10 newStudy10);
	public void notify(Member member);
}
