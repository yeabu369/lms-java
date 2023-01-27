package librarysystem.librarian;

import business.SystemController;
import librarysystem.LibrarySystem;
import librarysystem.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookCheckoutWindow extends JPanel implements ActionListener {
    LibrarySystem parentFrame;
    SystemController sc = SystemController.getInstance();
    JLabel bookCheckoutLabel = new JLabel("Book Checkout");
    JLabel memberIdLabel =new JLabel("Member ID");
    JLabel bookISBNLabel =new JLabel("ISBN");
    JTextField memberIdTextField =new JTextField();
    JTextField bookISBNTextField =new JTextField();
    JButton checkoutBtn =new JButton("Checkout");

    private BookCheckoutWindow(LibrarySystem parentFrame) {
        this.parentFrame = parentFrame;
        setLayoutManager();
        setColourManager();
        setVisible(true);
        loadComponents();
        loadEventListeners();
    }

    public static BookCheckoutWindow getInstance(LibrarySystem parentFrame) {
        return new BookCheckoutWindow(parentFrame);
    }

    public void setLayoutManager() {
        setLayout(null);
        bookCheckoutLabel.setBounds(0, 0, 100, 30);

        memberIdLabel.setBounds(50,130,100,30);
        memberIdTextField.setBounds(50,100,150,30);

        bookISBNLabel.setBounds(250,130,100,30);
        bookISBNTextField.setBounds(250,100,150,30);

        checkoutBtn.setBounds(250,200,100,30);
    }

    public void setColourManager() {
        bookCheckoutLabel.setForeground(Color.BLUE.darker());
    }

    void loadComponents() {
        this.add(bookCheckoutLabel);
        this.add(memberIdLabel);
        this.add(bookISBNLabel);
        this.add(memberIdTextField);
        this.add(bookISBNTextField);
        this.add(checkoutBtn);
    }

    void loadEventListeners() {
        checkoutBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String memberId = memberIdTextField.getText();
        String bookIsbn = bookISBNTextField.getText();

        if (memberId == null || bookIsbn == null) {
            Util.showDialog(this, "Please fill all fields");
            return;
        }

        String bookCheckoutStatus = sc.checkoutBook(memberId, bookIsbn);
        Util.showDialog(this, bookCheckoutStatus);
    }
}
