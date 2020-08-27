package sample;

import Domain.*;
import Infrastructure.Controller;
import Repository.Repository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application
{
    public static void initData(Controller<Entity> ctrl) throws Exception
    {
        ArrayList<Formation.Group.Subgroup> list_subgroup1 = new ArrayList<>();
        ArrayList<Formation.Group.Subgroup> list_subgroup2 = new ArrayList<>();
        ArrayList<Formation.Group.Subgroup> list_subgroup3 = new ArrayList<>();
        ArrayList<Formation.Group.Subgroup> list_subgroup6 = new ArrayList<>();

        list_subgroup1.add(new Formation.Group.Subgroup("821/1"));
        list_subgroup1.add(new Formation.Group.Subgroup("821/2"));

        list_subgroup2.add(new Formation.Group.Subgroup("811/1"));
        list_subgroup2.add(new Formation.Group.Subgroup("811/2"));

        list_subgroup3.add(new Formation.Group.Subgroup("812/1"));
        list_subgroup2.add(new Formation.Group.Subgroup("812/2"));

        list_subgroup6.add(new Formation.Group.Subgroup("121"));

        ArrayList<Formation.Group> listgroup1 = new ArrayList<>();
        ArrayList<Formation.Group> listgroup2 = new ArrayList<>();
        ArrayList<Formation.Group> listgroup3 = new ArrayList<>();

        listgroup1.add(new Formation.Group("821", list_subgroup1));
        listgroup2.add(new Formation.Group("811", list_subgroup2));
        listgroup2.add(new Formation.Group("812", list_subgroup3));
        listgroup3.add(new Formation.Group("121", list_subgroup6));

        Discipline d1 = new Discipline(1, "Computer System Architecture", 6);
        Discipline d2 = new Discipline(2, "Complex Analysis", 6);
        Discipline d3 = new Discipline(3, "Databases", 6);
        Discipline d4 = new Discipline(4, "Advanced Methods of Programming", 6);
        Discipline d5 = new Discipline(5, "Differential Equations", 6);
        Discipline d6 = new Discipline(6, "Algebra1", 6);
        Discipline d7 = new Discipline(7, "Basic Math", 6);
        Discipline d8 = new Discipline(8, "Programming and Algorithms", 6);
        Discipline d9 = new Discipline(9, "Geometry 3", 6);

        Teacher t1 = new Teacher(1, "Apatean Anca", "ancaa@cs.ubbcluj.ro", "12.03.1984", "Assistant");
        Teacher t2 = new Teacher(2, "Salagean Grigore", "salagean@cs.ubbcluj.ro", "03.05.1962", "Professor");
        Teacher t3 = new Teacher(3, "Emilia Pop", "popemilia@cs.ubbcluj.ro", "12.11.1985", "Assistant");
        Teacher t4 = new Teacher(4, "Bocicor Iuliana", "bocicor@cs.ubbcluj.ro", "02.10.1983","Lecturer");
        Teacher t5 = new Teacher(5, "Lorincz Beata", "lorincz@cs.ubbcluj.ro", "01.01.1980", "Assistant");
        Teacher t7 = new Teacher(7, "Surdu Sabina", "sabina@cs.ubbcluj.ro", "14.06.1980", "Lecturer");
        Teacher t8 = new Teacher(8, "Vancea Alexandru", "vancea@cs.ubbcluj.ro", "01.01.1970", "Lecturer");
        Teacher t9 = new Teacher(9, "Serban Marcel", "serban@cs.ubbcluj.ro", "07.12.1979", "Professor");
        Teacher t10 = new Teacher(10, "Pelea Cosmin", "pelea@cs.ubbcluj.ro", "13.10.1977", "Professor");
        Teacher t11 = new Teacher(11, "Trif Tiberiu", "ttrif@cs.ubbcluj.ro", "10.10.1979", "Professor");
        Teacher t12 = new Teacher(12, "Ludusan Mirela", "ludusan@cs.ubbcluj.ro", "01.01.1990", "Assistant");
        Teacher t13 = new Teacher(13, "Chira Camelia", "cchira@cs.ubbcluj.ro", "01.01.1980", "Professor");
        Teacher t14 = new Teacher(14, "Blaga Paul", "blaga@cs.ubbcluj.ro", "01.01.1970", "Professor");

        Room r1 = new Room(1, "A311", "Facultatea de Drept");
        Room r2 = new Room(2, "7/I", "Central Building");
        Room r3 = new Room(3, "A308", "Facultatea de Drept");
        Room r4 = new Room(4, "L339", "FSEGA");
        Room r5 = new Room(5, "C310", "FSEGA");
        Room r6 = new Room(6, "C510", "FSEGA");
        Room r7 = new Room(7, "L402", "FSEGA");
        Room r8 = new Room(8, "bratianu", "Cladirea Echinox");
        Room r9 = new Room(9, "A312", "Facultatea de Drept");
        Room r10 = new Room(10, "6/II", "Central Building");
        Room r11 = new Room (11, "pi", "Mathematica");
        Room r12 = new Room (12, "theta", "Mathematica");
        Room r13 = new Room (13, "gamma", "Mathematica");
        Room r14 = new Room(14, "A303", "Facultatea de Drept");
        Room r15 = new Room(15, "Goanga","Mathematica");
        Room r16 = new Room (16, "L439", "FSEGA");
        Room r19 = new Room (19, "A2", "FSEGA");
        Room r17 = new Room(17, "5/I", "Central Building");
        Room r18 = new Room (18, "e", "Mathematica");

        ctrl.getDisciplineRepository().addEntity(d1);
        ctrl.getDisciplineRepository().addEntity(d2);
        ctrl.getDisciplineRepository().addEntity(d3);
        ctrl.getDisciplineRepository().addEntity(d4);
        ctrl.getDisciplineRepository().addEntity(d5);
        ctrl.getDisciplineRepository().addEntity(d6);
        ctrl.getDisciplineRepository().addEntity(d7);
        ctrl.getDisciplineRepository().addEntity(d8);
        ctrl.getDisciplineRepository().addEntity(d9);

        ctrl.getTeacherRepository().addEntity(t1);
        ctrl.getTeacherRepository().addEntity(t2);
        ctrl.getTeacherRepository().addEntity(t3);
        ctrl.getTeacherRepository().addEntity(t4);
        ctrl.getTeacherRepository().addEntity(t5);
        ctrl.getTeacherRepository().addEntity(t7);
        ctrl.getTeacherRepository().addEntity(t8);
        ctrl.getTeacherRepository().addEntity(t9);
        ctrl.getTeacherRepository().addEntity(t10);
        ctrl.getTeacherRepository().addEntity(t11);
        ctrl.getTeacherRepository().addEntity(t12);
        ctrl.getTeacherRepository().addEntity(t13);
        ctrl.getTeacherRepository().addEntity(t14);

        ctrl.getRoomsRepository().addEntity(r1);
        ctrl.getRoomsRepository().addEntity(r2);
        ctrl.getRoomsRepository().addEntity(r3);
        ctrl.getRoomsRepository().addEntity(r4);
        ctrl.getRoomsRepository().addEntity(r5);
        ctrl.getRoomsRepository().addEntity(r6);
        ctrl.getRoomsRepository().addEntity(r7);
        ctrl.getRoomsRepository().addEntity(r8);
        ctrl.getRoomsRepository().addEntity(r9);
        ctrl.getRoomsRepository().addEntity(r10);
        ctrl.getRoomsRepository().addEntity(r11);
        ctrl.getRoomsRepository().addEntity(r12);
        ctrl.getRoomsRepository().addEntity(r13);
        ctrl.getRoomsRepository().addEntity(r14);
        ctrl.getRoomsRepository().addEntity(r15);
        ctrl.getRoomsRepository().addEntity(r16);
        ctrl.getRoomsRepository().addEntity(r17);
        ctrl.getRoomsRepository().addEntity(r18);
        ctrl.getRoomsRepository().addEntity(r19);

        Activity a1 = new Activity(t1, d1, 1 , "Activity1", "Seminar", "08:00-10:00", r14, "Monday");
        Activity a2 = new Activity(t2, d2, 2, "Activity2", "Course", "10:00-12:00", r15, "Monday");
        Activity a3 = new Activity(t2, d2, 3, "Activity3", "Seminar", "12:00-14:00", r3, "Monday");
        Activity a4 = new Activity(t1, d1, 4, "Activity4", "Laboratory", "08:00-10:00", r4, "Tuesday");
        Activity a5 = new Activity(t3, d3, 5, "Activity5", "Laboratory", "10:00-12:00", r2, "Tuesday");
        Activity a6 = new Activity(t4, d4, 6, "Activity6", "Course", "12:00-14:00", r5, "Tuesday");
        Activity a7 = new Activity(t4, d4, 7, "Activity7", "Laboratory", "14:00-16:00", r7, "Tuesday");
        Activity a8 = new Activity(t7, d3, 8, "Activity8", "Seminar", "10:00-12:00", r9, "Thursday");
        Activity a9 = new Activity(t7, d3, 9, "Activity9", "Course", "12:00-14:00", r10, "Thursday");
        Activity a10 = new Activity(t8, d1, 10, "Activity10", "Course", "18:00-20:00", r19, "Thursday");
        Activity a11 = new Activity(t9, d5, 11, "Activity11", "Course", "12:00-14:00", r13, "Friday");
        Activity a12 = new Activity(t9, d5, 12, "Activity12", "Seminar", "14:00-16:00", r11, "Friday");
        Activity a13 = new Activity(t9, d5, 13, "Activity13", "Laboratory", "16:00-18:00", r12, "Friday");

        Activity a14 = new Activity(t10, d6, 14, "Activity14", "Seminar", "08:00-10:00", r14, "Monday");
        Activity a15 = new Activity(t10, d6, 15, "Activity15", "Course", "10:00-12:00", r2, "Monday");
        Activity a16 = new Activity(t11, d7, 16, "Activity16", "Course", "08:00-10:00", r17, "Tuesday");
        Activity a17 = new Activity(t12, d8, 17, "Activity17", "Laboratory", "08:00-10:00", r16, "Wednesday");
        Activity a18 = new Activity(t13, d8, 18, "Activity18", "Laboratory", "18:00-20:00", r16, "Thursday");
        Activity a19 = new Activity(t13, d8, 19, "Activity19", "Seminar", "10:00-12:00", r9, "Friday");
        Activity a20 = new Activity(t13, d8, 20, "Activity20", "Course", "12:00-14:00", r10, "Friday");

        Activity a21 = new Activity(t10, d6, 21, "Activity21", "Seminar", "10:00-12:00", r9, "Tuesday");

        Activity a22 = new Activity(t14, d9, 22, "Activity22", "Course", "14:00-16:00", r18, "Thursday");
        Activity a23 = new Activity(t14, d9, 22, "Activity23", "Seminar", "16:00-18:00", r18, "Thursday");

        ctrl.getActivityRepository().addEntity(a1);
        ctrl.getActivityRepository().addEntity(a2);
        ctrl.getActivityRepository().addEntity(a3);
        ctrl.getActivityRepository().addEntity(a4);
        ctrl.getActivityRepository().addEntity(a5);
        ctrl.getActivityRepository().addEntity(a6);
        ctrl.getActivityRepository().addEntity(a7);
        ctrl.getActivityRepository().addEntity(a8);
        ctrl.getActivityRepository().addEntity(a9);
        ctrl.getActivityRepository().addEntity(a10);
        ctrl.getActivityRepository().addEntity(a11);
        ctrl.getActivityRepository().addEntity(a12);
        ctrl.getActivityRepository().addEntity(a13);
        ctrl.getActivityRepository().addEntity(a14);
        ctrl.getActivityRepository().addEntity(a15);
        ctrl.getActivityRepository().addEntity(a16);
        ctrl.getActivityRepository().addEntity(a17);
        ctrl.getActivityRepository().addEntity(a18);
        ctrl.getActivityRepository().addEntity(a19);
        ctrl.getActivityRepository().addEntity(a20);
        ctrl.getActivityRepository().addEntity(a21);
        ctrl.getActivityRepository().addEntity(a22);
        ctrl.getActivityRepository().addEntity(a23);

        ArrayList<Activity> list1 = new ArrayList<>();
        ArrayList<Activity> list2 = new ArrayList<>();
        ArrayList<Activity> list3 = new ArrayList<>();

        list1.add(a1);
        list1.add(a2);
        list1.add(a3);
        list1.add(a4);
        list1.add(a5);
        list1.add(a6);
        list1.add(a7);
        list1.add(a8);
        list1.add(a9);
        list1.add(a10);
        list1.add(a11);
        list1.add(a12);
        list1.add(a13);

        list2.add(a14);
        list2.add(a15);
        list2.add(a16);
        list2.add(a17);
        list2.add(a18);
        list2.add(a19);
        list2.add(a20);
        list2.add(a21);

        list3.add(a22);
        list3.add(a23);

        Formation f1 = new Formation(1, "MIE2", list1, listgroup1);
        Formation f2 = new Formation(2, "MIE1", list2, listgroup2);
        Formation f3 = new Formation(3, "M2", list3, listgroup3);

        ctrl.getFormationRepository().addEntity(f1);
        ctrl.getFormationRepository().addEntity(f2);
        ctrl.getFormationRepository().addEntity(f3);

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Repository<Entity> teacherRepo = new Repository<>();
        Repository<Entity> disciplineRepo = new Repository<>();
        Repository<Entity> activityRepo = new Repository<>();
        Repository<Entity> formationRepo = new Repository<>();
        Repository<Entity> roomRepo = new Repository<>();

        Controller<Entity> ctrl = new Controller<Entity>(teacherRepo, disciplineRepo, activityRepo, roomRepo, formationRepo);
        initData(ctrl);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Connections controllerConnections = new Connections(ctrl);
        loader.setController(controllerConnections);
        Parent root = loader.load();

        controllerConnections.populate();

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("mainIcon.png")));
        primaryStage.setTitle("Main Application");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args);}
}

