package task_6.ToDoAPP_GUI.src.todoapp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TodoApp extends JFrame {

    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;
    private JTextField taskInputField;
    private JButton addButton;
    private JButton deleteButton;

    // --- Color Palette ---
    private final Color PRIMARY_COLOR = new Color(52, 152, 219); // Peter River Blue
    private final Color ACCENT_COLOR = new Color(46, 204, 113); // Emerald Green
    private final Color DANGER_COLOR = new Color(231, 76, 60); // Alizarin Red
    private final Color BACKGROUND_LIGHT = new Color(240, 240, 240); // Light Gray
    private final Color BACKGROUND_DARK = new Color(220, 220, 220); // Slightly Darker Gray
    private final Color TEXT_COLOR = new Color(44, 62, 80); // Wet Asphalt
    private final Color LIST_BACKGROUND = Color.WHITE;
    private final Color LIST_SELECTION_BACKGROUND = new Color(178, 223, 255); // Light Blue

    public TodoApp() {
        // Set up the main frame
        setTitle("GUI To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_LIGHT);
        setLayout(new BorderLayout(20, 20));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- Apply Nimbus Look and Feel ---
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to default
            System.out.println("Nimbus L&F not available, using default.");
        }

        // --- Initialize Components ---
        todoListModel = new DefaultListModel<>();
        todoList = new JList<>(todoListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        todoList.setBackground(LIST_BACKGROUND);
        todoList.setForeground(TEXT_COLOR);
        todoList.setFixedCellHeight(30);
        todoList.setBorder(BorderFactory.createLineBorder(BACKGROUND_DARK, 1));
        todoList.setSelectionBackground(LIST_SELECTION_BACKGROUND);
        todoList.setSelectionForeground(TEXT_COLOR);

        JScrollPane scrollPane = new JScrollPane(todoList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        taskInputField = new JTextField();
        taskInputField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        taskInputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Padding inside text field
        taskInputField.setBackground(LIST_BACKGROUND); // Match list background
        taskInputField.setForeground(TEXT_COLOR);

        addButton = createStyledButton("Add Task", ACCENT_COLOR);
        deleteButton = createStyledButton("Delete Selected", DANGER_COLOR);

        // --- Layout Panels ---

        // Input Panel (North)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(10, 0)); // Spacing between text field and button
        inputPanel.setBackground(BACKGROUND_LIGHT);
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Buttons Panel (South)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0)); // Spacing between buttons
        buttonPanel.setBackground(BACKGROUND_LIGHT);
        buttonPanel.add(deleteButton);

        // --- Add Components to Frame ---
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Listeners ---
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        taskInputField.addActionListener(e -> addTask()); // Add task on Enter key

        // Make the frame visible
        setVisible(true);
    }

    /**
     * Helper method to create consistently styled buttons.
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); // Remove border when focused
        button.setBorder(new EmptyBorder(10, 15, 10, 15)); // Padding inside button
        button.setOpaque(true); // Ensure background is painted
        button.setBorderPainted(false); // Do not paint default border

        // Add a simple hover effect (background change)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter()); // Make it slightly brighter on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor); // Restore original color on exit
            }
        });
        return button;
    }

    private void addTask() {
        String task = taskInputField.getText().trim();
        if (!task.isEmpty()) {
            todoListModel.addElement(task);
            taskInputField.setText(""); // Clear input
            // Scroll to the bottom to show the newly added task
            int lastIndex = todoListModel.getSize() - 1;
            if (lastIndex >= 0) {
                todoList.ensureIndexIsVisible(lastIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please enter a task!",
                    "Empty Task",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a task to delete!",
                    "No Task Selected",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new TodoApp());
    }
}
