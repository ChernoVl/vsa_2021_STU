/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author igor
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjektTest {

    private static ResultSet rs = null;
    private static String db = "jdbc:derby://localhost:1527/sample";

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
    }

    @Test
    public void UT10_NovaKniha() {
        prepareTables();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.bookUpdate("N0", "nova", 20.0);

        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(true, r);
    }

    @Test
    public void UT11_NovaKnihaCountCheck() {
        long cnt = -1;
        String nazov = null;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(3, cnt);
    }

    @Test
    public void UT12_NovaKnihaCheck() {
        String nazov = null;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st2 = con.createStatement();
            rs = st2.executeQuery("SELECT NAME, PRICE FROM BOOK WHERE ISBN='N0'");
            rs.next();
            nazov = rs.getString("NAME");
            cena = rs.getDouble("PRICE");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }
        assertEquals("nova", nazov);
        assertEquals(20.0, cena, 0.001);
    }

    @Test
    public void UT20_NovaCena() {
        prepareTables();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.bookUpdate("K00", "stara", 30.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(true, r);
    }

    @Test
    public void UT21_NovaCenaCheck() {
        long cnt = -1;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

            Statement st2 = con.createStatement();
            rs = st2.executeQuery("SELECT PRICE FROM BOOK WHERE ISBN='K00'");
            rs.next();
            cena = rs.getDouble("PRICE");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(2, cnt);
        assertEquals(30.0, cena, 0.001);
    }

    @Test
    public void UT30_NovyNazovCena() {
        prepareTables();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.bookUpdate("K01", "kniha1", 30.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(true, r);
    }

    @Test
    public void UT31_NovyNazovCenaCheck() {
        long cnt = -1;
        String nazov = null;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

            Statement st2 = con.createStatement();
            rs = st2.executeQuery("SELECT NAME, PRICE FROM BOOK WHERE ISBN='K01'");
            rs.next();
            nazov = rs.getString("NAME");
            cena = rs.getDouble("PRICE");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(2, cnt);
        assertEquals("kniha1", nazov);
        assertEquals(30.0, cena, 0.001);
    }

    @Test
    public void UT40_NespravnyNazov() {
        prepareTables();
        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.bookUpdate("K00", "zly nazov", 30.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(false, r);
    }

    @Test
    public void UT41_NespravnyNazovCheck() {
        long cnt = -1;
        String nazov = null;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

            Statement st2 = con.createStatement();
            rs = st2.executeQuery("SELECT NAME, PRICE FROM BOOK WHERE ISBN='K00'");
            rs.next();
            nazov = rs.getString("NAME");
            cena = rs.getDouble("PRICE");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(2, cnt);
        assertEquals("stara", nazov);
        assertEquals(10.0, cena, 0.001);
    }

    @Test
    public void UT50_ZiadneUdaje() {

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.bookUpdate("K01", null, null);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
//        assertEquals(true, r);

        double cena = Double.NaN;

        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT PRICE FROM BOOK WHERE ISBN='K01'");
            rs.next();
            cena = rs.getDouble("PRICE");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        // ziadne zmeny
        assertEquals(10.0, cena, 0.001);
    }

    @Test
    public void UT60_NovyCennik() {
        prepareTables2();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            Map<String, Double> cennik = new HashMap<>();
            cennik.put("K11", 20.0);
            cennik.put("K12", 30.0);
            proj.priceListUpdate(cennik);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
    }

    @Test
    public void UT61_NovyCennikCountCheck() {
        long cnt = -1;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }
        assertEquals(3, cnt);

        String nazov = null;
        double c0 = Double.NaN;
        double c1 = Double.NaN;
        double c2 = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT PRICE FROM BOOK WHERE ISBN='K10'");
            rs.next();
            c0 = rs.getDouble("PRICE");
            st = con.createStatement();
            rs = st.executeQuery("SELECT PRICE FROM BOOK WHERE ISBN='K11'");
            rs.next();
            c1 = rs.getDouble("PRICE");
            st = con.createStatement();
            rs = st.executeQuery("SELECT PRICE FROM BOOK WHERE ISBN='K12'");
            rs.next();
            c2 = rs.getDouble("PRICE");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(10.0, c0, 0.001);
        assertEquals(20.0, c1, 0.001);
        assertEquals(30.0, c2, 0.001);
    }

    @Test
    public void UT70_DalsiCennik() {
        prepareTables2();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            Map<String, Double> cennik = new HashMap<>();
            cennik.put("K10", 20.0);    // stara kniha s nazvom
            cennik.put("N1", 20.0);    // nova kniha bez nazvu
            proj.priceListUpdate(cennik);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }

        long cnt = -1;
        String nazov = null;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(4, cnt);
    }

    @Test
    public void UT71_NovaKnihaCheck() {
        long cnt = -1;
        String nazov = null;
        double c = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

            st = con.createStatement();
            rs = st.executeQuery("SELECT PRICE, NAME FROM BOOK WHERE ISBN='N1'");
            rs.next();
            c = rs.getDouble("PRICE");
            nazov = rs.getString("NAME");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(20.0, c, 0.001);
        assertNull(nazov);
    }

    @Test
    public void UT72_StaraKnihaCheck() {
        long cnt = -1;
        String nazov = null;
        double c = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

            st = con.createStatement();
            rs = st.executeQuery("SELECT PRICE, NAME FROM BOOK WHERE ISBN='K10'");
            rs.next();
            c = rs.getDouble("PRICE");
            nazov = rs.getString("NAME");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(20.0, c, 0.001);
        assertEquals("stara", nazov);
    }

    @Test
    public void UT80_ZiadneKnihy() {
        prepareEmptyTables();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            Map<String, Double> cennik = new HashMap<>();
            cennik.put("N2", 20.0);    // nova kniha bez nazvu
            proj.priceListUpdate(cennik);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }

        long cnt = -1;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM BOOK");
            rs.next();
            cnt = rs.getLong(1);

        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(1, cnt);
    }

    @Test
    public void UT81_NullCennik() {
        try {
            Projekt proj = new Projekt();
            proj.priceListUpdate(null);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
    }

    static private void prepareEmptyTables() {
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            dropTables(st);
            st.executeUpdate(CreatePB);
            con.commit();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjektTest.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    static private void prepareTables() {
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            dropTables(st);
            st.executeUpdate(CreatePB);
            st.executeUpdate("INSERT INTO BOOK (ISBN, PRICE, NAME) VALUES ('K00', 10.0, 'stara' )");
            st.executeUpdate("INSERT INTO BOOK (ISBN, PRICE, NAME) VALUES ('K01', 10.0, NULL )");
            con.commit();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjektTest.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    static private void prepareTables2() {
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            dropTables(st);
            st.executeUpdate(CreatePB);
            st.executeUpdate("INSERT INTO BOOK (ISBN, PRICE, NAME) VALUES ('K10', 10.0, 'stara' )");
            st.executeUpdate("INSERT INTO BOOK (ISBN, PRICE, NAME) VALUES ('K11', 10.0, NULL )");
            st.executeUpdate("INSERT INTO BOOK (ISBN, PRICE, NAME) VALUES ('K12', 10.0, NULL )");
            con.commit();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjektTest.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    static String CreatePB
            = "CREATE TABLE BOOK ( "
            + "ISBN VARCHAR(80) CONSTRAINT BOOK_PK PRIMARY KEY, "
            + "NAME VARCHAR(80),"
            + "PRICE DOUBLE"
            + ")";

    static private void dropTables(Statement st) {
        try {
            st.executeUpdate("DROP TABLE BOOK");
        } catch (SQLException ex) {
        }
    }

}
