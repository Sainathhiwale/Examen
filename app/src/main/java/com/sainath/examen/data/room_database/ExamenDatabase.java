package com.sainath.examen.data.room_database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.sainath.examen.data.network.ExamenApiInterface;
import com.sainath.examen.data.room_database.dao.AddQuizDao;
import com.sainath.examen.data.room_database.entity.AddQuiz;

@Database(entities = {AddQuiz.class},version = 1,exportSchema = false)
public abstract class ExamenDatabase extends RoomDatabase {
   private static ExamenDatabase instance;

   public abstract AddQuizDao addQuizDao();


   public static synchronized ExamenDatabase getInstance(Context context){
      if (instance==null){
          instance = Room.databaseBuilder(context.getApplicationContext(),
                  ExamenDatabase.class,"Examen_database")
                  .fallbackToDestructiveMigration()
                  .addCallback(roomCallBack)
                  .build();
      }
      return instance;
   }

    public static  RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTaske(instance).execute();
        }
    };

   public  static  class  PopulateDbAsyncTaske extends AsyncTask<Void,Void,Void> {
        private AddQuizDao addQuizDao;



       public PopulateDbAsyncTaske(ExamenDatabase db) {
             this.addQuizDao = db.addQuizDao();
       }

       @Override
       protected Void doInBackground(Void... voids) {
            addQuizDao.insert(new AddQuiz(1,"What is Android","Android is os which is consist some middleware and key applicatio"));
            addQuizDao.insert(new AddQuiz(2,"What is Android","Android is os which is consist some middleware and key applicatio"));
            addQuizDao.insert(new AddQuiz(3,"What is Android","Android is os which is consist some middleware and key applicatio"));
            addQuizDao.insert(new AddQuiz(4,"What is Android","Android is os which is consist some middleware and key applicatio"));
            addQuizDao.insert(new AddQuiz(5,"What is Android","Android is os which is consist some middleware and key applicatio"));
            addQuizDao.insert(new AddQuiz(6,"What is Android","Android is os which is consist some middleware and key applicatio"));
            addQuizDao.insert(new AddQuiz(7,"What is Android","Android is os which is consist some middleware and key applicatio"));
           return null;
       }
   }

}
