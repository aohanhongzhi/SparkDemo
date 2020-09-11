package hxy.sparkjava.demo.dao;

import hxy.sparkjava.demo.dao.model.UserModel;
import org.rex.DB;
import org.rex.db.Ps;
import org.rex.db.exception.DBException;

/**
 * @author eric
 */
public class UserDao {
    public static UserModel getUser(Integer id) {
        String sql = "select * from user_model where id = ?";
        Ps ps = new Ps();
        ps.add(id);
        try {
            UserModel userModel = DB.get(sql, ps, UserModel.class);
            return userModel;
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
