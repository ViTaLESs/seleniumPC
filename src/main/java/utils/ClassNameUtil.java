package utils;

/**
 * Created by ViTaLES on 17.06.2016.
 */
public class ClassNameUtil {
    private ClassNameUtil(){}

    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e){
            return e.getStackTrace()[1].getClassName();
        }
    }
}
