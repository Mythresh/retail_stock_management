package enterprise.web.jdbc;


import enterprise.web.jdbc.items;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mythr
 */
public class itemdb {
    
    public static List<items> getitems(String cati) throws ClassNotFoundException, SQLException{
        String cat=cati;
         List<items> item=new ArrayList<>();
        try {
           
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/enterprise?useSSL=false", "root", "root");
            String qur="select * from items where  category=?";
            PreparedStatement pst=con.prepareStatement(qur);
            pst.setString(1, cat);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
            int itid=rs.getInt(1);
            String ite=rs.getString(2);
            int quan=rs.getInt(3);
            items itm=new items(itid,quan,ite);
            item.add(itm);
            }
            pst.close();
            rs.close();
            con.close();
            
            
            return item;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(itemdb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    
}

  
    
}
