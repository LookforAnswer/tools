package com.qxy.tools.zookeeper.curd;

import org.apache.zookeeper.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/5/2 9:05 PM
 * @Version: 1.0
 */
public class ConnectWacher implements Watcher {

    public static final int SESSION_TIMEOUT = 5000;

    protected ZooKeeper zooKeeper;

    private CountDownLatch connectedSignal = new CountDownLatch(1);


    public void connect(String host) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(host,SESSION_TIMEOUT, this);
        connectedSignal.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Objects.equals(watchedEvent.getState(),Event.KeeperState.SyncConnected)){
            connectedSignal.countDown();
        }
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
