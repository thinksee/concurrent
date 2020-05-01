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
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    public DBPool(int initialSize) {
        if(initialSize > 0) {
            for(int i = 0; i < initialSize; ++i) {
//                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }


}
