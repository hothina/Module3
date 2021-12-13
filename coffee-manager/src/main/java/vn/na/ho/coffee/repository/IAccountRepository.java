package vn.na.ho.coffee.repository;


import vn.na.ho.coffee.model.Account;

import java.io.IOException;
import java.util.List;

public interface IAccountRepository {
    Account getById(long id);
    List<Account> getAccount();
    boolean exist(long id);
    void add(Account newAccount) throws IOException;
    void update(Account account)throws IOException;

}
