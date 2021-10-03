package solution;

import java.util.ArrayList;
import java.util.Comparator;

//public class Couple implements Comparable<Couple> {
public class Couple {
    private ArrayList<Integer> coworkers = new ArrayList<>();
    private ArrayList<Project> projectsWorkedOnTogether = new ArrayList<>();
    //int emp1;
    //int emp2;
    private long daysWorkedTogether = 0L;

    public Couple(ArrayList<Integer> coworkers){
        this.coworkers = coworkers;
    }

    public Couple(ArrayList<Integer> coworkers, long daysWorkedTogether) {
        this.coworkers = coworkers;
        this.daysWorkedTogether = daysWorkedTogether;
    }

    public long getDaysWorkedTogether() {
        return daysWorkedTogether;
    }


    public void addProject(Project project){
        projectsWorkedOnTogether.add(project);
        //+calculate the duration
        daysWorkedTogether += project.getDaysWorked();
    }

    public void addProjectDetails(Project project){
        projectsWorkedOnTogether.add(project);
    }

    public ArrayList<Integer> getCoworkers() {
        return coworkers;
    }

    public boolean compareCouples(ArrayList<Integer> coworkers_compare){

        int counter = 0;
        if(this.coworkers.equals(coworkers_compare)) return true;
        for(int i : coworkers_compare){
            if(this.coworkers.contains(i)) counter++;
        }
        if(counter == this.coworkers.size()) return true;
        return false;
    }


    @Override
    public String toString() {
        return "coworkers=" + coworkers +
                ", projectsWorkedOnTogether=" + projectsWorkedOnTogether +
                ", daysWorkedTogether=" + daysWorkedTogether +
                '}';
    }
}
