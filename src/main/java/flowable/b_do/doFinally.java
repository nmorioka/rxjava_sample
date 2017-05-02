package flowable.b_do;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;


public class doFinally {

    public static void main(String[] args) {
        List<String> s1 = Arrays.asList("hoge1", "fuga1");
        List<String> s2 = Arrays.asList("hoge2", "fuga2");

        Content c1 = new Content();
        c1.mediaList = Arrays.asList("hoge1", "fuga1");
        Content c2 = new Content();
        c2.mediaList = Arrays.asList("hoge2", "fuga2");
        List<Content> contents = Arrays.asList(c1, c2);

        List<String> m1 = Arrays.asList("hoge1", "hoge2");

        Flowable.fromIterable(contents)
                .subscribeOn(Schedulers.io())
                .doFinally(() -> System.out.println("finally1"))
                .flatMap(content -> {
                    return Flowable.fromIterable(content.mediaList).filter(s -> m1.contains(s)).doFinally(() -> System.out.println("finally2"));
                })
                .subscribe(System.out::println);
    }

    static class Content {
        List<String> mediaList;
    }

}
