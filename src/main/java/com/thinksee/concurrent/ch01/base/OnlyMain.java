package com.thinksee.concurrent.ch01.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 **/
public class OnlyMain {
    /**
     * [6]Monitor Ctrl-Break
     * [5]Attach Listener
     * [4]Signal Dispatcher
     * [3]Finalizer
     * [2]Reference Handler
     * [1]main
     *  除了main主线程之外，其他的线程都为守护线程
     */
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for(ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }
    }
}
