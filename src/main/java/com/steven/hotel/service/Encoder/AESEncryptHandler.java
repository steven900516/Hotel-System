package com.steven.hotel.service.Encoder;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shkstart
 * @create 2021-07-25 11:51
 */
public class AESEncryptHandler extends BaseTypeHandler {
    private final String key = "CPnb66hrzjgzKfGoTbXgndWG";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, AESEncoder.encrypt4Aes((String)parameter, key));
    }



    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return AESDecoder.decrypt4Aes2Str(columnValue, key);
    }
    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return AESDecoder.decrypt4Aes2Str(columnValue, key);
    }
    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return AESDecoder.decrypt4Aes2Str(columnValue, key);

    }

}
