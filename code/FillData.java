package batta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class FillData {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/battadb" , "root" , "elephant");
		Statement st = con.createStatement();
		st.executeUpdate("create table patient_record( pid int primary key AUTO_INCREMENT ,age int(20)  , fnlwgt varchar(20),marital varchar(20),occupation varchar(20),race varchar(20) , gender varchar(20));");
		//st.executeQuery("LOAD DATA LOCAL INFILE 'E:/m.tech/project/datasets/1000dataset.txt' INTO TABLE patient_record FIELDS TERMINATED BY ',' LINES TERMINATED BY ';' (@col1,@col2,@col3,@col4,@col5,@col6) set pid=@col1,age=@col2,fnlwgt=@col3,marital=@col4,occupation=@col5,race=@col6,gender=@col7;");
		st.executeQuery("LOAD DATA LOCAL INFILE 'E:/m.tech/project/datasets/5000dataset.txt' INTO TABLE patient_record FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n' (@col1,@col2,@col3,@col4,@col5,@col6) set age=@col1,fnlwgt=@col2,marital=@col3,occupation=@col4,race=@col5,gender=@col6;");
		st.close();
		con.close();		
	}

}
