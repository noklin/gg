package rti;

import java.sql.Date;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Info implements SQLData{

	private String description = "description " + System.currentTimeMillis();
	private int infoLevel = (int)(System.currentTimeMillis() % 2);
	private Date createDate = new Date(System.currentTimeMillis());
	
	@Override
	public String getSQLTypeName() throws SQLException {
		return "BD_TB_INFO";
	}
	
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
	}
	
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeNString(description);
		stream.writeInt(infoLevel);
		stream.writeDate(createDate);
	}
}