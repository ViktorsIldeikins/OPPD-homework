package com.task.storage.system;

public class Main {


    public static final String TASK_TO_BLOCK = "Task to block";

    public static void main(String[] args) {
        final Facade facade = new FacadeImpl();

        final Task task1 = facade.makeFirstTask();
        System.out.println(task1);
        System.out.println("Names:");
        facade.getNamesOfAllSubTasks(task1).forEach(System.out::println);

        final Task task2 = facade.makeBlockerTask();
        System.out.println("-Setting blockers");
        facade.setBlocker(task2, task1);
        System.out.println("Blocked tasks:");
        facade.getNamesOfAllBlockedSubTasks(task1).forEach(System.out::println);

        System.out.println("-complete blocker");
        task2.complete();

        System.out.println("Blocked tasks:");
        facade.getNamesOfAllBlockedSubTasks(task1).forEach(System.out::println);

    }

}
