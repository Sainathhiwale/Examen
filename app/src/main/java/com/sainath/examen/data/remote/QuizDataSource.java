package com.sainath.examen.data.remote;

import com.sainath.examen.data.model.user.User;

public interface QuizDataSource {
    void saveNewUser(String userId, User user);

}
