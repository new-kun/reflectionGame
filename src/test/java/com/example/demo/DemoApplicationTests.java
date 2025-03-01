package com.example.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

	@Test
	void contextLoads() {
		log.info("wo");
	}

	@Test
	@DisplayName("獲取Clss對象")
	void excriseClassObj() throws ClassNotFoundException {
		log.info("通過class常量");
		Class clazz = String.class;

		log.info("透過Object實例");
		String str ="Hello";
		Class strClazz = str.getClass();

		log.info("透過Class.forName()");
		Class clazzByName = Class.forName("java.lang.String");
	}

	@Test
	@DisplayName("創建對象")
	void createObj()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		log.info("透過反射動態創建對象");
		Class clazz = Class.forName("java.lang.String");
		Object obj = clazz.getDeclaredConstructor().newInstance();
	}

	@Test
	@DisplayName("透過反射取得對象的屬性")
	void getObjFiled() throws NoSuchFieldException, IllegalAccessException {
		Person persionInstance = new Person("kun",30);
		Class<Person> clazz = Person.class;
		//如果屬性是私有的，設置他的訪問權限
		Field field = clazz.getDeclaredField("name");
		field.setAccessible(true);
		Object value = field.get(persionInstance);//獲取屬性
		field.set(persionInstance,"New Name");
		System.out.println(persionInstance);


	}



	@Data
	@AllArgsConstructor
	public class Person{
		private String name;
		private Integer age;
	}



}



