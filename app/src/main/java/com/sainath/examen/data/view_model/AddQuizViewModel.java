package com.sainath.examen.data.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sainath.examen.data.room_database.entity.AddQuiz;
import com.sainath.examen.data.room_database.repo.AddQuizRepository;

import java.util.List;

public class AddQuizViewModel extends AndroidViewModel {
    private AddQuizRepository addQuizRepository;
    private LiveData<List<AddQuiz>> allQuizDetails;
    public AddQuizViewModel(@NonNull Application application) {
        super(application);
        addQuizRepository = new AddQuizRepository(application);
        allQuizDetails = addQuizRepository.getAllQuiz();
    }

   public void insert(AddQuiz addQuiz){
       addQuizRepository.insert(addQuiz);
   }

   public LiveData<List<AddQuiz>> getAllQuizDetails(){
        return allQuizDetails;
   }
}
