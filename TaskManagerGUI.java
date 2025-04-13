import javax.swing.*;
import java.awt.*;

public class TaskManagerGUI {
    private TaskManager manager = new TaskManager();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(listModel);

    public TaskManagerGUI() {
        JFrame frame = new JFrame("Simple Task Manager");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        updateTaskList();

        JTextField taskInput = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark as Completed");

        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                manager.addTask(task);
                updateTaskList();
                taskInput.setText("");
            }
        });

        completeButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected >= 0) {
                manager.completeTask(selected);
                updateTaskList();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(taskInput, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.EAST);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);
        frame.add(completeButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateTaskList() {
        listModel.clear();
        for (Task task : manager.getTasks()) {
            listModel.addElement(task.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerGUI::new);
    }
}