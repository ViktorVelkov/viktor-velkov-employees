package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class HashMapProjects {

    private ArrayList<Project> projects = new ArrayList<>();

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }


    /*
    * Tokenization of the data file, parsing it into Projects later on to be used to form Couples.
    * The method starts with searching for a file to read from. Next it reads line by line from it
    * and created a HashMap of Projects with the key being the Project ID. The method returns the
    * HashMap
    * */
    public HashMap<Integer, Project> tokenizeProjects(String sDir){

        File data;
        Scanner scanner;
        String dirPathAndName = System.getProperty("user.dir");
        HashMap<Integer, Project> array = new HashMap<>();

        if(!sDir.isEmpty()){

            try {
                sDir = sDir.replace("\\", "\\\\");
                data = new File(sDir);
                if(!data.canRead()){
                    throw new Exception();
                }
            }catch (Exception e){
                System.out.println("File not found! Proceeding to the default data file");
                dirPathAndName = dirPathAndName.replace("\\", "\\\\") + "\\Data";
                data = new File(dirPathAndName);
                data = new File(dirPathAndName);
            }
        }
        else {
            //could also check for the operation system
            dirPathAndName = dirPathAndName.replace("\\", "\\\\") + "\\Data";
            data = new File(dirPathAndName);

        }
        //read from the file and create object classes


        try {
            scanner = new Scanner(data);
            while (scanner.hasNext()) {
                String lines[] = (scanner.nextLine().replaceAll("\\s", "")).trim().split(",");

                if(!array.containsKey(Integer.parseInt(lines[1]))){
                    Project project =  new Project(Integer.parseInt(lines[0]),Integer.parseInt(lines[1]), lines[2],lines[3]);
                    array.put(Integer.parseInt(lines[1]),project);      //please check how passing an Integer class to a primitive variable works
                }
                else{
                    array.get(Integer.parseInt(lines[1])).getEmpIDs().add((Integer.parseInt(lines[0])));
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return array;
    }


    public ArrayList<Couple> couplesCoworkersFromProjects(HashMap<Integer, Project> projects){

        ArrayList<Couple> result = new ArrayList<>();

        Iterator it = projects.values().iterator();
        while (it.hasNext()){
            Project project = (Project) it.next();
            Couple loop = new Couple(project.getEmpIDs(),project.getDaysWorked());
            loop.addProjectDetails(project);
            int flag = 0;
            if(result.isEmpty()){ result.add(loop); }
            else {
                for (Couple couple : result) {
                    if (couple.compareCouples(loop.getCoworkers()) == true) {
                        couple.addProject(project);
                        flag = 1;
                    }
                }
                if(flag == 0){
                    result.add(loop);
                }

            }
        }

        return result;
    }

    public ArrayList<Couple> coWorkersWithMost(ArrayList<Couple> couples){

        ArrayList<Couple> result = new ArrayList<>();
        long daysWorkedMost = 0L;
        int index = 0;
        int indexTwo = 0;
        int indexAt = 0;
        int indexTwoAt = 0;
        //find the most days Couple
        for(Couple loop: couples){

            if(loop.getDaysWorkedTogether() > daysWorkedMost)
            {
                daysWorkedMost = loop.getDaysWorkedTogether();
                indexAt = index;
            }
            index++;

        }
        //add the largest found
        result.add(couples.get(indexAt));
        ArrayList<Couple> additionalSolutions = new ArrayList<>();
        //add all the ones with equal days
        for(int i = 0; i < couples.size(); i++){
            indexTwo = 0;
            int counter = 0;
            for(int x = 0; x < result.size(); x++){
               if(couples.get(i).compareCouples(result.get(x).getCoworkers())==false && couples.get(i).getDaysWorkedTogether() == result.get(x).getDaysWorkedTogether()){
                    counter++;
               }
            }
            if(counter == result.size()){additionalSolutions.add(couples.get(i));}
        }
        return result;
    }

}
