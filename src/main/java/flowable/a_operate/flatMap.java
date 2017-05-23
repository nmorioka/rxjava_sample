package flowable.a_operate;

import io.reactivex.Flowable;


public class flatMap {

    public static Flowable<Integer> square(int v) {
        return Flowable.just(v).map(w -> w * w);
    }

    public static Flowable<Integer> twin(int v) {
        return Flowable.just(v, v);
    };

    public static void main(String[] args) {

        Flowable<Integer> f1 = Flowable.just(1,2);
        Flowable<Integer> f2 = f1.flatMap(v -> square(v));
        Flowable<Integer> f3 = f2.flatMap(v -> twin(v));

        f1.subscribe(v1 -> System.out.println("f1 : " + v1));
        f2.subscribe(v1 -> System.out.println("f2 : " + v1));
        f3.subscribe(v1 -> System.out.println("f3 : " + v1));
    }

}
