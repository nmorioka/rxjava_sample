package flowable.a_operate;


import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class map {

    public static void main(String[] args) {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> {
                    return v * v;
                })
                .blockingSubscribe(System.out::println);
    }
}

