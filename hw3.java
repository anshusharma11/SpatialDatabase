import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.*;
import java.net.URL;
import java.sql.DriverManager;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import oracle.jdbc.OracleResultSet;
import oracle.sql.STRUCT;
import oracle.spatial.geometry.*;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Anshu Padha
 */
public class hw3 extends javax.swing.JFrame 
{

	private String host;
    private String port;
    private String dbname;
    private String username;
    private String password;
    
    private javax.swing.ButtonGroup buttonGroup1;
    private ImageJPanel imageJPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
   
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jTextField1; 
    private javax.swing.JTextArea jTextArea1;  
    String sql_1 = null, sqlprint = null, sql_2 = null, sql_3 = null, sql_4 = null, sql_5 = null, sql_6 = null, sql_7 = null, sql_8 = null;
    
    
    public static Connection con = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;
    Graphics g = null;
    int radius;
    int pointQueryX;
    int pointQueryY;
    int FHX;
    int FHY;
    int fireBuildX;
    int fireBuildY;
    int queryNumber = 1;
    int vertices;
    String ovalQuery = "SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(";
    boolean rightClick = false;
    ArrayList<Integer> listX = new ArrayList<Integer>();
    ArrayList<Integer> listY = new ArrayList<Integer>();
    String NAPQuery;
    int radius1;
    int radius2;
    String rangeQuery;
    String findFireHydrantQuery;
    String nQuery = "SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(";
    int jRadioButton11 = 0;
   
    
   
    public hw3() 
    {
        // GUI setup
    	initComponents();
        //getProperties(path);
        connectDB();
        System.out.println("set the canvas for drawing");
        g = jLabel1.getGraphics();
    }


