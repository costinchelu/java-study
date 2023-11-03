package dp_structural.adapter.client;


import dp_structural.adapter.itarget.Employee;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // adapter pattern in use:
        Integer[] arrayOfIntegers = new Integer[] {6, 12, 9, 48};
        List<Integer> listOfIntegers = Arrays.asList(arrayOfIntegers);

        System.out.println(arrayOfIntegers);
        System.out.println(listOfIntegers);
        System.out.println("----------------------------");

        // implemented adapter pattern
        EmployeeClient client = new EmployeeClient();

        List<Employee> employees = client.getEmployeeList();

        System.out.println(employees);
    }
}
/*
Connecting new code to legacy code without having to change the working contract
that was produced from the legacy code originally. (can provide multiple adapters)

Doesn't add functionality (this would be a Decorator)
 */