package flowable.a_operate;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class retry {

    public static void main(String[] args) throws InterruptedException {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .flatMap(v -> {
                    return Flowable.just(v)
                            .map(w -> w * w);
                })
                .retry(3, throwable -> {
                    // retry可否コード
                    return true;
                })
                .blockingSubscribe(System.out::println);
    }

}
