package com.example.movie.lock;

public interface DistributedLock {
    
    public void acquireLock()throws Exception;
    public void releaseLock() throws Exception;
}
