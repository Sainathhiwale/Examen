package com.sainath.examen.data;

import android.support.v7.util.SortedList;

public interface DataHandler {

    void setUserInfo(Callback<Void> callback);

    void saveUserEmail(String email);

    void saveUserName(String displayName);

    void saveUserPic(String s);

    interface Callback<T> {
        /**
         * Gets invoked when call was successful
         *
         * @param result result of the operation
         */
        void onResponse(T result);

        /**
         * Gets invoked when there is an error in the operation
         */
        void onError();
    }
}
