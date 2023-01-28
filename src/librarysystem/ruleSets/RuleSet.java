package librarysystem.ruleSets;

import java.awt.*;

public interface RuleSet {
    public void applyRules(Component component) throws RuleException;
}
