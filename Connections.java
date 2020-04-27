package sample;

import Domain.*;
import Infrastructure.Controller;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Connections
{

    private Controller ctrl;

    public Connections(Controller ctrl) {this.ctrl = ctrl;}

    @FXML private ListView<String> generalList;

    @FXML private MenuButton professorMenu;
    @FXML private MenuButton disciplineMenu;
    @FXML private MenuButton roomMenu;
    @FXML private MenuButton formationMenu;

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function implements the options for the menu button roomMenu
     * It will only allow to visualize the timetable of a certain room
     */
    @FXML
    public void populateRoom()
    {

        for (int i=0;i<this.roomMenu.getItems().size();i++)
        {
            int finalI = i;
            this.roomMenu.getItems().get(i).setOnAction(event -> createTimetable("Room", roomMenu.getItems().get(finalI).getText()));
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function implements the options of the menu button formationButton
     * The first option will populate the generalList with all the groups associated with the main formation
     * The second option will create the timetable of the main formation
     */
    @FXML
    public void populateListFormation()
    {
        this.generalList.getItems().clear();
        for (MenuItem parent: this.formationMenu.getItems())
            for (int i=0; i<((Menu) parent).getItems().size(); i++)
            {
                switch (i)
                {
                    case 0:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            generalList.getItems().add("\tThese are the groups of this formation: \n \n");
                            ArrayList<Formation> list = ctrl.getFormationRepository().getAll();
                            for (Formation formation: list)
                                if (formation.getName().equals(parent.getText()))
                                {
                                    for (Formation.Group g: formation.getGroups())
                                        generalList.getItems().add(g.getName());
                                    break;
                                }
                            if (generalList.getItems().size() == 1)
                            {
                                generalList.getItems().clear();
                                generalList.getItems().add("This formation does not have any groups yet.");
                            }
                        });
                        break;
                    case 1:
                        ((Menu) parent).getItems().get(i).setOnAction(event -> createTimetable("Formation", parent.getText()));
                        break;
                    default:
                        break;
                }
            }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function implements the options of the menu button disciplineMenu
     * Each element of this menu will be a menu of its own.
     * disciplineMenu will be populated with all the disciplines available in the disciplineRepository
     * Each discipline will allow the user to execute 5 commands
     * The first command will display in the generalList all the associations of the current discipline with all available teachers
     * The second command will display in the generalList all the associations of the current discipline with all formations assigned to study it
     * The third command will display the timetable of the current discipline
     * The fourth command will remove the selected discipline from the repository
     * The last command will allow the user to modify the information regarding the current discipline
     * If the details are not modified, an alert will pop up informing the user
     */
    @FXML
    public void populateListDiscipline()
    {
        this.generalList.getItems().clear();
        for (MenuItem parent: this.disciplineMenu.getItems())
            for (int i=0; i<((Menu) parent).getItems().size(); i++)
            {
                switch (i)
                {
                    case 0:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            generalList.getItems().add("\tThese are the qualified teachers of this discipline: \n \n");
                            ArrayList<Activity> list = ctrl.getActivityRepository().getAll();
                            for (Activity a: list)
                                if (a.getDiscipline().getName().equals(parent.getText()))
                                    if (!generalList.getItems().contains(a.getTeacher().toString()))
                                        generalList.getItems().add(a.getTeacher().toString());
                            if (generalList.getItems().size() == 1)
                            {
                                generalList.getItems().clear();
                                generalList.getItems().add("This discipline does not have any assigned teachers.");
                            }
                        });
                        break;
                    case 1:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            generalList.getItems().add("\tThese are the formations that will study this discipline: \n\n");
                            ArrayList<Formation> list = ctrl.getFormationRepository().getAll();
                            for (Formation f: list)
                                for (Activity a: f.getActivity())
                                    if (a.getDiscipline().getName().equals(parent.getText()))
                                        if (!generalList.getItems().contains(f.toString()))
                                            generalList.getItems().add(f.toString());
                            if (generalList.getItems().size() == 1)
                            {
                                generalList.getItems().clear();
                                generalList.getItems().add("For the moment there are no formations available");
                            }
                        });
                        break;
                    case 2:
                        ((Menu) parent).getItems().get(i).setOnAction(event -> createTimetable("Discipline", parent.getText()));
                        break;
                    case 3:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            String name = parent.getText();
                            try {
                                ctrl.removeDisciplineByName(name);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            disciplineMenu.getItems().remove(parent);
                        });
                        break;
                    case 4:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            Stage stage =  new Stage();
                            GridPane g = new GridPane();

                            Label label1 = new Label("The name of the discipline: ");
                            Label label2 = new Label("The number of credits for this discipline: ");

                            Discipline discipline = (Discipline) ctrl.getDisciplineRepository().getEntity(parent.getText());

                            TextField textField1 = new TextField(discipline.getName());
                            TextField textField2 = new TextField(String.valueOf(discipline.getNumberOfCredits()));

                            g.add(label1, 0, 0);
                            g.add(label2, 0, 1);

                            g.add(textField1, 2, 0);
                            g.add(textField2, 2, 1);

                            Button button = new Button("Save the modifications");

                            g.add(button, 1, 7);

                            Scene scene = new Scene(g);
                            stage.getIcons().add(new Image(getClass().getResourceAsStream("mainIcon.png")));
                            stage.setScene(scene);
                            stage.show();

                            button.setOnAction(event12 ->
                            {
                                if (ctrl.updateDiscipline(discipline.getId(), discipline.getName(), discipline.getNumberOfCredits(),
                                                            discipline.getId(), textField1.getText(), Integer.parseInt(textField2.getText())) == 0)
                                {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Error!");
                                    alert.setHeaderText("Information Alert");
                                    String s ="Update failed! Please check again your information";
                                    alert.setContentText(s);
                                    alert.show();
                                }
                                else
                                {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Update successful!");
                                    alert.setHeaderText("Information Alert");
                                    String s = "The new information of the discipline are now available in the repository ";
                                    alert.setContentText(s);
                                    alert.show();
                                    for (Object activity: this.ctrl.getActivityRepository().getAll())
                                        if (((Activity) activity).getDiscipline().getName().equals(parent.getText()))
                                        {
                                            Activity newActivity = (Activity) activity;
                                            newActivity.setDiscipline(new Discipline(this.ctrl.getDisciplineRepository().getIndex(new Entity(0, textField1.getText())) + 1,
                                                    textField1.getText(), Integer.parseInt(textField2.getText())));
                                            this.ctrl.getActivityRepository().updateEntity((Entity) activity, newActivity);
                                        }
                                    Menu newMenu = new Menu(textField1.getText());
                                    newMenu.getItems().add(new MenuItem("See all the teachers that are qualified to teach this discipline."));
                                    newMenu.getItems().add(new MenuItem("See all the formations that have this discipline in their timetable"));
                                    newMenu.getItems().add(new MenuItem("See the timetable of this discipline"));
                                    newMenu.getItems().add(new MenuItem("Delete this discipline"));
                                    newMenu.getItems().add(new MenuItem("Update the information of this discipline"));
                                    disciplineMenu.getItems().set(disciplineMenu.getItems().indexOf(parent), newMenu);
                                }
                            });
                        });
                        break;
                    default:
                        break;
                }
            }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function implements the options of the menu button professorMenu
     * Each element of this menu will be a menu of its own.
     * professorMenu will be populated with all the teachers available in the teacherRepository
     * Each teacher will allow the user to execute 5 commands
     * The first command will display in the generalList all the associations of the current teacher with its assigned formations
     * The second command will display in the generalList all the associations of the current teacher with the discipline he may teach
     * The third command will display the timetable of the current teacher
     * The fourth command will remove the selected teacher from the repository
     * The last command will allow the user to modify the information regarding the current teacher
     * If the details are not modified, an alert will pop up informing the user
     */
    @FXML
    public void populateListTeacher()
    {
        this.generalList.getItems().clear();
        for (MenuItem parent: this.professorMenu.getItems())
            for (int i=0; i<((Menu) parent).getItems().size(); i++)
            {
                switch (i)
                {
                    case 0:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            generalList.getItems().add("\tThese are the formations of this teacher: \n \n");
                            ArrayList<Formation> list = ctrl.getFormationRepository().getAll();
                            for (Formation f: list)
                                for (Activity a: f.getActivity())
                                    if (a.getTeacher().getName().equals(parent.getText()))
                                    {
                                        generalList.getItems().add(f.toString());
                                        break;
                                    }
                            if (generalList.getItems().size() == 1)
                            {
                                generalList.getItems().clear();
                                generalList.getItems().add("This professor does not have any formations for coaching");
                            }
                        });
                        break;
                    case 1:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                               generalList.getItems().add("\tThese are the disciplines this professor is qualified to lecture: \n\n");
                               ArrayList<Activity> list = ctrl.getActivityRepository().getAll();
                               for (Activity a: list)
                                   if (a.getTeacher().getName().equals(parent.getText()))
                                        if (!generalList.getItems().contains(a.getDiscipline().getName()))
                                            generalList.getItems().add(a.getDiscipline().getName());
                               if (generalList.getItems().size() == 1)
                               {
                                   generalList.getItems().clear();
                                   generalList.getItems().add("Currently, this teacher does not have any assigned discipline");
                               }
                        });
                        break;
                    case 2:
                        ((Menu) parent).getItems().get(i).setOnAction(event -> createTimetable("Teacher", parent.getText()));
                        break;
                    case 3:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            String name = parent.getText();
                            try {
                                ctrl.removeTeacherByName(name);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            professorMenu.getItems().remove(parent);
                        });
                        break;
                    case 4:
                        ((Menu) parent).getItems().get(i).setOnAction(event ->
                        {
                            Stage stage =  new Stage();
                            stage.getIcons().add(new Image(getClass().getResourceAsStream("mainIcon.png")));
                            GridPane g = new GridPane();

                            Label label1 = new Label("The name of the professor: ");
                            Label label2 = new Label("Email: ");
                            Label label3 = new Label("Date of birth: ");
                            Label label4 = new Label("The rank of this professor: ");

                            Teacher teacher = (Teacher) ctrl.getTeacherRepository().getEntity(parent.getText());

                            TextField textField1 = new TextField(teacher.getName());
                            TextField textField2 = new TextField(teacher.getEmail());
                            TextField textField3 = new TextField(teacher.getDateOfBirth());
                            TextField textField4 = new TextField(teacher.getRank());

                            g.add(label1, 0, 0);
                            g.add(label2, 0, 1);
                            g.add(label3, 0, 2);
                            g.add(label4, 0, 3);

                            g.add(textField1, 2, 0);
                            g.add(textField2, 2, 1);
                            g.add(textField3, 2, 2);
                            g.add(textField4, 2, 3);

                            Button button = new Button("Save the modifications");

                            g.add(button, 1, 7);

                            Scene scene = new Scene(g);
                            stage.setScene(scene);
                            stage.show();

                            button.setOnAction(event12 ->
                            {
                                if (ctrl.updateTeacher(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.getDateOfBirth(), teacher.getRank(),
                                                       teacher.getId(), textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText()) == 0)
                                {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Error!");
                                    alert.setHeaderText("Information Alert");
                                    String s ="Update failed! Please check again your information";
                                    alert.setContentText(s);
                                    alert.show();
                                }
                                else
                                {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Update successful!");
                                    alert.setHeaderText("Information Alert");
                                    String s = "The new information of the teacher are now in the repository ";
                                    alert.setContentText(s);
                                    alert.show();
                                    for (Object activity: this.ctrl.getActivityRepository().getAll())
                                        if (((Activity) activity).getTeacher().getName().equals(parent.getText()))
                                        {
                                            Activity newActivity = (Activity) activity;
                                            newActivity.setTeacher(new Teacher(this.ctrl.getTeacherRepository().getIndex(new Entity(0, textField1.getText())) + 1,
                                                    textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText()));
                                            this.ctrl.getActivityRepository().updateEntity((Entity) activity, newActivity);
                                        }
                                    Menu newMenu = new Menu(textField1.getText());
                                    newMenu.getItems().add(new MenuItem("See the formations this professor coaches"));
                                    newMenu.getItems().add(new MenuItem("See the disciplines this professor may lecture"));
                                    newMenu.getItems().add(new MenuItem("See the timetable of this teacher"));
                                    newMenu.getItems().add(new MenuItem("Delete this teacher"));
                                    newMenu.getItems().add(new MenuItem("Update the information of this professor"));
                                    professorMenu.getItems().set(professorMenu.getItems().indexOf(parent), newMenu);
                                }
                            });
                        });
                        break;
                    default:
                        break;
                }
            }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function will construct the elements of the primaryStage from Main
     * Each menu button will be populated according to the formats previously mentioned
     */
    @FXML
    public void populate()
    {
        this.generalList.getItems().clear();
        this.formationMenu.getItems().clear();
        this.disciplineMenu.getItems().clear();
        this.roomMenu.getItems().clear();
        this.professorMenu.getItems().clear();

        ArrayList<Teacher> list1 = this.ctrl.getTeacherRepository().getAll();
        ArrayList<Discipline> list2 = this.ctrl.getDisciplineRepository().getAll();
        ArrayList<Room> list3 = this.ctrl.getRoomsRepository().getAll();
        ArrayList<Formation> list4 = this.ctrl.getFormationRepository().getAll();

        for (Teacher t: list1)
        {
            Menu m = new Menu(t.getName());
            m.getItems().add(new MenuItem("See the formations this professor coaches"));
            m.getItems().add(new MenuItem("See the disciplines this professor may lecture"));
            m.getItems().add(new MenuItem("See the timetable of this teacher"));
            m.getItems().add(new MenuItem("Delete this teacher"));
            m.getItems().add(new MenuItem("Update the information of this professor"));
            if (!this.professorMenu.getItems().contains(m))
                this.professorMenu.getItems().add(this.professorMenu.getItems().size(), m);
        }

        for (Discipline d: list2)
        {
            Menu m = new Menu(d.getName());
            m.getItems().add(new MenuItem("See all the teachers that are qualified to teach this discipline."));
            m.getItems().add(new MenuItem("See all the formations that have this discipline in their timetable"));
            m.getItems().add(new MenuItem("See the timetable of this discipline"));
            m.getItems().add(new MenuItem("Delete this discipline"));
            m.getItems().add(new MenuItem("Update the information of this discipline"));
            if (!this.disciplineMenu.getItems().contains(m))
                this.disciplineMenu.getItems().add(this.disciplineMenu.getItems().size(), m);
        }

        for (Room r: list3)
        {
            MenuItem m = new MenuItem(r.getName());
            if (!this.roomMenu.getItems().contains(m))
            this.roomMenu.getItems().add(this.roomMenu.getItems().size(), m);
        }

        for (Formation f: list4)
        {
            Menu m = new Menu(f.getName());
            m.getItems().add(new MenuItem("See the groups of this formation."));
            m.getItems().add(new MenuItem("See the timetable of this formation"));
            if (!this.formationMenu.getItems().contains(m))
                this.formationMenu.getItems().add(this.formationMenu.getItems().size(), m);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param type will contain one of the following instances: "Teacher", "Discipline", "Room", "Formation"
     * @param name will contain the name of a certain entity
     * This function will create the stage of the timetable
     * The parameter "type" will allow the function to implement different timetables based on the Entity it receives
     * The parameter "name" is used to collect from the activityRepository that specific activity,
     * which contains the required Entity (Teacher/Discipline/Room/Formation)
     * If the type is "Formation", then the function will create a timetable capable of adding new activities in a graphical fashion:
     *      -> For each section of the timetable that is not occupied by a certain activity, a plus icon will be added
     *      -> Pressing that plus icon will open a new window in which the user will be asked to enter the primary information of that activity
     *      -> In order for the program to work correctly, all textFields must contain a text
     *      -> This iteration is only available for entities of type "Formation"
     */
    @FXML
    public void createTimetable(String type, String name)
    {
        GridPane timetable = new GridPane();

        ArrayList<Activity> list = ctrl.getActivityRepository().getAll();

        for (DayOfWeek dayOfWeek = DayOfWeek.MONDAY; dayOfWeek.getValue() <= DayOfWeek.FRIDAY.getValue();
             dayOfWeek = DayOfWeek.of(dayOfWeek.getValue() + 1) )
        {

            Label label = new Label(dayOfWeek.toString());
            label.setAlignment(Pos.CENTER);
            label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label.setStyle("-fx-background-color: black, mintcream; -fx-background-insets: 0, 1 0 1 1 ;");
            label.setFont(new Font("Lucida Handwriting", 14));
            timetable.add(label, dayOfWeek.getValue(), 0);
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        int rowCount = 0 ;

        for (LocalTime time = LocalTime.of(8, 0); time.isBefore(LocalTime.of(20, 0)); time=time.plusMinutes(60))
        {
            rowCount++ ;
            Label label = new Label(timeFormatter.format(time) + " - " + timeFormatter.format(time.plusHours(1)));
            label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            String color;
            if (rowCount % 2 == 0)
                color = "mintcream";
            else
                color = "CORNFLOWERBLUE" ;
            label.setStyle("-fx-background-color: black, "+color+"; -fx-background-insets: 0, 1 0 0 1;");
            label.setFont(new Font("Lucida Handwriting", 15));
            timetable.add(label, 0, rowCount);
        }

        if (type.equals("Formation"))
            for (int x = 1; x <= 5; x++)
                for (int y = 1; y <= rowCount; y++)
                {
                    Region region = new Region();
                    String color;
                    if (y % 2 == 0)
                        color = "mintcream";
                    else
                        color = "CORNFLOWERBLUE";
                    region.setStyle("-fx-background-color: black, " + color + "; -fx-background-insets: 0, 0 0 1 1 ;");
                    timetable.add(region, x, y);

                    Image image = new Image(getClass().getResourceAsStream("icon.png"));
                    Label label = new Label();
                    label.setGraphic(new ImageView(image));
                    timetable.add(label, x, y);
                    GridPane.setHalignment(label, HPos.CENTER);

                    label.setOnMouseClicked(mouseEvent ->
                    {
                        Stage stage = new Stage();
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("mainIcon.png")));
                        GridPane gridPane = new GridPane();

                        Label label1 = new Label("Please enter a teacher. If the teacher is not in the repository, it will automatically be added");
                        Label label2 = new Label("Please enter a discipline. If the discipline is not in te repository, it will automatically be added");
                        Label label3 = new Label("Please enter a room: ");
                        Label label4 = new Label("Please enter the type of the activity (Seminar, Course, Laboratory); ");

                        TextField textField1 = new TextField();
                        TextField textField2 = new TextField();
                        TextField textField3 = new TextField();
                        TextField textField4 = new TextField();

                        Button button = new Button("Save the activity for this formation");

                        gridPane.add(label1, 0, 0);
                        gridPane.add(label2, 0, 1);
                        gridPane.add(label3, 0, 2);
                        gridPane.add(label4, 0, 3);

                        gridPane.add(textField1, 2, 0);
                        gridPane.add(textField2, 2, 1);
                        gridPane.add(textField3, 2, 2);
                        gridPane.add(textField4, 2, 3);

                        gridPane.add(button, 1, 6);

                        Scene scene = new Scene(gridPane);
                        stage.setScene(scene);
                        stage.show();

                        final Teacher[] teacher = {new Teacher()};
                        final Discipline[] discipline = {new Discipline()};
                        final Room[] room = {new Room()};
                        Activity activity = new Activity();
                        activity.setName("Activity"+ (this.ctrl.getActivityRepository().getAll().size() + 1));

                        button.setOnAction(event ->
                        {

                            String teacherName = textField1.getText();
                            teacher[0] = new Teacher(ctrl.getTeacherRepository().getAll().size()+1, teacherName, "default email addres", "dd:mm:yyyy", "default rank");
                            try {
                                ctrl.getTeacherRepository().addEntity(teacher[0]);
                            }
                            catch (Exception ignored) { }

                            String disciplineName = textField2.getText();
                            discipline[0] = new Discipline(ctrl.getDisciplineRepository().getAll().size(), disciplineName, 0);
                            try {
                                ctrl.getDisciplineRepository().addEntity(discipline[0]);
                            } catch (Exception ignored) { }

                            String roomName = textField3.getText();
                            room[0] = new Room(ctrl.getRoomsRepository().getAll().size(), roomName, "default building");
                            try {
                                ctrl.getRoomsRepository().addEntity(room[0]);
                            } catch (Exception ignored) { }

                            String typeOfActivity = textField4.getText();
                            activity.setTypeOfActivity(typeOfActivity);

                            switch (timetable.getColumnIndex(label))
                            {
                                case 1:
                                    activity.setDay("Monday");
                                    break;
                                case 2:
                                    activity.setDay("Tuesday");
                                    break;
                                case 3:
                                    activity.setDay("Wednesday");
                                    break;
                                case 4:
                                    activity.setDay("Thursday");
                                    break;
                                case 5:
                                    activity.setDay("Friday");
                                    break;
                                default:
                                    break;
                            }
                            switch (timetable.getRowIndex(label)) 
                            {
                                case 1:
                                    activity.setTimeSlot("08:00-09:00");
                                    break;
                                case 2:
                                    activity.setTimeSlot("09:00-10:00");
                                    break;
                                case 3:
                                    activity.setTimeSlot("10:00-11:00");
                                    break;
                                case 4:
                                    activity.setTimeSlot("11:00-12:00");
                                    break;
                                case 5:
                                    activity.setTimeSlot("12:00-13:00");
                                    break;
                                case 6:
                                    activity.setTimeSlot("13:00-14:00");
                                    break;
                                case 7:
                                    activity.setTimeSlot("14:00-15:00");
                                    break;
                                case 8:
                                    activity.setTimeSlot("15:00-16:00");
                                    break;
                                case 9:
                                    activity.setTimeSlot("16:00-17:00");
                                    break;
                                case 10:
                                    activity.setTimeSlot("17:00-18:00");
                                    break;
                                case 11:
                                    activity.setTimeSlot("18:00-19:00");
                                    break;
                                case 12:
                                    activity.setTimeSlot("19:00-20:00");
                                    break;
                                case 13:
                                    activity.setTimeSlot("20:00-21:00");
                                    break;
                                default:
                                    break;
                            }

                            try {
                                this.ctrl.getActivityRepository().addEntity(activity);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            stage.close();
                            activity.setDiscipline(discipline[0]);
                            activity.setTeacher(teacher[0]);
                            activity.setRoom(room[0]);

                            ((Formation) this.ctrl.getFormationRepository().getEntity(name)).getActivity().add(activity);
                            System.out.println(ctrl.getDisciplineRepository().getAll().size());
                            System.out.println(ctrl.getTeacherRepository().getAll().size());
                            System.out.println(ctrl.getActivityRepository().getAll().size());
                            populate();
                        });
                    });
                }
        else
            for (int x = 1 ; x <= 5; x++)
                for (int y = 1 ; y <= rowCount; y++)
                {
                    Region region = new Region();
                    String color;
                    if (y % 2 == 0)
                        color = "mintcream";
                    else
                        color = "CORNFLOWERBLUE";
                    region.setStyle("-fx-background-color: black, " + color + "; -fx-background-insets: 0, 0 0 1 1 ;");
                    timetable.add(region, x, y);
                }

        for (int x = 0 ; x <= 5 ; x++)
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / 6);
            cc.setFillWidth(true);
            timetable.getColumnConstraints().add(cc);
        }

        for (int y = 0 ; y <= rowCount; y++)
        {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / (rowCount+1));
            rc.setFillHeight(true);
            timetable.getRowConstraints().add(rc);
        }

        switch (type)
        {
            case "Teacher":
                for (Activity a : list)
                    if (a.getTeacher().getName().equals(name))
                        setParameters(timetable, a, type);
                break;
            case "Discipline":
                for (Activity a : list)
                    if (a.getDiscipline().getName().equals(name))
                        setParameters(timetable, a, type);
                break;
            case "Room":
                for (Activity a : list)
                    if (a.getRoom().getName().equals(name))
                        setParameters(timetable, a, type);
                break;
            case "Formation":
                ArrayList<Formation> formations = ctrl.getFormationRepository().getAll();
                for (Formation f: formations)
                    if (f.getName().equals(name))
                        for (Activity a: f.getActivity())
                            setParameters(timetable, a, type);
                break;
            default:
                break;
        }
        Scene scene = new Scene(timetable, 600, 450);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("mainIcon.png")));
        stage.setScene(scene);
        stage.setTitle("Timetable ( " + name + " ) ");
        stage.show();
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param timetable is the actual timetable, send via a parameter so that all the modification will be visible
     * @param a is the activity that needs to be added to the timetable
     * @param type is the type previously mentioned
     * This function will set the indexes which will be used to position our activity in the timetable, according to the type received
     * The parameters dayIndex, beginHourIndex and endHourIndex are the indexes that help to find the position in the timetable
     * where the activity needs to be placed.
     * Depending on the type of activity, we may have 3 different cases:
     *             -> a red box will appear, consisting on an activity that has a "Course" associated with;
     *             -> a green box will appear, consisting on an activity that has a "Seminar" associated with;
     *             -> a yellow box will appear, consisting on an activity that has a "Laboratory" associated with;
     */
    @FXML
    public void setParameters(GridPane timetable, Activity a, String type)
    {
        /**
         * These 3 parameters will help acquire the position in the timetable
         * where the activity a needs to be placed. A place in the timetable is of
         * the form (x, y), where x represents a day and y represents a time slot
         */
        int dayIndex = 0;
        int beginHourIndex = 0;
        int endHourIndex = 0;

        ArrayList<String> hours = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>();

        hours.add("08:00"); hours.add("09:00"); hours.add("10:00"); hours.add("11:00");
        hours.add("12:00"); hours.add("13:00"); hours.add("14:00"); hours.add("15:00");
        hours.add("16:00"); hours.add("17:00"); hours.add("18:00"); hours.add("19:00");
        hours.add("20:00");

        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");

        String day = a.getDay();
        String beginHour = a.getTimeSlot().substring(0, 5);
        String endHour = a.getTimeSlot().substring(6);

        for (int i = 0; i< days.size(); i++)
            if (day.equals(days.get(i)))
            {
                dayIndex = i + 1;
                break;
            }

        for (int i = 0; i < hours.size(); i++)
        {
            if (beginHour.equals(hours.get(i)))
                beginHourIndex = i;
            if (endHour.equals(hours.get(i)))
            {
                endHourIndex = i;
                break;
            }
        }

        String colour = "";
        switch (a.getTypeOfActivity()) 
        {
            case "Seminar":
                colour = "green";
                break;
            case "Course":
                colour = "red";
                break;
            case "Laboratory":
                colour = "yellow";
                break;
            default:
                break;
        }
        personalizeTimetable(timetable, beginHourIndex, endHourIndex, dayIndex, colour, type, a);
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param timetable is the timetable used in both previous functions (setParameters(), createTimetable())
     * @param startHourIndex will receive an Integer that represents the column where the activity needs to be added
     * @param endHourIndex will receive an Integer that represents the row where the activity starts
     * @param dayIndex will receive an Integer that represents the row where the activity ends
     * @param colour is a String consisting of the colours previously mentioned, to distinguish different types of activities
     * @param type is the entity type
     * @param a is the activity that needs to be added to the timetable
     * This function adds the activity to the timetable, taking into account the given positions determined by those 3 indexes mentioned above
     *
     */
    @FXML
    public void personalizeTimetable(GridPane timetable, int startHourIndex, int endHourIndex, int dayIndex, String colour, String type, Activity a)
    {
        for (int i = startHourIndex+1; i<=endHourIndex; i++)
        {   Region region = new Region();
            region.setStyle("-fx-background-color: black, "+colour+"; -fx-background-insets: 0, 0 0 0 0 ;");
            timetable.add(region, dayIndex, i);
        }

        Label l = new Label("-->Click here for more details<--");
        l.setFont(new Font("Arial", 14));
        l.setOnMouseClicked(mouseEvent ->
        {
            Stage stage =  new Stage();
            GridPane g = new GridPane();
            Button button = new Button("Remove this activity for this formation");
            button.setOnAction(event ->
            {
                try {
                    this.ctrl.removeActivityByName(a.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.close();
            });

            Label label1 = new Label();
            Label label2 = new Label();
            Label label3 = new Label();

            TextField textField1 = new TextField();
            TextField textField2 = new TextField();
            TextField textField3 = new TextField();

            textField1.setMinWidth(400);
            textField1.setMinHeight(30);

            switch (type)
            {
                case "Teacher":
                    label1.setText("The discipline he's lecturing: ");
                    label2.setText("The formation(s) assigned: ");
                    label3.setText("The room where the activity will take place: ");

                    textField1 = new TextField();
                    textField2 = new TextField();
                    textField3 = new TextField();

                    textField3.setText(a.getRoom().toString());
                    textField1.setText(a.getDiscipline().getName());

                    for (Object formation: ctrl.getFormationRepository().getAll())
                        for (Activity activity: ((Formation) formation).getActivity())
                            if (activity.equals(a))
                                if (textField2.getText().length() == 0)
                                    textField2.setText(((Formation) formation).getName());
                                else
                                {
                                    String variable = textField2.getText().concat(" ," + ((Formation) formation).getName());
                                    textField2.setText(variable);
                                }
                    if (textField2.getText().length() == 0)
                        textField2.setText("There are currently no formations assigned to this teacher");

                    break;
                case "Discipline":
                    label1.setText("The teacher who lectures this discipline: ");
                    label2.setText("The formation(s) assigned: ");
                    label3.setText("The room where the activity will take place: ");

                    textField3.setText(a.getRoom().toString());
                    textField1.setText(a.getTeacher().getName());

                    for (Object formation: ctrl.getFormationRepository().getAll())
                        for (Activity activity: ((Formation) formation).getActivity())
                            if (activity.equals(a))
                                if (textField2.getText().length() == 0)
                                    textField2.setText(((Formation) formation).getName());
                                else
                                {
                                    String variable = textField2.getText().concat(" ," + ((Formation) formation).getName());
                                    textField2.setText(variable);
                                }
                    if (textField2.getText().length() == 0)
                        textField2.setText("There are currently no formations assigned to this teacher");

                    break;
                case "Formation":
                    label1.setText("The discipline assigned for this timeslot:: ");
                    label2.setText("The responsible teacher for this " + type + ": ");
                    label3.setText("The room where the activity will take place: ");

                    textField3.setText(a.getRoom().toString());
                    textField1.setText(a.getDiscipline().getName());
                    textField2.setText(a.getTeacher().getName());

                    if (textField2.getText().length() == 0)
                        textField2.setText("There are currently no formations assigned to this teacher");

                    break;
                default:
                    break;
                case "Room":
                    label1.setText("The discipline assigned for this timeslot: ");
                    label2.setText("The formation(s) assigned: ");
                    label3.setText("The teacher responsible for this course: ");

                    textField3.setText(a.getTeacher().toString());
                    textField1.setText(a.getDiscipline().getName());

                    for (Object formation: ctrl.getFormationRepository().getAll())
                        for (Activity activity: ((Formation) formation).getActivity())
                            if (activity.equals(a))
                                if (textField2.getText().length() == 0)
                                    textField2.setText(((Formation) formation).getName());
                                else
                                {
                                    String variable = textField2.getText().concat(" ," + ((Formation) formation).getName());
                                    textField2.setText(variable);
                                }
                    if (textField2.getText().length() == 0)
                        textField2.setText("There are currently no formations assigned to this teacher");

                    break;
            }

            g.add(label1, 0, 0);
            g.add(label2, 0, 1);
            g.add(label3, 0, 2);

            g.add(textField1, 2, 0);
            g.add(textField2, 2, 1);
            g.add(textField3, 2, 2);

            g.add(new Label(), 1, 3);

            g.add(button, 1, 5);

            Scene scene = new Scene(g);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("mainIcon.png")));
            stage.setScene(scene);
            stage.show();
        });
        l.setFont(new Font("Times New Roman", 16));
        timetable.add(l, dayIndex, startHourIndex + 1);
    }
}
