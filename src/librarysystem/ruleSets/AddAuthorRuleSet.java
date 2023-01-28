package librarysystem.ruleSets;

import librarysystem.admin.AddAuthorPanel;

import java.awt.*;

public class AddAuthorRuleSet implements RuleSet {
    private AddAuthorPanel addAuthorPanel;

    @Override
    public void applyRules(Component component) throws RuleException {
        addAuthorPanel = (AddAuthorPanel) component;
        noneEmptyRule();
        zipNumericRule();
        stateRule();
    }

    private void noneEmptyRule() throws RuleException {
        if ( addAuthorPanel.getFirstName().trim().isEmpty() ||
                addAuthorPanel.getLastName().trim().isEmpty() ||
                addAuthorPanel.getStateValue().trim().isEmpty() ||
                addAuthorPanel.getStreet().trim().isEmpty() ||
                addAuthorPanel.getCity().trim().isEmpty() ||
                addAuthorPanel.getZip().trim().isEmpty() ||
                addAuthorPanel.getTelPhone().trim().isEmpty() ||
                addAuthorPanel.getBio().trim().isEmpty()
        )
            throw new  RuleException("All fields must be non-empty!");
    }

    private void zipNumericRule() throws RuleException {
        String val = addAuthorPanel.getZip().trim();

        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new RuleException("Zipcode must be numeric!");
        }
        if (val.length() != 5) throw new RuleException("Zipcode must have 5 digits!");
    }

    private void stateRule() throws RuleException {
        String val = addAuthorPanel.getStateValue().trim();
        if (val.length() != 2) throw new RuleException("State field must have two characters!");
        if (!Util.isInRangeAtoZ(val.charAt(0))
                || !Util.isInRangeAtoZ(val.charAt(1))) throw new RuleException("Characters of state field must be in range A-Z!");
    }
}
