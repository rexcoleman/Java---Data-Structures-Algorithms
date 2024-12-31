import java.util.NoSuchElementException;

/**
* Your implementation of an ArrayQueue.
*/
@SuppressWarnings("unchecked")
public class ArrayQueue<T> {

   public static final int INITIAL_CAPACITY = 9;

   private T[] backingArray;
   private int front;
   private int size;

   public ArrayQueue() {
       backingArray = (T[]) new Object[INITIAL_CAPACITY];
   }

   public void enqueue(T data) {
       if (data == null) {
           throw new IllegalArgumentException("Cannot enqueue null data");
       }

       if (size == backingArray.length) {
           Object[] temp = new Object[backingArray.length * 2];
           for (int i = 0; i < size; i++) {
               temp[i] = backingArray[(front + i) % backingArray.length];
           }
           backingArray = (T[]) temp;
           front = 0;
       }

       int addIndex = (front + size) % backingArray.length;
       backingArray[addIndex] = data;
       size++;
   }

   public T dequeue() {
       if (size == 0) {
           throw new NoSuchElementException("Queue is empty");
       }

       T data = backingArray[front];
       backingArray[front] = null;
       front = (front + 1) % backingArray.length;
       size--;

       return data;
   }

   public T[] getBackingArray() {
       return backingArray;
   }

   public int size() {
       return size;
   }
}