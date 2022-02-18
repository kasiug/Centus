package com.kgozdz.centus.repository;

import com.kgozdz.centus.model.User;

public interface IUserRepository {
    void login(String username, String password);
    void register(User user);
    User getUser(int id);
    boolean userExists(User user);

}
