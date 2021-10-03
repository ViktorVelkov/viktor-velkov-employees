package solution;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

public class Project implements Comparable<Project> {
    private ArrayList<Integer> empIDs = new ArrayList<>();
    private int projectID;
    private LocalDate localDateFrom;
    private LocalDate localDateTo;
    private long daysWorked;
    private int flag1;
    private int flag2;
    private int flag3;

    public Project() {}

    public Project(int empID,int projectID, String sLocalDateFrom, String slocalDateTo) {
            this.empIDs.add(empID);
            this.projectID = projectID;
            if( sLocalDateFrom.toLowerCase(Locale.ROOT).equals("null")) sLocalDateFrom = currentDateFormatted();
            if( slocalDateTo.toLowerCase(Locale.ROOT).equals("null")) slocalDateTo = currentDateFormatted();
            this.localDateTo = LocalDate.parse(slocalDateTo);
            this.localDateFrom = LocalDate.parse(sLocalDateFrom);
            this.daysWorked = calcDays(sLocalDateFrom, slocalDateTo);

    }


    public ArrayList<Integer> getEmpIDs() {
        return empIDs;
    }

    public void setEmpIDs(ArrayList<Integer> empIDs) {
        this.empIDs = empIDs;
    }

    public int getprojectID() {
        return projectID;
    }

    public void setprojectID(int projectID) {
        projectID = projectID;
    }

    public LocalDate getlocalDateFrom() {
        return localDateFrom;
    }

    public void setlocalDateFrom(LocalDate localDateFrom) {
        this.localDateFrom = localDateFrom;
    }

    public LocalDate getlocalDateTo() {
        return localDateTo;
    }

    public void setlocalDateTo(LocalDate localDateTo) {
        this.localDateTo = localDateTo;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }


    public static String currentDateFormatted(){
        String nullDate = "";
        try {
            LocalDate dateToday = LocalDate.now();
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-D");
            //lines[3] = dateToday.format(dtf);
            String day = Integer.toString(dateToday.getDayOfMonth());
            String month = Integer.toString(dateToday.getMonthValue());
            String year = Integer.toString(dateToday.getYear());
            nullDate += year + "-";
            nullDate += (month.length() == 1 ? "0" + month : month) + "-";
            nullDate += (day.length() == 1 ? "0" + day : day);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return nullDate;

    }

    public static long calcDays(String dateBeforeString, String dateAfterString){
        //Parsing the date
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        //calculating number of days in between
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        //displaying the number of days
//        System.out.println(noOfDaysBetween);

//        Period chronoPeriod = dateBefore.until(dateAfter);
//        long daysBetween = chronoPeriod.getDays();
//        System.out.println( daysBetween );
        return noOfDaysBetween;
    }



    @Override
    public int compareTo(Project o) {
        return (int)(this.daysWorked - o.daysWorked);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", localDateFrom=" + localDateFrom.toString() +
                ", localDateTo=" + localDateTo.toString() +
                ", daysWorked=" + daysWorked +
                '}' + "\n";
    }
}
