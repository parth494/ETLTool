import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlScript
{

     public final static char QUERY_ENDS = ';';

     private File script;

     private Connection con=null;

     private Statement stat;

     /**
     * @param args
     * @throws SQLException
     */

     public SqlScript(String scriptFileName) throws SQLException
     {
          script = new File(scriptFileName);
          try 
          {
               Class.forName("com.mysql.jdbc.Driver");
               con=DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse","root","");
               stat=con.createStatement();
          } 
          catch (ClassNotFoundException e)
          {
               e.printStackTrace();
          }
     }
     /*public static void main(String[] args) 
     {
          try
          {
               SqlScript sqlScript = new SqlScript("E:/proj/members.sql");
               sqlScript.loadScript();
              sqlScript.execute();
          }
          catch (SQLException e) 
          {
               e.printStackTrace();
          }
          catch (IOException e)
          {
               e.printStackTrace();
          }
     }*/
     protected void loadScript() throws IOException, SQLException 
     {
          BufferedReader reader = new BufferedReader(new FileReader(script));
          String line;
          StringBuffer query = new StringBuffer();
          boolean queryEnds = false;
          while ((line = reader.readLine()) != null) 
          {
               if (isComment(line))
                    continue;
               queryEnds = checkStatementEnds(line);
               query.append(line);
               if (queryEnds)
               {
                    System.out.println("query->"+query);
                    stat.addBatch(query.toString());
                    query.setLength(0);
               }
          }
     }
     private boolean isComment(String line) 
     {
          if ((line != null) && (line.length() > 0))
               return (line.charAt(0) == '#');
          return false;
     }
     public void execute() throws IOException, SQLException 
     {
          stat.executeBatch();
     }
     private boolean checkStatementEnds(String s) 
     {
          return (s.indexOf(QUERY_ENDS) != -1);
     }
}