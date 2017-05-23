package processor;

import io.reactivex.processors.PublishProcessor;

/**
 * Created by moriokanaoki on 2017/04/28.
 */
public class processorSample {
    public static void main(String[] args) {
        PublishProcessor<Integer> processor = PublishProcessor.create();
        processor.subscribe(num -> {
                    System.out.println("number1 : " + num);
                });
        processor.map(v -> v * v)
                .subscribe(num -> {
            System.out.println("number2 : " + num);
        });

        processor.onNext(1);
        processor.onNext(2);
        processor.onNext(3);
        processor.onNext(4);
    }
}
