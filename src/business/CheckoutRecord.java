package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private final List<CheckoutEntry> checkoutEntries;

    private final LibraryMember libraryMember;

    private CheckoutRecord(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
        checkoutEntries = new ArrayList<>();
    }

    public static CheckoutRecord createCheckoutRecord(LibraryMember libraryMember) {
        return new CheckoutRecord(libraryMember);
    }

    public void addCheckoutEntry(CheckoutEntry checkoutEntry) {
        checkoutEntries.add(checkoutEntry);
    }

    public List<CheckoutEntry> getCheckoutEntries() {
        return checkoutEntries;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }
}

// q: Why is the constructor private?
// a: The constructor is private because we want to enforce the creation of a checkout record through the factory method.