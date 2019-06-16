package com.phkj.service.praking;

public interface ParkingLockService {

    boolean unLock(String id);

    String getLockStatus(String id);
}
