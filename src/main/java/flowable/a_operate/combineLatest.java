package flowable.a_operate;

import com.google.gson.Gson;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class combineLatest {

    public static void main(String[] args)  {

        String url =  "https://api.github.com/users?since=" + ((int)Math.floor(Math.random()*500));
        Flowable<GithubUser[]> userRefreshFlowable = Flowable.interval(3L, TimeUnit.SECONDS).flatMap(time -> {
            return createGetFloable(url);
        });

        Flowable<Long> clickActionFloable = Flowable.interval(1L, TimeUnit.SECONDS);

        Flowable.combineLatest(clickActionFloable, userRefreshFlowable, (time, users) -> {
                    return users[(int)Math.floor(Math.random()*users.length)];
                })
                .blockingSubscribe(System.out::println);
    }

    public static Flowable<GithubUser[]> createGetFloable(String url) {
        return Flowable.just(httpGetRun(url))
                .observeOn(Schedulers.computation());
    }

    public static GithubUser[] httpGetRun(String url) {
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(15 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            System.out.println("## call github");
            return gson.fromJson(response.body().string(), GithubUser[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static GithubUser[] httpGetRun2(String url) throws IOException {
        Gson gson = new Gson();
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Response response = client.newCall(request).execute();
        System.out.println("## call github");
        return gson.fromJson(response.body().string(), GithubUser[].class);
    }

    class GithubUser {
        public String login;
        public String avatar_url;

        @Override
        public String toString() {
            return "[login : " + login + ", avator_url : " + avatar_url + "]";
        }
    }
}