import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AccountNumber {
    private long sequenceVal;

    AccountNumber(Long l) {
        this.sequenceVal = l;
    }

    public String generateAccountNo() {
        DecimalFormat df = new DecimalFormat("0000000");
        return ("A" + df.format(++sequenceVal));
    }

    public  Timestamp AccountcreationTime(String s, String e) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        java.util.Date dateFrom = dateFormat.parse("2013");
        long timestampFrom = dateFrom.getTime();
        Date dateTo = dateFormat.parse("2014");
        long timestampTo = dateTo.getTime();
        Random random = new Random();
        long timeRange = timestampTo - timestampFrom;
        long randomTimestamp = timestampFrom + (long) (random.nextDouble() * timeRange);
        Timestamp t1 = new Timestamp(randomTimestamp);
        return t1;
    }

    public  Timestamp AccountModifiedTime(String inyear, String byear) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date dateFrom = dateFormat.parse(inyear);
        long timestampFrom = dateFrom.getTime();
        Date dateTo = dateFormat.parse(byear);
        long timestampTo = dateTo.getTime();
        Random random = new Random();
        long timeRange = timestampTo - timestampFrom;
        long randomTimestamp = timestampFrom + (long) (random.nextDouble() * timeRange);
        Timestamp t1 = new Timestamp(randomTimestamp);
        return t1;
    }
    public static int AccountincreaseRate(int x, int y, boolean z) {

        double percentage;

        double price;


        if (z ==true) {

            percentage = y / 100;

            price = x * (1 + percentage);

            price = Math.round(price);

            return (int) price;

        } else {

            percentage = y / 100;

            price = x * (1 - percentage);

            price = Math.round(price);

            return (int) price;

        }

    }
}
