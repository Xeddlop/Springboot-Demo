package com.example.demo;

import com.example.demo.pojo.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


//Unit test
@SpringBootTest
class PojoTests {

	@Test
	void contextLoads() {
		User user = new User("test_user_1", "test_password_1");
		System.out.println(user);
	}

}

@SpringBootTest
class JdbcTest {
	//数据源组件
	@Autowired
	DataSource dataSource;
	//用于访问数据库的组件
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Test
	void contextLoads() throws SQLException {
		System.out.println("默认数据源为：" + dataSource.getClass());
		System.out.println("数据库连接实例：" + dataSource.getConnection());
		//访问数据库
		Integer i = jdbcTemplate.queryForObject("SELECT count(*) from `user`", Integer.class);
		System.out.println("user 表中共有" + i + "条数据。");
		//循环打印查询返回的list
		String sql = "SElECT * from `user`";
		List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
		System.out.println("user表中的数据：");
		for(int j = 0; j< list.size(); j++){
			System.out.println(list.get(j));
		}
	}
}

@SpringBootTest
class ServiceTest{

	@Autowired
	UserService userService;

	User testUser = new User("testUser", "111111");

	@Test
	void contextLoads(){
		testUser.setPriority(2);
		testUser.setInformation("I'm a test User");
		System.out.println("Service Test1, getInformationByUsername:");
		System.out.println(userService.getInformationByUsername("admin"));
		System.out.println("Service Test2, findUserByUsername");
		System.out.println(userService.findUserByUsername("admin"));
		System.out.println("Service Test3, registerWithUsernameAndPassword:");
		System.out.println(userService.register(testUser).getMsg());
		System.out.println("Service Test4, loginWithUsernameAndPassword");
		System.out.println(userService.login(testUser).getMsg());
	}

}

@SpringBootTest
class TokenTest{

	@Autowired
	User testUser;

	@Resource
	private TokenUtils tokenUtils;

	@Test
	void contextLoads(){
		testUser.setUsername("tokenUser");
		testUser.setInformation("I'm a token test user");
		String token = tokenUtils.createToken(testUser);
		System.out.println("Generated Token: "+ token);
		User verifiedUser = tokenUtils.validationToken(token);
		System.out.println("Verified User name: "+verifiedUser.getUsername());
		System.out.println("Verified User priority: "+verifiedUser.getPriority());
	}

}

