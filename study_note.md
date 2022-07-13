
## IntelliJ에서 단축키를 확실하게 검색하는 방법
  * File Settings에 들어간다.
  * 다음 화면 왼쪽에 보이는 것 처럼 keymap을 선택한다.
  * 다음 화면 오른쪽에 있는 검색창에 단축키 이름을 입력한다. 단축키 이름은 위 그림 처럼 영상 하단에나온다.
  * 다음 그림을 보면 Refactor This의 윈도우 단축키는 Ctrl + Alt + Shift + T 인 것을 알 수 있다.

## Gradle Build 설정 변경 (IntelliJ) 
  * File Settings(Ctrl+Alt+S)
  * Build, Executions, Deployment > Build Tools > Gradle  
    * Build and run using를 IntelliJ IDEA로 선택
    * Build tests using를 IntelliJ IDEA로 선택
    * Gradle JVM을 새로 설치한 자바 11로 지정

## 테스트 라이브러리
  * spring-boot-starter-test
  * junit: 테스트 프레임워크
  * mockito: 목 라이브러리
  * assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  * spring-test: 스프링 통합 테스트 지원

## 스프링 부트가 제공하는 Welcome Page 기능
  * static/index.html 을 올려두면 Welcome page 기능을 제공한다.
  * https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-welcome-page

## thymeleaf 템플릿 엔진
  * thymeleaf 공식 사이트: https://www.thymeleaf.org/
  * 스프링 공식 튜토리얼: https://spring.io/guides/gs/serving-web-content/
  * 스프링부트 메뉴얼: https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines

## MVC 
  * @ResponseBody 문자 반환
    * @ResponseBody 를 사용하면 뷰 리졸버( viewResolver )를 사용하지 않음
    * 대신에 HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님)
  * @ResponseBody 객체 반환
    * @ResponseBody 를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됨 
  * Service 작성시 repository를 직접 생성하지 않고, 생성자를 통해 주입받는것이 좋다( IOC, DI) 

## TC 작성 
  * 개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다.
  * 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한번에 실행하기 어렵다는 단점이 있다. 
  * 자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.
  * 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.

## @AfterEach  
  * 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 
  * 이렇게되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다.
  * @AfterEach 를 사용하면 각 테스트가종료될 때 마다 이 기능을 실행한다.
  * 여기서는 메모리 DB에 저장된 데이터를 삭제한다

## @BeforeEach 
  * 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고,의존관계도 새로 맺어준다.

## 스프링 빈과 의존관계
  * 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 
  * 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.
  * 이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입해준다.

## 스프링 빈을 등록하는 2가지 방법
  1. 컴포넌트 스캔과 자동 의존관계 설정
     * @Controller , @Service , @Repository 와 같은 @Component를 포함하는 어노테이션을 사용하여 스프링이 인식하고 Bean을 등록할수 있도록한다.
     * @Component 애노테이션이 있으면 스프링 빈으로 자동 등록된다. @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.
     * @Component 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
        * @Controller
        * @Service
        * @Repository
     * (기본적으로)메인 프로그램임의 패키지 하위를 탐색하여 자동 등록한다.

  2. 자바 코드로 직접 스프링 빈 등록하기
     * SpringConfig 클래스를 만들고 @Configuration 어노테이션을 달아주고  @Bean 어노테이션을 이용하여 일일이 등록해준다
     * 서비스와 리포지토리의 @Service, @Repository, @Autowired 애노테이션을 제거 한다. 

## 의존성 주입 방법 
  * DI의 방식은  필드주입 / 생성자 주입/ setter 주입 3가지 방식이 있다.
    1. 생성자 주입방법을 쓰는것이 권장된다. (의존관계가 실행중에 동적으로 변경되는경우가 없음)
    2. 필드주입: 스프링 초기구동시 외에는 변경할수 없다
    3. Setter주입: 메소드가 public으로 열려있어야 한다.

## 빈등록 방법 관례 
  * 실무에서는 주로 정형화된 컨트롤로,서비스,리파지토리 같은 코드는 컴포넌트 스캔을 사용한다.
  * 그리고 정형화 되지 않거나 상황에따라 구현 클래스를 변경해야 하는 경우는 설정 ( Configuration)  을 통해 스프링 빈을 등록한다.
     * e.g)  MemoryMemberRepository  ,   DbMemberRepository

## 테스트 
  * 단위테스트 : 스프링 컨테이너 와 상관없이 JAVA코드를 실행하는 수준의 테스트
    * 단위테스트를 잘 설계하는게 좋은 테스트일 확율이높다.
  * 통합테스트 : 스프링 컨텐이터와 같이 통합 되는 테스트
    *배민의 경우 60~70% 정도를 테스트 케이스 작성하는데 시간을 쓴다.

## Spring Data JPA
  * JPA를 Spring에서 한번 감싸는 기술
  * 꼭 JPA를 이해하고 사용하자 
  * 인터페이스 이름만으로도 데이터를 가져올수 있다. 
    * findByNamne
    * findByEmail 

## 실무에서는 아래 기술이 조합되어서 쓰이기도 한다.
  * Spring Data JPA + QueryDSL
  * Spring Data JPA + Mybatis
  * Spring Data JPA + JdbcTempalte

## AOP가 필요한 상황 
  * 공통 관심 사항

## 실전 스프링부트와 JPA 1,2
## 실전 스프링 Spring Data JPA
## 실전 Query DSL



