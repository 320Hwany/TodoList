## todoCalculator

첫 화면에서 로그인과 회원가입을 할 수 있도록 만들었고 로그인을 하여 들어가면 각자의 TodoList를 등록하고 그 리스트를 볼 수 있게 구현하였다.

## 개발 환경

Spring framework

project : Gradle project         
Language : java 11       
Spring boot : 2.6.11        
Dependencies : Spring Web, Spring Data JPA, Spring Security, Lombok, Thymeleaf, H2 Database, Validation

## trouble shooting

* Controller 에서 @ModelAttribute를 사용하여 MemberDto의 데이터를 넣으려면 @Setter를 넣어주어야 한다...!  
```
@NoArgsConstructor(access = AccessLevel.PROTECTED)
```
기본 생성자의 accessLevel을 protected나 privated으로 올리면 매개 변수가 존재하는 생성자로 객체생성을 하기 때문에  
@Setter없이도 get이든 post이든 받아올 수 있다.

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

* 밑의 코드에서 th:method='post'를 안썼다... @PostMapping을 해도 저걸 안쓰니 매핑이 안되었다...
```
<form th:action="@{/makeTodo}" th:method="post">
```

* @SpringBootTest 스프링 통합 테스트로 해야 memberService 가져올 수 있다. 없으면 @Autowired가 안된다.  
또한 테스트 돌릴 때 DB에 연결안하고 테스트를 했었다. 

* @GeneratedValue 로 id를 자동으로 생성해주는 것은 데이터 베이스에 연결할 때 일어난다. 테스트 코드를 작성할 때 Member 객체를 생성할 때는  
Member에 id가 부여되지 않고 null이다. memberService.join()을 할 때 id가 부여되는 것이다. 또한 join을 해도 member.getId()는 null인데   
Builder로 join을 할 때 auth도 넣어주어서 서로 다른 객체이다! 

* member를 삭제할 때 todos도 같이 삭제가 되어야 한다. 

```
@OneToMany(mappedBy = "member", orphanRemoval = true)
 ```

## 앞으로 처리해야할 문제들  

* @org.springframework.stereotype.Controller 이거 왜 축약이 안될까... 해결 : 클래스 이름이 Controller 이면 안된다.  
* 각 회원마다 자신만의 todo List가 있다. 로그인을 할 때 사용자 정보를 받아서 각자의 페이지를 보여주기  
* 로그인 후 그 멤버인지 기억하려고 계속 member.id 를 사용해서 매핑했다. 이렇게 하지 않고 뭔가 다른 방법이 있을 것 같은데.. 
* Member에 Setter 넣어주는 형태는 지양해야하는데... 테스트 코드를 작성하려면 필요하다.. 
* 비밀번호가 암호화 되어 DB에 저장되어 있다. 나중에 로그인하고 DB에 있는 비밀번호와 일치하는지 어떻게 확인할까..

