package com.sainath.examen.data.remote;

import com.sainath.examen.data.model.user.User;

public class QuizDataRepository implements QuizDataSource {
    private static QuizDataRepository INSTANCE = null;
    private final QuizDataSource mLocalDataSource;
    private final QuizDataSource mRemoteDataSource;

    private QuizDataRepository(QuizDataSource mLocalDataSource, QuizDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static QuizDataRepository getInstance(QuizDataSource localDataSource,QuizDataSource remoteDataSource){
       if (INSTANCE !=null){
           synchronized (QuizDataRepository.class){
               if (INSTANCE == null){
                   INSTANCE = new QuizDataRepository(localDataSource,remoteDataSource);
               }
           }
       }
       return INSTANCE;
    }

    @Override
    public void saveNewUser(String userId, User user) {
      mRemoteDataSource.saveNewUser(userId,user);
    }
}
