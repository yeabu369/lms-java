package librarysystem.admin;

import business.Address;
import business.Author;
import librarysystem.Util;

import javax.swing.*;
import java.awt.*;

public class AddAuthorPanel extends JFrame {
    JPanel topPanel = new JPanel();

    JLabel addAuthor = new JLabel("Add Author");
    JLabel firstNameLabel = new JLabel("First Name");

    JLabel lastNameLabel = new JLabel("Last Name");

    JLabel telephoneLabel = new JLabel("Telephone");

    JLabel streetLabel = new JLabel("Street");

    JLabel cityLabel = new JLabel("City");

    JLabel stateLabel = new JLabel("State");

    JLabel zipLabel = new JLabel("Zip Code");

    JLabel bioLabel = new JLabel("Bio");

    JTextField firstNameField = new JTextField();

    JTextField lastNameField = new JTextField();

    JTextField telephoneField = new JTextField();

    JTextField streetField = new JTextField();

    JTextField cityField = new JTextField();

    JTextField stateField = new JTextField();

    JTextField zipField = new JTextField();

    JTextField bioField = new JTextField();

    JButton addAuthorButton = new JButton("Add Author");
    private AddBookPanel addBookPanel;

    public AddAuthorPanel(AddBookPanel addBookPanel) {
        this.addBookPanel = addBookPanel;
        setSize(400, 485);
        setLocationRelativeTo(null);
        setUpTopPanel();
        setUpLayout();
        loadComponents();
    }

    public void setUpLayout() {
        setLayout(new BorderLayout());
        firstNameLabel.setBounds(30, 40, 150, 30);
        firstNameField.setBounds(150, 40, 150, 30);
        lastNameLabel.setBounds(30, 80, 150, 30);
        lastNameField.setBounds(150, 80, 150, 30);
        telephoneLabel.setBounds(30, 120, 150, 30);
        telephoneField.setBounds(150, 120, 150, 30);
        streetLabel.setBounds(30, 160, 150, 30);
        streetField.setBounds(150, 160, 150, 30);
        cityLabel.setBounds(30, 200, 150, 30);
        cityField.setBounds(150, 200, 150, 30);
        stateLabel.setBounds(30, 240, 150, 30);
        stateField.setBounds(150, 240, 150, 30);
        zipLabel.setBounds(30, 280, 150, 30);
        zipField.setBounds(150, 280, 150, 30);
        bioLabel.setBounds(30, 320, 150, 50);
        bioField.setBounds(150, 320, 150, 50);
        addAuthorButton.setBounds(90, 390, 150, 30);
        addAuthorActionListener(addAuthorButton);
    }

    public void loadComponents() {
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(telephoneLabel);
        this.add(telephoneField);
        this.add(stateLabel);
        this.add(stateField);
        this.add(streetLabel);
        this.add(streetField);
        this.add(cityLabel);
        this.add(cityField);
        this.add(zipLabel);
        this.add(zipField);
        this.add(bioField);
        this.add(bioLabel);
        this.add(addAuthorButton);
        this.add(topPanel);
    }

    public void setUpTopPanel() {
        Util.adjustLabelFont(addAuthor, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(0));
        topPanel.add(addAuthor);
    }

    private void addAuthorActionListener(JButton button) {
        button.addActionListener(evt -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String telephone = telephoneField.getText();
            String street = streetField.getText();
            String state = stateField.getText();
            String city = cityField.getText();
            String zip = zipField.getText();
            String bio = bioField.getText();
            if (Util.isEmpty(firstName) || Util.isEmpty(lastName) || Util.isEmpty(telephone) || Util.isEmpty(street) || Util.isEmpty(state) || Util.isEmpty(city) || Util.isEmpty(zip) || Util.isEmpty(bio)) {
                Util.showDialog(this, "Please fill missing fields");
                return;
            }
            addBookPanel.updateAuthors(new Author(firstName, lastName, telephone, new Address(street, city, state, zip), bio));
            dispose();
        });
    }
}
