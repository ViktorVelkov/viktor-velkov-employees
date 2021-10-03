package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static void run(){

        HashMapProjects projects = new HashMapProjects();
        System.out.println("Enter file destination or leave empty for default: ");
        Scanner filePath = new Scanner(System.in);
        String scannedPath = filePath.nextLine();
        filePath.close();
        System.out.println("You have enterred: " + scannedPath);
        HashMap<Integer,Project> map = projects.tokenizeProjects(scannedPath);
        ArrayList<Couple> result = projects.couplesCoworkersFromProjects(map);
        ArrayList<Couple> resultResult = projects.coWorkersWithMost(result);
        if(resultResult.size() == 1){
            System.out.println("Employees: " + resultResult.get(0).getCoworkers().get(0) + " and " + resultResult.get(0).getCoworkers().get(1) + " with " + resultResult.get(0).getDaysWorkedTogether() + " days ");
        }
    }

    public static void main(String[] args) {
        run();
    }

}