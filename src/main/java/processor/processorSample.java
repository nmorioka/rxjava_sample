package processor;

import io.reactivex.processors.PublishProcessor;

/**
 * Created by moriokanaoki on 2017/04/28.
 */
public class processorSample {
    public static void main(String[] args) {
        PublishProcessor<String> processor = PublishProcessor.create();
        processor.subscribe(s -> {
            int i = Integer.parseInt(s);
            System.out.println("number : " + i);
        });

        processor.onNext("1");
        processor.onNext("2");
        processor.onNext("3");
        processor.onNext("4");
    }
}
