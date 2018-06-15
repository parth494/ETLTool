
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Transform extends javax.swing.JFrame {

    /**
     * Creates new form Transform
     */
    Connection con;
    Statement state;
    String col1[]=new String[6];
    String filelist1;
    String filelist2;
    String filelist3;
    public Transform() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public Transform(String filelist1, String filelist2, String filelist3) throws Exception {
        initComponents();
        setLocationRelativeTo(null);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.filelist1=filelist1;
       this.filelist2=filelist2;
        this.filelist3=filelist3;
        try
   {


   }
    catch(Exception ex)
    {

     System.out.println("exception is"+ex);
    }


    }

    void startProcess() throws Exception{
        int dialogButton = JOptionPane.YES_NO_OPTION;
                int ress=JOptionPane.showConfirmDialog (null, "Start Transformation?","Warning",dialogButton);
                if(ress == JOptionPane.YES_OPTION){
                    SqlScript sqlScript = new SqlScript(filelist3);
        sqlScript.loadScript();
        sqlScript.execute();
        excelandaccess();
        JOptionPane.showMessageDialog(null,"Done.");
        jLabel1.setText("<html>Check the datawarehouse: <a href=\"\">http://localhost:8090//phpmyadmin//?db=warehouse</a></html>");
        jLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
           
               goWebsite(jLabel1); 
                
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Cancelled");
                    new Extraction().setVisible(true);
                    this.setVisible(false);                }
        
    }
    private void goWebsite(JLabel website) {
        website.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://localhost:8090//phpmyadmin//?db=warehouse"));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }
        });
    }
    private void excelandaccess() throws Exception {
      
        //Access
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    String connectionQuery="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+filelist2;
    con = DriverManager.getConnection(connectionQuery,"",""); 
    state=con.createStatement();
    ResultSet rs=state.executeQuery("select * from sports");
    Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse","root","");
          state=con.createStatement();
        
    while(rs.next())
    {
        state.execute("insert into sports values('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"')");
    }
      //Excel
        readContent();
    }

    public void readContent() throws Exception  {
    File inputWorkbook = new File(filelist1);
    Workbook w;
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      // Get the first sheet
      Sheet sheet = w.getSheet(0);
      // Loop over first 10 column and lines
String a[]=new String[sheet.getColumns()];
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse","root","");
          state=con.createStatement();
      for (int j = 1; j < sheet.getRows(); j++) {
        for (int i = 0; i < sheet.getColumns(); i++) {
          Cell cell = sheet.getCell(i, j);
          a[i]=cell.getContents()+"";
           System.out.println(cell.getContents());
       }
        state.execute("insert into sports values('"+a[0]+"','"+a[1]+"','"+a[2]+"','"+a[3]+"','2000-01-01 00:00:00')");
      }
    } catch (BiffException e) {
      e.printStackTrace();
    }
  }
public void readCol()throws Exception
{
        File inputWorkbook = new File(filelist1);
        Workbook w;
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      // Get the first sheet
      Sheet sheet = w.getSheet(0);
      
      for (int j = 0; j < sheet.getColumns(); j++) {
        
          Cell cell = sheet.getCell(j, 0);
          CellType type = cell.getType();
          if (type == CellType.LABEL) {
              col1[j]=cell.getContents();
            System.out.println(cell.getContents());
          }

       
        
      }
    } catch (BiffException e) {
      e.printStackTrace();
    }
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Transformation in progress");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel1)
                .addContainerGap(201, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables


   
}
