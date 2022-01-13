package com.kgozdz.centus.repository;

import com.kgozdz.centus.model.User;

public interface IUserRepository {
    boolean login(String username, String password);
    void register(User user);
}
