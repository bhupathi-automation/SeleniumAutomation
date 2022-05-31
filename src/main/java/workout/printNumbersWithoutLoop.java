package workout;

public class printNumbersWithoutLoop {

    public static void main(String[] args){

        print(1, 10);
    }

    static void print(int x,int n){

        if(x<=n){
            System.out.print(x++ + " ");
            print(x,n);
        }
    }
}
