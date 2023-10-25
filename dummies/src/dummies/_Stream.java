package dummies;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class _Stream {
	
	 
	
	public static void main(String[] args) {
		Person[] arr = {
				new Person("Dupon", 25, Gender.MALE),
				new Person("CoCo", 20, Gender.FEMALE),
				new Person("Peter", 18, Gender.MALE),
				new Person("Tony", 55, Gender.MALE),
				new Person("Helen", 42, Gender.FEMALE)
		};
	
		List<Person> listPerson = Arrays.asList(arr);
		
		Predicate<Person> predicate = (p) -> Gender.MALE.equals(p.gender);
		boolean l = listPerson.stream().anyMatch(predicate);
		
		listPerson.stream().map(p -> p.name).forEach(System.out::println);
		int sum = listPerson.stream().mapToInt(p -> p.getAge()).sum();
		
		int max = listPerson.stream().map(p -> p.getAge()).reduce((a,b) -> Math.max(a, b)).get();
		listPerson.stream().sorted().map(p -> p.getAge()).reduce((a,b) -> Math.max(a, b)).get();;
		System.out.println("count: " + l);
		System.out.println("max: " + max);
		System.out.println("sum: " + sum);
		listPerson.stream().skip(2).map(p -> p.name).forEach(System.out::println);
		
	}
	
	
	static class Person {
		
		private String name;
		private int age;
		private Gender gender;
		
		public Person(String name, int age, Gender gender) {
			super();
			this.name = name;
			this.age = age;
			this.gender = gender;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		
	}
	
	enum Gender {
		MALE,
		FEMALE;
	}
}
