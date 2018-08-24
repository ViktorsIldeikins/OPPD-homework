package com.task.storage.system;

public interface Blockable {

    void addBlocker(Blocker blocker);

    void removeBlocker();

    boolean isBlocked();
}
