package com.example.retrofitrxjava;

import com.example.retrofitrxjava.entities.Result;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by evin on 9/19/16.
 */

public class RetrofitHelper {
    public static class Factory {
        public static Retrofit create() {
            return new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }

        public static Observable<List<Result>> create(String user) {
            Retrofit retrofit = create();
            GitHubService gitHubService = retrofit.create(GitHubService.class);
            return gitHubService.getRepos(user);
        }
    }

    public interface GitHubService {
        @GET("/users/{user}/repos")
        Observable<List<Result>> getRepos(@Path("user") String user);
    }
}
