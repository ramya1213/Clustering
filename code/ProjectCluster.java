package batta;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.*;

import javax.swing.*;

public class ProjectCluster implements ActionListener , ItemListener,KeyListener {
	JLabel generate , g1 , kam ,pmi;
	Choice choice;
	JLabel f_l1,f_record,f_display;
	JTextField f_t1,temp;
	JButton search;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,msginsert,msgdelete,msgupdate,common;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
	JFrame f;
	JPanel p;
	List list1,list2,list3,list4,list5,list6;
	Connection con;
	Statement st;
	ResultSet rs;
	JLabel banner1,banner2,l_total_record,l_total_cluster , informationLoss , executiontime; 
//	JLabel inflossfage , inflossfzip,inflossfgen;
	JButton insert, update ,refresh , delete ,find ,b1,b2,b3;
	
	public ProjectCluster(String name){
		f = new JFrame(name);
		p = new JPanel();
		temp = new JTextField(20);
		list1 = new List();
		list2 = new List();
		list3 = new List();
		list4 = new List();
		list5 = new List();
		list6 = new List();
		choice = new Choice();
		b1  = new JButton("New Entity");
		b2  = new JButton("Update");
		b3  = new JButton("Delete");
		insert  = new JButton("Insert Confirm");
		update  = new JButton("Update Confirm");
		refresh  = new JButton("Refresh");
		delete  = new JButton("Delete Confirm");
		find  = new JButton("Find");
		search  = new JButton("Search");
		l_total_record = new JLabel("Toatal Number Of Records");
		l_total_cluster = new JLabel("Toatal Number Of Cluster");
		informationLoss = new JLabel("");
		executiontime = new JLabel("execution time:");
		
		kam = new JLabel("Systematic Clustering Approach for data utility and privacy");
		pmi = new JLabel("Equal Combination of Quasi idetifiers and Sensitive Attributes");
		banner1 = new JLabel("UCI Record");
		banner2 = new JLabel("Anonymized Record");
		banner1.setFont(new Font("senserrif" ,Font.BOLD,20));
		banner2.setFont(new Font("senserrif" ,Font.BOLD,18));
		kam.setFont(new Font("Times New Roman" , Font.BOLD , 20));
		pmi.setFont(new Font("Tahoma" , Font.ITALIC,18));
		pmi.setForeground(Color.blue);
		
		msginsert = new JLabel("Press Insert Confirm Button For Saving");
		msgupdate = new JLabel("Press update Confirm Button For Saving");
		msgdelete = new JLabel("Press delete Confirm Button For Saving");
		msginsert.setFont(new Font("senserrif" ,Font.BOLD,18));
		msgupdate.setFont(new Font("senserrif" ,Font.BOLD,18));
		msgdelete.setFont(new Font("senserrif" ,Font.BOLD,18));
		generate = new JLabel("Generate");
		generate.setFont(new Font("senserrif" ,Font.BOLD,20));
		g1 = new JLabel("Annomized Table");
		g1.setFont(new Font("senserrif" ,Font.BOLD,20));
//		choice.add("40");
//		choice.add("60");
//		choice.add("80");
//		choice.add("100");
//		choice.add("120");
		choice.add("200");
		choice.add("400");
		choice.add("600");
		choice.add("800");
		choice.add("1000");
		msginsert.setForeground(Color.red);
		msgupdate.setForeground(Color.red);
		msgdelete.setForeground(Color.red);
		l_total_record.setForeground(Color.blue);
		l_total_cluster.setForeground(Color.blue);
		executiontime.setForeground(Color.RED);
		l_total_record.setFont(new Font("Times New Roman" ,Font.BOLD,12));
		l_total_cluster.setFont(new Font("Times New Roman" ,Font.BOLD,12));
		informationLoss.setForeground(Color.RED);
		informationLoss.setFont(new Font("Times New Roman" ,Font.BOLD,18));
		executiontime.setFont(new Font("Times New Roman" ,Font.BOLD,18));
		
		common = new JLabel("");
		common.setForeground(Color.red);
		common.setFont(new Font("senserrif" ,Font.BOLD,20));
		
		f_l1 = new JLabel("Enter PID");
		f_l1.setForeground(Color.blue);
		f_l1.setFont(new Font("senserrif" ,Font.BOLD,18));
		f_record = new JLabel("Press Search Button For Display Record");
		f_record.setFont(new Font("Times New Roman" ,Font.BOLD,18));
		f_t1 = new JTextField(10);
		f_display = new JLabel();
		f_display.setForeground(Color.red);
		
		l1 = new JLabel("PID");
		l2 = new JLabel("NAME");
		l3 = new JLabel("PH NO");
		l4 = new JLabel("CITY");
		l5 = new JLabel("ZIPCODE");
		l6 = new JLabel("GENDER");
		l7 = new JLabel("AGE");
		l8 = new JLabel("DISEASE");
		
		t1 = new JTextField(10);
		t2 = new JTextField(10);
		t3 = new JTextField(10);
		t4 = new JTextField(10);
		t5 = new JTextField(10);
		t6 = new JTextField(10);
		t7 = new JTextField(10);
		t8 = new JTextField(10);
		
		t1.addKeyListener(this);
		try{
			Class.forName(DBvendor.DB_VENDOR_NAME);
			con = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
			st = con.createStatement();
			String sql = "select * from patient_record order by pid";
			rs = st.executeQuery(sql);
			list1.add("PID     AGE     FNLWGT     MARITAL-STATUS     OCCUPATION        RACE     GENDER ");
			list1.add("=================================================================================");
		    while(rs.next()){
				list1.add(rs.getString(1)+"        "+rs.getString(2)+"        "+rs.getString(3)+"        "+rs.getString(4)+"        "+rs.getString(5)+"        "+rs.getString(6)+"        "+rs.getString(7));
		        list1.add("---------------------------------------------------------------------");
		    }
		}catch(Exception e){
			System.out.println("EXCEPTION"+e.getMessage());
			e.printStackTrace();
		}
		p.setLayout(null);
		p.add(kam);
		p.add(pmi);
		p.add(list1);
		p.add(list2);
		p.add(list3);
		p.add(list4);
		
		p.add(list5);
		p.add(list6);
		
		p.add(banner1);
		p.add(banner2);
//		p.add(insert);
//		p.add(delete);
//		p.add(update);
//		p.add(refresh);
//		p.add(find);
//		p.add(search);
		
//		p.add(f_l1);
//		p.add(f_t1);
//		p.add(f_display);
//		p.add(f_record);
//		p.add(l1);
//		p.add(l2);
//		p.add(l3);
//		p.add(l4);
//		p.add(l5);
//		p.add(l6);
//		p.add(l7);
//		p.add(l8);
//		p.add(t1);
//		p.add(t2);
//		p.add(t3);
//		p.add(t4);
//		p.add(t5);
//		p.add(t6);
//		p.add(t7);
//		p.add(t8);
//		p.add(b1);
//		p.add(b2);
//		p.add(b3);
//		p.add(generate);
//		p.add(g1);
		p.add(choice);
//		p.add(msginsert);
//		p.add(msgupdate);
//		p.add(msgdelete);
//		p.add(common);
		p.add(l_total_record);
		p.add(l_total_cluster);
		p.add(informationLoss);
		p.add(executiontime);
		f.add(p);
		setEntryAdd(false);
		
		choice.addItemListener(this);
		insert.addActionListener(this);
		delete.addActionListener(this);
		update.addActionListener(this);
		refresh.addActionListener(this);
		find.addActionListener(this);
		search.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		p.setBackground(Color.pink);
		
		kam.setBounds(400, 1, 650, 40);
		pmi.setBounds(390, 31, 550, 40);
		b1.setBounds(30, 90, 100, 25);
		b2.setBounds(135, 90, 100, 25);
		b3.setBounds(240, 90, 100, 25);
		l1.setBounds(90, 130, 100, 25);
		t1.setBounds(210, 130, 120, 25);
		l2.setBounds(90, 160, 100, 25);
		t2.setBounds(210, 160, 120, 25);
		l3.setBounds(90, 190, 100, 25);
		t3.setBounds(210, 190, 120, 25);
		l4.setBounds(90, 220, 100, 25);
		t4.setBounds(210, 220, 120, 25);
		l5.setBounds(90, 250, 100, 25);
		t5.setBounds(210, 250, 120, 25);
		l6.setBounds(90, 280, 100, 25);
		t6.setBounds(210, 280, 120, 25);
		l7.setBounds(90, 310, 100, 25);
		t7.setBounds(210, 310,120, 25);
		l8.setBounds(90, 340, 100, 25);
		t8.setBounds(210, 340, 120, 25);
		common.setBounds(88, 430, 300, 25);
		msginsert.setBounds(75, 430, 360, 25);
		msginsert.setVisible(false);
		insert.setBounds(120,450,150,25);
		insert.setVisible(false);
		msgupdate.setBounds(75, 430, 360, 25);
		msgupdate.setVisible(false);
		update.setBounds(120,450,150,25);
		update.setVisible(false);
		msgdelete.setBounds(75, 430, 360, 25);
		msgdelete.setVisible(false);
		delete.setBounds(120,450,150,25);
		delete.setVisible(false);
		find.setBounds(150, 490, 80, 20);
		f_l1.setBounds(60, 515, 110, 25);
		f_t1.setBounds(150, 515, 120, 20);
		f_record.setBounds(40, 535, 300, 25);
		f_display.setBounds(50, 590, 400, 30);
		search.setBounds(150, 560, 80, 20);
		banner2.setBounds(510, 120, 250, 25);
		choice.setBounds(770, 120, 70, 25);		
//		list2.setBounds(510, 140, 250, 220);
//		list3.setBounds(765, 140, 250, 220);
//		list4.setBounds(1020, 140, 250, 220);
//		list5.setBounds(1, 140, 250, 220);
//		list6.setBounds(255, 140, 250, 220);
		list2.setBounds(1, 140, 250, 220);
		list3.setBounds(255, 140, 250, 220);
		list4.setBounds(510, 140, 250, 220);
		list5.setBounds(765, 140, 250, 220);
		list6.setBounds(1020, 140, 250, 220);
		
		informationLoss.setBounds(600 , 400 , 600 , 20);
		executiontime.setBounds(200 , 400 , 600 , 20);
		executiontime.setVisible(false);
		
		
		l_total_record.setBounds(600, 367, 200, 20);
		l_total_record.setVisible(false);

		l_total_cluster.setBounds(930, 367, 200, 20);
		l_total_cluster.setVisible(false);
		banner1.setBounds(480,410,300,40);
		refresh.setBounds(790,450,90,20);
//		list1.setBounds(500,480,710,220);
		list1.setBounds(250,480,710,220);
		addFind(false);
		setEntryAdd(false);
        f.setSize(1280,960);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addFind(boolean b) {
		if(b==true){
			f_l1.setVisible(true);
			f_record.setVisible(true);
			f_t1.setVisible(true);
			search.setVisible(true);
			f_t1.requestFocus();
		}else{
			f_l1.setVisible(false);
			f_record.setVisible(false);
			f_t1.setVisible(false);
			search.setVisible(false);
			}
		
	}

	private void setEntryAdd(boolean b) {
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t7.setText("");
		t8.setText("");
		t1.requestFocus();
		if(b==true){
			t1.setEditable(true);		
			t2.setEditable(true);t3.setEditable(true);t4.setEditable(true);t5.setEditable(true);t6.setEditable(true);t7.setEditable(true);t8.setEditable(true);
//			t9.setEditable(true);t10.setEditable(true);
		}else{
			t1.setEditable(false);		
			t2.setEditable(false);t3.setEditable(false);t4.setEditable(false);t5.setEditable(false);t6.setEditable(false);t7.setEditable(false);t8.setEditable(false);
//			t9.setEditable(false);t10.setEditable(false);
		}			
			
	}
	private void setEntryDelete(boolean b) {
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t7.setText("");
		t8.setText("");
//		t9.setText("");
//		t10.setText("");
		t1.requestFocus();
		if(b==true){
			t1.setEditable(true);		
			t2.setEditable(false);t3.setEditable(false);t4.setEditable(false);t5.setEditable(false);t6.setEditable(false);t7.setEditable(false);t8.setEditable(false);
//			t9.setEditable(false);t10.setEditable(false);
		}else{
			t1.setEditable(false);		
			t2.setEditable(false);t3.setEditable(false);t4.setEditable(false);t5.setEditable(false);t6.setEditable(false);t7.setEditable(false);t8.setEditable(false);
//			t9.setEditable(false);t10.setEditable(false);
		}			
	}
	private void setEntryUpdate(boolean b) {
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t7.setText("");
		t8.setText("");
//		t9.setText("");
//		t10.setText("");
		t1.requestFocus();
		if(b==true){
			t1.setEditable(true);		
			t2.setEditable(true);t3.setEditable(true);t4.setEditable(true);t5.setEditable(true);t6.setEditable(true);t7.setEditable(true);t8.setEditable(true);
//			t9.setEditable(true);t10.setEditable(true);
		}else{
			t1.setEditable(false);		
			t2.setEditable(false);t3.setEditable(false);t4.setEditable(false);t5.setEditable(false);t6.setEditable(false);t7.setEditable(false);t8.setEditable(false);
//			t9.setEditable(false);t10.setEditable(false);
		}			
			
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		try {
			Class.forName(DBvendor.DB_VENDOR_NAME);
			con = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
			st = con.createStatement();
			String sql1 = "select * from patient_information where pid = '"+t1.getText()+"'";
			rs = st.executeQuery(sql1);
			if(rs.next()){
				System.out.println("keypressed");
				t2.setText(rs.getString(2));
				t3.setText(rs.getString(3));
				t4.setText(rs.getString(4));
			}else{
				t2.setText("");
				t3.setText("");
				t4.setText("");
			}
			con.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
         String t = t1.getText();		
	}

	@Override
	public void itemStateChanged(ItemEvent ke) {
       int n = Integer.parseInt(choice.getSelectedItem());       
       NewEquivalencenCluster eqobj2 = new NewEquivalencenCluster();
       int r = eqobj2.totalRow();
       int c = eqobj2.totalRow()/n;
       
       Long start=System.currentTimeMillis();
       java.util.List<Choice[]> lis = eqobj2.assignRecordToCluster(n);
       Long end=System.currentTimeMillis();
       Long time=end-start;
       executiontime.setText("executiontime: " + time + "ms");
       executiontime.setVisible(true);
     
       System.out.println(executiontime);
       float infoLoss = eqobj2.informationloss;
       
       
       if(!lis.isEmpty()&&lis.size()==5){
    	   Choice nClusterage[]  = lis.get(0);
    	   Choice nClusterzipcode[]  = lis.get(1);
    	   Choice nClustergender[]  = lis.get(2);   	
    	   Choice nClusterms[]  = lis.get(3);
    	   Choice nClusterrace[]  = lis.get(4);
    	   
    	   list2.removeAll();
    	   list3.removeAll();
    	   list4.removeAll();
    	   list5.removeAll();
    	   list6.removeAll();
           list2.add("ID    AGE   OCCUPATION ");
           list3.add("ID    GENDER   OCCUPATION");
           list4.add("ID    FNLWGT  OCCUPATION");
           list5.add("ID    MARITAL-STATUS   OCCUPATION");
           list6.add("ID    RACE  OCCUPATION");
           int ageid = 0;
           int genId = 0;
           int zipId =0;
           int msId =0;
           int rcId = 0;
           for(int i=0;i<nClusterage.length;i++){
        	   list2.add("------------------------------------------------");
        	   list3.add("------------------------------------------------");
        	   list4.add("------------------------------------------------");
        	   list5.add("------------------------------------------------");
        	   list6.add("------------------------------------------------");
        	   for(int j=0;j<nClusterage[i].getItemCount();j++){
        		   ageid= ageid+1;
        		   String clstAge = nClusterage[i].getItem(j);
        		   list2.add(ageid+" "+clstAge.substring(clstAge.indexOf(" ")));
        	   }
        	   
	    	   for(int j=0;j<nClustergender[i].getItemCount();j++){
	    		   genId= genId+1;
	    		   list3.add(genId+" "+nClustergender[i].getItem(j));
	    	   }
	    	   
	    	   for(int j=0;j<nClusterzipcode[i].getItemCount();j++){
	    		   zipId= zipId+1;
	    		   list4.add(zipId+" "+nClusterzipcode[i].getItem(j));
	    	   }
	    	   
	    	   for(int j=0;j<nClusterms[i].getItemCount();j++){
	    		   msId= msId+1;
	    		   list5.add(msId+" "+nClusterms[i].getItem(j));
	    	   }
	    	   
	    	   for(int j=0;j<nClusterrace[i].getItemCount();j++){
	    		   rcId= rcId+1;
	    		   list6.add(rcId+" "+nClusterrace[i].getItem(j));
	    	   }
           }
    	   list2.add("------------------------------------------------");
    	   list3.add("------------------------------------------------");
    	   list4.add("------------------------------------------------");
    	   list5.add("------------------------------------------------");
    	   list6.add("------------------------------------------------");
    	   
  	   
    	   
    	   
       }else{
    	   list2.removeAll();
    	   list3.removeAll();
    	   list4.removeAll();
    	   list5.removeAll();
    	   list6.removeAll();
       }
       
      
       

	   
	   l_total_record.setText("Total Number of Records");
	   String t1 = l_total_record.getText();
	   t1 =t1+":"+String.valueOf(r);
	   l_total_record.setText(t1);
	   l_total_record.setVisible(true);
	   l_total_cluster.setText("Total Number of Cluster");
	   String t2 = l_total_cluster.getText();
	   t2 =t2+":"+String.valueOf(c);
	   l_total_cluster.setText(t2);
	   l_total_cluster.setVisible(true);
	   
   
	   informationLoss.setText("Information Loss :"+ infoLoss);
	   informationLoss.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//Object obj = e.getSource();
//if(obj==insert){
//	msginsert.setVisible(false);
//	common.setVisible(false);
//	Connection con1 , con2;
//	Statement st1 , st2;
//	try{
//		Connection con;
//		Statement st;
//		int n=0;
//		Class.forName(DBvendor.DB_VENDOR_NAME);
//		String sql1 = "select * from patient_information where pid = '"+t1.getText()+"'";
//		con = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
//		st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_UPDATABLE);
//		ResultSet rs = st.executeQuery(sql1);
//		int flag = 0;
//		while(rs.next()){
//			flag = 1;
//		}
//		con.close();
//		st.close();
//		if(flag !=1){
//			con1 = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
//			st1 = con1.createStatement();
//			String sql = "insert into patient_information values('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"')";
//			int r = st1.executeUpdate(sql);
//			if(r==1){
//				common.setText("Inserted Successfully");
//				common.setVisible(true);
//			}
//			con1.commit();
//			con1.close();
//			st1.close();
//			
//			Class.forName(DBvendor.DB_VENDOR_NAME);
//			con2 = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
//			st2 = con2.createStatement();
//			sql = "insert into patient_record values('"+t1.getText()+"','"+t5.getText()+"','"+t6.getText()+"','"+t7.getText()+"','"+t8.getText()+"')";
//             r = st2.executeUpdate(sql);
//			if(r==1)
//			con2.commit();
//			con2.close();
//			st2.close();
//	
//		}else{
//			Class.forName(DBvendor.DB_VENDOR_NAME);
//			con2 = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
//			st2 = con2.createStatement();
//			String sql = "insert into patient_record values('"+t1.getText()+"','"+t5.getText()+"','"+t6.getText()+"','"+t7.getText()+"','"+t8.getText()+"')";
//			int r = st2.executeUpdate(sql);
//			
//			if(r==1)
//				con2.commit();
//				con2.close();
//				st2.close();
//			
//		}
//	}catch(Exception e1){
//		System.out.println("Exception"+e1.getMessage());
//		e1.printStackTrace();
//		common.setText("Record cna't be inserted");
//		common.setVisible(true);
//	}
//	setEntryAdd(false);
//}
//if(obj==delete){
//	Connection con1,con2;
//	common.setVisible(false);
//	msgdelete.setVisible(false);
//    Statement st1 ,st2;
//    try{
//    	Class.forName(DBvendor.DB_VENDOR_NAME);
//		con2 = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
//		st2 = con2.createStatement();
//		String sql2 = "delete from patient_record where pid ='"+t1.getText()+"'";
//				int r2 = st2.executeUpdate(sql2);
//		
//		con1 = DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
//		st1 = con1.createStatement();
//		String sql1 = "delete from patient_information where pid ='"+t1.getText()+"'";
//				int r1 = st1.executeUpdate(sql1);
//		if(r1>=1||r2>=1){
//			common.setText("One Record Deleted");
//			common.setVisible(true);
//		}else{
//			con1.rollback();
//			con2.rollback();
//			common.setText("pid did not match");
//			common.setVisible(true);
//		}
//		con1.close();
//		con2.close();
//		st1.close();
//		st2.close();
//		
//    }catch(Exception e2){
//    	System.out.println("\n"+e2.getMessage());
//    	common.setText("Record can't be deleted");
//    	common.setVisible(true);
//    	setEntryDelete(false);
//    }
//}
//    
//    if(obj==update){
//    	msgupdate.setVisible(false);
//    	common.setVisible(false);
//    	Connection con1 , con2;
//    	Statement st1 , st2;
//    	try{
//    		Class.forName(DBvendor.DB_VENDOR_NAME);
//    		con1 = DriverManager.getConnection(DBvendor.DB_URL, DBvendor.USER_NAME,DBvendor.PASSWORD);
//    		st1 = con1.createStatement();
//    		
//    		String sql = "update patient_information set pid='"+t1.getText()+"',name = '"+t2.getText()+"',phno='"+t3.getText()+"',city='"+t4.getText()+"' where pid='"+t1.getText()+"'";
//    		
//    		
//    		int r = st1.executeUpdate(sql);
//    		if(r==1){
//    			common.setText("one record has updated");
//    			common.setVisible(true);
//    		}else{
//    			common.setText(" record not found");
//    			common.setVisible(true);
//    		}
//    		con1.commit();
//    		con1.close();
//    		st1.close();
//    	}catch(Exception e3){
//    		System.out.println("\n"+e3.getMessage());
//    		common.setText("updation failed in 1st table");
//    		common.setVisible(true);
//    	}
//    	try{
//    		Class.forName(DBvendor.DB_VENDOR_NAME);
//    		con2 = DriverManager.getConnection(DBvendor.DB_URL, DBvendor.USER_NAME,DBvendor.PASSWORD);
//    		st2 = con2.createStatement();
//    		
//    		String sql = "update patient_record set pid='"+t1.getText()+"',zipcode = '"+t5.getText()+"',gender='"+t6.getText()+"',age='"+t7.getText()+"',disease='"+t8.getText()+" ' where pid='"+t1.getText()+"'";
//    		
//    		
//    		int r = st2.executeUpdate(sql);
//    		if(r==1){
//    			common.setText("one record has updated");
//    			common.setVisible(true);
//    		}
//    		con2.commit();
//    		con2.close();
//    		st2.close();
//    	}catch(Exception e4){
//    		System.out.println("\n"+e4.getMessage());
//    		common.setText("updation failed ");
//    		common.setVisible(true);
//    		setEntryDelete(false);
//    	}
//    	}
//    	
//    	if(obj==b1){
//    		setEntryAdd(true);
//    		msginsert.setVisible(true);
//    		msgdelete.setVisible(false);
//    		msgupdate.setVisible(false);
//    		common.setVisible(false);
//    		insert.setVisible(true);
//    		delete.setVisible(false);
//    		update.setVisible(false);
//    	}
//    	
//    	
//    	if(obj==b2){
//    		setEntryUpdate(true);
//    		msginsert.setVisible(false);
//    		msgdelete.setVisible(false);
//    		msgupdate.setVisible(true);
//    		common.setVisible(false);
//    		insert.setVisible(false);
//    		delete.setVisible(false);
//    		update.setVisible(true);
//    	}
//    	
//    	if(obj==b3){
//    		setEntryDelete(true);
//    		msginsert.setVisible(false);
//    		msgdelete.setVisible(true);
//    		msgupdate.setVisible(false);
//    		common.setVisible(false);
//    		insert.setVisible(false);
//    		delete.setVisible(true);
//    		update.setVisible(false);
//    	}
//    	
//    	
//    	if(obj==refresh){
//    		setEntryAdd(false);
//    		addFind(false);
//    		msginsert.setVisible(false);
//    		msgdelete.setVisible(false);
//    		msgupdate.setVisible(false);
//    		common.setVisible(false);
//    		insert.setVisible(false);
//    		delete.setVisible(false);
//    		update.setVisible(false);
//    		
//    		Connection con;
//    		Statement st;
//    		ResultSet rs;
//    		list1.removeAll();
//    		try{
//    			Class.forName(DBvendor.DB_VENDOR_NAME);
//        		con = DriverManager.getConnection(DBvendor.DB_URL, DBvendor.USER_NAME,DBvendor.PASSWORD);
//        		st = con.createStatement();
//        		
//        		String sql = "select * from patient_record order by pid";    		
//        		
//        		 rs = st.executeQuery(sql);
//        		 list1.add("PID     ZIPCODE     GENDER     AGE     DESEASE ");
//        		 list1.add("===================================================");
//        		 while(rs.next()){
//        			 list1.add(rs.getString(1)+"     "+rs.getString(2)+"     "+rs.getString(3)+"     "+rs.getString(4)+"     "+rs.getString(5));
//        		 list1.add("------------------------------------------------------");
//        		 }
//    		}catch(Exception e5){
//    			System.out.println(e5.getMessage());
//    		}}
//
//    		if(obj==find){
//    			f_t1.setText("");
//    			f_t1.requestFocus();
//    			f_record.setText("press search button to display record");
//    			addFind(true);
//    		}
//    		if(obj==search){
//    			String t;
//    			Connection con1;
//    			Statement st1;
//    			try{
//    				Class.forName(DBvendor.DB_VENDOR_NAME); 
//            		con1 = DriverManager.getConnection(DBvendor.DB_URL, DBvendor.USER_NAME,DBvendor.PASSWORD);
//            		st1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            		
//            		String sql = "select * from patient_information where pid='"+f_t1.getText()+"'";    		
//            		
//            		ResultSet r = st1.executeQuery(sql);
//            		
//            		if(r.first()){
//            			t = r.getString(2)+"    "+r.getString(3)+"    "+r.getString(4);
//            			f_display.setText(t);
//            		}
//            		else{
//            			f_display.setText("record could not found");
//            		}
//            		con1.close();
//            		st1.close();
//    			}catch(Exception e6){
//    				System.out.println("\n"+e6.getMessage());
//    				e6.printStackTrace();
//    				f_display.setText("redord did not match");
//    				
//    			}
//    		}
    	}
    	public static void main(String a[]){
    		//Long start = System.currentTimeMillis();
    		new ProjectCluster("Systematic Clustering Approach");
    		//Long end = System.currentTimeMillis();
    		//Long exe =end-start;
    		//System.out.println(exe);
    	}	
    }

/*******************************************/

/*
 * 
 * EQUIVALENCECLUSTER.JAVA
 * */
//new method

/**********start******************/


class NewEquivalencenCluster {
	
	public float informationloss;
	
	private String mstitle = "not-realeased";
	private String racetitle = "[PERSON]";
	
	public String annomize(String t)
	{
		StringBuffer sb1=new StringBuffer(t);
		for(int i=sb1.length()-1;i>=sb1.length()-3;i--)
		{
			sb1.setCharAt(i,'*');
		}
		return(String.valueOf(sb1));
	}
	
	public String annomize_Age(String age)
	{
		String t="[";
		int n=Integer.parseInt(age);
		if(n>=1&&n<=10)
			t+="1-10";
		if(n>=11&&n<=20)
			t+="11-20";
		if(n>=21&&n<=30)
			t+="21-30";
		if(n>=31&&n<=40)
			t+="31-40";
		if(n>=41&&n<=50)
			t+="41-50";
		if(n>=51&&n<=60)
			t+="51-60";
		if(n>=61&&n<=70)
			t+="61-70";
		if(n>=71&&n<=80)
			t+="71-80";
		if(n>=81&&n<=90)
			t+="81-90";
		if(n>=91&&n<=100)
			t+="91-100";
		t+="]";
		return(t);
	}
	
//	public String format(String t)
//	{
//		String t1=t;
//		for(int i=t.length();i<=20;i++)
//		t1+=" ";
//		return(t1);
//	}
	
	
	public java.util.List<Choice[]> assignRecordToCluster(int k){
		
		
		java.util.List<Choice[]> lis = new ArrayList<>();
		
		int noOfRecordsInTable = totalRow();
		
		if(k<=noOfRecordsInTable){
		Float finalTotalInformationLoss = (float) 0.0;
		
        int noOfCluster=noOfRecordsInTable/k;//total=6  r=2  k = 3;
		
		Choice nClusterage1[]=new Choice[noOfCluster];
		Choice nClusterzip1[]=new Choice[noOfCluster];
		Choice nClustergen1[]=new Choice[noOfCluster];
		Choice nClusterms1[]=new Choice[noOfCluster];
		Choice nClusterrace1[]=new Choice[noOfCluster];
		for(int i=0;i<noOfCluster;i++)
	             {
			 nClusterage1[i]=new Choice();
			 nClusterzip1[i]=new Choice();
			 nClustergen1[i]=new Choice();
			 nClusterms1[i] = new Choice();
			 nClusterrace1[i] = new Choice();
	             }
		System.out.print("\n Total Cluster:"+noOfCluster);
		
		ResultSet rs = getAllRecords();		
		
		try {
//			ResultSet rs1 = rs;
			java.util.List<Integer> recordsTaken = new ArrayList<>();
			
			
			
		//maximum and minimum value of age	
			int maximum = 0,minimum = 0;
			
			
			int c = 0;
			while(rs.next()){
				c++;
				int age = Integer.parseInt(rs.getString(1));
				if(c==1){
					maximum = age;
					minimum = age;
				}else{
					 if(age>maximum){
						   maximum = age;
					   }
					   if(age<minimum){
						 minimum = age;
					   }
				}
			}			
			
			
			
			
			
			java.util.List<String> extraRecords = new ArrayList<>();
			int randomRecord=k/2;
			
			if(randomRecord>0){
				for(int i=0;i<noOfCluster;i++){
					if(i==0){
						System.out.println("IVALUE"+i);
						System.out.println("RANDOM REC0RD"+randomRecord);
						rs.absolute(randomRecord);
						recordsTaken.add(randomRecord);
						nClusterage1[i].add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5));
						nClusterzip1[i].add(annomize(rs.getString(3))+" "+rs.getString(5));
						nClustergen1[i].add("[PERSON] "+rs.getString(5));
						nClusterms1[i].add(mstitle+" "+rs.getString(5));
						nClusterrace1[i].add(racetitle+" "+rs.getString(5));
					}else{
						System.out.println("IVALUE"+i);
						System.out.println("RANDOM REC0RD"+randomRecord+(i*k));
						rs.absolute(randomRecord+(i*k));
						recordsTaken.add(randomRecord+(i*k));
					    nClusterage1[i].add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5));
						nClusterzip1[i].add(annomize(rs.getString(3))+" "+rs.getString(5));
						nClustergen1[i].add("[PERSON] "+rs.getString(5));
						nClusterms1[i].add(mstitle+" "+rs.getString(5));
						nClusterrace1[i].add(racetitle+" "+rs.getString(5));
					}
				}				
			}
			
			for(int j=1;j<=k;j++){
				java.util.List<Float> lowInforLoss = new ArrayList<Float>();
				if(j!=randomRecord){				
					rs.absolute(j);	
					recordsTaken.add(j);
					for(int i=0;i<noOfCluster;i++){
						String nextRecordToinsert = rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5);
//						Choice dummyCluster = nClusterage1[i];
						
						lowInforLoss.add(claculateInformationLoss(nClusterage1[i] ,nextRecordToinsert , k , maximum , minimum));
				}
					
					Float minInfls = (float) 0.0;
					
					int clusterNumber = 0;
					int count = 0;
					for(Float infloss : lowInforLoss){
						count ++;
						if(count==1){
							if(infloss!=null){
								clusterNumber = count;
								minInfls = infloss;
							}
						}else{
							if(infloss!=null){
								if(minInfls >infloss){
									clusterNumber = count;
									minInfls = infloss;
								}
							}
						}
						
						
					}
					
					if(clusterNumber!=0&&clusterNumber<=noOfCluster){
						if(nClusterage1[clusterNumber-1].getItemCount()<k){
							nClusterage1[clusterNumber-1].add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5));
							nClusterzip1[clusterNumber-1].add(annomize(rs.getString(3))+" "+rs.getString(5));
							nClustergen1[clusterNumber-1].add("[PERSON] "+rs.getString(5));
							 nClusterms1[clusterNumber-1].add(mstitle+" "+rs.getString(5));
							 nClusterrace1[clusterNumber-1].add(racetitle+" "+rs.getString(5));
						}else{
							extraRecords.add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5)+":"+annomize(rs.getString(3))+" "+rs.getString(5)+": [PERSON] "+rs.getString(5)+":"+mstitle+" "+rs.getString(5)+":"+racetitle+" "+rs.getString(5));
						}
						
						if(clusterNumber!=1){
							int ch = 1;
							for(int otherCluster=clusterNumber+1;otherCluster<=noOfCluster;otherCluster++){
								rs.absolute(j+(k * ch));
								recordsTaken.add(j+(k * ch));
								ch++;
								
								if(nClusterage1[otherCluster-1].getItemCount()<k){
									nClusterage1[otherCluster-1].add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5));
									nClusterzip1[otherCluster-1].add(annomize(rs.getString(3))+" "+rs.getString(5));
									nClustergen1[otherCluster-1].add("[PERSON] "+rs.getString(5));
									nClusterms1[otherCluster-1].add(mstitle+" "+rs.getString(5));
									nClusterrace1[otherCluster-1].add(racetitle+" "+rs.getString(5));
									
								}else{
									extraRecords.add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5)+":"+annomize(rs.getString(3))+" "+rs.getString(5)+": [PERSON] "+rs.getString(5)+":"+mstitle+" "+rs.getString(5)+":"+racetitle+" "+rs.getString(5));
								}
							}
							
							int remCluster = clusterNumber;
							int remainingRecCount = ch;
							if(remCluster>0){
								for(int remainingCluster=1;remainingCluster<=remCluster-1;remainingCluster++){
									rs.absolute(j+(k*(remainingRecCount)));
									recordsTaken.add(j+(k*(remainingRecCount)));
									remainingRecCount++;
									if(nClusterage1[remainingCluster-1].getItemCount()<k){
										nClusterage1[remainingCluster-1].add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5));
										nClusterzip1[remainingCluster-1].add(annomize(rs.getString(3))+" "+rs.getString(5));
										nClustergen1[remainingCluster-1].add("[PERSON] "+rs.getString(5));
										nClusterms1[remainingCluster-1].add(mstitle+" "+rs.getString(5));
										nClusterrace1[remainingCluster-1].add(racetitle+" "+rs.getString(5));
									}else{
										extraRecords.add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5)+":"+annomize(rs.getString(3))+" "+rs.getString(5)+": [PERSON] "+rs.getString(5)+":"+mstitle+" "+rs.getString(5)+":"+racetitle+" "+rs.getString(5));
									}
								}
								
							}							
						
					}else{
						
						for(int otherCluster=2;otherCluster<=noOfCluster;otherCluster++){
							
							rs.absolute(j+(k*(otherCluster-1)));
							recordsTaken.add(j+(k*(otherCluster-1)));
							if(nClusterage1[otherCluster-1].getItemCount()<k){
								nClusterage1[otherCluster-1].add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5));
								nClusterzip1[otherCluster-1].add(annomize(rs.getString(3))+" "+rs.getString(5));
								nClustergen1[otherCluster-1].add("[PERSON] "+rs.getString(5));
								nClusterms1[otherCluster-1].add(mstitle+" "+rs.getString(5));
								nClusterrace1[otherCluster-1].add(racetitle+" "+rs.getString(5));
							}else{
								
								extraRecords.add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5)+":"+annomize(rs.getString(3))+" "+rs.getString(5)+": [PERSON] "+rs.getString(5)+":"+mstitle+" "+rs.getString(5)+":"+racetitle+" "+rs.getString(5));

							}
						}
					}
						
					}
					
				}	
			}
			
			
			if((noOfCluster*k)!=noOfRecordsInTable){
				if(!recordsTaken.isEmpty()){
					for(int ne=1;ne<=noOfRecordsInTable;ne++){
						if(!recordsTaken.contains(ne)){
							rs.absolute(ne);
							extraRecords.add(rs.getString(2)+" "+annomize_Age(rs.getString(2))+" "+rs.getString(5)+":"+annomize(rs.getString(3))+" "+rs.getString(5)+": [PERSON] "+rs.getString(5)+":"+mstitle+" "+rs.getString(5)+":"+racetitle+" "+rs.getString(5));
						}
					}
				}
			}
			if(!extraRecords.isEmpty()){			
				
				java.util.List<Float> extralowInforLoss = new ArrayList<Float>();	
				
				
				for(int i=0;i<noOfCluster;i++){
				   extralowInforLoss.add(claculateInformationLoss(nClusterage1[i] ,null , k , maximum , minimum));
				}
				
				Iterator<String> itr = extraRecords.iterator();

				
				int updatedClusterNumber = 0;
				
				
				
				while(itr.hasNext()){
					String val = itr.next();
					String an[] = val.split(":");
					
					int exclusterNumber = 0;
					int excount = 0;
					Float exminInfls = (float) 0.0;
					
			
				
					for(Float infloss : extralowInforLoss){
						excount ++;
						if(excount==1){
							if(infloss!=null){
								exclusterNumber = excount;
								exminInfls = infloss;
							}
						}else{
							if(infloss!=null){
								if(exminInfls >infloss){
									exclusterNumber = excount;
									exminInfls = infloss;
								}
							}
						}
						
					}
					if(exclusterNumber>0){
						updatedClusterNumber = exclusterNumber-1;
						nClusterage1[updatedClusterNumber].add(an[0]);
						nClusterzip1[updatedClusterNumber].add(an[1]);
						nClustergen1[updatedClusterNumber].add(an[2]);
						nClusterms1[updatedClusterNumber].add(an[3]);
						nClusterrace1[updatedClusterNumber].add(an[3]);
						extralowInforLoss.set(updatedClusterNumber,claculateInformationLoss(nClusterage1[updatedClusterNumber] ,null , k , maximum , minimum));

					}
					
					
				}			
				
			}
			
	              java.util.List<Float> finalInforLoss = new ArrayList<Float>();	
					for(int i=0;i<noOfCluster;i++){
						finalInforLoss.add(claculateInformationLoss(nClusterage1[i] ,null , k , maximum , minimum));
					}
					
					if(!finalInforLoss.isEmpty()){
						for(Float infloss : finalInforLoss){
								if(infloss!=null){
									finalTotalInformationLoss = finalTotalInformationLoss + infloss;
								}						
						}
						informationloss = finalTotalInformationLoss;
					}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		lis.add(nClusterage1);
		lis.add(nClusterzip1);
		lis.add(nClustergen1);
		lis.add(nClusterms1);
		lis.add(nClusterrace1);
	}
		return lis;
		
	}
	
	
	public Float claculateInformationLoss(Choice nClusterage2, String nextRecordToinsert, int k , int maximum, int minimum){
		Float totalInformationLoss = (float) 0.0;
		try {
			if(nextRecordToinsert!=null){
				nClusterage2.add(nextRecordToinsert);
			}
			 float mMN = maximum-minimum;
			 float mmn = 0;
			 float ttl = 0;
			if(maximum!=0&&minimum!=0){
				
				
				if(nClusterage2.getItemCount()>1){
					int max =0 , min = 0;
					for(int j=0;j<nClusterage2.getItemCount();j++){
						String clstAge = nClusterage2.getItem(j);
						int val =  Integer.parseInt(clstAge.substring(0 , clstAge.indexOf(" ")));
						   if(j==0){
				      			 min = val;
				      			 max = val;
				      		   }else{
				      			 if(val<min){
				      				 min = val;
				      			 }
				      			 if(val>max){
				      				max = val; 
				      			 }
				      		   }
					}
					  if(mMN!=0){
			      		   mmn = max-min;
			      		   ttl = mmn/mMN;
			      		   totalInformationLoss += (k*(ttl+1+1+1+1))/10;
			      	   }
					
					
				}else{
					totalInformationLoss += (k*(ttl+1+1))/10;
				}
			}
			
			if(nextRecordToinsert!=null){
				nClusterage2.getItemCount();
				nClusterage2.remove(nClusterage2.getItemCount()-1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalInformationLoss;
	}	
	
	public ResultSet getAllRecords(){
		ResultSet rs = null;
		try {
			Connection con;
			Statement st;
			Class.forName(DBvendor.DB_VENDOR_NAME);
			con=DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String sql="select * from Patient_record order by pid";
			rs=st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public int totalRow()
	{
		int n=0;
		ResultSet rs;
		try{
			Statement st;
			Connection con;
			Class.forName(DBvendor.DB_VENDOR_NAME);
				con=DriverManager.getConnection(DBvendor.DB_URL,DBvendor.USER_NAME,DBvendor.PASSWORD);
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				String sql="select * from patient_record order by pid";
				rs=st.executeQuery(sql);
				if(rs.last())
				{
					n=rs.getRow();
					st.close();
					con.close();
					return(n);
				}
				con.close();
				st.close();
		}
	catch(Exception e5){System.out.print("\nError totalRow"+e5.getMessage());}
	return(0);
	}
	
	
}

/***********end****************/


 class DBvendor {	
	public static String DB_VENDOR_NAME = "com.mysql.jdbc.Driver";
	public static String USER_NAME="root";
	public static String PASSWORD="elephant";
//	public static String PASSWORD="admin";
	public static String DB_URL = "jdbc:mysql://localhost:3306/battadb?rewriteBatchedStatements=true&relaxAutoCommit=true";

}
