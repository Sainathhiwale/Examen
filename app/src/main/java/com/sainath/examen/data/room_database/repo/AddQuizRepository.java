package com.sainath.examen.data.room_database.repo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.sainath.examen.data.room_database.ExamenDatabase;
import com.sainath.examen.data.room_database.dao.AddQuizDao;
import com.sainath.examen.data.room_database.entity.AddQuiz;

import java.util.List;

public class AddQuizRepository {
   private AddQuizDao addQuizDao;
    private LiveData<List<AddQuiz>> allQuiz;


    public AddQuizRepository(Application application){
      ExamenDatabase examenDatabase = ExamenDatabase.getInstance(application);
      addQuizDao  = examenDatabase.addQuizDao();
     allQuiz  = addQuizDao.getAllQuiz();
  }

  public void insert(AddQuiz addQuiz){
    new InsertAddQuizAsyncTask(addQuizDao).execute(addQuiz);
  }

    public LiveData<List<AddQuiz>> getAllQuiz() {
        return allQuiz;
    }

    @SuppressLint("StaticFieldLeak")
  public class InsertAddQuizAsyncTask extends AsyncTask<AddQuiz,Void,Void>{
      private AddQuizDao addQuizDao;

      public InsertAddQuizAsyncTask(AddQuizDao addQuizDao) {
          this.addQuizDao = addQuizDao;
      }


      @Override
      protected Void doInBackground(AddQuiz... addQuizs) {
          addQuizDao.insert(addQuizs[0]);
          return null;
      }
  }
}
