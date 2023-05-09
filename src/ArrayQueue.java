import java.util.EmptyStackException;
public final class ArrayQueue<T> {
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayQueue(int initialCapacity) {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[])new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
    }
    public void enqueue(T newEntry) {
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return queue[frontIndex];
        }
    }
    public T dequeue() {
        T front = queue[frontIndex];
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % queue.length;
        return front;
    }
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % queue.length);
    }
}
