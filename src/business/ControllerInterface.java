package business;

import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
    public Auth login(String id, String password) throws LoginException;

    public List<String> allMemberIds();

    public List<String> allBookIds();
}
