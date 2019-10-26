package cn.notacat.cat.web.util;

import java.util.concurrent.LinkedBlockingQueue;

/****************************************
 * @@CREATE : 2019-10-26 9:14 下午
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
public class LimitedQueue<E> extends LinkedBlockingQueue<E>
{
    public LimitedQueue(int maxSize)
    {
        super(maxSize);
    }

    @Override
    public boolean offer(E e)
    {
        // turn offer() and add() into a blocking calls (unless interrupted)
        try {
            put(e);
            return true;
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return false;
    }

}
