package com.task.storage.system;

import java.util.List;

public interface Facade {

    public Task makeFirstTask();

    public Task makeBlockerTask();

    public List<String> getNamesOfAllSubTasks(Task task);

    public List<String> getNamesOfAllBlockedSubTasks(Task task);

    void setBlocker(Task blocker, Task blockable);
}
