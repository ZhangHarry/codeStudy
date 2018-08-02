package l2t.disruptor.example.simple;

import com.lmax.disruptor.EventHandler;

public class ValueEventHandler  implements EventHandler<ValueEvent> {
	 // event will eventually be recycled by the Disruptor after it wraps
    public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception {
        Thread.sleep(10000);
        System.out.println("Sequence: " + sequence);
        System.out.println("ValueEvent: " + event.getValue());
    }
}