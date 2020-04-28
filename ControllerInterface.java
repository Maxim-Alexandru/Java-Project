package Infrastructure;
import Domain.*;
import Repository.*;
import java.util.ArrayList;

public interface ControllerInterface<T extends Entity>
{
    Repository<T> getTeacherRepository();
    Repository<T> getDisciplineRepository();
    Repository<T> getActivityRepository();
    Repository<T> getRoomsRepository();

    int addTeacher(int index, String name, String email, String dateOfBirth, String rank) throws Exception;
    int removeTeacherByName(String name) throws Exception;
    int updateTeacher(int index1, String name1, String email1, String dateOfBirth1, String rank1,
                      int index2, String name2, String email2, String dateOfBirth2, String rank2);

    int addDiscipline(int index, String name, int credits) throws Exception;
    int removeDisciplineByName(String name) throws Exception;
    int updateDiscipline(int index1, String name1, int credits1, int index2, String name2, int credits2);

    int addActivity(int index, String name, Teacher t, Discipline d, String type, Room room, String time, String day) throws Exception;
    int removeActivityByName(String name) throws Exception;
    int updateActivity(int index1, String name1, Teacher t1, Discipline d1, String type1, Room room1, String time1, String day1,
                       int index2, String name2, Teacher t2, Discipline d2, String type2, Room room2, String time2, String day2);

    int addRoom(int id, String name, String building) throws Exception;
    int removeRoom(String name) throws Exception;
    int updateRoom(int id1, String name1, String building1,
                   int id2, String name2, String building2);

    int addFormation(int id, String name, ArrayList<Activity> activity, ArrayList<Formation.Group> groups) throws Exception;
    int addGroup(String formation, String nameGroup, ArrayList<Formation.Group.Subgroup> subgroups) throws Exception;
    int addSubgroup(String formation, String nameGroup, String subgroup) throws Exception;
    int removeFormation(String name) throws Exception;
    int removeGroup(String name) throws Exception;
    int removeSubgroup(String name) throws Exception;
    int updateFormation(int id1, String name1, ArrayList<Activity> activity1, ArrayList<Formation.Group> groups1,
                        int id2, String name2, ArrayList<Activity> activity2, ArrayList<Formation.Group> groups2);
}
