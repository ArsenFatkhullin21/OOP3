package ru.arsen.oop3;

public class MyList<T>{

    private Node<T> head;
    private Node<T> tail;
    private int size;


    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) { // Удаление первого элемента
            head = head.getNext();
            if (head == null) { // Если список стал пустым
                tail = null;
            }
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());

            if (temp.getNext() == null) { // Если удаляем последний элемент
                tail = temp;
            }
        }

        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T value) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.getData().equals(value)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void removeAll(){
        for(int i = 0; i < size; i++){
            remove(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.getData());
            if (temp.getNext() != null) {
                sb.append(", ");
            }
            temp = temp.getNext();
        }
        sb.append("]");
        return sb.toString();
    }




}
