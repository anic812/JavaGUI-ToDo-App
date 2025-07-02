-----

# Simple GUI To-Do List Application

This is a straightforward To-Do List application built with Java Swing. It provides a user-friendly graphical interface to add and delete tasks, helping you keep track of your daily to-dos beside it's made with the help of ai for tha UI.


-----

## Features

  * **Add Tasks:** Easily add new tasks to your list using the input field and "Add Task" button or by pressing Enter.
  * **Delete Tasks:** Remove completed or unwanted tasks by selecting them from the list and clicking the "Delete Selected" button.
  * **Clean Interface:** A straightforward and intuitive design makes managing your tasks a breeze.
  * **Responsive Design:** The application window is resizable, and components adjust accordingly.
  * **Nimbus Look and Feel:** Utilizes the Nimbus Look and Feel for a modern and polished appearance.
  * **Hover Effects:** Buttons include a subtle hover effect for better user feedback.

-----

## Project Structure

  * `TodoApp.java`: Contains all the source code for the To-Do List application, including GUI setup, component initialization, event handling, and helper methods.

-----

## How to Run

1.  **Compile:** Open your terminal or command prompt. Navigate to the directory where you saved the `TodoApp.java` file. Compile the Java code using:
    ```bash
    javac TodoApp.java
    ```
2.  **Run:** After successful compilation, execute the application with:
    ```bash
    java TodoApp
    ```

-----

## Requirements

  * **Java Development Kit (JDK)** installed on your system.

-----

## Customization

You can easily customize the application's appearance by modifying the **color palette variables** defined at the beginning of the `TodoApp` class:

  * `PRIMARY_COLOR`
  * `ACCENT_COLOR`
  * `DANGER_COLOR`
  * `BACKGROUND_LIGHT`
  * `BACKGROUND_DARK`
  * `TEXT_COLOR`
  * `LIST_BACKGROUND`
  * `LIST_SELECTION_BACKGROUND`

You can also adjust fonts, padding, and other UI elements within the `TodoApp()` constructor and `createStyledButton()` method to suit your preferences.