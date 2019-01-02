import java.util.HashMap;
import java.util.Map;

public class Main {
    public  static void main(String[] args)
    {
        Map<String,Double> probabilityMap =new HashMap<>();
        probabilityMap.put("IND", 0.25);
        probabilityMap.put("JPY", 0.10);
        probabilityMap.put("EUR", 0.20);
        probabilityMap.put("USD", 0.25);
        probabilityMap.put("GBP", 0.20);
        int startValue = 0;
        int records = 250;
        int numThreads = 1;
        for (int index = 0; index < numThreads; index++) {
            ThreadInsertDemo thread = new ThreadInsertDemo(startValue, probabilityMap, records);
            startValue += records;
            thread.start();
            System.out.println("Thread [" + index + "] started successfully");
        }
    }
}
