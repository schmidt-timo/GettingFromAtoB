import java.util.Random;

public class Vertex {

    Object data;
    static Random rand = new Random();

    public Vertex(Object data) {
        this.data = data;
    }

    public static boolean search(int key, int[] list) {
        for (int value : list) {
            if (key == value)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(search(110, a));

    }

}
