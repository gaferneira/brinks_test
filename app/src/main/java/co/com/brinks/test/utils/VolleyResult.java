package co.com.brinks.test.utils;

import android.widget.Toast;

import com.android.volley.VolleyError;

import co.com.brinks.test.base.BrinksApplication;

public abstract class VolleyResult<T> {
    public abstract  void onSuccess(T response) throws Exception;
    public void onError(VolleyError error, T response){
        Toast.makeText(BrinksApplication.getInstance(), response != null ? response.toString() : error.getMessage(), Toast.LENGTH_LONG).show();
    }
}