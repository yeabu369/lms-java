package librarysystem.admin;

import business.SystemController;
import librarysystem.LibWindow;
import librarysystem.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberPanel extends JPanel implements ActionListener {

    private LibWindow parentFrame;
    SystemController sc = SystemController.getInstance();

    // define labels first name, last name, street, city, zip, state, telephone number
    JLabel firstNameLabel = new JLabel("First Name");
    JLabel lastNameLabel = new JLabel("Last Name");
    JLabel streetLabel = new JLabel("Street");
    JLabel cityLabel = new JLabel("City");
    JLabel zipLabel = new JLabel("Zip");
    JLabel stateLabel = new JLabel("state");
    JLabel telNumberLabel = new JLabel("Telephone number");

    // define input fields
    JTextField firstNameTextField = new JTextField();
    JTextField lastNameTextField = new JTextField();
    JTextField streetTextField = new JTextField();
    JTextField cityTextField = new JTextField();
    JTextField zipTextField = new JTextField();
    JTextField stateTextField = new JTextField();
    JTextField telNumberTextField = new JTextField();

    // define buttons
    JButton addMemberBtn = new JButton("Add Member");

    private AddMemberPanel(LibWindow parentFrame) {
        this.parentFrame = parentFrame;

        setupLayout();
        loadComponents();
        loadEventListeners();
    }

    public static AddMemberPanel getInstance(LibWindow parentFrame) {
        return new AddMemberPanel(parentFrame);
    }

    void setupLayout() {
        setLayout(null);

        firstNameLabel.setBounds(0, 0, 150, 30);
        firstNameTextField.setBounds(200, 0, 300, 30);

        lastNameLabel.setBounds(0, 50, 150, 30);
        lastNameTextField.setBounds(200, 50, 300, 30);

        streetLabel.setBounds(0, 100, 150, 30);
        streetTextField.setBounds(200, 100, 300, 30);

        cityLabel.setBounds(0, 150, 150, 30);
        cityTextField.setBounds(200, 150, 300, 30);

        zipLabel.setBounds(0, 200, 150, 30);
        zipTextField.setBounds(200, 200, 300, 30);

        stateLabel.setBounds(0, 250, 150, 30);
        stateTextField.setBounds(200, 250, 300, 30);

        telNumberLabel.setBounds(0, 300, 150, 30);
        telNumberTextField.setBounds(200, 300, 300, 30);

        addMemberBtn.setBounds(200, 400, 150, 30);
    }

    void loadComponents() {
        this.add(firstNameLabel);
        this.add(lastNameLabel);
        this.add(streetLabel);
        this.add(cityLabel);
        this.add(zipLabel);
        this.add(stateLabel);
        this.add(telNumberLabel);
        this.add(firstNameTextField);
        this.add(lastNameTextField);
        this.add(streetTextField);
        this.add(cityTextField);
        this.add(zipTextField);
        this.add(stateTextField);
        this.add(telNumberTextField);
        this.add(addMemberBtn);
    }

    void loadEventListeners() {
        this.addMemberBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String street = streetTextField.getText();
        String city = cityTextField.getText();
        String zip = zipTextField.getText();
        String state = stateTextField.getText();
        String telephone = telNumberTextField.getText();

        // simple validation
        if (firstName.equals("") || lastName.equals("") || street.equals("") || city.equals("") || zip.equals("") || state.equals("") || telephone.equals("")) {
            showDialog("Please fill missing fields");
        } else {
            sc.addNewMember(firstName, lastName, street, city, zip, state, telephone);
            System.out.println(sc.getAllLibraryMembers());
            showDialog("Member successfully added");
        }
    }

    private void showDialog(String message) {
        Util.showDialog(this, message);
    }
}
