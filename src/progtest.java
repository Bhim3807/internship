import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;

import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class progtest {
    private static long sequenceVal = 0;
    private static int rvn = 1;
   private static  Map<String, Double> m = new HashMap<>();

    private static String generateAccountNo() {
        DecimalFormat df = new DecimalFormat("0000000");
        return ("A" + df.format(++sequenceVal));
    }

    public static Date nextDate(Date min, Date max) {
        RandomData randomData = new RandomDataImpl();
        return new Date(randomData.nextLong(min.getTime(), max.getTime()));
    }

    public static Timestamp creationTime12() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        java.util.Date dateFrom = dateFormat.parse("2012");
        long timestampFrom = dateFrom.getTime();
        Date dateTo = dateFormat.parse("2013");
        long timestampTo = dateTo.getTime();
        Random random = new Random();
        long timeRange = timestampTo - timestampFrom;
        long randomTimestamp = timestampFrom + (long) (random.nextDouble() * timeRange);
        Timestamp t1 = new Timestamp(randomTimestamp);
        return t1;
    }

    public static Timestamp creationTime(String s, String e) throws ParseException {
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

    public static Timestamp creationTime14() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        java.util.Date dateFrom = dateFormat.parse("2014");
        long timestampFrom = dateFrom.getTime();
        Date dateTo = dateFormat.parse("2015");
        long timestampTo = dateTo.getTime();
        Random random = new Random();
        long timeRange = timestampTo - timestampFrom;
        long randomTimestamp = timestampFrom + (long) (random.nextDouble() * timeRange);
        Timestamp t1 = new Timestamp(randomTimestamp);
        return t1;
    }

    public static int curencyInd() {
        Random r = new Random();
        int low = 0;
        int high = 1000000;
        int result = r.nextInt(high - low) + low;
        int result1 = result;
        return result1;
    }

    public static Timestamp lastChangeTime(String inyear, String byear) throws ParseException {
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

    public static String[] currencyNamecreator(Map<String, Double> map) {
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
        String root[] = new String[temporayStringList.size()];


        for (int y = 0; y < temporayStringList.size(); y++) {


            root[y] = temporayStringList.get(y);
        }
        return root;
    }


    private static String currencyFetch(String[] s) {
        int rnd = new Random().nextInt(s.length);
        return s[rnd];
    }


    public static StringBuilder content() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(4000);
        Random random = new Random();
        for (int i = 0; i < 4000; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb;

    }

    public static void insertInOne() throws ParseException {

        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            int updatedRows = 0;
            con.setAutoCommit(false);
            for (int i = 0; i < 100000; i++) {
                try (PreparedStatement stmt = con.prepareStatement("insert into PROGTEST1 values(?,?,?,?,?,?,?,?,?)")) {
                    stmt.setString(1, generateAccountNo());//1 specifies the first parameter in the query
                    stmt.setInt(2, rvn);
                    if (sequenceVal <= increase(1,20,true)) {
                        stmt.setTimestamp(3, creationTime("2013", "2014"));
                        stmt.setTimestamp(4, lastChangeTime("2015", "2016"));
                    } else if (sequenceVal <= increase(increase(1,20,true),20,true)) {
                        stmt.setTimestamp(3, creationTime("2014", "2015"));
                        stmt.setTimestamp(4, lastChangeTime("2016", "2017"));
                    } else {
                        stmt.setTimestamp(3, creationTime("2015", "2016"));
                        stmt.setTimestamp(4, lastChangeTime("2016", "2017"));
                    }
                    //stmt.setString(5, currency());
                    stmt.setString(6, content().toString());
                    stmt.setString(7, content().toString());
                    stmt.setString(8, content().toString());
                    stmt.setString(9, content().toString());

                    updatedRows += stmt.executeUpdate();
                }
            }
            System.out.println("[" + updatedRows + "] rows inserted");
            if (updatedRows != 100000) {
                int failed = 100000 - updatedRows;
                System.out.println("Failed to insert [" + failed + "] rows");
            }
            con.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while performing data bootstrapping: " + e.getMessage() + ". Rolling back changes of connection");
            e.printStackTrace();
            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                System.out.println("Exception occurred while rolling back connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred while closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void insertNewcon() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            int updatedRows = 0;
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into PROGTEST1 values(?,?,?,?,?,?,?,?,?)")) {
                statement.setString(1, generateAccountNo());//1 specifies the first parameter in the query
                statement.setInt(2, rvn);
                if (sequenceVal <= (100000 / Math.pow((6 / 5), 3))) {
                    statement.setTimestamp(3, creationTime("2013", "2014"));
                    statement.setTimestamp(4, lastChangeTime("2015", "2016"));
                } else if (sequenceVal <= (100000 / Math.pow((6 / 5), 2))) {
                    statement.setTimestamp(3, creationTime("2014", "2015"));
                    statement.setTimestamp(4, lastChangeTime("2016", "2017"));
                } else {
                    statement.setTimestamp(3, creationTime("2015", "2016"));
                    statement.setTimestamp(4, lastChangeTime("2016", "2017"));
                }
                // statement.setString(5, currency());
                statement.setString(6, content().toString());//1 specifies the first parameter in the query
                statement.setString(7, content().toString());
                statement.setString(8, content().toString());//1 specifies the first parameter in the query
                statement.setString(9, content().toString());

                updatedRows += statement.executeUpdate();

            }
            System.out.println("[" + updatedRows + "] rows inserted");
            // if (updatedRows != 100) {
            //   int failed = 100 - updatedRows;
            // System.out.println("Failed to insert [" + failed + "] rows");
            //}
            connection.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while performing data bootstrapping: " + e.getMessage() + ". Rolling back changes of connection");
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback();
            } catch (Exception ex) {
                System.out.println("Exception occurred while rolling back connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Exception occurred while closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void insertBtach() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            int updatedRows = 0;
            con.setAutoCommit(false);
            try (PreparedStatement stmt = con.prepareStatement("insert into PROGTEST1 values(?,?,?,?,?,?,?,?,?)")) {
                for (int i = 0; i < 100; i++) {
                    stmt.setString(1, generateAccountNo());//1 specifies the first parameter in the query
                    stmt.setInt(2, rvn);
                    if (sequenceVal <= 27) {
                        stmt.setTimestamp(3, creationTime("2013", "2014"));
                        stmt.setTimestamp(4, lastChangeTime("2015", "2016"));
                    } else if (sequenceVal <= 42) {
                        stmt.setTimestamp(3, creationTime("2014", "2015"));
                        stmt.setTimestamp(4, lastChangeTime("2016", "2017"));
                    } else {
                        stmt.setTimestamp(3, creationTime("2015", "2016"));
                        stmt.setTimestamp(4, lastChangeTime("2016", "2017"));
                    }
                    stmt.setString(5, currencyFetch(currencyNamecreator(m)));
                    stmt.setString(6, content().toString());//1 specifies the first parameter in the query
                    stmt.setString(7, content().toString());
                    stmt.setString(8, content().toString());//1 specifies the first parameter in the query
                    stmt.setString(9, content().toString());

                    stmt.addBatch();
                }
                stmt.executeBatch();
            }
            System.out.println("[" + updatedRows + "] rows inserted");
            if (updatedRows != 100000) {
                int failed = 100000 - updatedRows;
                System.out.println("Failed to insert [" + failed + "] rows");
            }
            con.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while performing data bootstrapping: " + e.getMessage() + ". Rolling back changes of connection");
            e.printStackTrace();
            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                System.out.println("Exception occurred while rolling back connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred while closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static int increase(int x, int y, boolean z) {

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


    public static void main(String[] args) {
        //long start = System.currentTimeMillis();
        //insertInOne();
        //long stop = System.currentTimeMillis();
        //System.out.println(stop - start);
        //long start1 = System.currentTimeMillis();
        //for (int i = 0; i < 100000; i++)
        //  insertNewcon();
        //long stop2 = System.currentTimeMillis();
        //System.out.println(stop2 - start1);
        //long start3 = System.currentTimeMillis();
        //insertBtach();
        //long stop3 = System.currentTimeMillis();
        //System.out.println(stop3 - start3);
        //int count = 0;

        m.put("IND", 0.25);
        m.put("JPY", 0.10);
        m.put("EUR", 0.20);
        m.put("USD", 0.25);
        m.put("GBP", 0.20);
        insertBtach();
        }
    }
