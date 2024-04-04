package simulator;

import main.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;

import static main.Main.CONFIG;

public class PropertiesChecker extends Thread {


    @Override
    public void run() {
        String line;
        List<String> fileLines;
        while (true) {
            try {
                fileLines = Files.readAllLines(Paths.get(CONFIG));
                line = fileLines.get(3);
                if ("true".equals(line.split("=")[1]))
                    Simulator.foreignMilitary = true;
                else if("false".equals(line.split("=")[1]))
                    Simulator.foreignMilitary = false;
                line = fileLines.get(4);
                if ("true".equals(line.split("=")[1]))
                    Simulator.domesticMilitary = true;
                else if("false".equals(line.split("=")[1]))
                    Simulator.domesticMilitary = false;
                Thread.sleep(1000);
            } catch (InterruptedException | ArrayIndexOutOfBoundsException | NullPointerException | IOException e) {
                Main.log(Level.SEVERE, PropertiesChecker.class.getName(), e);
            }
        }
    }
}
