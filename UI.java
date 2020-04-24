package UI;

import Domain.*;
import Repository.*;
import Infrastructure.*;
import Utils.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UI
{
    private Controller ctrl;
    private String type;

    public UI(Controller ctrl, String type)
    { this.ctrl = ctrl; this.type = type;}

    private static void printMenu()
    {
        System.out.println("Please enter an option in order to access a certain database: \n");
        System.out.println("\t 1) Teacher database. ");
        System.out.println("\t 2) Discipline database. ");
        System.out.println("\t 3) Activity database. ");
        System.out.println("\t 4) Rooms database. ");
        System.out.println("\t 5) Formation database. ");
        System.out.println("\t 0) Exit. \n");
    }

    private static void printMenuTeacher()
    {
        System.out.println("Please enter an option to modify the database: \n");
        System.out.println("\t 1) Add a teacher to the database. ");
        System.out.println("\t 2) Remove a teacher by its name. ");
        System.out.println("\t 3) Update the information of a certain teacher. ");
        System.out.println("\t 4) See the database. ");
        System.out.println("\t 5) Sort the database by the teachers names. ");
        System.out.println("\t 6) Filter the teachers who have a certain surname. ");
        System.out.println("\t 7) Filter the teachers that are born in a certain year." );
        System.out.println("\t 8) Filter all the teachers by a given rank.");
        System.out.println("\t 0) Exit. \n");
    }

    private static void printMenuDiscipline()
    {
        System.out.println("Please enter an option to modify the database: \n");
        System.out.println("\t 1) Add a discipline to the database. ");
        System.out.println("\t 2) Remove a discipline by its name. ");
        System.out.println("\t 3) Update the information of a certain discipline. ");
        System.out.println("\t 4) See the database. ");
        System.out.println("\t 5) Sort the database by the disciplines names. ");
        System.out.println("\t 6) Sort the disciplines by their number of credits. " ) ;
        System.out.println("\t 7) Filter the disciplines by their number of credits. ");
        System.out.println("\t 0) Exit. \n");
    }

    private static void printMenuActivity()
    {
        System.out.println("Please enter an option to modify the database: \n");
        System.out.println("\t 1) Add an activity to the database. ");
        System.out.println("\t 2) Remove an activity by its name. ");
        System.out.println("\t 3) Update the information of a certain activity. ");
        System.out.println("\t 4) See the activity database. ");
        System.out.println("\t 5) Filter the activities by room and sort the remaining items by their timeslot");
        System.out.println("\t 0) Exit. \n");
    }

    private static void printMenuRoom()
    {
        System.out.println("Please enter an option to modify the database: \n");
        System.out.println("\t 1) Add a room to the database. ");
        System.out.println("\t 2) Remove a room by its name. ");
        System.out.println("\t 3) Update the information of a certain room. ");
        System.out.println("\t 4) See the room database. ");
        System.out.println("\t 0) Exit. \n");
    }

    private static void printMenuFormation()
    {
        System.out.println("Please enter an option to modify the database: \n");
        System.out.println("\t 1) Add a formation to the database. ");
        System.out.println("\t 2) Add a group to a certain formation. ");
        System.out.println("\t 3) Add a subgroup to a certain group from a given formation. ");
        System.out.println("\t 4) Remove a formation by its name. ");
        System.out.println("\t 5) Remove a group by its name from a formation. ");
        System.out.println("\t 6) Remove a subgroup by its name from a formation. ");
        System.out.println("\t 7) Update the information of a certain formation. ");
        System.out.println("\t 8) See the formation database. ");
        System.out.println("\t 9) See the timetable of a given formation. ");
        System.out.println("\t 0) Exit. \n");
    }


    private ArrayList<String> readTeacher()
    {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of a teacher: ");
        String name = scanner.nextLine();
        if (name.equals(""))
            while (name.equals(""))
            {
                System.out.println("The name you provided is not valid. Please enter a new one: ");
                name = scanner.nextLine();
            }
        System.out.println("Please provide some contact information: ");
        String email = scanner.nextLine();
        if (email.equals(""))
            while (email.equals(""))
            {
                System.out.println("The information you provided is not valid. Please enter a new one: ");
                email = scanner.nextLine();
            }
        System.out.println("Please tell the date of birth of the teacher: ");
        String date = scanner.nextLine();
        if (date.equals(""))
            while (date.equals(""))
            {
                System.out.println("The information you provided is not valid. Please enter a new one: ");
                date = scanner.nextLine();
            }
        if (date.length()!=10)
            while (date.length()!=10)
            {
                System.out.println("The format of the date is not valid. The standard format is DD.MM.YYYY. Please enter a new date:  ");
                date = scanner.nextLine();
            }
        if (Integer.parseInt(date.substring(0,2)) < 1 || Integer.parseInt(date.substring(0, 2)) > 31)
            while (Integer.parseInt(date.substring(0,2)) < 1 || Integer.parseInt(date.substring(0, 2)) > 31)
            {
                System.out.println("The day is not a valid one. Please enter a new date: ");
                date = scanner.nextLine();
            }
        if (Integer.parseInt(date.substring(3,5)) < 1 || Integer.parseInt(date.substring(3, 5)) > 12)
            while (Integer.parseInt(date.substring(3,5)) < 1 || Integer.parseInt(date.substring(3, 5)) > 12)
            {
                System.out.println("The month is not a valid one. Please enter a new date: ");
                date = scanner.nextLine();
            }
        if (Integer.parseInt(date.substring(6,10)) < 1940 || Integer.parseInt(date.substring(6, 10)) > 2019)
            while (Integer.parseInt(date.substring(6,10)) < 1940 || Integer.parseInt(date.substring(6, 10)) > 2000)
            {
                System.out.println("The year is not a valid one. Please enter a new date: ");
                date = scanner.nextLine();
            }
        System.out.println("Please enter the rank of a teacher: ");
        String rank = scanner.nextLine();
        if (rank.equals(""))
            while (rank.equals(""))
            {
                System.out.println("The rank you provided is not valid. Please enter a new one: ");
                rank = scanner.nextLine();
            }
        result.add(String.valueOf(this.ctrl.getTeacherRepository().getAll().size() + 1));
        result.add(name);
        result.add(email);
        result.add(date);
        result.add(rank);
        return result;
    }

    private ArrayList<String> readDiscipline()
    {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the discipline: ");
        String name = scanner.nextLine();
        if (name.equals(""))
            while (name.equals(""))
            {
                System.out.println("The name you provided is not valid. Please enter a new one: ");
                name = scanner.nextLine();
            }
        System.out.println("Please enter the number of credits for this course: ");
        String credits = scanner.nextLine();
        if (Integer.parseInt(credits) < 0)
            while (Integer.parseInt(credits) < 0)
            {
                System.out.println("The number of credits must be a positive, not null value. Please enter a new value: ");
                credits = scanner.nextLine();
            }
        result.add(String.valueOf(this.ctrl.getDisciplineRepository().getAll().size() + 1));
        result.add(name);
        result.add(credits);
        return result;
    }

    private ArrayList<String> readActivity()
    {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> teacher = this.readTeacher();
        ArrayList<String> discipline = this.readDiscipline();
        Scanner scanner = new Scanner(System.in);
        String name = "Activity" + (this.ctrl.getActivityRepository().getAll().size() + 1);
        System.out.println("Please enter the type of the activity: ");
        String type = scanner.nextLine();
        if (type.equals("") || !(type.equals("Activity") || type.equals("Laboratory") || type.equals("Course")))
            while (type.equals(""))
            {
                System.out.println("The type you provided is not valid. These are the valid types: \n" +
                       "\t Course \n" + "\t Seminar \n" + "\t Laboratory \n" + "Please enter a new one: ");
                type = scanner.nextLine();
            }
        ArrayList<String> r = readRoom();
        System.out.println("Please enter a time slot for this activity. The format of the hours should be like this: HH:MM-HH:MM");
        String time = scanner.nextLine();
        if (time.equals("") || time.length()!=11)
            while (time.equals("") || time.length()!=11)
            {
                System.out.println("The time format is not valid. Please enter a new one: ");
                time = scanner.nextLine();
            }
        System.out.println("Please enter a day for the activity. Please add only days starting from Monday and ending with Friday.");
        String day = scanner.nextLine();
        ArrayList<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        if (day.equals("") || !days.contains(day))
            while (day.equals("") || !days.contains(day))
            {
                System.out.println("The day is not a valid one. Please enter a new one: ");
                day = scanner.nextLine();
            }
        result.add(String.valueOf(this.ctrl.getActivityRepository().getAll().size() + 1));
        result.add(name);
        result.addAll(teacher);
        result.addAll(discipline);
        result.add(type);
        result.addAll(r);
        result.add(time);
        result.add(day);
        return result;
    }

    private ArrayList<String> readRoom()
    {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the room: ");
        String name = scanner.nextLine();
        if (name.equals(""))
            while (name.equals(""))
            {
                System.out.println("The name you provided is not valid. Please enter a new one: ");
                name = scanner.nextLine();
            }
        System.out.println("Please enter the name of building where the room is located: ");
        String building = scanner.nextLine();
        if (building.equals(""))
            while (building.equals("")) {
                System.out.println("The name of the building you provided is not valid. Please enter a new one: ");
                building = scanner.nextLine();
            }
        result.add(String.valueOf(this.ctrl.getRoomsRepository().getAll().size() + 1));
        result.add(name);
        result.add(building);
        return result;
    }

    private ArrayList<String> readFormation()
    {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the formation: ");
        String nameFormation = scanner.nextLine();
        if (nameFormation.equals(""))
            while(nameFormation.equals(""))
            {
                System.out.println("Please enter a valid name.");
                nameFormation = scanner.nextLine();
            }
        System.out.println("Please enter the name of a group: ");
        String nameGroup = scanner.nextLine();
        if (nameGroup.equals(""))
            while(nameGroup.equals(""))
            {
                System.out.println("Please enter a valid name.");
                nameGroup = scanner.nextLine();
            }
        System.out.println("Please enter the name of a subgroup: ");
        String nameSubgroup = scanner.nextLine();
        if (nameSubgroup.equals(""))
            while(nameSubgroup.equals(""))
            {
                System.out.println("Please enter a valid name.");
                nameSubgroup = scanner.nextLine();
            }
        result.add(String.valueOf(this.ctrl.getFormationRepository().getAll().size() + 1));
        result.add(nameFormation);
        result.add(nameGroup);
        result.add(nameSubgroup);
        System.out.println("Please enter the name of the activity: ");
        String activity = scanner.nextLine();
        if (activity.equals(""))
            while (activity.equals(""))
            {
                System.out.println("Please enter a valid name.");
                activity = scanner.nextLine();
            }
        result.add(activity);
        return result;
    }

    private void teacherOptions() {
        boolean ok = true;
        while(ok)
        {
            printMenuTeacher();
            int option;
            System.out.println("Please choose an option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option < 0 || option > 8)
                while (option < 0) {
                    System.out.println("Please enter a valid option: ");
                    option = scanner.nextInt();
                }
            switch (option)
            {
                case 0: { ok = false; break; }
                case 1:
                {
                    if (this.type.equals("text") || this.type.equals("binary"))
                    {
                        try {
                        ArrayList<String> l = this.readTeacher();
                        if (this.ctrl.addTeacher(Integer.parseInt(l.get(0)), l.get(1), l.get(2), l.get(3), l.get(4)) == 1)
                            System.out.println("Adding was successful. \n");
                        this.ctrl.writeToFile();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Error! The teacher already exists in the database. \n");
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        ArrayList<String> l = this.readTeacher();
                        Teacher t = new Teacher(Integer.parseInt(l.get(0)), l.get(1), l.get(2), l.get(3), l.get(4));
                        this.ctrl.addTeacherDatabase(t);
                    }
                    break;
                }
                case 2:
                {
                    if (this.type.equals("text") || this.type.equals("binary"))
                    {
                        try {
                            System.out.println("Please enter the name of the teacher you want to remove: ");
                            Scanner scanner1 = new Scanner(System.in);
                            String name = scanner1.nextLine();
                            if (this.ctrl.removeTeacherByName(name) == 1)
                                System.out.println("Removing was successful. \n");
                            this.ctrl.writeToFile();
                        }
                        catch (Exception e)
                        {
                            System.out.println("Error! The teacher you want to remove does not exist in the database. \n");
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        System.out.println("Please enter the name of the teacher you want to remove: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        Teacher t = new Teacher(-1, name, "", "", "");
                        this.ctrl.removeTeacherByNameDatabase(t.getName());
                    }
                    break;
                }
                case 3:
                {
                    try {
                        ArrayList<String> l1 = this.readTeacher();
                        ArrayList<String> l2 = this.readTeacher();
                        if (this.ctrl.updateTeacher(Integer.parseInt(l1.get(0)), l1.get(1), l1.get(2), l1.get(3), l1.get(4),
                                Integer.parseInt(l2.get(0)), l2.get(1), l2.get(2), l2.get(3), l2.get(4)) == 1)
                            System.out.println("Updating was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! Either the teacher you want to update does not exist " +
                                "or the new teacher is in the database." + "\n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 4:
                {
                    if (this.type.equals("text") || this.type.equals("binary"))
                    {
                        Repository repo = this.ctrl.getTeacherRepository();
                        if (repo.getAll().size() == 0)
                            System.out.println("There are currently no teachers in the database. \n");
                        else {
                            System.out.println("Here is a list of the teachers: \n");
                            for (Object t : repo.getAll())
                                if (t instanceof Teacher) {
                                    String line = "\t *) ";
                                    line = line.concat(t.toString());
                                    System.out.println(line);
                                }
                        }
                        System.out.println("\n");
                    }
                    else
                    {
                        ArrayList<Teacher> list = this.ctrl.getAllDatabaseTeacher();
                        if (list.size() == 0)
                            System.out.println("There are currently no teachers in the database. \n");
                        else {
                            System.out.println("Here is a list of the teachers: \n");
                            for (Teacher t : list) {
                                String line = "\t *) ";
                                line = line.concat(t.toString());
                                System.out.println(line);
                            }
                        }
                    }
                    break;
                }
                case 5:
                {   try
                    {
                        SortClass sort = new SortByName();
                        ArrayList<Teacher> l = this.ctrl.sortTeacherRepositoryByName(sort);
                        System.out.println("This is the sorted database: \n");
                        for (Teacher t : l)
                            System.out.println(t.toString());
                        System.out.println("\n");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                }
                case 6:
                {
                    try {
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("Please enter a name in order to filter the database: ");
                        String name = scanner1.nextLine();
                        FilterClass f = new FilterByName(name);
                        this.ctrl.filterTeachersByName(f);
                        System.out.println("\n Filtering was successful. \n");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                }
                case 7:
                {
                    try {
                        System.out.println("Please enter a year in order to filter the database. The year must be from 4 digits. ");
                        Scanner scanner1 = new Scanner(System.in);
                        String year = scanner1.nextLine();
                        while (year.length() != 4) {
                            System.out.println("Please enter a valid year: ");
                            year = scanner.nextLine();
                        }
                        FilterClass f = new FilterByYear(year);
                        this.ctrl.filterTeachersByDOB(f);
                        System.out.println("\n Filtering was successful. \n");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                }
                case 8:
                {
                    System.out.println("Please enter a rank: ");
                    Scanner scanner1 = new Scanner(System.in);
                    String rank = scanner1.nextLine();
                    ArrayList<Teacher> result = this.ctrl.filterByRank(rank);
                    if (result.size() == 0)
                        System.out.println("There are no teachers with this rank.");
                    else
                        for (Teacher t: result)
                            System.out.println(t.toString());
                    break;
                }
            }
        }
    }

    private void disciplineOptions() {
        boolean ok = true;
        while(ok)
        {
            printMenuDiscipline();
            int option;
            System.out.println("Please choose an option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option < 0 || option > 7)
                while (option < 0) {
                    System.out.println("Please enter a valid option: ");
                    option = scanner.nextInt();
                }
            switch (option)
            {
                case 0: { ok = false; break; }
                case 1:
                {
                    if (this.type.equals("text") || this.type.equals("binary")) {
                        try {
                            ArrayList<String> l = this.readDiscipline();
                            if (this.ctrl.addDiscipline(Integer.parseInt(l.get(0)), l.get(1), Integer.parseInt(l.get(2))) == 1)
                                System.out.println("Adding was successful. \n");
                            this.ctrl.writeToFile();

                        } catch (Exception e) {
                            System.out.println("Error! The discipline already exists in the database. \n");
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        ArrayList<String> l = this.readDiscipline();
                        Discipline d = new Discipline(Integer.parseInt(l.get(0)), l.get(1), Integer.parseInt(l.get(2)));
                        this.ctrl.addDisciplineDatabase(d);
                    }
                    break;
                }
                case 2:
                {
                    if (this.type.equals("text") || this.type.equals("binary")) {
                        try {
                            System.out.println("Please enter the name of the discipline you want to remove: ");
                            Scanner scanner1 = new Scanner(System.in);
                            String name = scanner1.nextLine();
                            if (this.ctrl.removeDisciplineByName(name) == 1)
                                System.out.println("Removing was successful. \n");
                            this.ctrl.writeToFile();
                        } catch (Exception e) {
                            System.out.println("Error! The discipline you want to remove does not exist in the database. \n");
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        System.out.println("Please enter the name of the discipline you want to remove: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        Discipline d = new Discipline(-1, name, 0);
                        this.ctrl.removeDisciplineByNameDatabase(d.getName());
                    }
                    break;
                }
                case 3:
                {
                    try {
                        ArrayList<String> l1 = this.readDiscipline();
                        ArrayList<String> l2 = this.readDiscipline();
                        if (this.ctrl.updateDiscipline(Integer.parseInt(l1.get(0)), l1.get(1), Integer.parseInt(l1.get(2)),
                                Integer.parseInt(l2.get(0)), l2.get(1), Integer.parseInt(l2.get(2))) == 1)
                            System.out.println("Updating was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! Either the discipline you want to update does not exist " +
                                "or the new discipline is in the database." + "\n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 4:
                {
                    if (this.type.equals("text") || this.type.equals("binary")) {
                        Repository repo = this.ctrl.getDisciplineRepository();
                        if (repo.getAll().size() == 0)
                            System.out.println("There are currently no disciplines in the database. \n");
                        else {
                            System.out.println("Here is a list of the disciplines: \n");
                            for (Object t : repo.getAll())
                                if (t instanceof Discipline) {
                                    String line = "\t *) ";
                                    line = line.concat(t.toString());
                                    System.out.println(line);
                                }
                        }
                        System.out.println("\n");
                    }
                    else
                    {
                        ArrayList<Discipline> list = this.ctrl.getAllDatabaseDiscipline();
                        if (list.size() == 0)
                            System.out.println("There are currently no disciplines in the database. \n");
                        else {
                            System.out.println("Here is a list of the disciplines: \n");
                            for (Discipline discipline : list) {
                                String line = "\t *) ";
                                line = line.concat(discipline.toString());
                                System.out.println(line);
                            }
                        }
                    }
                    break;
                }
                case 5:
                {
                    try {
                        SortClass sort = new SortByName();
                        ArrayList<Discipline> l = this.ctrl.sortDisciplineRepositoryByName(sort);
                        System.out.println("This is the sorted database: \n");
                        for (Discipline d : l)
                            System.out.println(d.toString());
                        System.out.println("\n");
                    }
                    catch (Exception e) {e.printStackTrace();}
                    break;
                }
                case 6:
                {
                    try {
                        SortClass sort = new SortByCredits();
                        ArrayList<Discipline> l = this.ctrl.sortDisciplinesByCredits(sort);
                        System.out.println("This is the sorted database: \n");
                        for (Discipline d : l)
                            System.out.println(d.toString());
                        System.out.println("\n");
                    }
                    catch (Exception e) {e.printStackTrace();}
                    break;
                }
                case 7:
                {
                    try {
                        System.out.println("Please enter a number of credits in order to filter the database: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String credits = scanner1.nextLine();
                        FilterClass f = new FilterByCredit(Integer.parseInt(credits));
                        this.ctrl.filterDisciplinesByCredits(f);
                        System.out.println("\n Filtering was successful. \n");
                    }
                    catch (Exception e) {e.printStackTrace();}
                    break;
                }
            }
        }
    }

    private void activityOptions() {
        boolean ok = true;
        while(ok)
        {
            printMenuActivity();
            int option;
            System.out.println("Please choose an option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option < 0 || option > 5)
                while (option < 0) {
                    System.out.println("Please enter a valid option: ");
                    option = scanner.nextInt();
                }
            switch (option)
            {
                case 0: { ok = false; break; }
                case 1:
                {
                    try {
                        ArrayList<String> l = this.readActivity();
                        Teacher t = new Teacher(Integer.parseInt(l.get(2)), l.get(3), l.get(4), l.get(5), l.get(6));
                        Discipline d = new Discipline(Integer.parseInt(l.get(7)), l.get(8), Integer.parseInt(l.get(9)));
                        Room r = new Room(Integer.parseInt(l.get(11)), l.get(12), l.get(13));
                        if (this.ctrl.addActivity(Integer.parseInt(l.get(0)), l.get(1), t, d, l.get(10), r, l.get(14), l.get(15)) == 1)
                            System.out.println("Adding was successful. \n");
                        if (this.type.equals("text"))
                            this.ctrl.writeToFile();
                        else
                            this.ctrl.serializationActivity();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The activity already exists in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 2:
                {
                    try {
                        System.out.println("Please enter the name of the activity you want to remove: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        if (this.ctrl.removeActivityByName(name) == 1)
                            System.out.println("Removing was successful. \n");
                        if (this.type.equals("text"))
                            this.ctrl.writeToFile();
                        else
                            this.ctrl.serializationActivity();
                        assert (this.ctrl.getActivityRepository().equals(this.ctrl.deserializeActivity()));
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The activity you want to remove does not exist in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 3:
                {
                    try {
                        ArrayList<String> l1 = this.readActivity();
                        ArrayList<String> l2 = this.readActivity();

                        Teacher t1 = new Teacher(Integer.parseInt(l1.get(2)), l1.get(3), l1.get(4), l1.get(5), l1.get(6));
                        Discipline d1 = new Discipline(Integer.parseInt(l1.get(7)), l1.get(8), Integer.parseInt(l1.get(9)));
                        Room r1 = new Room(Integer.parseInt(l1.get(11)), l1.get(12), l1.get(13));

                        Teacher t2 = new Teacher(Integer.parseInt(l2.get(2)), l2.get(3), l2.get(4), l2.get(5), l2.get(6));
                        Discipline d2 = new Discipline(Integer.parseInt(l2.get(7)), l2.get(8), Integer.parseInt(l2.get(9)));
                        Room r2 = new Room(Integer.parseInt(l2.get(11)), l2.get(12), l2.get(13));

                        if (this.ctrl.updateActivity(Integer.parseInt(l1.get(0)), l1.get(1), t1, d1, l1.get(10), r1, l1.get(14), l1.get(15),
                                Integer.parseInt(l2.get(0)), l2.get(1), t2, d2, l2.get(10), r2, l2.get(14), l2.get(15)) == 1)
                        System.out.println("Updating was successful. \n");
                        if (this.type.equals("text"))
                            this.ctrl.writeToFile();
                        else
                            this.ctrl.serializationActivity();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! Either the activity you want to update does not exist " +
                                "or the new activity is in the database." + "\n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 4:
                {
                    Repository repo = this.ctrl.getActivityRepository();
                    if (repo.getAll().size() == 0)
                        System.out.println("There are currently no activities in the database. \n");
                    else {
                        System.out.println("Here is a list of the activities: \n");
                        for (Object t : repo.getAll())
                            if (t instanceof Activity)
                            {
                                String line = "\t *) ";
                                line = line.concat(t.toString());
                                System.out.println(line);
                            }
                    }
                    System.out.println("\n");
                    break;
                }
                case 5:
                {
                    ArrayList<String> r = readRoom();
                    Room room = new Room(Integer.parseInt(r.get(0)), r.get(1), r.get(2));
                    ArrayList<Activity> list = this.ctrl.sortAndFilterActivities(room);
                    if (list.size() == 0)
                        System.out.println("There are currently no activities with these properties \n");
                    else {
                        System.out.println("Here is a list of the activities: \n");
                        for (Object t : list)
                            if (t instanceof Activity)
                            {
                                String line = "\t *) ";
                                line = line.concat(t.toString());
                                System.out.println(line);
                            }
                    }
                    System.out.println("\n");
                    break;
                }
            }
        }
    }

    private void roomsOptions() {
        boolean ok = true;
        while(ok)
        {
            printMenuRoom();
            int option;
            System.out.println("Please choose an option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option < 0 || option > 4)
                while (option < 0) {
                    System.out.println("Please enter a valid option: ");
                    option = scanner.nextInt();
                }
            switch (option)
            {
                case 0: { ok = false; break; }
                case 1:
                {
                    try {
                        ArrayList<String> l = this.readRoom();
                        if (this.ctrl.addRoom(Integer.parseInt(l.get(0)), l.get(1), l.get(2)) == 1)
                            System.out.println("Adding was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The room already exists in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 2:
                {
                    try {
                        System.out.println("Please enter the name of the room you want to remove: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        if (this.ctrl.removeRoom(name) == 1)
                            System.out.println("Removing was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The room you want to remove does not exist in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 3:
                {
                    try {
                        ArrayList<String> l1 = this.readRoom();
                        ArrayList<String> l2 = this.readRoom();

                        if (this.ctrl.updateRoom(Integer.parseInt(l1.get(0)), l1.get(1), l1.get(2),
                                Integer.parseInt(l2.get(0)), l2.get(1), l2.get(2)) == 1)
                            System.out.println("Updating was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! Either the room you want to update does not exist " +
                                "or the new room is in the database." + "\n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 4:
                {
                    Repository repo = this.ctrl.getRoomsRepository();
                    if (repo.getAll().size() == 0)
                        System.out.println("There are currently no rooms in the database. \n");
                    else {
                        System.out.println("Here is a list of the rooms available: \n");
                        for (Object t : repo.getAll())
                            if (t instanceof Room)
                            {
                                String line = "\t ";
                                line = line.concat(t.toString());
                                System.out.println(line);
                            }
                    }
                    System.out.println("\n");
                    break;
                }
            }
        }
    }

    private void formationsOptions() {
        boolean ok = true;
        while(ok)
        {
            printMenuFormation();
            int option;
            System.out.println("Please choose an option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option < 0 || option > 9)
                while (option < 0) {
                    System.out.println("Please enter a valid option: ");
                    option = scanner.nextInt();
                }
            switch (option)
            {
                case 0: { ok = false; break; }
                case 1:
                {
                    try {
                        ArrayList<String> l = this.readFormation();
                        Activity activity = new Activity();
                        for (Object a : this.ctrl.getActivityRepository().getAll())
                            if (a instanceof Activity)
                                if (((Activity) a).getName().equals(l.get(4)))
                                    activity = (Activity) a;
                        ArrayList<Activity> lst = new ArrayList<>();
                        lst.add(activity);
                        Formation.Group.Subgroup subgroup = new Formation.Group.Subgroup(l.get(3));
                        ArrayList<Formation.Group.Subgroup> s = new ArrayList<>();
                        s.add(subgroup);
                        Formation.Group g = new Formation.Group(l.get(2), s);
                        ArrayList<Formation.Group> groups = new ArrayList<>();
                        groups.add(g);
                        if (this.ctrl.addFormation(Integer.parseInt(l.get(0)), l.get(1), lst, groups) == 1)
                            System.out.println("Adding was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The formation already exists in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 2:
                {
                    try {
                        System.out.println("Please enter the name of the formation where you want to add the group: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String formation = scanner1.nextLine();
                        System.out.println("Please enter the name of group you want to add: ");
                        String group = scanner1.nextLine();
                        ArrayList<Formation.Group.Subgroup> subgroups = new ArrayList<>();
                        boolean k = true;
                        while (k) {
                            System.out.println("Please add a subgroup for your new group: ");
                            String s = scanner1.nextLine();
                            Formation.Group.Subgroup sub = new Formation.Group.Subgroup(s);
                            subgroups.add(sub);
                            System.out.println("Would you like to add another one? ");
                            s = scanner1.nextLine();
                            if (s.equals("no"))
                                k = false;
                        }
                        if (this.ctrl.addGroup(formation, group, subgroups) == 1)
                            System.out.println("Adding was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The group already exists in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 3:
                {
                    try {
                        System.out.println("Please enter the name of the formation where you want to add the group: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String formation = scanner1.nextLine();
                        System.out.println("Please enter the name of group where you want to add: ");
                        String group = scanner1.nextLine();
                        System.out.println("Please enter the name of the subgroup you want to add: ");
                        String subgroups = scanner1.nextLine();
                        if (this.ctrl.addSubgroup(formation, group, subgroups) == 1)
                            System.out.println("Adding was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The subgroup already exists in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 4:
                {
                    try {
                        System.out.println("Please enter the name of the formation you want to remove: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        if (this.ctrl.removeFormation(name) == 1)
                            System.out.println("Removing was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The formation you want to remove does not exist in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 5:
                {
                    try {
                        System.out.println("Please enter the name of the group you want to remove: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        if (this.ctrl.removeGroup(name) == 1)
                            System.out.println("Removing was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The group you want to remove does not exist in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 6:
                {
                    try {
                        System.out.println("Please enter the name of the subgroup you want to remove: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.nextLine();
                        if (this.ctrl.removeSubgroup(name) == 1)
                            System.out.println("Removing was successful. \n");
                        this.ctrl.writeToFile();

                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! The subgroup you want to remove does not exist in the database. \n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 7:
                {
                    try {
                        ArrayList<String> l1 = this.readFormation();
                        ArrayList<String> l2 = this.readFormation();

                        Activity activity1 = new Activity();
                        for (Object a : this.ctrl.getActivityRepository().getAll())
                            if (a instanceof Activity)
                                if (((Activity) a).getName().equals(l1.get(4)))
                                    activity1 = (Activity) a;
                        ArrayList<Activity> lst1 = new ArrayList<>();
                        lst1.add(activity1);
                        Formation.Group.Subgroup subgroup1 = new Formation.Group.Subgroup(l1.get(3));
                        ArrayList<Formation.Group.Subgroup> s1 = new ArrayList<>();
                        s1.add(subgroup1);
                        Formation.Group g1 = new Formation.Group(l1.get(2), s1);
                        ArrayList<Formation.Group> groups1 = new ArrayList<>();
                        groups1.add(g1);

                        Activity activity2 = new Activity();
                        for (Object a : this.ctrl.getActivityRepository().getAll())
                            if (a instanceof Activity)
                                if (((Activity) a).getName().equals(l2.get(4)))
                                    activity2 = (Activity) a;
                        ArrayList<Activity> lst2 = new ArrayList<>();
                        lst2.add(activity2);
                        Formation.Group.Subgroup subgroup2 = new Formation.Group.Subgroup(l2.get(3));
                        ArrayList<Formation.Group.Subgroup> s2 = new ArrayList<>();
                        s2.add(subgroup2);
                        Formation.Group g2 = new Formation.Group(l2.get(2), s2);
                        ArrayList<Formation.Group> groups2 = new ArrayList<>();
                        groups2.add(g2);

                        if (this.ctrl.updateFormation(Integer.parseInt(l1.get(0)), l1.get(1), lst1, groups1,
                                Integer.parseInt(l2.get(0)), l2.get(1), lst2, groups2) == 1)
                        System.out.println("Updating was successful. \n");
                        this.ctrl.writeToFile();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! Either the formation you want to update does not exist " +
                                "or the new formation is in the database." + "\n");
                        e.printStackTrace();
                    }
                    break;
                }
                case 8:
                {
                    Repository repo = this.ctrl.getFormationRepository();
                    if (repo.getAll().size() == 0)
                        System.out.println("There are currently no formations in the database. \n");
                    else {
                        System.out.println("Here is a list of the formations available: \n");
                        for (Object t : repo.getAll())
                            if (t instanceof Formation)
                            {
                                String line = "";
                                line = line.concat(t.toString());
                                line = line.concat("Here is a list of activities that students of this specialization can follow: \n \n");
                                for (Activity a: ((Formation) t).getActivity()) {
                                    line = line.concat(a.getName());
                                    line = line.concat("\n");
                                }
                                System.out.println(line);
                            }
                    }
                    System.out.println("\n");
                    break;
                }
                case 9:
                {
                    System.out.println("Please enter the name of the formation for which you want to print the timetable: ");
                    Scanner scanner1 = new Scanner(System.in);
                    String name = scanner1.nextLine();
                    Formation f = new Formation(-1, name, new ArrayList<>(), new ArrayList<>());
                    ArrayList<Formation> result = this.ctrl.getTimetable(f);
                    System.out.println("This is the timetable for " + name + ": \n");
                    for (Formation formation: result)
                        for (Activity a: formation.getActivity())
                            System.out.println(a.getDay() + " | " + a.getTimeSlot() + " | " + a.getRoom().getName() + " | " + a.getRoom().getBuilding() + " | " + a.getDiscipline().getName() + " | " + a.getTeacher().getName() + " | ");
                    break;
                }
            }
        }
    }

    public void start() {
        boolean ok = true;
        while(ok) {
            printMenu();
            int option;
            System.out.println("Please choose an option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option < 0)
                while (option < 0 || option > 5) {
                    System.out.println("Please enter a valid option: ");
                    option = scanner.nextInt();
                }
            switch (option) {
                case 0: { System.out.println("Thank you for using my programme. "); ok = false; break; }
                case 1: { this.teacherOptions(); break; }
                case 2: { this.disciplineOptions(); break; }
                case 3: { this.activityOptions(); break; }
                case 4: { this.roomsOptions(); break; }
                case 5: { this.formationsOptions();break;}
            }
        }
    }
}
