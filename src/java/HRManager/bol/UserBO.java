/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.bol;

import HRManager.dal.DAO;
import HRManager.entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBO {

    public boolean authorization(User u) {
        String sql = "select * from users where username=? and userpassword=?";
        DAO dao = new DAO();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps= dao.getConnection().prepareStatement(sql);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getUserPassword());
            rs = dao.executeQuery(ps);
            if (!rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
}
