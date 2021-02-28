package rti;

import java.sql.Array;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class InfoArray implements SQLData{

	private final Array data;
	public InfoArray(Array data) {
		this.data = data;
	}
	
	@Override
	public String getSQLTypeName() throws SQLException {
		return "INFO_DATA";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
	    stream.writeArray(data);
	}

}