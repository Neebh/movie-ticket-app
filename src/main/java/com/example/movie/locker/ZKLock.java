package com.example.movie.locker;

/* 
import java.util.Objects;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.movie.lock.DistributedLock;

@Service
public class ZooKeeperDistributedLocker implements DistributedLocker{

    private String connectString;
    private CuratorFramework client;
    private int MAX_RETRY=1;
    private int sleepMsBetweenRetries = 10;
    private String basePath;

    @Autowired
    public ZooKeeperDistributedLocker(@Value("${zookeeper.url}") String connectString) {
        this.connectString = connectString;
         basePath = new StringBuilder("/bookTicket/distributed-locks").toString();
         //initialize();
    }

    private void initialize() {
        RetryPolicy retryPolicy = new RetryNTimes(MAX_RETRY, sleepMsBetweenRetries);
        client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
        client.start();
    }
 
    @Override
    public DistributedLock getLock(String key){
        String lock = new StringBuilder(basePath).append(key).toString();
        try {
            client.create().creatingParentsIfNeeded().forPath(lock);
        } catch (Exception e) {
           throw new RuntimeException("Error creating Lock");
        }

        return new ZKLock(new InterProcessSemaphoreMutex(client, lock));
    }

    @Override
    public boolean exists(String key) {
        String lock = new StringBuilder(basePath).append(key).toString();
        try {
            return Objects.nonNull(client.checkExists().forPath(lock));
        } catch (Exception e) {
            throw new RuntimeException("Key exists thrown exception");
        }
    }



 
    private class ZKLock implements DistributedLock {
        private final InterProcessLock lock;
 
        public ZKLock(InterProcessLock lock){
            this.lock = lock;
        }
 
        @Override
        public void acquireLock() {
            try {
                lock.acquire();
            } catch (Exception e) {
                throw new RuntimeException("Error while acquiring lock", e);
            }
        }

      
       
        @Override
        public void releaseLock() {
            try {
                lock.release();
            } catch (Exception e) {
                throw new RuntimeException("Error while releasing lock", e);
            }
        }
    }
}
*/