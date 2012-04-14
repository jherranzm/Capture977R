/**
 * 
 */
package telefonica.aaee.webutils;

/**
 * @author t130796
 *
 */
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

public class CachedRowSetFactory
{
    public static CachedRowSet getCachedRowSet()
        throws SQLException
    {
        try
        {
            return (CachedRowSet) Class
                .forName("com.sun.rowset.CachedRowSetImpl").newInstance();
        }
        catch (Exception e)
        {
            throw new SQLException("No se pudo crear la clase CachedRowSet", e);
        }
    }
}
