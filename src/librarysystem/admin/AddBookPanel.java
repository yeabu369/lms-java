package librarysystem.admin;

import business.Author;
import business.Book;
import business.SystemController;
import librarysystem.LibWindow;
import librarysystem.Util;
import librarysystem.ruleSets.RuleException;
import librarysystem.ruleSets.RuleSet;
import librarysystem.ruleSets.RuleSetFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddBookPanel extends JPanel {
    private LibWindow parentFrame;

    List<Author> authors = new ArrayList<>();

    SystemController controller = SystemController.getInstance();
    JPanel topPanel = new JPanel();

    JLabel addBook = new JLabel("Add Book");
    JLabel isbnLabel = new JLabel("ISBN");

    JLabel authorsLabel = new JLabel("Authors");

    JLabel titleLabel = new JLabel("Title");

    JLabel checkoutLengthLabel = new JLabel("Checkout Length");

    JLabel numberOfCopiesLabel = new JLabel("Number of copies");

    JTextField isbnField = new JTextField();

    JTextField authorsField = new JTextField();

    JTextField titleField = new JTextField();

    JTextField checkOutLengthField = new JTextField();

    JTextField copiesField = new JTextField();

    JButton addBookButton = new JButton("Save Book");
    JButton addAuthorButton = new JButton("Add Author");

    private AddBookPanel(LibWindow parentFrame) {
        this.parentFrame = parentFrame;
        setUpTopPanel();
        setUpLayout();
        loadComponents();
    }

    public static AddBookPanel getInstance(LibWindow parentFrame) {
        return new AddBookPanel(parentFrame);
    }

    public void setUpLayout() {
        setLayout(new BorderLayout());
        isbnLabel.setBounds(30, 40, 150, 30);
        isbnField.setBounds(150, 40, 150, 30);
        authorsLabel.setBounds(30, 80, 150, 30);
        authorsField.setBounds(150, 80, 150, 30);
        authorsField.setEnabled(false);
        addAuthorButton.setBounds(320, 80, 100, 30);
        titleLabel.setBounds(30, 120, 150, 30);
        titleField.setBounds(150, 120, 150, 30);
        checkoutLengthLabel.setBounds(30, 160, 150, 30);
        checkOutLengthField.setBounds(150, 160, 150, 30);
        numberOfCopiesLabel.setBounds(30, 200, 150, 30);
        copiesField.setBounds(150, 200, 150, 30);
        addBookButton.setBounds(90, 250, 150, 30);
        addAuthorButtonListener(addAuthorButton);
        saveNewBookButtonListener(addBookButton);
    }

    public void loadComponents() {
        this.add(isbnLabel);
        this.add(isbnField);
        this.add(authorsLabel);
        this.add(authorsField);
        this.add(addAuthorButton);
        this.add(titleLabel);
        this.add(titleField);
        this.add(checkOutLengthField);
        this.add(checkoutLengthLabel);
        this.add(numberOfCopiesLabel);
        this.add(copiesField);
        this.add(addBookButton);
        this.add(topPanel);
    }

    public void setUpTopPanel() {
        Util.adjustLabelFont(addBook, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(0));
        topPanel.add(addBook);
    }

    public void updateAuthors(Author author) {
        if (author != null) {
            authors.add(author);
            String namesList = getAuthorsLastNames();
            authorsField.setText(namesList);
        }
    }

    private String getAuthorsLastNames() {
        if (authors == null || authors.size() == 0) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < authors.size(); i++) {
            if (i > 0) stringBuilder.append(", ");
            stringBuilder.append(authors.get(i).getLastName());
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void addAuthorButtonListener(JButton button) {
        button.addActionListener(evt -> {
            AddAuthorPanel addAuthorPanel = new AddAuthorPanel(this);
            addAuthorPanel.setVisible(true);
        });
    }

    private void saveNewBookButtonListener(JButton button) {
        button.addActionListener(evt -> {
            try {
                RuleSet rules = RuleSetFactory.getRuleSet(this);
                rules.applyRules(this);
                Book book = controller.getBook(getIsbn());
                if (book == null)
                    saveNewBook(new Book(getIsbn(), getTitle(), Integer.parseInt(getCheckOutLength()), authors), Integer.parseInt(getCopies()));
                else Util.showDialog(this, "Book with ISBN:" + getIsbn() + " already exists!");
            } catch (RuleException e) {
                Util.showDialog(this, e.getMessage());
            }
        });
    }

    public String getIsbn() {
        return isbnField.getText();
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getCopies() {
        return copiesField.getText();
    }

    public String getCheckOutLength() {
        return checkOutLengthField.getText();
    }

    public String getNumberOfAuthors() {
        return authorsField.getText();
    }

    public int getAuthorsSize() {
        return authors.size();
    }

    private void saveNewBook(Book book, int copies) {
        for (int i = 0; i < copies - 1; i++) {
            book.addCopy();
        }
        controller.saveBook(book);
        Util.showDialog(this, "Book: '" + book.getTitle() + "' is successfully added!");
    }
}