    @SuppressWarnings("unchecked")
    // 
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        imageJPanel1 = new ImageJPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Anshu Padha [Student ID:00001165827 ]");
        setResizable(false);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jLabel4.setText("Current Coordinates(X,Y):");

        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 14)); 

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 100));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel2.setText("Active Feature Type");

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jCheckBox1.setText("Building");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox3.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jCheckBox3.setText("Fire Building");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jCheckBox2.setText("Fire Hydrant");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox3)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jLabel3.setText("Query");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jRadioButton1.setText("Whole Region");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jRadioButton2.setText("Range Query");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jRadioButton3.setText("Find Neighbor Buildings");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jRadioButton4.setText("Find Closest Fire Hydrant");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Your submitted query should be displayed here...");
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jButton1.setText("Submit Query");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        imageJPanel1.setMaximumSize(new java.awt.Dimension(820, 580));
        //imageJPanel1.setMinimumSize(new java.awt.Dimension(820, 580));
       // imageJPanel1.setPreferredSize(new java.awt.Dimension(820, 580));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel1MouseMoved(evt);
            }
        });

        javax.swing.GroupLayout imageJPanel1Layout = new javax.swing.GroupLayout(imageJPanel1);
        imageJPanel1.setLayout(imageJPanel1Layout);
        imageJPanel1Layout.setHorizontalGroup(
            imageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
            .addGroup(imageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE))
        );
        imageJPanel1Layout.setVerticalGroup(
            imageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(imageJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 129, Short.MAX_VALUE))
                    .addComponent(imageJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
    
    public void getProperties(String path) 
    {
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(path));
            host = inFile.readLine();
            port = inFile.readLine();
            dbname = inFile.readLine();
            username = inFile.readLine();
            password = inFile.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("cannot find the property file");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //connect to Oracle database
    private void connectDB() 
    {
        
        try {
        	String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            System.out.println("connecting to database");
            
            //String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + dbname;
            //con = DriverManager.getConnection(url, username, password);
            String url = "jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:db11g";
            con = DriverManager.getConnection(url, "apadha","Siddharthu1234 ");
            
            System.out.println("connect to database successfully");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //draw all the FIRE_BUILDING on the map
    private void showFireBuilding(Graphics g) 
    {
        try {
            st = con.createStatement();
            sql_3 = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
            rs = st.executeQuery(sql_3);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                JGeometry bShape = JGeometry.load(dbObject);
                vertices = rs.getInt("b_vertices");
                int[] fBuildX = new int[vertices];
                int[] fBuildY = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                    fBuildX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                    fBuildY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                }
                g.setColor(Color.RED);
                g.drawPolygon(fBuildX, fBuildY, vertices);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_3);
            queryNumber++;
    
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the fireBuilding");
        }
    }
    
    //draw all the FIRE_HYDRANT on the map
    private void showFireHydrant(Graphics g) 
    {
        try {
            st = con.createStatement();
            sql_2 = "SELECT fh.h_location FROM FIRE_HYDRANT fh";
            rs = st.executeQuery(sql_2);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("h_location");
                JGeometry h_location = JGeometry.load(dbObject);
                FHX = (int) h_location.getPoint()[0];
                FHY = (int) h_location.getPoint()[1];
                radius = 100;//100 pixels
                g.setColor(Color.GREEN);
                g.fillRect(FHX - 15 / 2, FHY - 15 / 2, 15, 15);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_2);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the fire hydrant.");
        }
    }

    //draw all the BUILDING on the map
    private void showBuilding(Graphics g) 
    {

        try {
            st = con.createStatement();
            sql_1 = "SELECT b.b_shape, b.b_vertices FROM BUILDING b";
            rs = st.executeQuery(sql_1);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                JGeometry bShape = JGeometry.load(dbObject);
                vertices = rs.getInt("b_vertices");
                int[] buildX = new int[vertices];
                int[] buildY = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                    buildX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                    buildY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                }
                g.setColor(Color.YELLOW);
                g.drawPolygon(buildX, buildY, vertices);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_1);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //Query 2 - Buildings: draw all the building in the polygon range
    private void showRangeBuilding(Graphics g) 
    {
        try {
            st = con.createStatement();
            sql_1 = "SELECT b.b_shape, b.b_vertices FROM BUILDING b WHERE sdo_relate(b.b_shape," + rangeQuery + ",\'MASK=inside+anyinteract\')=\'TRUE\'";
            System.out.println(""+sql_1);
            rs = st.executeQuery(sql_1);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                JGeometry bShape = JGeometry.load(dbObject);
                vertices = rs.getInt("b_vertices");
                int[] buildX = new int[vertices];
                int[] buildY = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                    buildX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                    buildY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                }
                g.setColor(Color.YELLOW);
                g.drawPolygon(buildX, buildY, vertices);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_1);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the range building");
        }
    }
    
    //Query 2 - Fire Buildings : draw all the fireBuilding in the polygon range
    private void showRangeFireBuilding(Graphics g) 
    {
        try {
            st = con.createStatement();
            sql_3 = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id AND sdo_relate(b.b_shape," + rangeQuery + ",\'MASK=inside+anyinteract\')=\'TRUE\'";

            System.out.println("Query"+sql_3);
            rs = st.executeQuery(sql_3);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                JGeometry bShape = JGeometry.load(dbObject);
                vertices = rs.getInt("b_vertices");
                int[] buildX = new int[vertices];
                int[] buildY = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                    buildX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                    buildY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                }
                g.setColor(Color.RED);
                g.drawPolygon(buildX, buildY, vertices);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_3);
            queryNumber++;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the range fireBuilding");

        }
    }
    
    //Query 2 - Fire Hydrants : draw all the FIRE_HYDRANT in the polygon range
    private void showRangeFireHydrant(Graphics g) 
    {
        try {
            st = con.createStatement();
            sql_2 = "SELECT fh.h_location FROM FIRE_HYDRANT fh WHERE sdo_relate(fh.h_location," + rangeQuery + ",\'MASK=inside+anyinteract\')=\'TRUE\'";
            System.out.println("Query"+sql_2);
            rs = st.executeQuery(sql_2);
            while (rs.next()) {
                STRUCT dbObject = (STRUCT) rs.getObject("h_location");
                JGeometry h_location = JGeometry.load(dbObject);
                FHX = (int) h_location.getPoint()[0];
                FHY = (int) h_location.getPoint()[1];

                g.setColor(Color.GREEN);
                g.fillRect(FHX - 15 / 2, FHY - 15 / 2, 15, 15);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_2);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the range fire hydrant");
        }
    }
    
    //Query 3 - neighbor buildings : draw all the BUILDING within the distance of 100 points (old, working)
    private void findNeighborBuildings(Graphics g) 
    {
        try {
            st = con.createStatement();
            
            String sqlForCenterOfBuilding = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
            System.out.println("sqlForCenterOfBuilding = "+sqlForCenterOfBuilding);
            ResultSet rs1 = st.executeQuery(sqlForCenterOfBuilding);
            while (rs1.next()) 
            {
                int centerOfBuildingX = 0;
                int centerOfBuildingY = 0;
                int buildingMaxX = 0;
                int buildingMaxY = 0;
                int buildingMinX = 0;
                int buildingMinY = 0;

                STRUCT dbObjectVal = (STRUCT) rs1.getObject("b_shape");
                JGeometry bShapeVal = JGeometry.load(dbObjectVal);
                vertices = rs1.getInt("b_vertices");
                int[] fBuildingXVal = new int[vertices];
                int[] fBuildingYVal = new int[vertices];

                
                for (int i = 0; i < vertices; i++) {
                	centerOfBuildingX += (int) bShapeVal.getOrdinatesArray()[2 * i];
                	centerOfBuildingY += (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                	if (buildingMinX == 0){
                		buildingMinX = (int) bShapeVal.getOrdinatesArray()[2 * i];
                	} else if ((int) bShapeVal.getOrdinatesArray()[2 * i] < buildingMinX) {
                		buildingMinX = (int) bShapeVal.getOrdinatesArray()[2 * i];
                	}
                	if (buildingMinY == 0){
                		buildingMinY = (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                	} else if ((int) bShapeVal.getOrdinatesArray()[2 * i + 1] < buildingMinX) {
                		buildingMinY = (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                	}
                	if (buildingMaxX == 0){
                		buildingMaxX = (int) bShapeVal.getOrdinatesArray()[2 * i];
                	} else if ((int) bShapeVal.getOrdinatesArray()[2 * i] > buildingMaxX) {
                		buildingMaxX = (int) bShapeVal.getOrdinatesArray()[2 * i];
                	}
                	if (buildingMaxY == 0){
                		buildingMaxY = (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                	} else if ((int) bShapeVal.getOrdinatesArray()[2 * i + 1] > buildingMaxX) {
                		buildingMaxY = (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                	}
                }
                centerOfBuildingX = centerOfBuildingX/fBuildingXVal.length;
                centerOfBuildingY = centerOfBuildingY/fBuildingYVal.length;
                int buildingWidthX = buildingMaxX - buildingMinX;
                int buildingWidthY = buildingMaxY - buildingMinY;

                String ovalQueryRemaining = centerOfBuildingX + "," + (centerOfBuildingY + 100 + buildingWidthY) + "," + (centerOfBuildingX + 100 + buildingWidthX) + "," + centerOfBuildingY + "," + (centerOfBuildingX - 100 - buildingWidthX) + "," + (centerOfBuildingY - 100 - buildingWidthY) + "))";

                sql_4 = "SELECT b.b_shape, b.b_vertices FROM BUILDING b WHERE sdo_relate(b.b_shape," + ovalQuery + ovalQueryRemaining + ",\'MASK=inside+anyinteract\')=\'TRUE\'";
                System.out.println("sql_4 = "+sql_4);
                Statement st1 = con.createStatement();
                rs = st1.executeQuery(sql_4);
                while (rs.next()) 
                {
                    STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                    JGeometry bShape = JGeometry.load(dbObject);
                    vertices = rs.getInt("b_vertices");
                    int[] buildingX = new int[vertices];
                    int[] buildingY = new int[vertices];
                    for (int i = 0; i < vertices; i++) {
                        buildingX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                        buildingY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                    }
                    g.setColor(Color.YELLOW);
                    g.drawPolygon(buildingX, buildingY, vertices);
                }
                rs.close();
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_4);
                queryNumber++;
            }
            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            st = con.createStatement();
            
            String sqlForCenterOfBuilding = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
            System.out.println("sqlForCenterOfBuilding = "+sqlForCenterOfBuilding);
            ResultSet rs1 = st.executeQuery(sqlForCenterOfBuilding);
            while (rs1.next()) 
            {
                int centerOfBuildingX = 0;
                int centerOfBuildingY = 0;
                STRUCT dbObjectVal = (STRUCT) rs1.getObject("b_shape");
                JGeometry bShapeVal = JGeometry.load(dbObjectVal);
                vertices = rs1.getInt("b_vertices");
                int[] fBuildingXVal = new int[vertices];
                int[] fBuildingYVal = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                	centerOfBuildingX += (int) bShapeVal.getOrdinatesArray()[2 * i];
                	centerOfBuildingY += (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                }
                centerOfBuildingX = centerOfBuildingX/fBuildingXVal.length;
                centerOfBuildingY = centerOfBuildingY/fBuildingYVal.length;

                String ovalQueryRemaining = centerOfBuildingX + "," + (centerOfBuildingY + 50) + "," + (centerOfBuildingX + 50) + "," + centerOfBuildingY + "," + centerOfBuildingX + "," + (centerOfBuildingY - 50) + "))";
                String nQueryRemaining = centerOfBuildingX + "," + centerOfBuildingY + ",NULL),NULL,NULL)";
                sql_4 = "SELECT b.b_shape, b.b_vertices FROM BUILDING b WHERE sdo_relate(b.b_shape," + ovalQuery + ovalQueryRemaining + ",\'MASK=inside+anyinteract\')=\'TRUE\' AND SDO_NN(b.b_shape," + nQuery + nQueryRemaining + ",\'sdo_num_res=1\')=\'TRUE\'";
                System.out.println("sql_4 =  "+sql_4);
                Statement st1 = con.createStatement();
                rs = st1.executeQuery(sql_4);
                while (rs.next()) 
                {
                    STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                    JGeometry bShape = JGeometry.load(dbObject);
                    vertices = rs.getInt("b_vertices");
                    int[] buildingX = new int[vertices];
                    int[] buildingY = new int[vertices];
                    for (int i = 0; i < vertices; i++) {
                        buildingX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                        buildingY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                    }
                    g.setColor(Color.YELLOW);
                    g.drawPolygon(buildingX, buildingY, vertices);
                }
                rs.close();
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_4);
                queryNumber++;
            }
            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //Query 3 - neighbor fire buildings : draw all the Fire BUILDINGs within the distance of 100 points (old, working)
    private void showinsideFireBuilding(Graphics g) 
    {
        try {
            st = con.createStatement();
            
            String sqlForCenterOfBuilding = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
            System.out.println("sqlForCenterOfBuilding = "+sqlForCenterOfBuilding);
            ResultSet rs1 = st.executeQuery(sqlForCenterOfBuilding);
            while (rs1.next()) 
            {
                int centerOfBuildingX = 0;
                int centerOfBuildingY = 0;
                STRUCT dbObjectVal = (STRUCT) rs1.getObject("b_shape");
                JGeometry bShapeVal = JGeometry.load(dbObjectVal);
                vertices = rs1.getInt("b_vertices");
                int[] fBuildingXVal = new int[vertices];
                int[] fBuildingYVal = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                	centerOfBuildingX += (int) bShapeVal.getOrdinatesArray()[2 * i];
                	centerOfBuildingY += (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                }
                centerOfBuildingX = centerOfBuildingX/fBuildingXVal.length;
                centerOfBuildingY = centerOfBuildingY/fBuildingYVal.length;

                String ovalQueryRemaining = centerOfBuildingX + "," + (centerOfBuildingY + 50) + "," + (centerOfBuildingX + 50) + "," + centerOfBuildingY + "," + centerOfBuildingX + "," + (centerOfBuildingY - 50) + "))";

                sql_5 = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id AND sdo_relate(b.b_shape," + ovalQuery + ovalQueryRemaining + ",\'MASK=inside+anyinteract\')=\'TRUE\'";
                System.out.println("sql_5 = "+sql_5);
                Statement st1 = con.createStatement();
                rs = st1.executeQuery(sql_5);
                while (rs.next()) 
                {
                    STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
//                    JGeometry fireBuildingLocation = JGeometry.load(dbObject);
//                    fireBuildingX = (int) fireBuildingLocation.getPoint()[0];
//                    fireBuildY = (int) fireBuildingLocation.getPoint()[1];
//                    g.setColor(Color.RED);
//                    g.fillRect(fireBuildingX - 10 / 2, fireBuildY - 10 / 2, 10, 10);

                    JGeometry bShape = JGeometry.load(dbObject);
                    vertices = rs.getInt("b_vertices");
                    int[] fBuildingX = new int[vertices];
                    int[] fBuildingY = new int[vertices];
                    for (int i = 0; i < vertices; i++) {
                        fBuildingX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                        fBuildingY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                    }
                    g.setColor(Color.RED);
                    g.drawPolygon(fBuildingX, fBuildingY, vertices);
                }
                rs.close();
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_5);
                queryNumber++;
            }
            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the inside fireBuilding");

        }

        try {
            st = con.createStatement();
            
            String sqlForCenterOfBuilding = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
            System.out.println("sqlForCenterOfBuilding = "+sqlForCenterOfBuilding);
            ResultSet rs1 = st.executeQuery(sqlForCenterOfBuilding);
            while (rs1.next()) 
            {
                int centerOfBuildingX = 0;
                int centerOfBuildingY = 0;
                STRUCT dbObjectVal = (STRUCT) rs1.getObject("b_shape");
                JGeometry bShapeVal = JGeometry.load(dbObjectVal);
                vertices = rs1.getInt("b_vertices");
                int[] fBuildingXVal = new int[vertices];
                int[] fBuildingYVal = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                	centerOfBuildingX += (int) bShapeVal.getOrdinatesArray()[2 * i];
                	centerOfBuildingY += (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                }
                centerOfBuildingX = centerOfBuildingX/fBuildingXVal.length;
                centerOfBuildingY = centerOfBuildingY/fBuildingYVal.length;

                String ovalQueryRemaining = centerOfBuildingX + "," + (centerOfBuildingY + 50) + "," + (centerOfBuildingX + 50) + "," + centerOfBuildingY + "," + centerOfBuildingX + "," + (centerOfBuildingY - 50) + "))";
                String nQueryRemaining = centerOfBuildingX + "," + centerOfBuildingY + ",NULL),NULL,NULL)";
                sql_5 = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id AND sdo_relate(b.b_shape," + ovalQuery + ovalQueryRemaining + ",\'MASK=inside+anyinteract\')=\'TRUE\' AND SDO_NN(b.b_shape," + nQuery + nQueryRemaining + ",\'sdo_num_res=1\')=\'TRUE\'";
                System.out.println("sql_5 =  "+sql_5);
                Statement st1 = con.createStatement();
                rs = st1.executeQuery(sql_5);
                while (rs.next()) 
                {
                    STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
//                    JGeometry fireBuildingLocation = JGeometry.load(dbObject);
//                    fireBuildingX = (int) fireBuildingLocation.getPoint()[0];
//                    fireBuildY = (int) fireBuildingLocation.getPoint()[1];
//                    g.setColor(Color.RED);
//                    g.fillRect(fireBuildingX - 10 / 2, fireBuildY - 10 / 2, 10, 10);

                    JGeometry bShape = JGeometry.load(dbObject);
                    vertices = rs.getInt("b_vertices");
                    int[] fBuildingX = new int[vertices];
                    int[] fBuildingY = new int[vertices];
                    for (int i = 0; i < vertices; i++) {
                        fBuildingX[i] = (int) bShape.getOrdinatesArray()[2 * i];
                        fBuildingY[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                    }
                    g.setColor(Color.RED);
                    g.drawPolygon(fBuildingX, fBuildingY, vertices);
                }
                rs.close();
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_5);
                queryNumber++;
            }
            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the nearest inside fireBuilding");
        }
    }
    
    //Query 3 - fire hydrants : draw all the fire hydrants within the distance of 100 points (old, working)
    private void showBuildingsAndFireHydrantsAroundFireBuilding(Graphics g) 
    {
        try {
            st = con.createStatement();
            
            String sqlForCenterOfBuilding = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
            System.out.println("sqlForCenterOfBuilding = "+sqlForCenterOfBuilding);
            ResultSet rs1 = st.executeQuery(sqlForCenterOfBuilding);
            while (rs1.next()) 
            {
                int centerOfBuildingX = 0;
                int centerOfBuildingY = 0;
                STRUCT dbObjectVal = (STRUCT) rs1.getObject("b_shape");
                JGeometry bShapeVal = JGeometry.load(dbObjectVal);
                vertices = rs1.getInt("b_vertices");
                int[] fBuildingXVal = new int[vertices];
                int[] fBuildingYVal = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                	centerOfBuildingX += (int) bShapeVal.getOrdinatesArray()[2 * i];
                	centerOfBuildingY += (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                }
                centerOfBuildingX = centerOfBuildingX/fBuildingXVal.length;
                centerOfBuildingY = centerOfBuildingY/fBuildingYVal.length;

                String ovalQueryRemaining = centerOfBuildingX + "," + (centerOfBuildingY + 50) + "," + (centerOfBuildingX + 50) + "," + centerOfBuildingY + "," + centerOfBuildingX + "," + (centerOfBuildingY - 50) + "))";

                sql_6 = "SELECT fh.h_location FROM FIRE_HYDRANT fh WHERE sdo_relate(fh.h_location," + ovalQuery + ovalQueryRemaining + ",\'MASK=inside+anyinteract\')=\'TRUE\'";
                System.out.println("sql_6 = "+sql_6);
                Statement st1 = con.createStatement();
                rs = st1.executeQuery(sql_6);
                while (rs.next()) 
                {
                    STRUCT dbObject = (STRUCT) rs.getObject("h_location");
                    JGeometry h_location = JGeometry.load(dbObject);
                    FHX = (int) h_location.getPoint()[0];
                    FHY = (int) h_location.getPoint()[1];
                    g.setColor(Color.GREEN);
                    g.fillRect(FHX - 15 / 2, FHY - 15 / 2, 15, 15);
                }
                rs.close();
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_6);
                queryNumber++;
            }
            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the inside range of fire hydrant");
        }

        try {
            st = con.createStatement();
            
            String sqlForCenterOfBuilding = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
            System.out.println("sqlForCenterOfBuilding = "+sqlForCenterOfBuilding);
            ResultSet rs1 = st.executeQuery(sqlForCenterOfBuilding);
            while (rs1.next()) 
            {
                int centerOfBuildingX = 0;
                int centerOfBuildingY = 0;
                STRUCT dbObjectVal = (STRUCT) rs1.getObject("b_shape");
                JGeometry bShapeVal = JGeometry.load(dbObjectVal);
                vertices = rs1.getInt("b_vertices");
                int[] fBuildingXVal = new int[vertices];
                int[] fBuildingYVal = new int[vertices];
                for (int i = 0; i < vertices; i++) {
                	centerOfBuildingX += (int) bShapeVal.getOrdinatesArray()[2 * i];
                	centerOfBuildingY += (int) bShapeVal.getOrdinatesArray()[2 * i + 1];
                }
                centerOfBuildingX = centerOfBuildingX/fBuildingXVal.length;
                centerOfBuildingY = centerOfBuildingY/fBuildingYVal.length;

                String ovalQueryRemaining = centerOfBuildingX + "," + (centerOfBuildingY + 50) + "," + (centerOfBuildingX + 50) + "," + centerOfBuildingY + "," + centerOfBuildingX + "," + (centerOfBuildingY - 50) + "))";
                String nQueryRemaining = centerOfBuildingX + "," + centerOfBuildingY + ",NULL),NULL,NULL)";
                sql_6 = "SELECT fh.h_location FROM FIRE_HYDRANT fh WHERE sdo_relate(fh.h_location," + ovalQuery + ovalQueryRemaining + ",\'MASK=inside+anyinteract\')=\'TRUE\' AND SDO_NN(fh.h_location," + nQuery + nQueryRemaining + ",\'sdo_num_res=1\')=\'TRUE\'";
                System.out.println("sql_6 =  "+sql_6);
                Statement st1 = con.createStatement();
                rs = st1.executeQuery(sql_6);
                
                while (rs.next()) 
                {
                    
                    STRUCT dbObject = (STRUCT) rs.getObject("h_location");
                    JGeometry h_location = JGeometry.load(dbObject);
                    FHX = (int) h_location.getPoint()[0];
                    FHY = (int) h_location.getPoint()[1];
                    g.setColor(Color.GREEN);
                    g.fillRect(FHX - 15 / 2, FHY - 15 / 2, 15, 15);
                }
                rs.close();
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_6);
                queryNumber++;
            }
            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display inside the range of fire hydrant");
        }
    }
    
    //Query 4 : draw the nearest FIRE_HYDRANT (old, not working)
    private void find_FireHydrant(Graphics g) 
    {
        try {
            st = con.createStatement();
            sql_7 = "SELECT fh.h_location FROM FIRE_HYDRANT fh WHERE SDO_NN(fh.h_location," + findFireHydrantQuery + ",\'sdo_num_res=1\')=\'TRUE\'";
            rs = st.executeQuery(sql_7);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("h_location");
                JGeometry h_location = JGeometry.load(dbObject);
                FHX = (int) h_location.getPoint()[0];
                FHY = (int) h_location.getPoint()[1];
                radius = 100;//in pixels
                g.setColor(Color.GREEN);
                g.fillRect(FHX - 15 / 2, FHY - 15 / 2, 15, 15);
                g.drawOval(FHX - radius, FHY - radius, radius * 2, radius * 2);
                NAPQuery = "SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(" + FHX + "," + FHY + ",NULL),NULL,NULL)";
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_7);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the nearest fire hydrant");
        }
    }
    
    //draw the lines from the FIRE_HYDRANT to the fireBuilding in the radius
    private void showLines(Graphics g) 
    {
        try {
            st = con.createStatement();
            sql_8 = "SELECT b.b_shape FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id AND SDO_WITHIN_DISTANCE(b.b_shape," + NAPQuery + ", 'distance = " + radius + "')='TRUE'";
            rs = st.executeQuery(sql_8);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                JGeometry fireBuildingLocation = JGeometry.load(dbObject);
                fireBuildX = (int) fireBuildingLocation.getPoint()[0];
                fireBuildY = (int) fireBuildingLocation.getPoint()[1];
                g.setColor(Color.RED);
                g.fillRect(fireBuildX - 10 / 2, fireBuildY - 10 / 2, 10, 10);
                g.setColor(Color.RED);
                g.drawLine(FHX, FHY, fireBuildX, fireBuildY);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_8);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the yellow line");
        }
        try {
            st = con.createStatement();
            radius1 = radius + 5;
            sql_8 = "SELECT b.b_shape FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id AND SDO_GEOM.SDO_DISTANCE(b.b_shape," + NAPQuery + ",0.005)< " + radius1 + " AND SDO_GEOM.SDO_DISTANCE(b.b_shape," + NAPQuery + ",0.005)> " + radius + "";
            rs = st.executeQuery(sql_8);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                JGeometry fireBuildingLocation = JGeometry.load(dbObject);
                fireBuildX = (int) fireBuildingLocation.getPoint()[0];
                fireBuildY = (int) fireBuildingLocation.getPoint()[1];
                g.setColor(Color.RED);
                g.fillRect(fireBuildX - 10 / 2, fireBuildY - 10 / 2, 10, 10);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_8);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the blue line");
        }
        try {
            st = con.createStatement();
            radius2 = radius + 10;
            sql_8 = "SELECT b.b_shape FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id AND SDO_GEOM.SDO_DISTANCE(b.b_shape," + NAPQuery + ",0.005)< " + radius2 + " AND SDO_GEOM.SDO_DISTANCE(b.b_shape," + NAPQuery + ",0.005)> " + radius1 + "";
            rs = st.executeQuery(sql_8);
            while (rs.next()) 
            {
                STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                JGeometry fireBuildingLocation = JGeometry.load(dbObject);
                fireBuildX = (int) fireBuildingLocation.getPoint()[0];
                fireBuildY = (int) fireBuildingLocation.getPoint()[1];
                g.setColor(Color.RED);
                g.fillRect(fireBuildX - 10 / 2, fireBuildY - 10 / 2, 10, 10);
            }
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_8);
            queryNumber++;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cannot display the cyan line");
        }
    }
    
    //show the polygon that user draws
    private void showPolygon(Graphics g) 
    {
        int[] X = new int[listX.size()];
        int[] Y = new int[listY.size()];
        for (int i = 0; i < listX.size(); i++) 
        {
            X[i] = listX.get(i);
            Y[i] = listY.get(i);
        }
        g.setColor(Color.red);
        g.drawPolygon(X, Y, listX.size());
    }
    
    //listener of the image, click on the map
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
        //  add  handling code here:
        if (jRadioButton2.isSelected() && rightClick == false) 
        {
            int a = evt.getX();
            int b = evt.getY();
            listX.add(evt.getX());
            listY.add(evt.getY());
            g.setColor(Color.RED);
            g.fillRect(a - 5 / 2, b - 5 / 2, 5, 5);
            if (listX.size() > 1) 
            {
                g.drawLine(listX.get(listX.size() - 2), listY.get(listY.size() - 2), listX.get(listX.size() - 1), listY.get(listY.size() - 1));
            }
        } else if (jRadioButton3.isSelected()) 
        {
            pointQueryX = evt.getX();
            pointQueryY = evt.getY();
            g.setColor(Color.RED);
            //g.fillRect(pointQueryX - 5 / 2, pointQueryY - 5 / 2, 5, 5);
            //g.drawOval(pointQueryX - 50, pointQueryY - 50, 50 * 2, 50 * 2);
            //ovalQuery = "SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(" + pointQueryX + "," + (pointQueryY + 50) + "," + (pointQueryX + 50) + "," + pointQueryY + "," + pointQueryX + "," + (pointQueryY - 50) + "))";
            ovalQuery = "SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(";
            //nQuery = "SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(" + pointQueryX + "," + pointQueryY + ",NULL),NULL,NULL)";
            nQuery = "SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(";
            
        } else if (jRadioButton4.isSelected()) 
        {
            pointQueryX = evt.getX();
            pointQueryY = evt.getY();
            findFireHydrantQuery = "SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(" + pointQueryX + "," + pointQueryY + ",NULL),NULL,NULL)";
            //find_FireHydrant(g);
            showClosetFireHydrantNearFireBul(pointQueryX, pointQueryY);

        }
    }//GEN-LAST:event_jLabel1MouseClicked
    
    //listener of the image, entered the map, get the mouse location on the map, and show the x, y coordinates on the text field
    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        String s1 = String.valueOf(x);
        String s2 = String.valueOf(y);
        jTextField1.setText("(" + s1 + ", " + s2 + ")");
    }//GEN-LAST:event_jLabel1MouseEntered
    
    //listener of the image, move on the map, get the mouse location on the map, and show the x, y coordinates on the text field   
    private void jLabel1MouseMoved(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        int x, y;
        x = evt.getX();
        y = evt.getY();
        String s1 = String.valueOf(x);
        String s2 = String.valueOf(y);
        jTextField1.setText("(" + s1 + ", " + s2 + ")");
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        //  add your handling code here:
    }

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        //  add your handling code here:   
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        //  add your handling code here:
    }

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        //  add  handling code here:
        if (jRadioButton1.isSelected()) 
        {
            repaint();
            listX.clear();
            listY.clear();
        }
    }

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        //  add handling code here:
        if (jRadioButton2.isSelected()) 
        {
            repaint();
        }
    }

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        //  add handling code here:
        if (jRadioButton3.isSelected()) 
        {
            repaint();
            listX.clear();
            listY.clear();
        }
    }
    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        //  add the handling code here:
        if (jRadioButton4.isSelected()) 
        {
            imageJPanel1.update(g);
            //showBuilding(g);
            //showFireBuilding(g);
            //showFireHydrant(g);
            //showClosetFireHydrantNearFireBul();
            listX.clear();
            listY.clear();
        }
    }
    
    //submit button
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        //  here is handling code:

        if (jRadioButton1.isSelected()) 
        {
            imageJPanel1.update(g); // refresh the map
            if (jCheckBox1.isSelected()) 
            {
                showBuilding(g);
            }
            if (jCheckBox2.isSelected()) 
            {
                showFireHydrant(g);
            }
            if (jCheckBox3.isSelected()) 
            {
                showFireBuilding(g);
            }
        } else if (jRadioButton2.isSelected()) 
        {
            imageJPanel1.update(g);
            showPolygon(g);
            if (jCheckBox1.isSelected()) 
            {
                showRangeBuilding(g);
            }
            if (jCheckBox2.isSelected()) 
            {
                showRangeFireHydrant(g);
            }
            if (jCheckBox3.isSelected()) 
            {
                showRangeFireBuilding(g);
            }

            rightClick = false;

        } else if (jRadioButton3.isSelected()) 
        {
            imageJPanel1.update(g);
            g.setColor(Color.RED);
            g.fillRect(pointQueryX - 5 / 2, pointQueryY - 5 / 2, 5, 5);
            //g.drawOval(pointQueryX - 50, pointQueryY - 50, 50 * 2, 50 * 2);
          
            if (jCheckBox1.isSelected()) 
            {
                findNeighborBuildings(g);//(old)
            }
            if (jCheckBox2.isSelected()) 
            {
                showBuildingsAndFireHydrantsAroundFireBuilding(g);//Using SDO_RELATE() query (old)
            	//showFireBuildingNeighbours();//Using SDO_DISTANCE() query
            }
            if (jCheckBox3.isSelected()) 
            {
                showinsideFireBuilding(g);//(old)
            }
        } else if (jRadioButton4.isSelected()) 
        {
            showLines(g);
        }
    }

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {
        //  add your handling code here:
    	System.out.println("Starting jLabel1MousePressed");
    	System.out.println("jRadioButton2.isSelected() = "+jRadioButton2.isSelected());
    	System.out.println("evt.isPopupTrigger() = "+evt.isPopupTrigger());
        if (jRadioButton2.isSelected() && evt.isPopupTrigger()) 
        {
            rightClick = true;
            g.drawLine(listX.get(listX.size() - 1), listY.get(listY.size() - 1), listX.get(0), listY.get(0));
            rangeQuery = "SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(";
            for (int i = 0; i < listX.size(); i++) 
            {
                rangeQuery = rangeQuery + listX.get(i) + "," + listY.get(i);
                if (i < listX.size() - 1) 
                {
                    rangeQuery = rangeQuery + ",";
                }
            }
            rangeQuery = rangeQuery + "," + listX.get(0) + "," + listX.get(0) + "))";
        } else if (jRadioButton4.isSelected() && evt.isPopupTrigger()){
            rightClick = true;
            g.drawLine(listX.get(listX.size() - 1), listY.get(listY.size() - 1), listX.get(0), listY.get(0));
            rangeQuery = "SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(";
            for (int i = 0; i < listX.size(); i++) 
            {
                rangeQuery = rangeQuery + listX.get(i) + "," + listY.get(i);
                if (i < listX.size() - 1) 
                {
                    rangeQuery = rangeQuery + ",";
                }
            }
            rangeQuery = rangeQuery + "," + listX.get(0) + "," + listX.get(0) + "))";
        }
    	System.out.println("Ending jLabel1MousePressed");
    	System.out.println("rangeQuery = "+rangeQuery);


    }

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {
        //  add your handling code here:
    	System.out.println("Starting jLabel1MouseReleased");
    	System.out.println("jRadioButton2.isSelected() = "+jRadioButton2.isSelected());
    	System.out.println("evt.isPopupTrigger() = "+evt.isPopupTrigger());
        if (jRadioButton2.isSelected() && evt.isPopupTrigger()) 
        {
            rightClick = true;
            g.drawLine(listX.get(listX.size() - 1), listY.get(listY.size() - 1), listX.get(0), listY.get(0));
            rangeQuery = "SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(";
            for (int i = 0; i < listX.size(); i++) 
            {
                rangeQuery = rangeQuery + listX.get(i) + "," + listY.get(i);
                if (i < listX.size() - 1) 
                {
                    rangeQuery = rangeQuery + ",";
                }
            }
            rangeQuery = rangeQuery + "," + listX.get(0) + "," + listX.get(0) + "))";
        }
    	System.out.println("Ending jLabel1MouseReleased");
    	System.out.println("rangeQuery = "+rangeQuery);


    }

    //Query 3 - show fire building neighbors using SDO_DISTANCE() (new method, not working) 
    private void showFireBuildingNeighbours() {
        String sqlfb = "";
        ArrayList<int[]> fbxy;
        int xctr = 0, yctr = 0;
        showFireBuilding(g);
        String d = "";
        double[] value = null;
        fbxy = this.getFireOnBuidings();

        for (int[] objArr : fbxy) {
            System.out.println(Arrays.toString(objArr));
            //iterating over the array and doing operation based on it's type
            for (int obj : objArr) {

                System.out.println("Element of fb arraylist : " + obj);
                d += Integer.toString(obj) + ",";

                String newd = d.substring(0, d.length() - 1);
                System.out.println("concated value : " + d);
                try {

                    sqlfb = "SELECT B.B_SHAPE FROM BUILDING B WHERE SDO_WITHIN_DISTANCE(B.B_SHAPE,\n"
                            + "SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,1),\n"
                            + "SDO_ORDINATE_ARRAY(" + newd + ")),'distance=100')='TRUE'";
                    System.out.println(sqlfb);

                    OracleResultSet rs = (OracleResultSet) st.executeQuery(sqlfb);
                    while (rs.next()) {
                        //Retrieve by column name
                        STRUCT str = (oracle.sql.STRUCT) rs.getSTRUCT("b_shape");
                        JGeometry j_geom = JGeometry.load(str);
                        double[] bxy = j_geom.getOrdinatesArray();
                        int[] buildingX = new int[bxy.length / 2];
                        int[] buildingY = new int[bxy.length / 2];
                        for (int i = 0; i < bxy.length; i++) {
                            if (i % 2 == 0) {

                                buildingX[xctr++] = (int) bxy[i];
                            } else {
                                buildingY[yctr++] = (int) bxy[i];
                            }
                        }
                        drawBuildings(buildingX, buildingY, buildingX.length);
                        xctr = 0;
                        yctr = 0;
                    }

                }catch (Exception e) {
                    System.out.println(e);
                }
             // resultY=statement.executeQuery(queryY);
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sqlfb);
                queryNumber++;
            }
        }
    }

    
    //Query 4 : Click to get fire on building
    private ArrayList<int[]> getFireOnBuidings() {
    	ArrayList<int[]> verticesOfFireBuilding = new ArrayList<int[]>();
            try {
                st = con.createStatement();
                sql_3 = "SELECT b.b_shape, b.b_vertices FROM FIRE_BUILDING fb, BUILDING b WHERE b.b_id = fb.fb_id";
                rs = st.executeQuery(sql_3);
                while (rs.next()) 
                {
                    STRUCT dbObject = (STRUCT) rs.getObject("b_shape");
                    JGeometry bShape = JGeometry.load(dbObject);
                    vertices = rs.getInt("b_vertices");
                    int[] arr = new int[vertices*2];
                    for (int i = 0; i < vertices; i++) {
                    	arr[i] = (int) bShape.getOrdinatesArray()[2 * i];
                    	arr[i] = (int) bShape.getOrdinatesArray()[2 * i + 1];
                    }
                    verticesOfFireBuilding.add(arr);
                }
                
                jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_3);
                queryNumber++;
        
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("cannot display the fireBuilding");
            }
		return verticesOfFireBuilding;
	}


	private void drawBuildings(int[] buildingX, int[] buildingY, int length) {
    	g.setColor(Color.YELLOW);
        g.drawPolygon(buildingX, buildingY, length);
	}

	//Show closest fire hydrant near fire building
	public void showClosetFireHydrantNearFireBul(int x, int y) {
        String sqlBul = "";
        int xctr = 0, yctr = 0;
        int FHX;
        int FHY;
        int radius;
        String sqlFH;
        String d = "";
        String newd = "";
        double[] bxy = null;
        try {

            st = con.createStatement();

            sqlBul = "SELECT B.B_SHAPE FROM BUILDING B WHERE SDO_WITHIN_DISTANCE(B.B_SHAPE,\n"
                    + "SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(" + x + "," + y + ",null),null,null),\n"
                    + "'distance=0')='TRUE' ";
            // System.out.println(sqlBul);
            OracleResultSet rs = (OracleResultSet) st.executeQuery(sqlBul);
            while (rs.next()) {
                //Retrieve by column name
                STRUCT str = (oracle.sql.STRUCT) rs.getSTRUCT("b_shape");
                JGeometry j_geom = JGeometry.load(str);
                bxy = j_geom.getOrdinatesArray();
                int[] buildingX = new int[bxy.length / 2];
                int[] buildingY = new int[bxy.length / 2];
                for (int i = 0; i < bxy.length; i++) {
                    if (i % 2 == 0) {

                        buildingX[xctr++] = (int) bxy[i];
                    } else {
                        buildingY[yctr++] = (int) bxy[i];
                    }
                }
                drawFireBuildings(buildingX, buildingY, buildingX.length);
                xctr = 0;
                yctr = 0;
            }
            for (double obj : bxy) {

                System.out.println("Element of fb arraylist : " + obj);
                d += Double.toString(obj) + ",";

                newd = d.substring(0, d.length() - 1);
                System.out.println("concated value : " + d);
            }
            // resultY=statement.executeQuery(queryY);
//            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_1);
//
//            queryNumber++;
            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sqlBul);
            queryNumber++;
        } catch (Exception e) {

            System.out.println(e);

        }

        try {

            st = con.createStatement();

            sqlFH = "SELECT h.h_location ,sdo_nn_distance(1) \n"
                    + "FROM fire_hydrant h ,building b  \n"
                    + "WHERE sdo_nn(h.h_location,SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,1),\n"
                    + "SDO_ORDINATE_ARRAY(" + newd + ")), 'sdo_num_res=1' , 1) = 'TRUE' AND ROWNUM<2 ";
            //System.out.println(sqlFH);
            rs = st.executeQuery(sqlFH);

            while (rs.next()) {

                STRUCT dbObject = (STRUCT) rs.getObject("h_location");

                JGeometry h_location = JGeometry.load(dbObject);

                FHX = (int) h_location.getPoint()[0];

                FHY = (int) h_location.getPoint()[1];

//                radius = 100;//100 pixels
                drawHydrants(FHX, FHY);

                // resultY=statement.executeQuery(queryY);
//            jTextArea1.append("\r\n" + "Query" + queryNumber + ":" + sql_1);
//
//            queryNumber++;
            }
        } catch (Exception e) {

            System.out.println(e);

        }

    }
    


    //draw all the FIRE_HYDRANT on the map
    private void drawHydrants(int FHX, int FHY) 
    {
                radius = 100;//100 pixels
                g.setColor(Color.GREEN);
                g.fillRect(FHX - 15 / 2, FHY - 15 / 2, 15, 15);
    }
    
    //draw Fire Buildings on the map
    private void drawFireBuildings(int[] buildingX, int[] buildingY, int buildingXLen)
    {
    	g.setColor(Color.RED);
        g.drawPolygon(buildingX, buildingY, buildingXLen);      
    }
    
   
    public static void main(String args[]) 
    {
       
        //final String propertiesPath = args[0];
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new hw3().setVisible(true);
            }
        });
    }
    
}

class ImageJPanel extends javax.swing.JPanel 
{

	static final long serialVersionUID = 3049504395346L; 
    Graphics g = null;
    Image image = null;
  
	
    public ImageJPanel() 
    {
        initComponents();
        URL imgURL=getClass().getResource("map.jpg");
        image =new ImageIcon(imgURL).getImage();
        Dimension size = new Dimension(image.getWidth(this), image.getHeight(this));
        setSize(size);
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
    @Override
    public void update(Graphics g)
    {
      g.drawImage(image, 0, 0, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }
}