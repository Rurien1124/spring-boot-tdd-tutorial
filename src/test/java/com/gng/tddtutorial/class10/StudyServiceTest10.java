package com.gng.tddtutorial.class10;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gng.tddtutorial.class10.exception.MemberNotFoundException;
import com.gng.tddtutorial.class10.model.Member;
import com.gng.tddtutorial.class10.model.Study10;
import com.gng.tddtutorial.class10.repository.StudyRepository;
import com.gng.tddtutorial.class10.service.MemberService;
import com.gng.tddtutorial.class10.service.StudyService;

/**
 * Mockito
 * @author gchyoo
 *
 */
@ExtendWith(MockitoExtension.class) // @Mock을 사용하기 위해 등록해주어야 함
public class StudyServiceTest10 {
	
	// 1. Mockito의 @Mock 어노테이션으로 Mocking할 인스턴스를 지정
	@Mock
	private MemberService memberService;
	
	@Mock
	private StudyRepository studyRepository;
	
	// 1. @Mock 어노테이션으로 지정된 인스턴스를 @InjectMocks로 지정된 인스턴스에 주입
	@InjectMocks
	private StudyService studyService;
	
	
	// StudyService의 인스턴스를 생성해야 함
	// MemberService와 Studyrepository가 구현되지 않은 상태이므로 Mock을 사용하여야 함
	
	/**
	 * 2. Mockito의 mock 함수를 이용하여 가짜 인스턴스를 주입하는 방법
	 */
//	@Test
//	void createStudyService() {
//		// StudyService의 생성자에 주입할 MemberService와 StudyRepository를 Mock
//		MemberService memberService = Mockito.mock(MemberService.class);
//		StudyRepository studyRepository = Mockito.mock(StudyRepository.class);
//		
//		StudyService studyService = new StudyService(memberService, studyRepository);
//		
//		assertNotNull(studyService);
//	}

	/**
	 * 3. @Mock 어노테이션을 파라미터로 주입하는 방법
	 */
//	@Test
//	void createStudyService(
//		@Mock MemberService memberService,
//		@Mock StudyRepository studyRepository
//		) {
//		StudyService studyService = new StudyService(memberService, studyRepository);
//		
//		assertNotNull(studyService);
//	}
	

	@Test
	@DisplayName("단일 Stubbing 테스트")
	void createStudyService01() {
		assertNotNull(studyService);
		
		// Given
		// 1. 필요한 값 준비
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@email.com");
		
		Study10 study10 = new Study10(10, "java");
		
		// 2. 실제 구현코드가 없는 memberService 인터페이스의 findById() 메서드의 리턴을 미리 생성해 둔 member 객체로 약속
		// 이를 'Stubbing' 이라고 함
//		Mockito.when(memberService.findById(1L)) // Stubbing : 1로 호출이 되'면'(when)
//				.thenReturn(Optional.of(member)); // member를 리턴하라
		Mockito.when(memberService.findById(any())) // findById()가 아무 값(any)로 호출이 되'면'(when)
				.thenReturn(Optional.of(member)); // member를 리턴하라

		Mockito.doThrow(new IllegalArgumentException()) // 예외를 던져라
				.when(memberService) // memberService의
				.validate(any()); // validate()가 호출되면

		// When
		// 3. '2'에서 지정한 findById() 메서드를 호출
		// (이 때, Mockito.when에서 지정해 준 값과 동일한 값(1L)이 들어가야 정상적으로 작동)
		// (any()를 사용할 시에는 해당 파라미터 타입과 동일하면 작동)
		Member memberFound = memberService.findById(1L).orElseThrow(
				() -> new MemberNotFoundException()
				);
		
		// Then
		studyService.createNewStudy(memberFound.getId(), study10);
		
		// validate를 호출할 시 IllegalArgumentException을 throw 하도록 Stubbing 하였으므로
		// assertThrows를 이용하여 확인
		assertThrows(IllegalArgumentException.class,
				() -> memberService.validate(1L)
				);
	}

	@Test
	@DisplayName("다중 Stubbing 테스트")
	void createStudyService02() {
		assertNotNull(studyService);
		
		// Given
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@email.com");
		
		// 여러 개의 Stubbing을 이어서 지정할 수 있음
		// 동일한 메서드를 여러 번 호출하였을 때 다르게 동작하도록
		Mockito.when(memberService.findById(any())) // 아무 값(any)로 호출이 되'면'(when)
				.thenReturn(Optional.of(member)) // 첫번째는 member를 리턴
				.thenThrow(new RuntimeException()) // 두번째는 RuntimeException을 throw
				.thenReturn(Optional.empty()); // 세번째는 비어있는 객체를 리턴

		// When
		
		// Then
		// 첫번째 호출은 member를 리턴
		memberService.findById(any());
		
		// 두번째 호출은 RuntimeException을 throw
		assertThrows(RuntimeException.class, 
				() -> memberService.findById(any())
				);
		
		// 세번째 호출은 비어있는 객체를 리턴
		assertEquals(Optional.empty(), memberService.findById(any()));
	}

	@Test
	@DisplayName("Stubbing 연습")
	void createStudyService03() {
		assertNotNull(studyService);
		
		// Given
		Study10 study10 = new Study10(10, "테스트");
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@email.com");
		
		// findById(1L)을 호출하면 member를 리턴하도록 설정
		Mockito.when(memberService.findById(1L))
				.thenReturn(Optional.of(member));
		
		// save(study10)을 호출하면 study10을 리턴하도록 설정
		Mockito.when(studyRepository.save(study10))
				.thenReturn(study10);
		
		// When
		Study10 newStudy10 = studyService.createNewStudy(1L, study10);
		Member newMember10 = memberService.findById(1L).get();
		
		// Then
		assertAll(
				() -> assertEquals(member, newMember10),
				() -> assertEquals(study10, newStudy10)
		);
	}
}
