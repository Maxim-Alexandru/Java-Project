package Infrastructure;

import Domain.*;
import Repository.*;
import Utils.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controller<T extends Entity> implements ControllerInterface<T>
{
    private Repository<T> teacherRepository;
    private Repository<T> disciplineRepository;
    private Repository<T> activityRepository;
    private Repository<T> roomsRepository;
    private Repository<T> formationRepository;

    private String filenameBinary;
    private String filenameText;

    private TeacherDatabase database1;
    private DisciplineDatabase database2;

    public Controller() {}

    public Controller(Repository<T> repo1, Repository<T> repo2, Repository<T> repo3)
    {
        this.teacherRepository = repo1;
        this.disciplineRepository = repo2;
        this.activityRepository = repo3;
    }

    public Controller(Repository<T> repo1, Repository<T> repo2, Repository<T> repo3, Repository<T> repo4)
    {
        this.teacherRepository = repo1;
        this.disciplineRepository = repo2;
        this.activityRepository = repo3;
        this.formationRepository = repo4;
    }

    public Controller(Repository<T> repo1, Repository<T> repo2, Repository<T> repo3, Repository<T> repo4, Repository<T> repo5)
    {
        this.teacherRepository = repo1;
        this.disciplineRepository = repo2;
        this.activityRepository = repo3;
        this.roomsRepository = repo4;
        this.formationRepository = repo5;
    }

    public Controller(Repository<T> repo1, Repository<T> repo2, Repository<T> repo3, Repository<T> repo4, Repository<T> repo5, String filenameBinary, String filenameText, TeacherDatabase database1, DisciplineDatabase database2)
    {
        this.teacherRepository = repo1;
        this.disciplineRepository = repo2;
        this.activityRepository = repo3;
        this.roomsRepository = repo4;
        this.formationRepository = repo5;
        this.filenameBinary = filenameBinary;
        this.filenameText = filenameText;
        this.database1 = database1;
        this.database2 = database2;
    }

    public Repository<T> getTeacherRepository() {return this.teacherRepository;}
    public Repository<T> getDisciplineRepository() {return this.disciplineRepository;}
    public Repository<T> getActivityRepository() {return  this.activityRepository;}
    public Repository<T> getRoomsRepository() {return  this.roomsRepository;}
    public Repository<T> getFormationRepository() {return this.formationRepository;}

    public void setActivityRepository(Repository<T> repo) {this.activityRepository = repo;}

    public int addTeacher(int index, String name, String email, String dateOfBirth, String rank) throws Exception {
        T teacher = (T) new Teacher(index, name, email, dateOfBirth, rank);
        int in;
        in = this.teacherRepository.addEntity(teacher);
        return in;
    }
    public int removeTeacherByName(String name) throws Exception {
        int index;
        /**
         * When we want to remove a teacher from the database,
         * if this already exists in the activity database,
         * all the activities containing that Teacher should be removed as well.
         */
        int result;
        for (index = 0; index < this.getActivityRepository().getAll().size(); index++)
            if (this.getActivityRepository().getAll().get(index) instanceof Activity)
                if (((Activity) this.getActivityRepository().getAll().get(index)).getTeacher().getName().equals(name)) {
                    this.getActivityRepository().removeEntityByName(this.getActivityRepository().getAll().get(index).getName());
                    index--;
                }
        for (T f: this.getFormationRepository().getAll())
            for (int i=0;i<((Formation) f).getActivity().size(); i++)
                if (this.getActivityRepository().getIndex((T) ((Formation) f).getActivity().get(i)) == -1)
                {
                    ((Formation) f).getActivity().remove(((Formation) f).getActivity().get(i));
                    i--;
                }
        result =  this.teacherRepository.removeEntityByName(name);
        return result;
    }
    public int updateTeacher(int index1, String name1, String email1, String dateOfBirth1, String rank1,
                             int index2, String name2, String email2, String dateOfBirth2, String rank2)
    {
        T teacher1 = (T) new Teacher(index1, name1, email1, dateOfBirth1, rank1);
        T teacher2 = (T) new Teacher(index2, name2, email2, dateOfBirth2, rank2);
        int index;
        /**
         * When we update the information of a teacher,
         * the modifications should be found at all the
         * activities containing the updated teacher.
         */
        for (index = 0; index < this.getActivityRepository().getAll().size();index++)
            if (this.getActivityRepository().getAll().get(index) instanceof Activity)
                if (((Activity) this.getActivityRepository().getAll().get(index)).getTeacher().equals(teacher1))
                {
                    ((Activity) this.getActivityRepository().getAll().get(index)).setTeacher((Teacher) teacher2);
                    index--;
                }
        if (this.getTeacherRepository().updateEntity(teacher1, teacher2) == 0)
            if (((Teacher) teacher1).getDateOfBirth().equals(((Teacher) teacher2).getDateOfBirth()) &&
                    ((Teacher) teacher1).getEmail().equals(((Teacher) teacher2).getEmail()) &&
                    ((Teacher) teacher1).getRank().equals(((Teacher) teacher2).getRank()))
                return 0;
        return 1;
    }


    public int addDiscipline(int index, String name, int credits) throws Exception {
        T discipline = (T) new Discipline(index, name, credits);
        int in;
        in = this.disciplineRepository.addEntity(discipline);
        return in;
    }
    public int removeDisciplineByName(String name) throws Exception {
        int index;
        /**
         * When we want to remove a discipline from the database,
         * if this already exists in the activity database,
         * all the activities containing that Discipline should be removed as well.
         */
        int result;
        for (index = 0; index < this.getActivityRepository().getAll().size(); index++)
            if (this.getActivityRepository().getAll().get(index) instanceof Activity)
                if (((Activity) this.getActivityRepository().getAll().get(index)).getDiscipline().getName().equals(name))
                {
                    this.getActivityRepository().getAll().remove(index);
                    index--;
                }
        for (T f: this.getFormationRepository().getAll())
            for (int i=0;i<((Formation) f).getActivity().size(); i++)
                if (this.getActivityRepository().getIndex((T) ((Formation) f).getActivity().get(i)) == -1)
                {
                    ((Formation) f).getActivity().remove(((Formation) f).getActivity().get(i));
                    i--;
                }
        result = this.disciplineRepository.removeEntityByName(name);
        return result;
    }
    public int updateDiscipline(int index1, String name1, int credits1, int index2, String name2, int credits2)
    {
        T discipline1 = (T) new Discipline(index1, name1, credits1);
        T discipline2 = (T) new Discipline(index2, name2, credits2);

        for (T a: this.getActivityRepository().getAll())
            if (a instanceof Activity)
                if (((Activity) a).getDiscipline().equals(discipline1))
                {
                    if (!((Activity) a).getDiscipline().equals(discipline2))
                        ((Activity) a).setDiscipline((Discipline) discipline2);
                    else
                        return 0;
                }
        if (this.getDisciplineRepository().updateEntity(discipline1, discipline2) == 0)
            if (((Discipline) discipline1).getNumberOfCredits() == ((Discipline) discipline2).getNumberOfCredits())
                return 0;
        return 1;
    }

    int verifyIfRoomIsAvailable(Activity a)
    {
        for (T activity: this.getActivityRepository().getAll())
            if (activity instanceof Activity)
                if (((Activity) activity).getRoom().getName().equals(a.getRoom().getName()))
                    if (((Activity) activity).getDay().equals(a.getDay()) && ((Activity) activity).getTimeSlot().equals(a.getTimeSlot()))
                        return -1;
        return 1;
    }

    public int addActivity(int index, String name, Teacher t, Discipline d, String type, Room room, String time, String day) throws Exception {
        /**
         * In the case we add a new Activity having new entries of Teacher/Discipline,
         * these entities will be added to the corresponding databases as well.
         */
        T activity = (T) new Activity(t, d, index, name, type, time, room, day);
        int in;
        this.teacherRepository.addEntity((T) t);
        this.disciplineRepository.addEntity((T) d);
        if (verifyIfRoomIsAvailable((Activity) activity) != -1)
            in = this.activityRepository.addEntity(activity);
        else
            return -1;
        return in;
    }

    public int removeActivityByName(String name) throws Exception {
        T activity = (T) new Activity(new Teacher(), new Discipline(), -1, name, "", "", new Room(), "");
        int index = this.activityRepository.getIndex(activity);
        this.activityRepository.removeEntityByName(name);
        if (index != -1)
        {
            for (T formation: this.getFormationRepository().getAll())
                for (int i = 0; i< ((Formation) formation).getActivity().size(); i++)
                    if (!this.getActivityRepository().getAll().contains(((Formation) formation).getActivity().get(i)))
                    {
                        ((Formation) formation).getActivity().remove(((Formation) formation).getActivity().get(i));
                        i--;
                    }
            return 1;
        }
        else
            return 0;
    }

    public int updateActivity(int index1, String name1, Teacher t1, Discipline d1, String type1, Room room1, String time1, String day1,
                              int index2, String name2, Teacher t2, Discipline d2, String type2, Room room2, String time2, String day2)
    {
        T activity1 = (T) new Activity(t1, d1, index1, name1, type1, time1, room1, day1);
        T activity2 = (T) new Activity(t2, d2, index2, name2, type2, time2, room2, day2);
        if (this.verifyIfRoomIsAvailable((Activity) activity1) != -1 && this.verifyIfRoomIsAvailable((Activity) activity2)!=-1)
            return this.activityRepository.updateEntity(activity1, activity2);
        else
            return 0;
    }

    public int addRoom(int index, String name, String building) throws Exception {
        T room = (T) new Room(index, name, building);
        int in;
        in = this.roomsRepository.addEntity(room);
        return in;
    }

    public int removeRoom(String name) throws Exception {
        T room = (T) new Room(-1, name, "");
        int index = this.getRoomsRepository().getIndex(room);
        int value = -2;
        if (index != -1)
        {
            Activity a = new Activity();
            if (this.getRoomsRepository().getAll().get(index) instanceof Room)
                a = new Activity(new Teacher(), new Discipline(), -1, "", "", "", (Room) room, "");
            value = this.roomsRepository.removeEntityByName(name);
            for (Object activity : this.getActivityRepository().getAll())
                if (activity instanceof Activity)
                    if (((Activity) activity).getRoom().getName().equals(a.getRoom().getName()))
                        ((Activity) activity).setRoom(new Room(-1, "", ""));
        }

        return value;
    }

    public int updateRoom(int id1, String name1, String building1,
                          int id2, String name2, String building2)
    {
        T room1 = (T) new Room(id1, name1, building1);
        T room2 = (T) new Room(id2, name2, building2);
        return this.roomsRepository.updateEntity(room1, room2);
    }

    public int addFormation(int id, String name, ArrayList<Activity> activity, ArrayList<Formation.Group> groups) throws Exception {
        T formation = (T) new Formation(id, name, activity, groups);
        int in;
        in = this.formationRepository.addEntity(formation);
        return in;
    }

    public int addGroup(String formation, String nameGroup, ArrayList<Formation.Group.Subgroup> subgroups) throws Exception {
        for (T f: this.getFormationRepository().getAll())
            if (f instanceof Formation)
                if (f.getName().equals(formation))
                {
                    for (Formation.Group g: ((Formation) f).getGroups())
                        if (g.getName().equals(nameGroup))
                            throw new Exception("The group you are trying to add already exists");
                    Formation.Group group = new Formation.Group(nameGroup, subgroups);
                    ((Formation) f).getGroups().add(group);
                }
        return 1;
    }

    public int addSubgroup(String formation, String nameGroup, String subgroup) throws Exception {
        for (T f: this.getFormationRepository().getAll())
            if (f instanceof Formation)
                if (f.getName().equals(formation))
                    for (Formation.Group g: ((Formation) f).getGroups())
                        if (g.getName().equals(nameGroup))
                        {
                            for (Formation.Group.Subgroup s : g.getSubgroups())
                                if (s.getSub().equals(subgroup))
                                    throw new Exception("The group you are trying to add already exists");
                            Formation.Group.Subgroup sub = new Formation.Group.Subgroup(subgroup);
                            g.getSubgroups().add(sub);
                        }
        return 1;
    }

    public int removeFormation(String name) throws Exception {
        int result;
        result = this.formationRepository.removeEntityByName(name);
        return result;
    }

    public int removeGroup(String name) throws Exception {
        for (T formation: this.getFormationRepository().getAll())
            if (formation instanceof Formation)
                for (Formation.Group g: ((Formation) formation).getGroups())
                    if (g.getName().equals(name))
                    {
                        ((Formation) formation).getGroups().remove(g);
                        return 1;
                    }
        throw new Exception("Could not find the the group in order to remove it");
    }

    public int removeSubgroup(String name) throws Exception {
        for (T formation: this.getFormationRepository().getAll())
            if (formation instanceof Formation)
                for (int i=0;i<((Formation) formation).getGroups().size();i++)
                    if (((Formation) formation).getGroups().get(i).getName().contains(name))
                        for (Formation.Group.Subgroup s: ((Formation) formation).getGroups().get(i).getSubgroups())
                            if(s.getSub().equals(name))
                            {
                                ((Formation) formation).getGroups().get(i).getSubgroups().remove(s);
                                return 1;
                            }
        throw new Exception("Could not find the the subgroup in order to remove it");
    }

    public int updateFormation(int id1, String name1, ArrayList<Activity> activity1, ArrayList<Formation.Group> groups1,
                               int id2, String name2, ArrayList<Activity> activity2, ArrayList<Formation.Group> groups2)
    {
        T formation1 = (T) new Formation(id1, name1, activity1, groups1);
        T formation2 = (T) new Formation(id2, name2, activity2, groups2);
        return this.formationRepository.updateEntity(formation1, formation2);
    }

    public ArrayList sortTeacherRepositoryByName(SortClass s) { return s.sort(this.teacherRepository); }
    public ArrayList sortDisciplineRepositoryByName(SortClass s) { return s.sort(this.disciplineRepository); }
    public ArrayList sortDisciplinesByCredits(SortClass s) { return s.sort(this.disciplineRepository); }

    public void filterTeachersByName(FilterClass f) throws Exception { this.teacherRepository = f.filter(this.teacherRepository);}
    public void filterTeachersByDOB(FilterClass f) throws Exception { this.teacherRepository = f.filter(this.teacherRepository);}
    public void filterDisciplinesByCredits(FilterClass f) throws Exception {this.disciplineRepository = f.filter(this.disciplineRepository);}

    public void serializationActivity()
    {
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream(filenameBinary));
            out.writeObject(this.getActivityRepository().getAll());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Repository<T> deserializeActivity()
    {
        ObjectInputStream in = null;
        Repository<T> r = new Repository<T>();
        try
        {
            in = new ObjectInputStream(new FileInputStream(filenameBinary));
            r = new Repository<T>((ArrayList) in.readObject());
            this.setActivityRepository(r);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public void readFromFile() throws Exception {
        File file = new File((filenameText));
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine())
        {
            String entity = scanner.nextLine();
            int teacherSize = Integer.parseInt(entity);
            for (int i=0;i<teacherSize;i++)
            {
                entity = scanner.nextLine();
                String[] attributes = entity.split("/");
                this.addTeacher(Integer.parseInt(attributes[0]), attributes[1], attributes[2], attributes[3], attributes[4]);
            }

            entity = scanner.nextLine();
            int disciplineSize = Integer.parseInt(entity);
            for (int i=0;i<disciplineSize;i++)
            {
                entity = scanner.nextLine();
                String[] attributes = entity.split("/");
                this.addDiscipline(Integer.parseInt(attributes[0]), attributes[1], Integer.parseInt(attributes[2]));
            }

            entity = scanner.nextLine();
            int activitySize = Integer.parseInt(entity);
            for (int i=0;i<activitySize;i++)
            {
                entity = scanner.nextLine();
                String[] attributes = entity.split("/");
                Teacher teacher = new Teacher(Integer.parseInt(attributes[2]), attributes[3], attributes[4], attributes[5], attributes[6]);
                Discipline discipline = new Discipline(Integer.parseInt(attributes[7]), attributes[8], Integer.parseInt(attributes[9]));
                Room room = new Room(Integer.parseInt(attributes[11]), attributes[12], attributes[13]);
                Activity a = new Activity(teacher, discipline, Integer.parseInt(attributes[0]), attributes[1], attributes[10], attributes[14], room, attributes[15]);
                this.getActivityRepository().addEntity((T) a);
            }

            entity = scanner.nextLine();
            int roomSize = Integer.parseInt(entity);
            for (int i=0;i<roomSize;i++)
            {
                entity = scanner.nextLine();
                String[] attributes = entity.split("/");
                this.addRoom(Integer.parseInt(attributes[0]), attributes[1], attributes[2]);
            }
        }
    }

    public void writeToFile() throws IOException {
        File f = new File(filenameText);
        FileWriter writer = new FileWriter(f);
        writer.write(String.valueOf(this.getTeacherRepository().getAll().size()));
        writer.flush();
        writer.write("\n");
        writer.flush();
        for (Object t: this.getTeacherRepository().getAll())
        {
            if (t instanceof  Teacher)
            {
                String object = "";
                object = object.concat(((Teacher) t).getId() + "/" +
                        ((Teacher) t).getName() + "/" +
                        ((Teacher) t).getEmail() + "/" +
                        ((Teacher) t).getDateOfBirth() + "/" +
                        ((Teacher) t).getRank() + "\n");
                writer.write(object);
                writer.flush();
            }
        }
        writer.write(String.valueOf(this.getDisciplineRepository().getAll().size()));
        writer.flush();
        writer.write("\n");
        writer.flush();
        for (Object t: this.getDisciplineRepository().getAll())
        {
            if (t instanceof  Discipline)
            {
                String object = "";
                object = object.concat(((Discipline) t).getId() + "/" +
                        ((Discipline) t).getName() + "/" +
                        ((Discipline) t).getNumberOfCredits() + "\n");
                writer.write(object);
                writer.flush();
            }
        }
        writer.write(String.valueOf(this.getActivityRepository().getAll().size()));
        writer.flush();
        writer.write("\n");
        writer.flush();
        for (Object t: this.getActivityRepository().getAll())
        {
            if (t instanceof  Activity)
            {
                String object = "";
                object = object.concat(((Activity) t).getId() + "/" +
                        ((Activity) t).getName() + "/" +
                        ((Activity) t).getTeacher().getId() + "/" +
                        ((Activity) t).getTeacher().getName() + "/" +
                        ((Activity) t).getTeacher().getEmail() + "/" +
                        ((Activity) t).getTeacher().getDateOfBirth() + "/" +
                        ((Activity) t).getTeacher().getRank() + "/" +
                        ((Activity) t).getDiscipline().getId() + "/" +
                        ((Activity) t).getDiscipline().getName() + "/" +
                        ((Activity) t).getDiscipline().getNumberOfCredits() + "/" +
                        ((Activity) t).getTypeOfActivity() + "/" +
                        ((Activity) t).getRoom().getId() + "/" +
                        ((Activity) t).getRoom().getName() + "/" +
                        ((Activity) t).getRoom().getBuilding() + "/" +
                        ((Activity) t).getTimeSlot() + "/" +
                        ((Activity) t).getDay() + "\n");
                writer.write(object);
                writer.flush();
            }
        }
        writer.write(String.valueOf(this.getRoomsRepository().getAll().size()));
        writer.flush();
        writer.write("\n");
        writer.flush();
        for (Object t: this.getRoomsRepository().getAll())
        {
            if (t instanceof  Room)
            {
                String object = "";
                object = object.concat(((Room) t).getId() + "/" +
                        ((Room) t).getName() + "/" +
                        ((Room) t).getBuilding() + "\n");
                writer.write(object);
                writer.flush();
            }
        }
    }

    public void addTeacherDatabase(Teacher t) {this.database1.addTeacher(t);}
    public void removeTeacherByNameDatabase(String name) {this.database1.removeTeacherByName(name);}
    public ArrayList<Teacher> getAllDatabaseTeacher() { return this.database1.getAll();}

    public void addDisciplineDatabase(Discipline d) {this.database2.addDiscipline(d);}
    public void removeDisciplineByNameDatabase(String name) {this.database2.removeDisciplineByName(name);}
    public ArrayList<Discipline> getAllDatabaseDiscipline() { return this.database2.getAll();}

    //methods that are using streams

    public ArrayList<Teacher> filterByRank(String rank)
    {
        return (ArrayList<Teacher>) this.teacherRepository.getAll().stream().filter(x -> ((Teacher) x).getRank().equals(rank)).collect(Collectors.toList());
    }
    public ArrayList<Activity> sortAndFilterActivities(Room room)
    {
        return (ArrayList<Activity>) this.activityRepository.getAll().stream().filter(x -> ((Activity) x).getRoom().getName().equals(room.getName())).sorted(Comparator.comparing(a -> ((Activity) a).getTimeSlot())).collect(Collectors.toList());
    }
    public ArrayList<Formation> getTimetable(Formation formation)
    {
        return (ArrayList<Formation>) this.formationRepository.getAll().stream().filter(x -> x.getName().equals(formation.getName())).collect(Collectors.toList());
    }
}
