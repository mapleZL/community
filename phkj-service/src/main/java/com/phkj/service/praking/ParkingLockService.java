package com.phkj.service.praking;

public interface ParkingLockService {

    boolean unLock(String id, String type);

    String getLockStatus(String id, String type);
}
