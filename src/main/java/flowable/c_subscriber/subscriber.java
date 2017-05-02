package flowable.c_subscriber;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by moriokanaoki on 2017/05/03.
 */
public class subscriber {
    public static void main(String[] args) {
        Flowable.just("1", "2", "3", "4")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                        System.out.println("subscribed");
                    }

                    @Override
                    public void onNext(String s) {
                        int num = Integer.parseInt(s);
                        System.out.println(num);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("completed");
                    }
                });
    }
}
