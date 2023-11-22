package application;


public class FarmSingleTon{
    private static FarmSingleTon instance;

    private FarmSingleTon() {}

    public static FarmSingleTon getInstance() {
        if (instance == null) {
            instance = new FarmSingleTon();
        }
        return instance;
    }

    

    public String getTitle() {
        return "DashBoard";
    }
}
