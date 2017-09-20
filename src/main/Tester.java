package main;

import java.io.PrintStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Scanner;

import oop.Polymorphism;

public class Tester {

	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Test{
		public String description() default "";
		public int runTimes() default 1;
	}
	
	public static Scanner in;
	public static PrintStream out;
	
	public static void main(String[] args){
		
		out = System.out;
		in = new Scanner(System.in);
		
		
		run(Polymorphism.class, 10);
	}
	
	private static void run(Class<?> testclass, int execTimes){
		Object instance = null;
		try{
			instance = testclass.newInstance();
		}catch(Exception e){
			out.println("Error instantiating class: "+e.getMessage());
		}
		
		if(instance == null){
			out.println("Unable to instantiate: "+testclass.getName());
			return;
		}
		
		out.println("Executing tests in class: "+testclass.getName());
		
		int count = 0;
		
		Method[] methods = testclass.getDeclaredMethods();
		for (Method method : methods) {
			if(method.isAnnotationPresent(Test.class)){
				Test test = method.getAnnotation(Test.class);
				long timeAvg = 0;
				int times = execTimes > 0? execTimes : test.runTimes();
				String name = method.getName();
				int i;
				count++;
				
				out.printf("\nExecuting test: '%s' - %s", name, test.description());
				out.printf("\nExecuting %d times \n\n", times);
				
				for (i = 0; i < times; i++) {
					try{
						long starttime = System.currentTimeMillis();
						method.invoke(instance);
						long time = System.currentTimeMillis() - starttime;
						timeAvg += time;
						out.printf("\nTest passed: '%s' time - %d ms \n\n", name, time);
					}catch(Throwable t){
						out.printf("\nTest failed: '%s' \n\n", name, t.getMessage());
					}
				}
				
				if(i > 0)
					timeAvg /= i;
				
				out.printf("%d - Test Summery: '%s' Runs : %d (%d), Average Time : %d ms \n", count, name, i, times, timeAvg);
			}
		}
		
		out.printf("\nRun Summery: Total : %d \n", count);
	}
}
