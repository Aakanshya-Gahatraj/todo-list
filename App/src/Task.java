//class created for the Task object

public class Task{
    //class variables
    private int id;
    private String taskdesc;
    private boolean comp;

    public Task( int id, String taskdesc, boolean comp){
        //initializing variables
        this.id=id;
        this.taskdesc=taskdesc;
        this.comp=comp;
    }

    //getters
    public int getID(){return id;}
    public String getTaskDesc(){return taskdesc;}
    public boolean getComp(){return comp;}

    //toString method
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskdesc='" + taskdesc + '\'' +
                ", comp=" + comp +
                '}';
    }
}
