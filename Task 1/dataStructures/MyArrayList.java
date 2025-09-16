package dataStructures;

public class MyArrayList {

    private Object[] arr;
    private int size;

    public MyArrayList() {
        arr = new Object[10];
        size = 0;
    }

    public MyArrayList(int max) {
        arr = new Object[max];
        size = 0;
    }

    public void add(Object value) {
        if (size == arr.length) {
            Object[] tempArray = new Object[arr.length * 2];
            for (int i = 0; i < arr.length; i++)
                tempArray[i] = arr[i];
            arr = tempArray;
        }
        arr[size] = value;
        size++;
    }

    public Object get(int key) {
        if (key <= arr.length)
            return arr[key];
        return null;
    }

    public boolean remove(Object value) {
        int i;
        for (i = 0; i < size; i++)
            if (arr[i].equals(value)) break;
        if (i == size) {
            return false;
        } else {
            for (int j = i; j < size; j++) {
                arr[j] = arr[j + 1];
            }
            size--;
            return true;
        }
    }

    public void addAll(Object[] input) {
        for (Object o : input) add(o);
    }

    public boolean find(Object value) {
        int i;
        for (i = 0; i < size; i++)
            if (arr[i].equals(value)) break;
        return i != size;
    }
}
