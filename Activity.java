package Domain;

public class Activity extends Entity implements java.io.Serializable
{
    private Discipline discipline;
    private String typeOfActivity;
    private Teacher teacher;
    private String timeSlot;
    private Room room;
    private String day;

    public Activity() {}

    public Activity(Teacher t, Discipline d, int id, String name, String type, String timeSlot, Room room, String day)
    {
        super(id, name);
        this.typeOfActivity = type;
        this.discipline = d;
        this.teacher = t;
        this.room = room;
        this.timeSlot = timeSlot;
        this.day = day;
    }

    public String getTypeOfActivity() { return typeOfActivity; }
    public void setTypeOfActivity(String typeOfActivity) { this.typeOfActivity = typeOfActivity; }

    public Discipline getDiscipline() { return discipline; }
    public void setDiscipline(Discipline discipline) { this.discipline = discipline; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public int compareTypes(Activity a) {return this.typeOfActivity.compareTo(a.getTypeOfActivity()); }

    @Override
    public String toString() {
        if (room.getName() == "")
        return "This is the activity " + name + " and for this you have the following information: \n" +
                "\t \t1) Teacher: " + teacher + "\n" +
                "\t \t2) Discipline: " + discipline + "\n" +
                "\t \t3) The type of activity: " + typeOfActivity + "\n" +
                "\t \t4) There is currently no available room" + "\n";
        else
            return "This is the activity " + name + " and for this you have the following information: \n" +
                    "\t \t1) Teacher: " + teacher + "\n" +
                    "\t \t2) Discipline: " + discipline + "\n" +
                    "\t \t3) The type of activity: " + typeOfActivity + "\n" +
                    "\t \t4) The room where the activity will take place: " + room + ", in the building " + room.getBuilding() + "\n" +
                    "\t \t5) The activity will take place on " + day + " between " + timeSlot + "\n";

    }
}
