package com.gng.tddtutorial.class12;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gng.tddtutorial.class01.StudyStatus;
import com.gng.tddtutorial.class10.model.Member;
import com.gng.tddtutorial.class10.model.Study10;
import com.gng.tddtutorial.class10.repository.StudyRepository;
import com.gng.tddtutorial.class10.service.MemberService;
import com.gng.tddtutorial.class10.service.StudyService;

/**
 * Mockito BDD(Behavior-Driven Development) style API
 * @author gchyoo
 *
 */
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest12 {

	@Mock
	private MemberService memberService;
	
	@Mock
	private StudyRepository studyRepository;
	
	@InjectMocks
	private StudyService studyService;
	

	@Test
	@DisplayName("BDD 스타일 테스트")
	void createNewStudy01() {
		assertNotNull(studyService);
		
		// 1. Given : 주어진 상황에서
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@email.com");
		
		Study10 study10 = new Study10(10, "java");
		
		BDDMockito.given(memberService.findById(any())) // Mockito.when() => BDDMockito.given()
				.willReturn(Optional.of(member)); // Mockito.thenReturn() => BDDMockito.willReturn()
		BDDMockito.given(studyRepository.save(study10))
				.willReturn(study10);
		
		// 2. When : 어떤 행동을 하면
		studyService.createNewStudy(1L, study10);
		
		// 3. Then : 이런 결과가 나올 것이다
		// AS-IS : verify(memberService, times(1)).notify(any(Study10.class));
		// TO-BE
		BDDMockito.then(memberService)
				.should(times(1))
				.notify(any(Study10.class));
		
		// AS-IS : verify(memberService, never()).validate(any());
		// TO-BE
		BDDMockito.then(memberService)
				.should(never())
				.notify(any(Member.class));

		// AS-IS : Mockito.verifyNoMoreInteractions(memberService);
		// TO-BE
		BDDMockito.then(memberService)
				.shouldHaveNoMoreInteractions();
		
	}
	
	@Test
	@DisplayName("스터디 공개 테스트")
	void openStudy() {
		// Given
		Study10 study10 = new Study10(10, "Java");
		
		BDDMockito.given(studyRepository.save(study10))
				.willReturn(study10);
		
		// When
		studyService.openStudy(study10);
		
		// Then
		assertAll(
				() -> assertEquals(StudyStatus.OPENED, study10.getStatus()),
				() -> assertNotNull(study10.getOpenedDateTime())
				);
		BDDMockito.then(memberService)
				.should(times(1))
				.notify(any(Study10.class));
	}
}
