package business;

public class BookCheckoutRecordAndEntryFactory {
    private BookCheckoutRecordAndEntryFactory() {
    }

    public static CheckoutRecord createCheckoutRecord(LibraryMember libraryMember) {
        if (libraryMember == null) throw new IllegalArgumentException("Library member cannot be null");

        return CheckoutRecord.createCheckoutRecord(libraryMember);
    }

    public static CheckoutEntry createCheckoutEntry(CheckoutRecord checkoutRecord, BookCopy bookCopy) {
        if (checkoutRecord == null)
            throw new IllegalArgumentException("Checkout entry cannot be created without a checkout record");
        if (bookCopy == null) throw new IllegalArgumentException("Checkout entry must be tied to a book");

        CheckoutEntry checkoutEntry = CheckoutEntry.getInstance(bookCopy, checkoutRecord);
        checkoutRecord.addCheckoutEntry(checkoutEntry);
        return checkoutEntry;
    }
}
