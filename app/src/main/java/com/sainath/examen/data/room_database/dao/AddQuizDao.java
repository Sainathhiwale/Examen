package com.sainath.examen.data.room_database.dao;

/*Data access object is used to interact with sqlite database with CURD operation*/

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sainath.examen.data.room_database.entity.AddQuiz;

import java.util.List;

@Dao
public interface AddQuizDao {
    @Insert
    void insert(AddQuiz addQuiz);

    @Update
    void update(AddQuiz addQuiz);

    @Delete
    void delete(AddQuiz addQuiz);

    @Query("DELETE FROM interview_quiz")
    void getAllDelete();


    @Query("SELECT * FROM interview_quiz ORDER BY id DESC")
    LiveData<List<AddQuiz>> getAllQuiz();


}
