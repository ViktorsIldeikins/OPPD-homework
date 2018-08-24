package com.task.storage.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FacadeImpl implements Facade {

    public static final String TASK_TO_BLOCK = "Task to block";

    @Override
    public Task makeFirstTask() {
        final TaskBuilder builder = new TaskBuilder();
        builder.mainTask("Task A")
                .subTask("Task A", "Task AA")
                .subTask("Task A", "Task AB")
                .subTask("Task AA", "Task AAA")
                .subTask("Task AB", "Task ABA")
                .subTask("Task A", TASK_TO_BLOCK + " 1")
                .subTask("Task A", TASK_TO_BLOCK + " 2");
        return builder.build();
    }

    @Override
    public Task makeBlockerTask() {
        final TaskBuilder builder = new TaskBuilder();
        builder.mainTask("Blocker");
        return builder.build();
    }

    @Override
    public List<String> getNamesOfAllSubTasks(Task task) {
        final List<String> names = new ArrayList<>();
        final Iterator<Task> iterator = task.getIterator();
        while (iterator.hasNext()) {
            names.add(iterator.next().getName());
        }
        return names;
    }

    @Override
    public List<String> getNamesOfAllBlockedSubTasks(Task task) {
        final List<String> names = new ArrayList<>();
        final Iterator<Task> iterator = task.getIterator();
        while (iterator.hasNext()) {
            final TaskImpl currentTask = (TaskImpl) iterator.next();
            if (currentTask.isBlocked()) {
                names.add(currentTask.getName());
            }

        }
        return names;
    }

    @Override
    public void setBlocker(final Task blocker, final Task blockable) {
        final Iterator<Task> iterator = blockable.getIterator();
        while (iterator.hasNext()) {
            final Task task = iterator.next();
            if (task.getName().startsWith(TASK_TO_BLOCK) && task instanceof Blockable) {
                ((Blockable) task).addBlocker((Blocker) blocker);
            }
        }
    }
}
