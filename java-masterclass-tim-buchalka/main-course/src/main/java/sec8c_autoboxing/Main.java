package sec8c_autoboxing;

// converting primitive type to object (wrapping) and getting primitive from object (unwrapping)

import java.util.ArrayList;

public class Main {

	public static void main(String[] args)
	{
		String[] strArray = new String[10];
		int[] primitiveIntArray = new int[10];
		
		ArrayList<String> strArrayList = new ArrayList<String>();
		strArrayList.add("Tim");

		ArrayList<Integer> intArr= new ArrayList<>();
		intArr.add(Integer.valueOf(45));	//unnecesary boxing
		intArr.add(65);
		for (int i = 0; i < intArr.size(); i++) {
			System.out.println(intArr.get(i));
		//  System.out.println(intArr.get(Integer.valueOf(i)));	//unnecesary unboxing
		}
		// simple foreach
		for (int element: intArr) {
			System.out.println(element);
		}

		// I - longer version
		Integer integerValue = new Integer(54);
		Double doubleValue = new Double(5.5);
		
		ArrayList<Integer> intArrayList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
		{
			intArrayList.add(Integer.valueOf(i) + 20);
		}
		
		for (int i = 0; i < intArrayList.size(); i++)
		{
			System.out.println("int " + i + " --> " + intArrayList.get(i).intValue());
		}
		
		// II - shorter version
		Integer myIntValue = 56; 			// Integer.valueOf(56);
		int myInt = myIntValue;				//myIntValue.intValue();
		
		ArrayList<Double> myDoubleValues = new ArrayList<Double>();
		for (double dbl = 0.0; dbl < 10.0; dbl += 1.0)
		{
			myDoubleValues.add(dbl + 20.0);
		}
		
		for (int i = 0; i < myDoubleValues.size(); i++)
		{
			System.out.println("dbl " + i + " --> " + myDoubleValues.get(i));
		}
	}

}
