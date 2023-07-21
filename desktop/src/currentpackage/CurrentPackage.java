package currentpackage;

public class CurrentPackage {

    public static String getCurrentPackage(Class currentClass) {
        return currentClass.getPackage().getName();
    }

}
