package pl.pwr.bdmap.exceptions;

public class ListCreationException extends Exception {
    public ListCreationException(int index) {
        super(String.format("List creation failed at index %d", index));
    }
}
