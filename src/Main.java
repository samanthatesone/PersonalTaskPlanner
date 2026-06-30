import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Task> myTaskList = new ArrayList<>();

        Task task1 = new Task("Intro to C++", "Complete programming assignment 6A and 6B");
        Task task2 = new Task("Assembly Language", "Read chapter 4 and review extensively");
        Task task3 = new Task("UC Transfer App Questions", "Draft outlines for the mandatory PIQ responses");

        myTaskList.add(task1);
        myTaskList.add(task2);
        myTaskList.add(task3);

        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning == true) {
            System.out.println("\n --- MAIN MENU ---");
            System.out.println("1. View Current Tasks");
            System.out.println("2. Add a New Task");
            System.out.println("3. Mark a Task as Completed");
            System.out.println("4. Delete a Task");
            System.out.println("5. Exit Program");
            System.out.println("Choose an option (1-5): ");

            int userChoice = input.nextInt();
            input.nextLine();

            if (userChoice == 1) {
                System.out.println("\n --- YOUR CURRENT TASKS ---");

                if (myTaskList.isEmpty()) {
                    System.out.println("You do not have any tasks currently.");
                }
                else {
                    for (int i = 0; i < myTaskList.size(); i++) {
                        Task currentTask = myTaskList.get(i);

                        String status = "[ ]";
                        if (currentTask.isCompleted()) {
                            status = "[COMPLETED]";
                        }

                        System.out.println((i + 1) + ". " + status + " " + currentTask.getTitle() + " --> " + currentTask.getDescription());
                    }
                }
            }

            else if (userChoice == 2) {
                System.out.println("Enter new task title: ");
                String newTitle = input.nextLine();

                System.out.println("Enter new task description: ");
                String newDescription = input.nextLine();

                Task newTask = new Task(newTitle, newDescription);

                myTaskList.add(newTask);
                System.out.println("Successfully added task: " + newTitle);
            }

            else if (userChoice == 3) {
                System.out.println("Enter the task you want to mark as completed: ");
                int taskNumber = input.nextInt();
                input.nextLine();

                int index = taskNumber - 1;

                if (index >= 0 && index < myTaskList.size()) {
                    Task targetTask = myTaskList.get(index);
                    targetTask.setCompleted(true);
                    System.out.println("Marked task as completed: " + targetTask.getTitle());
                }
                else {
                    System.out.println("Invalid task number.");
                }
            }

            else if (userChoice == 4) {
                System.out.println("Enter the task you want to delete: ");
                int taskNumber = input.nextInt();
                input.nextLine();

                int index = taskNumber - 1;

                if (index >= 0 && index < myTaskList.size()) {
                    Task removedTask = myTaskList.remove(index);
                    System.out.println("Successfully removed task: " + removedTask.getTitle());
                }
                else {
                    System.out.println("Invalid task number.");
                }
            }

            else if (userChoice == 5) {
                System.out.println("Closing task list. Goodbye!");
                keepRunning = false;
            }

            else {
                System.out.println("Invalid option. Please choose a valid option number between 1 and 5!");
            }
        }

        System.out.println("--- MY CURRENT TASKS ---");
        for (int i = 0; i <myTaskList.size(); i++) {
            Task currentTask = myTaskList.get(i);
            System.out.println((i + 1) + ". " + currentTask.getTitle() +  " --> " + currentTask.getDescription());
        }

    }
}
