package com.gng.tddtutorial.class11;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gng.tddtutorial.class10.model.Member;
import com.gng.tddtutorial.class10.model.Study10;
import com.gng.tddtutorial.class10.repository.StudyRepository;
import com.gng.tddtutorial.class10.service.MemberService;
import com.gng.tddtutorial.class10.service.StudyService;

/**
 * Mockito object
 * @author gchyoo
 *
 */
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest11 {

	@Mock
	private MemberService memberService;
	
	@Mock
	private StudyRepository studyRepository;
	
	@InjectMocks
	private StudyService studyService;
	

	@Test
	@DisplayName("Verify(호출 횟수) 테스트")
	void createNewStudy01() {
		assertNotNull(studyService);
		
		// Given
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@email.com");
		
		Study10 study10 = new Study10(10, "java");
		
		Mockito.when(memberService.findById(any()))
				.thenReturn(Optional.of(member));
		Mockito.when(studyRepository.save(study10))
				.thenReturn(study10);
		
		// When
		studyService.createNewStudy(1L, study10);
		
		// Then
		// 메서드가 몇 번 호출이 되었는지 확인할 수 있음
		// notify()가 한 번만 호출(times(1))되어야 하고, 호출되지 않거나 2번 이상 호출되면 테스트 실패
		// createNewStudy() 함수 내부에서 notify()가 실행되므로 1회 호출됨
		verify(memberService, times(1)).notify(any(Study10.class));
		
		// never()는 호출되지 않아야 하고, 호출되면 테스트 실패
		verify(memberService, never()).validate(any());
	}

	@Test
	@DisplayName("Order(호출 순서) 테스트")
	void createNewStudy02() {
		assertNotNull(studyService);
		
		// Given
		// 1. 필요한 값 준비
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@email.com");
		
		Study10 study10 = new Study10(10, "java");
		
		Mockito.when(memberService.findById(any())) // findById()가 아무 값(any)로 호출이 되'면'(when)
				.thenReturn(Optional.of(member)); // member를 리턴하라
		Mockito.when(studyRepository.save(study10))
				.thenReturn(study10);
		
		// When
		studyService.createNewStudy(1L, study10);
		
		// Then
		InOrder inOrder = Mockito.inOrder(memberService);
		
		// notify(Study10 study10)이 호출되고 나서 notify(Member member)가 호출되어야 함
		inOrder.verify(memberService).notify(any(Study10.class));
		inOrder.verify(memberService).notify(any(Member.class));
		
		// MemberService가 더 이상 호출되면 안 됨으로 설정
		// 특정 액션 이후에 더 이상 해당 Mock이 사용되지 않도록 함
		Mockito.verifyNoMoreInteractions(memberService);
	}
}
