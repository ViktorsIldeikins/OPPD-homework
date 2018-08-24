package com.task.storage.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskImpl implements Task, Blockable, Blocker {

    private String name;
    private List<Task> subTasks;
    private boolean completed;
    private boolean blocked;
    private List<Blockable> blockedTasks;

    public TaskImpl(final List<Task> subTasks, final String name) {
        this.subTasks = subTasks;
        this.name = name;
        this.completed = false;
        this.blockedTasks = new ArrayList<Blockable>();
        this.blocked = false;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blockable> getBlockedTasks() {
        return blockedTasks;
    }

    @Override
    public String toString() {
        return "TaskImpl{" +
                "name='" + name + '\'' +
                ", subTasks=" + subTasks +
                '}';
    }

    @Override
    public void addBlocker(Blocker blocker) {
        this.blocked = true;
        blocker.getBlockedTasks().add(this);
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public Iterator<Task> getIterator() {
        return new TaskIterator();
    }

    @Override
    public void removeBlocker() {
        this.blocked = false;
    }

    @Override
    public void complete() {
        this.completed = true;
        blockedTasks.forEach(Blockable::removeBlocker);
    }

    private class TaskIterator implements Iterator<Task> {

        private Iterator<Task> iterator = subTasks.iterator();
        private Iterator<Task> subTaskIterator;
        private Task currentTask;

        @Override
        public boolean hasNext() {
            return iterator.hasNext() || (subTaskIterator != null && subTaskIterator.hasNext());
        }

        @Override
        public Task next() {
            if (subTaskIterator != null && subTaskIterator.hasNext()) {
                return subTaskIterator.next();
            } else if (iterator.hasNext()) {
                currentTask = iterator.next();
                subTaskIterator = currentTask.getIterator();
                return currentTask;
            }
            return null;
        }
    }

}
