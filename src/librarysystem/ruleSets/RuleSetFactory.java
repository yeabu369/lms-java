package librarysystem.ruleSets;

import librarysystem.admin.AddAuthorPanel;
import librarysystem.admin.AddBookPanel;
import librarysystem.admin.AddMemberPanel;

import java.awt.*;
import java.util.HashMap;

public final class RuleSetFactory {
    private RuleSetFactory() {};

    static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();
    static {
        map.put(AddMemberPanel.class, new AddMemberRuleSet());
        map.put(AddBookPanel.class, new AddBookRuleSet());
        map.put(AddAuthorPanel.class, new AddAuthorRuleSet());
    }

    public static RuleSet getRuleSet(Component component) {
        Class<? extends Component> cl = component.getClass();

        if (!map.containsKey(cl)) throw new IllegalArgumentException("No RuleSet found for this Component");

        return map.get(cl);
    }
}
