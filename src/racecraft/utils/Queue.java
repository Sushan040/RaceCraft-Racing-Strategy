/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.utils;

import java.util.Iterator;

public class Queue<T> implements Iterable<T>{
    private Object[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T item) { 
        if (isFull()) { 
            // Remove oldest if full
            dequeue(); 
        }
        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() { 
        if (isEmpty()) return null; 
        T item = (T) queue[front];
        front = (front + 1) % capacity;
        size--;
        return item; 
    }

    public boolean isEmpty() { 
        return size == 0; 
    }

    public boolean isFull() { 
        return size == capacity; 
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = front;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T item = (T) queue[current];
                current = (current + 1) % capacity;
                count++;
                return item;
            }
        };
    }
}
