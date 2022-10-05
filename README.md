## todoCalculator

첫 화면에서 로그인과 회원가입을 할 수 있도록 만들었고 로그인을 하여 들어가면 todo List, Calculator를 사용할 수 있도록 구현할 것이다.

## 개발 환경

Spring framework

project : Gradle project         
Language : java 11       
Spring boot : 2.6.11        
Dependencies : Spring Web, Spring Data JPA, Spring Security, Lombok, Thymeleaf, H2 Database, Validation

## trouble shooting

* [MemberDto](https://github.com/yhwjd/todoCalculator/blob/master/src/main/java/toyproject/todoCalculator/todo/dto/MemberDto.java)  
Controller 에서 @ModelAttribute를 사용하여 MemberDto의 데이터를 넣으려면 @Setter를 넣어주어야 한다...!  

* Controller
localhost:8080을 로그인 창으로 설정하였다. 그러면 Getmapping("/")을 해주지 않아도 알아서 index.html의 파일을 불러온다. 하지만 Postmapping("/")을 사용하니
@Getmapping("/")을 해주어야 오류가 발생하지 않았다.

* 밑의 코드처럼 name, id 부분을 username으로 설정했어야 하는데.. 그냥 name으로 설정해 한참을 헤매었다... 
```
<input type="text" name="username" id="username">
```

* 로그인 정보를 @ModelAttribute로 받아오려고 했는데 잘 안되었다. Spring Security를 이용하면 login을 쉽게 처리해주는데 이때는 Principal을 이용해  
로그인한 사용자를 찾았다. 또한 redirect 할 때 {id} 와 같이 쓰려면 RedirectAttributes 를 이용해 addAttribute를 한 후에 가능하다!!
```
    @GetMapping("/menu") 
    public String menu(Principal principal, RedirectAttributes redirectAttributes) {

        String name = principal.getName();
        Member member = memberService.findMemberByName(name);
        Long id = member.getId();
        redirectAttributes.addAttribute("id", id); // redirect 로 파라미터 넘기려면 필요하다
        return "redirect:/menu/{id}";
    }
```

## 앞으로 처리해야할 문제들  

@org.springframework.stereotype.Controller 이거 왜 축약이 안될까...  
회원가입에서 최소 필수 조건 넣기, 비밀번호 DB에 저장할 때 암호화 하기
