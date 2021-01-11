package com.doksam.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.model.RoleType;
import com.doksam.blog.model.User;
import com.doksam.blog.repository.UserRepository;

@RestController
public class DummyContollerTest {

	@Autowired // 의존성 주입(DI) : DummyContollerTest 메모리에 올라 갈 때, UserRepository로 올라간다.
	private UserRepository userRepository;
	//userRepository.save()
	//id를 전달하지 않으면 insert를 해주고,
	//id를 전달하면 해당 id에 대한 데이터가 있으면 update
	//id를 전달하면 해당 id에 대한 데이터가 없으면 insert

	
	// http://localhost:8000/blog/dummy/user/1
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);	
		}catch( EmptyResultDataAccessException e) { //Exception 타입 모르겠으면 Exception e로 처리해도 됨.
			//return "삭제에 실패하였습니다 해당 아이디는 없습니다. id : " + id; //여기서 끝.
			throw new EmptyResultDataAccessException( "삭제에 실패하였습니다 해당 아이디는 없습니다. id : " + id, 0);// Exception Text 수정
		}
		
		return "삭제되었습니다 . id : " + id;
	}
	
	
	// http://localhost:8000/blog/dummy/user/1
	@Transactional //함수 종료 시에 자동 commit
	@PutMapping("/dummy/user/{id}") //email, password
	//@RequestBody json 데이터 요청 => Java Object 변환 ( MessageConverter의 Jackson 라이브러리가 변환해 줌 )
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		System.out.println("위 코드가 Exception Throw 던져서 여긴 출력 안 됨");
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());		
		//userRepository.save(user);//save 대신 @Transactional 사용
		return user;	
	}//더티 체킹 : 수정 사항이 없으면 @Transactional로 발생하는 commit 작업하지 않는다.
	
	// http://localhost:8000/blog/dummy/users
	@GetMapping("dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	// 한 페이지 당 n건의 데이터를 리턴 받기
	// http://localhost:8000/blog/dummy/user
	// http://localhost:8000/blog/dummy/user?page=0
	@GetMapping("dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		if (pagingUser.isLast()) {
			// ~~~
		}
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	/*
	 * //페이지 정보를 브라우저에서 추가로 받고 싶다면 public Page<User>
	 * pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC)
	 * Pageable pageable){ Page<User> users = userRepository.findAll(pageable);
	 * return users; }
	 */

	// {id} 주소로 파라미터를 전달 받을 수 잇음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// userRepository.findById(id); // return optional
		// user /4 를 찾는데 데이터베이스에서 못 찾으면 user가 null return하기 때문에 optional로 User객체를 감싸서
		// null 판단 할 수 있도록

		// User user = userRepository.findById(id).get(); //무조건 있다. 오류 메시지 설명 X

		/*
		 * User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
		 * 
		 * @Override public User get() { return new User(); //null 리턴 } });
		 */

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});

		/*
		 * //JAVA 1.8 이상 lambda식 User user =
		 * userRepository.findById(id).orElseThrow(()->{ return new
		 * IllegalArgumentException("해당 유저는 없습니다. id : " + id); })
		 */

		// 요청은 웹 브라우저로 해줬는데 RestController가 Data를 리턴해준다.
		// user 객체 = 자바 오브젝트
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터 ) -> json ( 예전에는 Gson 라이브러리 )
		// 스프링 부트는 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 응답해준다.
		return user;
	}

	// http://localhost:8000/blog/dummy/join
	// body : username, password, email 데이터를 가지고 request
	@PostMapping("dummy/join")
	// public String join(String username, String password, String email) { // Key -
	// Value (약속된 규칙)
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원 가입이 완료되었습니다.";
	}
}
