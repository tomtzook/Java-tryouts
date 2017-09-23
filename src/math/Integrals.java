package math;

import main.Tester.Test;

public class Integrals {

	private static interface Function{
		double f(double x);
	}
	
	@Test(description = "Testing performence and accuracy of Trapezoidal rule vs Simpson's rule")
	public void testSimpsonVSTrapezoidal(){
		Function polyFunc = (x)->{
			return (x * x * x);
		};
		Function linearFunc = (x)->{
			return (10 * x + 5);
		};
		
		final double MAXVAL = 1000.0;
		final int SLICES = 1000;
		
		long time, start;
		double result;
		
		start = System.currentTimeMillis();
		result = trapezoidalRule(linearFunc, 0, MAXVAL, SLICES);
		time = System.currentTimeMillis() - start;
		
		System.out.println("Linear - Trapezoidal: "+result+", (0, "+MAXVAL+"), Trapezoids: "
							+ SLICES+", Time: "+time+" ms");
		
		start = System.currentTimeMillis();
		result = simpsonsRule(linearFunc, 0, MAXVAL, SLICES);
		time = System.currentTimeMillis() - start;
		
		System.out.println("Linear - Simpson's: "+result+", (0, "+MAXVAL+"), Slices: "
				+ SLICES+", Time: "+time+" ms");
		
		//POLY
		
		start = System.currentTimeMillis();
		result = trapezoidalRule(polyFunc, 0, MAXVAL, SLICES);
		time = System.currentTimeMillis() - start;
		
		System.out.println("Polynomial - Trapezoidal: "+result+", (0, "+MAXVAL+"), Trapezoids: "
							+ SLICES+", Time: "+time+" ms");
		
		start = System.currentTimeMillis();
		result = simpsonsRule(polyFunc, 0, MAXVAL, SLICES);
		time = System.currentTimeMillis() - start;
		
		System.out.println("Polynomial - Simpson's: "+result+", (0, "+MAXVAL+"), Slices: "
				+ SLICES+", Time: "+time+" ms");
	}
	
	private static double trapezoidalRule(Function func, double min, double max, int trapezoids){
		double h = (max - min) / trapezoids;
		double s = 0.5 * (func.f(min) + func.f(max));
		for(int i = 1; i < trapezoids; i++)
			s += func.f(min + i * h); 
		return (s * h);
	}
	private static double simpsonsRule(Function func, double min, double max, int slices){
		double h = (max - min) / slices;
		double s = func.f(min) + func.f(max), s1 = 0, s2 = 0;
		for(int i = 1; i <= slices / 2; i++){
			s1 += func.f(min + (2 * i - 1) * h);
			if(i < slices / 2) s2 += func.f(min + 2 * i * h);
		}
		return (1 / 3.0) * h * (s + 4 * s1 + 2 * s2);
	}
}
