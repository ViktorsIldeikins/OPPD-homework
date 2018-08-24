package com.task.storage.system;

import java.util.Iterator;
import java.util.List;

public interface Task {

    public String getName();

    public Iterator<Task> getIterator();

    public String toString();

    public void complete();

    public List<Task> getSubTasks();
}
