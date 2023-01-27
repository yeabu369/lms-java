package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
    private final LocalDate checkoutDate;
    private final BookCopy bookCopy;
    private final LocalDate dueDate;

    private final CheckoutRecord checkoutRecord;

    private CheckoutEntry(BookCopy bookCopy, CheckoutRecord checkoutRecord) {
        this.bookCopy = bookCopy;
        this.checkoutRecord = checkoutRecord;

        this.checkoutDate = LocalDate.now();
        this.dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
    }

    public static CheckoutEntry getInstance(BookCopy bookCopy, CheckoutRecord checkoutRecord) {
        return new CheckoutEntry(bookCopy, checkoutRecord);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }
}
