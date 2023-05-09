import java.util.GregorianCalendar;

public class AList<T> {
    private T[] list;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    public AList() {
        this(DEFAULT_CAPACITY);
    }
    public AList(int initialCapacity) {
        if (initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else {
            @SuppressWarnings("unchecked")
            T[] tempList = (T[])new Object[initialCapacity + 1];
            list = tempList;
            numberOfEntries = 0;
        }
    }
    public void add(T newEntry) {
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
    }
    public void add(int newPosition, T newEntry) {
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            if (newPosition <= numberOfEntries) {
                makeRoom(newPosition);
            }
            list[newPosition] = newEntry;
            numberOfEntries++;
        } else {
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
        }
    }
    public int getLength() {
        return numberOfEntries;
    }
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index + 1];
        }
        return result;
    }
    private void makeRoom(int newPosition) {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;
        for (int index = lastIndex; index >= newIndex; index--) {
            list[index + 1] = list[index];
        }
    }
    public T remove(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T result = list[givenPosition];
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }
            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }
    public void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for (int index = removedIndex; index < lastIndex; index++) {
            list[index] = list[index + 1];
        }
    }
    public T replace(int givenPosition, T newEntry) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }
    }
    public T getEntry(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return list[givenPosition];
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }
    }
    public boolean contains(T anEntry) {
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries)) {
            if (anEntry.equals(list[index])) {
                found = true;
            }
            index++;
        }
        return found;
    }
}
