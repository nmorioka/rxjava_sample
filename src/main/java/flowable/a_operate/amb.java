package flowable.a_operate;

import com.sun.tools.javac.comp.Flow;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class amb {

    public static void main(String[] args) {
        Flowable<String> f1 = Flowable.timer(10, TimeUnit.SECONDS).map(t -> "10sec");
        Flowable<String> f2 = Flowable.timer(5, TimeUnit.SECONDS).map(t -> "5sec");
        Flowable<String> f3 = Flowable.timer(2, TimeUnit.SECONDS).map(t -> "2sec");
        Flowable<String> f4 = Flowable.timer(4, TimeUnit.SECONDS).map(t -> "4sec");

        Flowable.ambArray(f1, f2, f3, f4)
                .observeOn(Schedulers.computation())
                .blockingSubscribe(System.out::println);
        // "2sec" is output
    }

}
