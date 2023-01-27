package business;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataaccess.Auth;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import librarysystem.Util;

public class SystemController implements ControllerInterface {
    public static Auth currentAuth = null;
    private static DataAccessFacade da = new DataAccessFacade();

    private SystemController() {
    }

    public static SystemController getInstance() {
        return new SystemController();
    }

    public Auth login(String id, String password) throws LoginException {
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();
        return currentAuth;
    }

    @Override
    public List<String> allMemberIds() {
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readMemberMap().keySet());
        return retval;
    }

    @Override
    public List<String> allBookIds() {
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    }

    public void addNewMember(String firstName, String lastName, String street, String city, String zip, String state, String telephone) {
        Address address = new Address(street, city, state, zip);
        da.saveNewMember(new LibraryMember(Util.generateMemberId(), firstName, lastName, telephone, address));
    }

    public List<FormattedLibraryMember> getReadableLibraryMembers() {
        Map<String, LibraryMember> members = da.readMemberMap();
        System.out.println(members);
        List<FormattedLibraryMember> libraryMembers = new ArrayList<>();

        if (members != null) {
            for (Map.Entry<String, LibraryMember> member : members.entrySet()) {
                LibraryMember libMem = member.getValue();
                libraryMembers.add(new FormattedLibraryMember(libMem.getMemberId(), libMem.getFirstName(), libMem.getLastName(), libMem.getTelephone(), libMem.getAddress().toString()));
            }
        }

        return libraryMembers;
    }

    public HashMap<String, LibraryMember> getAllLibraryMembers() {
        return da.readMemberMap();
    }

    public String checkoutBook(String memberId, String bookIsbn) {
        LibraryMember libraryMember = getLibraryMember(memberId);
        Book book = getBook(bookIsbn);
        if (libraryMember == null) return "Library member with ID: " + memberId + " not found";
        if (book == null) return "The requested book with ISBN: " + bookIsbn + " is not available";

        int bookCopyNum = book.getCopyNums().get(0);
        BookCopy bookCopy = book.getCopy(bookCopyNum);

        CheckoutEntry checkoutEntry = BookCheckoutRecordAndEntryFactory.createCheckoutEntry(libraryMember.getCheckoutRecord(), bookCopy);
        CheckoutRecord checkoutRecord = libraryMember.getCheckoutRecord();
        checkoutRecord.addCheckoutEntry(checkoutEntry);

        libraryMember.updateCheckoutRecord(checkoutRecord);

        bookCopy.changeAvailability();
        book.updateCopy(bookCopy);

        da.updateBook(book);
        da.updateMember(libraryMember);

        return "Book checkout successful!";
    }

    private LibraryMember getLibraryMember(String memberId) {
        Map<String, LibraryMember> libraryMembers = da.readMemberMap();
        return libraryMembers.get(memberId);
    }

    //optional
    public void addCopyOfBook(String isbn, Component c) {
        Book book = getBook(isbn);
        if (book == null) {
            Util.showDialog(c, "Book with ISBN: " + isbn + " is not found!");
            return;
        }

        book.addCopy();
        saveBook(book);
        Util.showDialog(c, "Copy of Book: " + isbn + " is successfully added!");
    }

    public void saveBook(Book book) {
        da.saveNewBook(book);
    }

    public Book getBook(String bookIsbn) {
        Book book = null;
        HashMap<String, Book> bookHashMap = da.readBooksMap();
        if (bookHashMap.containsKey(bookIsbn)) {
            book = bookHashMap.get(bookIsbn);
        }
        return book;
    }

    public static class FormattedLibraryMember {
        String memberId;
        String firstName;
        String lastName;
        String telephone;
        String address;

        FormattedLibraryMember(String memberId, String firstName, String lastName, String telephone, String address) {
            this.memberId = memberId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.telephone = telephone;
            this.address = address;
        }

        public Map<String, String> memberDataMap() {
            return new HashMap<>() {{
                put("memberId", memberId);
                put("firstName", firstName);
                put("lastName", lastName);
                put("telephone", telephone);
                put("address", address);
            }};
        }
    }
}
