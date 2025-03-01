package com.example.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	@DisplayName("獲取Clss類別資訊")
	void excriseClassObj() throws ClassNotFoundException {
		log.info("使用 Class.forName('類別名稱') 或 ClassName.class 獲取 Class 物件");

		log.info("通過class常量");
		Class clazz = String.class;

		log.info("透過Object實例");
		String str ="Hello";
		Class strClazz = str.getClass();

		log.info("透過Class.forName()");
		Class clazzByName = Class.forName("java.lang.String");
	}

	@Test
	@DisplayName("創建實例")
	void createObj()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		log.info("通過 Constructir的newInstance()方法創建物件");

		log.info("透過反射動態創建對象");
		Class clazz = Class.forName("java.lang.String");
		Object obj = clazz.getDeclaredConstructor().newInstance();
	}

	@Test
	@DisplayName("創建實例2")
	void createInstance(){
		log.info("通過 Constructir的newInstance()方法創建物件");
		try {
			Class<?> clazz = Class.forName("java.lang.String");
			Constructor<?> constructor = clazz.getConstructor(String.class);
			Object instance = constructor.newInstance("Hello");
			System.out.println(instance);
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
             InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		} finally {
			log.info("end");
		}

  }

	@Test
	@DisplayName("透過反射取得對象的屬性")
	void getObjField() throws NoSuchFieldException, IllegalAccessException {
		Person persionInstance = new Person("kun",30);
		Class<Person> clazz = Person.class;
		//如果屬性是私有的，設置他的訪問權限
		Field field = clazz.getDeclaredField("name");
		field.setAccessible(true);
		Object value = field.get(persionInstance);//獲取屬性
		field.set(persionInstance,"New Name");
	}

	@Test
	void exerciseGetObjField() throws IllegalAccessException, NoSuchFieldException {
		Class<?> clazz = Person.class;
		Field field = clazz.getDeclaredField("age");
		field.setAccessible(true);
		Person person = new Person("kun",30);
		String fieldName = (String) field.getName();
		System.out.println(fieldName);
		Object value = field.get(person);
		System.out.println(value);
	}

	@Test
	@DisplayName("調用方法")
	void getMethods()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		Class<?> clazz = Class.forName("java.lang.String");
		Method method = clazz.getMethod("length");
		String str = "Hi";
		Integer length = (Integer) method.invoke(str);
		System.out.println("字串長度"+ length);
	}


	@Test
	@DisplayName("獲取構造函數")
	void exerciseGetConstructor()
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		Class<?> clazz = Person.class;
		Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, Integer.class);
		constructor.setAccessible(true);
		Object obj = constructor.newInstance("John",42);
		System.out.println(obj);

	}

	@Test
	@DisplayName("獲取接口和父類")
	void exerciseGetInterfaceAndSuper(){
		log.info("獲取所有接口");
		Class<?>[]  interfaces = Person.class.getInterfaces();

		for(Class<?> clz : interfaces){
			System.out.println("Interface: "+clz.getName());
			System.out.println("-----");
		}
	}








}
@Data
@AllArgsConstructor
@NoArgsConstructor
 class Person{
	private String name;
	private Integer age;
}



