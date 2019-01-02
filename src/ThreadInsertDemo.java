import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

public class ThreadInsertDemo  extends Thread{
    long l;
    long recordsNumber;
    Map m;
    ThreadInsertDemo(long l,Map m,long recordsNumber)
    {
        this.l=l;
        this.m=m;
        this.recordsNumber=recordsNumber;
    }
    @Override
    public void run() {
        int rvn=1;
        AccountNumber Aobject=new AccountNumber(l);
        Currency Cobject=new Currency(m);
        Content coObject=new Content();
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            int updatedRows = 0;
            con.setAutoCommit(false);
               //for(int inset=0;inset<(recordsNumber/10);inset++) {
                   try (PreparedStatement stmt = con.prepareStatement("insert into PROGTEST1 values(?,?,?,?,?,?,?,?,?)")) {
                       for (int i = 0; i < recordsNumber; i++) {
                           stmt.setString(1, Aobject.generateAccountNo());//1 specifies the first parameter in the query
                           stmt.setInt(2, rvn);
                           stmt.setTimestamp(3, Aobject.AccountcreationTime("2013", "2014"));
                           stmt.setTimestamp(4, Aobject.AccountModifiedTime("2015", "2016"));
                           stmt.setString(5, Cobject.currencyFetch());
                           stmt.setString(6, coObject.contentCreator().toString());
                           stmt.setString(7, coObject.contentCreator().toString());
                           stmt.setString(8, coObject.contentCreator().toString());
                           stmt.setString(9, coObject.contentCreator().toString());

                           stmt.addBatch();
                           System.out.println("Thread [" + this.getName() + "] Inserting [" + i + "] record");
                       }
                       stmt.executeBatch();
                  // }
               }
            System.out.println("[" + updatedRows + "] rows inserted");
            if (updatedRows != this.recordsNumber) {
                long failed = this.recordsNumber - updatedRows;
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
}
