package com.example.movie.locker;

import com.example.movie.lock.DistributedLock;

public interface DistributedLocker {
     
    /**
     * This method only fetches the lock object but does not explicitly lock. Lock has to be acquired and released.
     * specifically
     * @param key Fetch the lock object based on the key provided.
     * @return Implementation of DistributedLock object
     */
    DistributedLock getLock(String key);
    public boolean exists(String key);
 
}
