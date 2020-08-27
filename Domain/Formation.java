package Domain;

import java.util.ArrayList;

public class Formation extends Entity
{
    ArrayList<Activity> activity;
    ArrayList<Group> groups;

    public Formation() {}
    public Formation(int id, String name, ArrayList<Activity> a, ArrayList<Group> group)
    {
        super(id, name);
        this.activity = a;
        this.groups = group;
    }

    public ArrayList<Activity> getActivity() { return activity; }
    public void setActivity(ArrayList<Activity> activity) { this.activity = activity; }
    public ArrayList<Group> getGroups() { return groups; }
    public void setGroups(ArrayList<Group> groups) { this.groups = groups; }

    public static class Group
    {
        String groupName;
        ArrayList<Subgroup> subgroups;

        public Group() {}
        public Group(String name, ArrayList<Subgroup> l) {this.groupName = name; this.subgroups = l;}

        public void setName(String name) {this.groupName = name;}
        public String getName() {return this.groupName;}

        public void setSubgroups(ArrayList<Subgroup> l) {this.subgroups = l;}
        public ArrayList<Subgroup> getSubgroups() {return this.subgroups;}

        public static class Subgroup
        {
            String sub;

            public Subgroup() {}
            public Subgroup(String sub)
            {
                this.sub = sub;
            }

            public String getSub() { return sub; }
            public void setSub(String sub) { this.sub = sub; }
        }

    }

    @Override
    public String toString()
    {
        String result = "";
        result = result +  "This is the formation " + this.name + ". It contains the following groups: \n";
        for (Group g: this.groups)
            result = result + "\t \t*) " + g.getName() + "\n";
        return result;
    }
}
