package rti;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import oracle.jdbc.OracleConnection;

public class Example {

	
	public static void main(String[] args) throws SQLException {
		
		
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = XE)))", "c##noklin", "1234")){
			JdbcTemplate tmpl = new JdbcTemplate(new SingleConnectionDataSource(conn, false));
			tmpl.execute(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					
					
					CallableStatement cs = con.prepareCall("{call add_info(?)}");
					ArrayList<Info> data = new ArrayList<>(Arrays.asList(new Info(),new Info(),new Info(),new Info(),new Info()));
					
					
					OracleConnection oracleConnection = con.unwrap(OracleConnection.class);
				    Array array = oracleConnection.createOracleArray("BD_TB_INFO_ARRAY", data.toArray());
					cs.setObject(1, new InfoArray(array));
					
			        return cs;
				}
			}, new CallableStatementCallback<Void>() {
				@Override
				public Void doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					cs.execute();
					return null;
				}
			});
		}
	}
	
}