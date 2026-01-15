/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// src/racecraft/utils/Stack.java
package racecraft.utils;

public class Stack<T> {
    private Object[] stack;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
        stack = new Object[capacity];
        top = -1;
    }

    public void push(T item) { 
        if (isFull()) return; 
        stack[++top] = item; 
    }

    @SuppressWarnings("unchecked")
    public T pop() { 
        if (isEmpty()) return null; 
        return (T) stack[top--]; 
    }

    @SuppressWarnings("unchecked")
    public T peek() { 
        if (isEmpty()) return null; 
        return (T) stack[top]; 
    }

    public boolean isEmpty() { 
        return top == -1; 
    }

    public boolean isFull() { 
        return top == capacity - 1; 
    }
}
