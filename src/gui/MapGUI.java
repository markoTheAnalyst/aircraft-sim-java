package gui;

import main.Main;
import radar.Collision;
import simulator.Simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;

import static main.Main.*;

public class MapGUI extends Thread{
    private final Object lock;
    private int rows;
    private int columns;

    public MapGUI(Object lock, int rows, int columns) {
        this.lock = lock;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Main.log(Level.SEVERE, MapGUI.class.getName(), e);
        }
        JLabel lab = new JLabel("",SwingConstants.CENTER);
        new FileWatcher(Paths.get(EVENTS),lab).start();
        new FileWatcher(Paths.get(ALERT)).start();
        JFrame mainFrame = new JFrame();
        JButton btn = new JButton("Flying Permission");
        JButton btn2 = new JButton("events");
        JButton btn3 = new JButton("alerts");
        JPanel panel = new JPanel();


        JPanel lineStart = new JPanel(new GridBagLayout());
        mainFrame.add(lineStart, BorderLayout.LINE_START);
        JPanel buttonsCentered = new JPanel(new GridLayout(0, 1, 10, 10));
        lineStart.add(buttonsCentered);
        buttonsCentered.add(btn);
        buttonsCentered.add(btn2);
        buttonsCentered.add(btn3);

        mainFrame.add(panel);
        mainFrame.add(lab,BorderLayout.NORTH);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(100, 100, 500, 500);
        panel.setLayout(new GridLayout(rows, columns));

        JLabel[][] grid= new JLabel[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = new JLabel();
                grid[i][j].setBorder(new LineBorder(Color.BLACK));
                grid[i][j].setBackground(Color.white);
                grid[i][j].setOpaque(true);
                panel.add(grid[i][j]);
            }
        }
        mainFrame.setVisible(true);

        btn.addActionListener(e -> {
            System.out.println("Pressed");
            Simulator.permission = !Simulator.permission;
        });

        btn2.addActionListener(e -> {
           JFrame frame = new JFrame();
           JPanel panel1 = new JPanel();
           JButton list = new JButton("list");
           JTextArea tarea = new JTextArea(10, 10);
           tarea.setEditable(false);
           panel1.add(list);
           JFileChooser fc = new JFileChooser();
           frame.add(panel1,BorderLayout.NORTH);
           frame.setBounds(100, 100, 500, 200);
           frame.add(tarea, BorderLayout.CENTER);
           frame.setVisible(true);
           list.addActionListener(e1 -> {
               fc.setCurrentDirectory(new File(EVENTS ));
               int result = fc.showOpenDialog(frame);
               if (result == JFileChooser.APPROVE_OPTION) {
                   File selectedFile = fc.getSelectedFile();
                   System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                   try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)))) {
                       tarea.read(input, "reading");
                   } catch (IOException ex) {
                       Main.log(Level.SEVERE, MapGUI.class.getName(), ex);
                   }

               }
           });
        });

        btn3.addActionListener(e -> {
            JFrame frame = new JFrame();
            JPanel panel1 = new JPanel();
            JButton list = new JButton("list");
            JTextArea tarea = new JTextArea(10, 10);
            tarea.setEditable(false);
            panel1.add(list);
            JFileChooser fc = new JFileChooser();
            frame.add(panel1,BorderLayout.NORTH);
            frame.setBounds(100, 100, 500, 200);
            frame.add(tarea, BorderLayout.CENTER);
            frame.setVisible(true);
            list.addActionListener(e12 -> {
                fc.setCurrentDirectory(new File(ALERT ));
                int result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    Collision object;
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(selectedFile))) {
                        object = (Collision) objectInputStream.readObject();
                        tarea.setText(object.getDescription()+" "+object.getPosition()+" at  time "+object.getTime());
                    } catch (IOException | ClassNotFoundException ex) {
                        Main.log(Level.SEVERE, MapGUI.class.getName(), ex);
                    }
                }
            });
        });
        try {

            List<String> fileLines;
            String[] coordinates;
            while (true) {
                synchronized (lock) {
                    fileLines = Files.readAllLines(Paths.get(MAP));
                    for (String line : fileLines) {
                        coordinates = line.split(" ");
                        if("Foreign".equals(coordinates[2]))
                            grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])].setBackground(Color.magenta);
                        else if("Domestic".equals(coordinates[2]))
                            grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])].setBackground(Color.BLACK);
                        else if("Airplane".equals(coordinates[2]))
                            grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])].setBackground(Color.blue);
                        else if("Helicopter".equals(coordinates[2]))
                            grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])].setBackground(Color.red);
                        else if("UnmannedAerialVehicle".equals(coordinates[2]))
                            grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])].setBackground(Color.GREEN);

                    }
                }
                Thread.sleep(1000);
                for (int i = 0; i < rows; i++)
                    for (int j = 0; j < columns; j++)
                        grid[i][j].setBackground(Color.white);
            }
        } catch (IOException | InterruptedException e) {
            Main.log(Level.SEVERE, MapGUI.class.getName(), e);
        }
    }
}
