package librarysystem.ruleSets;

import librarysystem.admin.AddMemberPanel;

import java.awt.*;

public class AddMemberRuleSet implements RuleSet {
    private AddMemberPanel addMemberWindow;


    @Override
    public void applyRules(Component component) throws RuleException {
        addMemberWindow = (AddMemberPanel) component;
        noneEmptyRule();
//        idNumericRule();
        zipNumericRule();
        stateRule();
//        idNotZipRule();
//        isPhoneNumber();
    }

    private void noneEmptyRule() throws RuleException {
        if ( addMemberWindow.getFirstName().trim().isEmpty() ||
                addMemberWindow.getLastName().trim().isEmpty() ||
                addMemberWindow.getStateValue().trim().isEmpty() ||
                addMemberWindow.getStreet().trim().isEmpty() ||
                addMemberWindow.getCity().trim().isEmpty() ||
                addMemberWindow.getZip().trim().isEmpty() ||
                addMemberWindow.getTelPhone().trim().isEmpty()
        )
            throw new  RuleException("All fields must be non-empty!");
    }

    private void zipNumericRule() throws RuleException {
        String val = addMemberWindow.getZip().trim();

        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new RuleException("Zipcode must be numeric!");
        }
        if (val.length() != 5) throw new RuleException("Zipcode must have 5 digits!");
    }

    private void stateRule() throws RuleException {
        String val = addMemberWindow.getStateValue().trim();
        if (val.length() != 2) throw new RuleException("State field must have two characters!");
        if (!Util.isInRangeAtoZ(val.charAt(0))
                || !Util.isInRangeAtoZ(val.charAt(1))) throw new RuleException("Characters of state field must be in range A-Z!");
    }

//    private void idNotZipRule() throws RuleException {
//        String zip = addMemberWindow.getZip().trim();
//        String id = addMemberWindow.getMemberId().trim();
//        if(zip.equals(id)) throw new RuleException("ID may not be the same as zipcode");
//    }

    private void isPhoneNumber() throws RuleException {
        String val = addMemberWindow.getTelPhone().trim();
        try {
            Long.parseLong(val);
        } catch (NumberFormatException e) {
            throw new RuleException("Telephone number must be numeric!");
        }
        if (val.length() != 10) throw new RuleException("Telephone number must have 10 digits!");
    }
}
