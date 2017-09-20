package oop;

import main.Tester;
import main.Tester.Test;

public class Polymorphism {

	private static class NormalClass{
		
		void doWork(){
		}
	}
	private static class NormalClass2{
		
		void doWork(){
		}
	}
	
	private static interface PolyBase{
		void doWork();
	}
	private static class Poly1 implements PolyBase{

		@Override
		public void doWork() {
		}
	}
	private static class Poly2 implements PolyBase{

		@Override
		public void doWork() {
		}
	}
	
	private static final int OBJECT_COUNT = (int)1e6;
	private static final int HALF_OBJECT_COUNT = OBJECT_COUNT / 2;
	
	@Test(description = "Single array of classes, initialization and method calling")
	public void testNormal(){
		Tester.out.println("Array : size - "+OBJECT_COUNT + " type - "+NormalClass.class.getSimpleName());
		
		NormalClass[] normals1 = new NormalClass[OBJECT_COUNT];
		
		for (int i = 0; i < normals1.length; i++) {
			normals1[i] = new NormalClass();
		}
		for (int i = 0; i < normals1.length; i++) {
			normals1[i].doWork();
		}
	}
	@Test(description = "Two arrays of classes, initialization and method calling")
	public void testNormal2(){
		Tester.out.println("Array 1: size - "+HALF_OBJECT_COUNT + " type - "+NormalClass.class.getSimpleName());
		Tester.out.println("Array 2: size - "+HALF_OBJECT_COUNT + " type - "+NormalClass2.class.getSimpleName());
		
		NormalClass[] normals1 = new NormalClass[HALF_OBJECT_COUNT];
		NormalClass2[] normals2 = new NormalClass2[HALF_OBJECT_COUNT];
		
		for (int i = 0; i < normals1.length; i++) {
			normals1[i] = new NormalClass();
		}
		for (int i = 0; i < normals2.length; i++) {
			normals2[i] = new NormalClass2();
		}
		
		for (int i = 0; i < normals1.length; i++) {
			normals1[i].doWork();
		}
		for (int i = 0; i < normals2.length; i++) {
			normals2[i].doWork();
		}
	}
	@Test(description = "One array of interface implementations, initialization of 1 implementation type and method calling")
	public void testPoly(){
		Tester.out.println("Array : size - "+OBJECT_COUNT + " type - "+PolyBase.class.getSimpleName());
		Tester.out.println("Implementation 1: amount - " + HALF_OBJECT_COUNT + " type - " + Poly1.class.getSimpleName());
		
		PolyBase[] polys = new PolyBase[OBJECT_COUNT];
		
		for (int i = 0; i < OBJECT_COUNT; i++) {
			polys[i] = new Poly1();
		}
		
		for (int i = 0; i < polys.length; i++) {
			polys[i].doWork();
		}
	}
	@Test(description = "One array of interface implementations, initialization of 2 implementation types and method calling")
	public void testPoly2(){
		Tester.out.println("Array : size - "+OBJECT_COUNT + " type - "+PolyBase.class.getSimpleName());
		Tester.out.println("Implementation 1: amount - " + HALF_OBJECT_COUNT + " type - " + Poly1.class.getSimpleName());
		Tester.out.println("Implementation 2: amount - " + HALF_OBJECT_COUNT + " type - " + Poly2.class.getSimpleName());
		
		PolyBase[] polys = new PolyBase[OBJECT_COUNT];
		
		for (int i = 0; i < HALF_OBJECT_COUNT; i++) {
			polys[i] = new Poly1();
		}
		for (int i = HALF_OBJECT_COUNT; i < OBJECT_COUNT; i++) {
			polys[i] = new Poly2();
		}
		
		for (int i = 0; i < polys.length; i++) {
			polys[i].doWork();
		}
	}
}
