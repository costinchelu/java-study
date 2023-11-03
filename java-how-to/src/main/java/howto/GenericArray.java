package howto;

public class GenericArray<T> {

    T[] data;

    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
    }

    public T get(int index) {
        return data[index];
    }

    public void set(int index, T element) {
        data[index] = element;
    }
}
