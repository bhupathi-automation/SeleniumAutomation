package workout.concepts;

public class ThreadLocal1 {

    public static ThreadLocal<String> thread = new ThreadLocal<String>();

    public static void setThread(String s){
        thread.set(s);
    }

    public static String getThread(){
        return thread.get();
    }

    public static void main(String[] args){
        setThread("Thread1");

        System.out.println(getThread()

        );
    }
}
