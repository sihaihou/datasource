package com.reyco.order.core.datasources.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@author reyco
*@date  2021年3月9日---上午10:29:38
*<pre>
*
*<pre> 
*/
@SuppressWarnings("all")
public class ConnectionWrapper extends ConnectionAdapter {
	
	protected static Logger logger = LoggerFactory.getLogger(ConnectionWrapper.class);
	
	private Connection connection;
	
	private volatile ReycoDataSource reycoDataSource;
	
	public ConnectionWrapper() {
		// TODO Auto-generated constructor stub
	}
	public ConnectionWrapper(Connection connection, ReycoDataSource reycoDataSource) {
		super();
		this.connection = connection;
		this.reycoDataSource = reycoDataSource;
	}
	@Override
	public void close() throws SQLException {
		LinkedList<Connection> pool = reycoDataSource.getPool();
		Integer maxActive = reycoDataSource.getMaxActive();
		//
		if(pool.size()<maxActive) {
			synchronized (this) {
				if(pool.size()<maxActive) {
					logger.debug("回收连接");
					pool.add(connection);
				}else {
					logger.debug("关闭连接");
					connection.close();
				}
			}
		}else {
			logger.debug("关闭连接");
			connection.close();
		}
	}
	@Override
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}
	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
}
