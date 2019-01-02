import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Currency {
    List<String> probabiltyStringList;
    Currency(Map<String, Double> map)
    {
        double sum = 0;
        int i = 0;
        List<String> temporayStringList = new ArrayList<>();
        String[] str = new String[map.size()];
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            sum = sum + entry.getValue();
            str[i++] = entry.getKey();
        }
        for (int g = 0; g < str.length; g++) {
            double d = map.get(str[g]);
            for (int k = 0; k < (d * 100); k++) {
                temporayStringList.add(str[g]);
            }

        }

        this.probabiltyStringList=temporayStringList;
    }
    public  String currencyFetch() {
        int rnd = new Random().nextInt(probabiltyStringList.size());
        return probabiltyStringList.get(rnd);
    }
    }

