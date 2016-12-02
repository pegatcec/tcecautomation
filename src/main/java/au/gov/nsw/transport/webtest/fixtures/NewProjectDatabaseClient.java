package au.gov.nsw.transport.webtest.fixtures;

import java.io.IOException;
import java.sql.*;

/**
 * User: GumpuR
 */
public class NewProjectDatabaseClient {

    TestDatabaseConfig testDatabaseConfig;

    public NewProjectDatabaseClient() {
        try {
            this.testDatabaseConfig = new TestDatabaseConfig();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public int getProjectCount() throws ClassNotFoundException, SQLException {

        Connection connection = getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM PROJECT");
        rs.next();
        int count = rs.getInt(1);
        connection.close();

        return count;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        String host = testDatabaseConfig.getHost();
        String port = testDatabaseConfig.getPort();
        String sid = testDatabaseConfig.getSid();
        String user = testDatabaseConfig.getUser();
        String password = testDatabaseConfig.getPassword();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid, user, password);
    }

    public void deleteProjectWithName(String projectName) {

        deleteWithName("delete from PROJECT_PIDS where PROJECT_PIDS.PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
//        deleteWithName("delete from PROJECT_AGENCY where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
//        deleteWithName("delete from PROJECT_ASSET_OWNER_ID where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
//        deleteWithName("delete from PROJECT_CORE_BUSINESS_FRAMEWORK where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_SPONSOR_AGENCY where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
//        deleteWithName("delete from PROJECT_DELIVERY_AGENCY where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_STATE_ELECTORATE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
//        deleteWithName("delete from PROJECT_EXPENDITURE_TYPE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_PROJECT_TYPE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_FUNDING_SOURCE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from SCENARIO_PROJECT where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_LOCAL_GOV_AREA where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);


//        deleteWithName("DELETE from exp_profile_funding_source where funding_source = '1'", "");
//        deleteWithName("DELETE from exp_profile_funding_source where funding_type = '1'", "");
//        deleteWithName("DELETE from exp_profile_funding_source where funded = '1'", "");

        deleteEP("DELETE from EXP_PROFILE_FUNDING_SOURCE where FUNDING_SOURCE IN (SELECT FUNDING_SOURCE FROM exp_profile_funding_source WHERE FUNDING_SOURCE = ?)", "1");
//        deleteWithName("delete from EXP_PROFILE_AUD where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from EXP_PROFILE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_PROJECT_TYPE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from MEMBER where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_CONTRACT where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from GATE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);
        deleteWithName("delete from PROJECT_MAINTENANCE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)", projectName);

        deleteWithName("DELETE FROM PROJECT_EXP_PROFILE_SEQUENCE WHERE project IN (select project from project where project_name = ?)", projectName);
        deleteWithName("DELETE FROM PROJECT WHERE PROJECT_NAME = ?", projectName);
    }

    public void deleteWithName(String query, String projectName) {
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, projectName);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            throw new IllegalStateException(sqle);
        } catch (ClassNotFoundException cnf) {
            throw new IllegalStateException(cnf);
        }
    }

    public void deleteEP(String query, String EP) {
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, EP);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            throw new IllegalStateException(sqle);
        } catch (ClassNotFoundException cnf) {
            throw new IllegalStateException(cnf);
        }
    }

    /*
delete from PROJECT_PIDS where PROJECT_PIDS.PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
delete from PROJECT_SPONSOR_AGENCY where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
delete from PROJECT_DELIVERY_AGENCY where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
delete from PROJECT_LOCAL_GOV_AREA where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
delete from PROJECT_STATE_ELECTORATE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
delete from PROJECT_EXPENDITURE_TYPE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
delete from PROJECT_PROJECT_TYPE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
delete from PROJECT_FUNDING_SOURCE where PROJECT IN (SELECT PROJECT FROM PROJECT WHERE PROJECT_NAME = ?)
     */

    public int countProjectsWithName(String projectName) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        String query = "SELECT COUNT(*) FROM PROJECT WHERE PROJECT_NAME = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, projectName);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        connection.close();

        return count;
    }
}
