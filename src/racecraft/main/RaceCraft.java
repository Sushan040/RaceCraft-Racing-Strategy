/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package racecraft.main;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import racecraft.view.MainDashboard;
import racecraft.controller.CollectionManager;

public class RaceCraft {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 1. Create the GUI
                MainDashboard frame = new MainDashboard();

                // 2. Create the controller and pass the GUI
                CollectionManager controller = new CollectionManager(frame);

                // 3. Show the GUI
                frame.setVisible(true);

                // 4. Optional: display startup message
                System.out.println("=== RaceCraft System Started ===");
                System.out.println(controller.getStatistics());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error starting application: " + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}


