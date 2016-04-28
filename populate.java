import java.io.*;
//import oracle.sql.*;
//import oracle.jdbc.*;
import java.sql.*;

public class populate{
    /**
     * @param args the command line arguments
     */
    private String host;
    private String port;
    private String dbname;
    private String username;
    private String password;
    private Connection con = null;
    private Statement st = null;
    
    //get the properties from the file
    public void getProperties(String path){
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
    
    //connect to Oracle database, and delete all the old data from the table
    public void connectDB(){
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            System.out.println("connecting to database");
            String url = "jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:db11g";
            //String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + dbname;
            //con = DriverManager.getConnection(url, username, password);
            //String url = "jdbc:oracle:thin:@localhost:1521/db11g";
            con = DriverManager.getConnection(url, "apadha", "Siddharthu1234 ");
            
            st = con.createStatement();
            System.out.println("connect to database successfully");
            String sql = "DELETE FROM BUILDING";
            st.execute(sql);
            sql = "DELETE FROM Fire_Hydrant";
            st.execute(sql);
            sql = "DELETE FROM Fire_Building";
            st.execute(sql);
        } catch (SQLException e) {
        	System.out.println("cannot connect to database");
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("connected successfully"); 
    }
    
    //manipulate the data, populate the data from the file to the database
    public void populateData(String file1, String file2, String file3)
    {
            try 
            {
                String line;
                //each arg corresponding to a column
                String buildingId, buildingName, countVertices, arg4;
                
                //read the building file
                BufferedReader inFile1 = new BufferedReader(new FileReader(file1));                
                while ((line = inFile1.readLine()) != null) 
                {
                	String[] items = line.split(","+" ");
                	buildingId = items[0];
                	buildingName = items[1];
                	countVertices = items[2];
                	Integer pointNumber = Integer.valueOf(items[2]);
                	String startX = items[3];
                	String startY = items[4];
                	String pointPosition = items[3] + "," + items[4] + "," + " ";
                	int count = 1;
                	while (count < pointNumber.intValue())
					{
                		/*
						if ()//if the number beyond the boundary
						{
							
						}*/
						pointPosition = pointPosition + items[3+2*count] + "," + items[3+2*count+1] + "," + " ";
						count++;
					}
                    pointPosition = pointPosition + startX + "," + startY;
                    String sql1 = "INSERT INTO BUILDING VALUES ('" + buildingId + "','" + buildingName + "','" + countVertices + "',SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(" + pointPosition + ")))";
                    System.out.println(sql1);
                    st.execute(sql1);
                }
                
                //read the people file
                BufferedReader inFile2 = new BufferedReader(new FileReader(file2));
                while ((line = inFile2.readLine()) != null) 
                {
                    String[] items = line.split(","+" ");
                    String hId = items[0];
                    String x = items[1];
                    String y = items[2];
                    String sql2 = "INSERT INTO Fire_Hydrant VALUES ('" + hId + "'," + "SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(" + x + "," + y + ",NULL),NULL,NULL))";
                    System.out.println(sql2);
                    st.execute(sql2);
                }

                //read the ap file 
                BufferedReader inFile3 = new BufferedReader(new FileReader(file3));
                while ((line = inFile3.readLine()) != null) 
                {
                    String[] items = line.split(","+" ");
                    String bName = items[0];
                    
                    String sql4 = "SELECT b_id FROM BUILDING WHERE b_name = '" + bName + "'";
                    System.out.println(sql4);
                    ResultSet rs = st.executeQuery(sql4);
                    while (rs.next()) {
                        String b_id = rs.getString("b_id");
                        String sql5 = "INSERT INTO Fire_Building VALUES ('" + b_id + "')";
                        System.out.println(sql5);
                        st.execute(sql5);
                    }
                }
            } catch (FileNotFoundException e) {
            	System.out.println("File does not exist!");
                e.printStackTrace();
            } catch (IOException e) {
            	System.out.println("IO problem!");
                e.printStackTrace();
            } catch (SQLException e) {
            	System.out.println("Database query problem");
		e.printStackTrace();
            } 
            finally{
		try{
	        		if (con != null){
	        			con.close();
	        			System.out.println("connection closed");
	        		}
	        	} catch (SQLException e) {
	        		System.out.println("cannot close to database");
	        		e.printStackTrace();
	            }
	        }
    }

    /**
     * @param args
     */
    public static void main(String[] args){
//        System.out.println("-------- Oracle JDBC Connection Testing ------");
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            System.out.println(System.getProperty("java.home"));
//        } catch (ClassNotFoundException e) {
//            System.out.println("Where is your Oracle JDBC Driver?");
//            e.printStackTrace();
//            return;
//        }
//        System.out.println("Oracle JDBC Driver Registered!");
//        Connection con = null;
//        try {
//            con = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@127.0.0.1:1521/orcl",
//                    "scott",
//                    "oracle");
//            Statement stmt = con.createStatement();
//            
//            ResultSet rs = stmt.executeQuery("SELECT empno, ename FROM EMP");
//            System.out.println("-------- EMP ------");
//            while (rs.next()) {
//                int empno = rs.getInt("empno");
//                String ename = rs.getString("ename");
//                System.out.println("EmpNo = "+empno+"; Ename = "+ename);
//            }
//            
//            ResultSet rs2 = stmt.executeQuery("SELECT count(b_name) count FROM Building");
//            System.out.println("-------- BUILDING ------");
//            while (rs2.next()) {
//                int count = rs2.getInt("count");
//                System.out.println("count = "+count);
//            }
//            
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
        
        if (args.length != 3) {
            System.out.println("Usage:\n     java populate building.xy firehydrant.xy firebuilding.txt");
        } else {
            //String building_file = "./ building.xy";
            String building_file = args[0];
            String fire_hydrant_file = args[1];
            String fire_building_file = args[2];
            System.out.println("args[0] = "+args[0]);
            System.out.println("args[1] = "+args[1]);
            System.out.println("args[2] = "+args[2]);

            populate populateDatabase = new populate();
            //populateDatabase.getProperties(properties_file);
            populateDatabase.connectDB();
            populateDatabase.populateData(building_file, fire_hydrant_file, fire_building_file);
        }
	}
}
