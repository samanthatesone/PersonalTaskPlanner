import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        ArrayList<Task> myTaskList = new ArrayList<>();
        loadTasks(myTaskList);

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
                            status = "[X]";
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

                Task newTask = new Task(newTitle, newDescription, false);

                myTaskList.add(newTask);
                System.out.println("Successfully added task: " + newTitle + " --> " + newDescription);
            }

            else if (userChoice == 3) {
                System.out.println("Enter the task you want to mark as completed: ");
                int taskNumber = input.nextInt();
                input.nextLine();

                int index = taskNumber - 1;

                if (index >= 0 && index < myTaskList.size()) {
                    Task targetTask = myTaskList.get(index);
                    targetTask.setCompleted(true);
                    System.out.println("Marked task as completed: " + targetTask.getTitle() + " --> " + targetTask.getDescription());
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
                    System.out.println("Successfully removed task: " + removedTask.getTitle() + " --> "  + removedTask.getDescription());
                }
                else {
                    System.out.println("Invalid task number.");
                }
            }

            else if (userChoice == 5) {
                System.out.println("Closing task list. Goodbye!");
                saveTasks(myTaskList);
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

    public static void loadTasks(ArrayList<Task> list) {
        File file = new File("tasks.txt");
        if (!file.exists()) {
            return;
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean isCompleted = Boolean.parseBoolean(parts[2]);

                    Task t = new Task(title, description, isCompleted);
                    list.add(t);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public static void saveTasks(ArrayList<Task> list) {
        File file = new File("tasks.txt");

        try (PrintWriter writer = new PrintWriter(new File("tasks.txt"))) {
            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                writer.println(t.getTitle() + "|" + t.getDescription() + "|" + t.isCompleted());
            }
        }
        catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
