package com.fsc.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:ID生成器</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class IdGenerator {
    private static final long STEP = 10;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    private final long time;
    private final short count;

    /** 构造方法 */
    public IdGenerator() {
        LOCK.lock();

        try {
            if (lastCount == STEP) {
                boolean done = false;

                while (!done) {
                    long now = System.currentTimeMillis();

                    if (now == lastTime) {
                        try {
                            Thread.currentThread().sleep(1);
                        } catch (java.lang.InterruptedException e) {
                        }

                        continue;
                    } else {
                        lastTime = now;
                        lastCount = 0;
                        done = true;
                    }
                }
            }

            time = lastTime;
            count = lastCount++;
        } finally {
            LOCK.unlock();
        }
    }

    /** 生成ID */
    public String getId() {
        String id = "";
        id = String.valueOf(time) + String.valueOf(count);

        return id;
    }
}
