
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedago
 */
public class DAOextends extends DAO {
    
    public DAOextends(DataSource dataSource) {
        super(dataSource);
    }
    
    public List<String> listOfState() throws DAOException{
            List<String> states = new ArrayList<>();
            
            String sql = "SELECT DISTINCT(STATE) FROM CUSTOMER";
            
            try( Connection connection = myDataSource.getConnection();
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)
                ){
                
                while(rs.next()){
                    states.add(rs.getString("STATE"));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException(ex.getMessage());
            }
            
            
            
            return states;
        }
    
}
