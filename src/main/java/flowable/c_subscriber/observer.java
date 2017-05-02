package flowable.c_subscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by moriokanaoki on 2017/05/03.
 */
public class observer {
    public static void main(String[] args) {
        Observable.fromArray("1", "2", "3", "4")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("subscrigbe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("next : " + s);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });
    }
}
