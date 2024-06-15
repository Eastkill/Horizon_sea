package Utils;

public class EntityUtils {
    public static boolean isNeigbours(int[]x1, int[]x2){
        if(Math.max(Math.abs(x1[0]-x2[0]),Math.abs(x1[1]-x2[1]))<=1)return true;
        return false;
    }
}
