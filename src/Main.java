import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Task> myTaskList = new ArrayList<>();

        Task task1 = new Task("Intro to C++", "Complete programming assignment 6A and 6B");
        Task task2 = new Task("Assembly Language", "Read chapter 4 and review extensively");
        Task task3 = new Task("UC Transfer App Questions", "Draft outlines for the mandatory PIQ responses");

        myTaskList.add(task1);
        myTaskList.add(task2);
        myTaskList.add(task3);

        System.out.println("---- MY CURRENT TASKS ----");
        for (int i = 0; i <myTaskList.size(); i++) {
            Task currentTask = myTaskList.get(i);
            System.out.println((i + 1) + ". " + currentTask.getTitle() +  " --> " + currentTask.getDescription());
        }

    }
}
