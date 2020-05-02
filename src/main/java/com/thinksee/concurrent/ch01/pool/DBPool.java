package com.thinksee.concurrent.ch01.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description
 **/
public class DBPool {
    // 存放连接的容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    // 限制了连接池的大小
    public DBPool(int initialSize) {
        if(initialSize > 0) {
            for(int i = 0; i < initialSize; ++i) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }
    // 释放连接，通知其他的等待连接的线程
    public void releaseConnection(Connection connection) {
        if(connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
    // 在mills内无法获取到连接，将会返回null 1S
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool) {
            System.out.println("Size of pool :" + pool.size());
            if(mills <= 0) {
                while(pool.isEmpty()) {
                    pool.wait(); // 释放锁，等待资源
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                // 当前线程池不为空，并且在超时时间范围内
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining); // 释放锁，等待资源，等待时间依赖于notifyAll和notify的反馈
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if(!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }

        }

    }
}
