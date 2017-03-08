package flowable.a_operate;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by morioka b_on 2017/03/08.
 */
public class flatMap {

    public static void main(String[] args) {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .flatMap(v -> {
                    // new flowable
                    return Flowable.just(v)
                            .map(w -> w * w);
                })
                .blockingSubscribe(System.out::println);
    }
}
