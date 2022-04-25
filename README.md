> # 스프링 부트 TDD 연습 프로젝트

- class01 : 기본 어노테이션, Assert
```
@Test             : 테스트 할 메서드
@DisplayName      : 테스트 콘솔에 표시할 테스트 명칭
@BeforeAll        : 모든 테스트가 실행되기 전에 실행되는 메서드
@AfterAll         : 모든 테스트가 실행된 후에 실행되는 메서드
@BeforeEach       : 각각의 테스트가 실행되기 전에 실행되는 메서드
@AfterEach        : 각각의 테스트가 실행된 후에 실행되는 메서드

assertEquals      : expected와 actual이 동일한지 확인
assertThrows      : expected의 Exception 타입이 executable에서 throw하는 Exception의 타입과 동일한지 확인
assertTimeout     : expected의 Duration(ofMills, ofSeconds ...)가 executable의 실행시간 이내인지 확인
assertAll         : 람다 형식으로 여러개의 assert* 메서드를 호출하여 실행
```

- class02 : 조건 어노테이션
```
@EnabledOnOs      : OS(OS.WINDOWS, OS.MAC ...) 조건에 따른 테스트 분리
@EnabledOnJre     : JRE 버전(JRE.JAVA_8, JRE.JAVA_9 ...)에 따른 테스트 분리
@EnabledIfEnvironmentVariable : 환경변수 값에 따른 테스트 분리
```

- class03 : 태깅
```
@Tag              : Eclipse 기준 'Run configurations > Include and exclude tags > Configure'에
                  설정된 태그 조건에 따라 메서드 실행
```

- class04 : 커스텀 태그
```
@CustomTest       : 중복되는 테스트 어노테이션을 묶어 만든 커스텀 어노테이션
  ├ @Target
  ├ @Retention
  ├ @Test
  └ @Tag
```

- class05 : 반복 테스트
```
@RepeatedTest     : 지정된 횟수만큼 테스트 반복
@ParameterizedTest: 테스트 콘솔에 표시할 명칭
                    {index}       : 현재 반복 횟수
                    {displayName} : @DisplayName에 지정한 이름
                    {n}           : n(숫자), 입력된 파라미터의 번호(args[] 개념)
@EmptySource      : 비어있는 문자열을 파라미터에 추가
@NullSource       : Null을 파라미터에 추가
@NullAndEmptySource : @EmptySource + @NullSource이며, 셋 다 존재할 경우 @NullAndEmptySource만이 실행됨
```

- class06 : 반복 테스트(Argument)
```
@ValueSource      : Primitive 값을 배열 형식으로 테스트에 전달
@ConvertWith      : @ValueSource에 입력된 값을 SimpleArgumentConverter를 상속받은 Converter 클래스에서 Override하고,
                    @ConvertWith(CustomConverter.class)를 변환하기 원하는 메서드의 파라미터 앞에 붙여 실행

@CsvSource        : CSV 형태로 파라미터를 입력받을 수 있으며, 공백이 들어가는 문자열은 따옴표로 묶어서('white space') 구분하고
                    콤마(,)로 각 파라미터의 index를 구분({"01-01, '01 02'", "02-01, 02-02", "03-01, 03-02"})
                    ArgumentsAccessor의 'getInteger(index), getString(index)' 등을 사용하여 몇 번째 파라미터를 사용할 지 지정
@AggregateWith    : ArgumentsAggregator 인터페이스를 구현한 CustomArgumentsAggregator를 사용하며
                    내부 동작은 ArgumentsAccessor의 행동을 정의
```

- class07 : 테스트 인스턴스
```
@TestInstance
  ├ LifeCycle.PER_METHOD  : Default 설정이며, 모든 테스트 메서드는 개별 인스턴스를 가지게 됨(전역 변수를 포함)
  └ LifeCycle.PER_CLASS   : 동일한 클래스의 테스트 메서드는 동일한 인스턴스를 가지게 됨
```

- class08 : 테스트 순번
```
@TestMethodOrder  : AlphaNumeric/Annotation/OrderAnnotation 등을 사용할 수 있으며, @Order를 사용하기 위해 선언
@Order            : 테스트 메서드의 실행 순서를 지정
```

- class09 : 확장 모델
```
BeforeTestExecutionCallback : 상속받은 BeforeTestExecution 메서드를 overrride하여 테스트 메서드 실행 전 인터셉트
BeforeTestExecutionCallback : 상속받은 AfterTestExecutionCallback 메서드를 overrride하여 테스트 메서드 실행 후 인터셉트
                              상속 가능한 클래스는 Javadoc에 상세히 기록되어 있음
@RegisterExtension          : 상속받아 구현한 확장 모델을 등록
```

- class10 : Mockito 기본

  - given : 상황을 주고 예상되는 결과를 지정해 둠(when에서 사용하기 위해)
  - when  : 어떠한 행동을 수행할 때(테스트 할 메서드를 실행)
  - then  : 이러한 결과가 나올 것이다(assert(), then() 등)
```
@Mock                             : 실제로 사용할 필요가 없는, 또는 구현되지 않은 클래스/인터페이스의 가짜 객체를 복제하여 사용
@InjectMocks                      : @Mock 어노테이션으로 지정된 객체를 @InjectMocks로 지정된 객체에 주입

Mockito.when().thenReturn()       : 특정 함수가 실행될 때(when()), 그 결과(thenReturn)를 특정 값으로 지정
Mockito.doThrow().when().method() : 특정 함수(method())가 실행될 때(when()), 예외(doThrow())를 throw 하도록 지정
```

- class11 : Mockito 오브젝트
```
Mockito.verify().method()         : 메서드가 몇 번 호출되었는지 확인(times(n), never() ...)
Mockito.inOrder().verify()        : 메서드의 호출 순서를 지정
Mockito.verifyNoMoreInteractions(): 해당 Mock 객체가 더이상 사용되지 않는지 확인
```

- class12 : BDD(Behavior Driven Development) Mockito
```
BDDMockito.given().willReturn()     : Mockito.when().thenReturn()이 BDD 스타일로 변화된 형태
BDDMockito.then().should().method() : Mockito.verify().method()가 BDD 스타일로 변화된 형태
BDDMockito.then().shouldHaveNoMoreInteractions  : Mockito.verifyNoMoreInteractions()가 BDD 스타일로 변화된 형태
```
