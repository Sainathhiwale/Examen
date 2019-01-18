package com.sainath.examen.data.remote.firebase;

import com.google.firebase.database.FirebaseDatabase;
import com.sainath.examen.data.model.user.User;
import com.sainath.examen.data.remote.QuizDataSource;
import com.sainath.examen.utils.FirebaseConstents;

public class QuizRemoteDataSource  implements QuizDataSource{
    public static QuizRemoteDataSource INSTANCE;
    private FirebaseDatabase mFirebaseDatabase;

    public QuizRemoteDataSource(FirebaseDatabase mFirebaseDatabase) {
        this.mFirebaseDatabase = mFirebaseDatabase;
    }
   public static QuizDataSource getInstance(FirebaseDatabase firebaseDatabase ){
       if (INSTANCE == null) {
           synchronized (QuizRemoteDataSource.class) {
               if (INSTANCE == null) {
                   INSTANCE = new QuizRemoteDataSource(firebaseDatabase);
               }
           }
       }
       return INSTANCE;
   }
    @Override
    public void saveNewUser(String userId, User user) {
     mFirebaseDatabase.getReference().child(FirebaseConstents.USERS).child(userId).setValue(user);
    }
}
