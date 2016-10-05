package com.example.retrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofitrxjava.entities.Result;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.support.test.espresso.idling.CountingIdlingResource;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    private CountingIdlingResource countingIdlingResource = new CountingIdlingResource("RetrofitResult");

    private boolean completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<List<Result>> resultObservable = RetrofitHelper.Factory
                .create("Evin1-");

        countingIdlingResource.increment();

        resultObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Result>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                        completed = true;
                        countingIdlingResource.decrement();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Result> results) {
                        Log.d(TAG, "onNext: " + results.size());
                        for (Result result : results) {
                            Log.d(TAG, "onNext: " + result);
                        }
                    }
                });
    }

    public CountingIdlingResource getCountingIdlingResource() {
        return countingIdlingResource;
    }

    public boolean isCompleted() {
        return completed;
    }
}
