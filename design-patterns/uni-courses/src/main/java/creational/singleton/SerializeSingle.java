package creational.singleton;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class SerializeSingle implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private double amount;

    private static SerializeSingle instance = null;

    public SerializeSingle(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public static SerializeSingle getInstance(String name, double amount) {
        if (instance == null) {
            instance = new SerializeSingle(name, amount);
        }
        return instance;
    }

    @Serial
    protected Object readResolve() {
        return getInstance("Doesn't matter", 0);
    }
}


class SerializeSingleDemo {

    public static void main(String[] args) {
        SerializeSingle single1 = SerializeSingle.getInstance("FOO", 1);

        String path = "C:/WORK/in-out/singleSerialize.dat";
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(path));
             ObjectInput in = new ObjectInputStream(new FileInputStream(path))) {
            out.writeObject(single1);
            SerializeSingle single2 = (SerializeSingle) in.readObject();
            single2.setName("BAR");
            single2.setAmount(5);

            System.out.println(single1);
            System.out.println(single2);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
