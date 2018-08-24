package com.task.storage.system;

import java.util.*;

import static java.util.Collections.*;

public class TaskBuilder {

    public static String MAIN_TASK = "Main task";

    /**
     * value = names of subtasks
     * key = name of the suppertask
     */
    private Map<String, Set<String>> taskToSubTaskRelations;

    public TaskBuilder() {
        taskToSubTaskRelations = new HashMap<String, Set<String>>();
    }

    public TaskBuilder mainTask(final String name) {
        taskToSubTaskRelations.put(MAIN_TASK, new HashSet<String>(singletonList(name)));
        return this;
    }

    public TaskBuilder subTask(final String nameOfSupperTask, final String nameOfSubTask) {
        final Set<String> subtasks  = taskToSubTaskRelations.get(nameOfSupperTask);
        if (subtasks != null) {
            subtasks.add(nameOfSubTask);
        } else {
            taskToSubTaskRelations.put(nameOfSupperTask, new HashSet<String>(singletonList(nameOfSubTask)));
        }
        return this;
    }

    public Task build() {
        final String mainTaskName = getMainTaskName();
        final List<Task> subTasks = findSubtasks(mainTaskName);
        return new TaskImpl(subTasks, mainTaskName);
    }

    //
    // Auxiliary methods
    //


    private String getMainTaskName() {
        final Set<String> setOfNames = taskToSubTaskRelations.get(MAIN_TASK);
        return (String) setOfNames.toArray()[0];
    }

    private List<Task> findSubtasks(final String mainTaskName) {
        final List<Task> result = new ArrayList<Task>();
        final Set<String> subTasks = taskToSubTaskRelations.get(mainTaskName);

        if (subTasks != null) {
            for (String subTaskName : subTasks) {
                final List<Task> subsubTasks = findSubtasks(subTaskName);
                result.add(new TaskImpl(subsubTasks, subTaskName));
            }
        }

        return result;
    }

}
