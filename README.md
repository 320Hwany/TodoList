## todoCalculator

첫 화면에서 로그인과 회원가입을 할 수 있도록 만들었고 로그인을 하여 들어가면 todo List, Calculator를 사용할 수 있도록 구현할 것이다.

## 개발 환경

Spring framework

project : Gradle project         
Language : java         
Spring boot : 2.7.4         
Dependencies : Spring Web, Spring Data JPA, Lombok, Thymeleaf, H2 Database, Validation

## trouble shooting

[MemberDto](https://github.com/yhwjd/todoCalculator/blob/master/src/main/java/toyproject/todoCalculator/todo/dto/MemberDto.java)  
Controller 에서 @ModelAttribute를 사용하여 MemberDto의 데이터를 넣으려면 @Setter를 넣어주어야 한다...!  

[Controller](https://github.com/yhwjd/todoCalculator/blob/master/src/main/java/toyproject/todoCalculator/todo/Controller.java)       
localhost:8080을 로그인 창으로 설정하였다. 그러면 Getmapping("/")을 해주지 않아도 알아서 index.html의 파일을 불러온다. 하지만 Postmapping("/")을 사용하니
@Getmapping("/")을 해주어야 오류가 발생하지 않았다.

## 앞으로 처리해야할 문제들  

@org.springframework.stereotype.Controller 이거 왜 축약이 안될까...  
회원가입에서 최소 필수 조건 넣기, 비밀번호 DB에 저장할 때 
