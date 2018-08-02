package lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by zhanghr on 2018/4/26.
 */

public class OneShotLatch {
    private class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected int tryAcquireShared(int ignored){
            return getState() == 1 ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignored){
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void signal(){
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireInterruptibly(0);
    }
}
