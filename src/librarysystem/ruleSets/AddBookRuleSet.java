package librarysystem.ruleSets;

import librarysystem.admin.AddBookPanel;

import java.awt.*;

public class AddBookRuleSet implements RuleSet {

    private AddBookPanel addBookPanel;
    @Override
    public void applyRules(Component component) throws RuleException {
        addBookPanel = (AddBookPanel) component;
        noneEmptyRule();
        copiesNumericRule();
        checkoutNumericRule();
        authorsRule();
    }

    private void noneEmptyRule() throws RuleException {
        if ( addBookPanel.getIsbn().trim().isEmpty() ||
                addBookPanel.getTitle().trim().isEmpty() ||
                addBookPanel.getCopies().trim().isEmpty() ||
                addBookPanel.getCheckOutLength().trim().isEmpty()
        )
            throw new  RuleException("All fields must be non-empty!");
    }

    private void copiesNumericRule() throws RuleException {
        String val = addBookPanel.getCopies().trim();

        int copies;
        try {
            copies = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new RuleException("Number of copies must be numeric!");
        }

        if (copies < 1) throw new RuleException("Book should have at least one copy!");
    }

    private void checkoutNumericRule() throws RuleException {
        String val = addBookPanel.getCheckOutLength().trim();

        int checkOut;
        try {
            checkOut = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new RuleException("Checkout Length must be numeric!");
        }

        if (checkOut != 7 && checkOut != 21) throw new RuleException("CheckOut length must be 7 or 21");
    }

    private void authorsRule() throws RuleException {
        int authorsAdded = addBookPanel.getAuthorsSize();

        if (authorsAdded == 0) throw new RuleException("Authors must be greater than 0");
    }
}
