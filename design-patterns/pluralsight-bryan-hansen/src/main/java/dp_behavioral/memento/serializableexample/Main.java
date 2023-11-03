package dp_behavioral.memento.serializableexample;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setAddress("111 Some Street");
        employee.setPhone("111-111-1111");

        serialize(employee);

        Employee newEmployee = deserialize();
        System.out.println(newEmployee.getName());
        System.out.println(newEmployee.getAddress());
        System.out.println(newEmployee.getPhone());
    }

    private static void serialize(Employee employee) {

        try {

            FileOutputStream fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(employee);
            objOut.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Employee deserialize() {

        Employee employee = null;

        try {

            FileInputStream fileIn = new FileInputStream("employee.ser");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            employee = (Employee) objIn.readObject();
            objIn.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
/*
- restore an object to previous state by externalizing it's internal state
- implement undo/rollback
- shields complex internals

java.util.Date
java.io.Serializable

- Originator is the object we want a copy to be saved. It creates an actual memento
- Caretaker - manages the mementos (copies) that we have created
- Memento - copy of the originator that we want to store
- can be resource expensive
 */