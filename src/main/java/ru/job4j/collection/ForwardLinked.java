package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T deleteFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> oldNode = head;
        T oldValue = head.value;
        head = head.next;
        oldNode.next = null;
        oldNode.value = null;
        return oldValue;
    }

    public void addFirst(T value) {
      head = new Node<>(value, head);
    }

    public boolean revert() {
        boolean rsl = false;
         Node<T> prev = null;
        if (head != null && head.next != null) {
            Node<T> curr = head.next;
            head.next = null;
            while (curr != null) {
                Node<T> following = curr.next;
                curr.next = head;
                head = curr;
                curr = following;
            }
            rsl = true;
        }
        return rsl;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }


}