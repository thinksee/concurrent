- Thread是对线程的抽象，Runnable是对任务的抽象
- JDK的线程是协作式的，不是中断式的。
- 死锁状态不会被中断。
- start方法启动，仅能执行一次
- run方法算是业务逻辑，不是启动程序本身
- yield()会让出cpu，不会让出锁
- join()在线程中中断执行其他的线程，保证两个线程顺序执行
- 守护线程中的finally不一定执行
- 线程优先级：？？ 2-10 只是优先级较高的线程执行次数较多
- synchronized内置锁，对象锁
- this和对象锁（方法和同步块）
- 类锁和对象锁的区别？？？？
- run方法可以执行多次？ 多次

![](./meta/threadstatetransform.jpeg)
1. NEW，实现Runable接口和继承Thread可以得到一个线程类，
    new一个实例出来，线程就进入了初始状态。

2. READY，只是资源准备好，没有获得CPU执行权。start进入执行

3. TIMED_WAITING

- 常用类型的 Integer的i++使得 锁的对象发生了变化。
- volatile 不能保证线程的安全，仅是保证变量使得class可见（一写多度）
- [ThreadLocal](https://www.bilibili.com/video/BV1N741127FH?p=12)为每个线程提供变量副本，实现了线程隔离，spring使用事务的使用
   了ThreadLocal，保证了每个Thread都有一个ThreadLocalMap，其中key为当前的ThreadLocal，value为具体的值。
    - 弱引用，弱引用虽然不能保证ThreadLocalMap的内存泄露，但是可以为解决内存泄露提供了一层屏障，因为当使用暴露接口set/get或者其他方式的时候
   会进行value=null进行回收。清除不及时。
    - ThreadLocal仅是保证了不同线程之间调用同一份的变量备份。
    - static变量需要注意，就是每个class的static变量只是加载一次。
   
- 强/软/弱/虚引用
    - 软:若内存空间足够，垃圾回收器就不会回收它，若内存空间不足，就会回收这些
        对象的内存。
    - 弱：不论当前空间是否充足，都会回收它的内存。
    
    - 虚：在任何时候都会被垃圾回收器进行回收。
    
- wait释放锁，notify/notifyAll不释放锁
 
- Fork/Join工作

- CountDownLatch的作用
 
- 乐观锁-CAS(Compare And Swap)
    compare/swap->CAS指令
    CAS指令(内存地址V，旧值，新值)
    问题：
    1. ABA问题 + 版本号
    2. 开销问题 （自旋死循环问题）
    3. 只能保证一个共享变量的原子操作 
    
- ABA操作：JDK原子操作类中有方法可以进行操作 

- 显示锁 + AQS
    - 显示锁：Lock接口和核心方法（lock/unlock/tryLock）
        - 会消耗内存等
    - 可重入锁，不会把自己（函数）锁住
    - 公平锁：拿锁和释放锁的顺序是一致的
    - 非公平：利用线程唤醒的时间进行
    - ReadWriteLock接口和读写锁ReentrantLock、ReenReadLock
    
    - 场景：读多写少（业务场景） -> 读写(10:1)分离
        - 读写分离锁 
        - 读写锁互斥性：写锁严格性更强，
        - ReadWriteLock/
        - 等待通知：Lock和Condition
 
 
 
 
 