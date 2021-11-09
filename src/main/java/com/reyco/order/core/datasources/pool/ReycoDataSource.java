package com.reyco.order.core.datasources.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author reyco
 * @date 2021年3月8日---下午2:29:40
 * 
 *       <pre>
 *
 *       <pre>
 */
public class ReycoDataSource implements DataSource {

	protected static Logger logger = LoggerFactory.getLogger(ReycoDataSource.class);

	private String dirverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/test";
	private String username = "root";
	private String password = "123456";
	/**
	 * 最大连接
	 */
	private Integer maxActive = 20;
	/**
	 * 最新连接
	 */
	private Integer minIdle = 1;
	/**
	 * 初始化大小
	 */
	private Integer initialSize = 1;

	private static LinkedList<Connection> pool = new LinkedList<>();
	
	private static LinkedList<Connection> poolActive = new LinkedList<>();
	
	/**
	 * 初始化
	 */
	public void init() {
		while(pool.size() < initialSize || pool.size() < minIdle) {
			try {
				Connection connection =  getConnection(username, password);
				pool.add(connection);
				poolActive.add(connection);
				logger.debug("初始化连接");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public Connection getConnection() throws SQLException {
		ConnectionWrapper connectionWrapper = null;
		Connection connection = null;
		synchronized (pool) {
			if (pool.size() > 0) {
				connection = pool.removeFirst();
				logger.debug("获取连接");
			} else {
				 connection = getConnection(username,password);
			}
		}
		connectionWrapper = new ConnectionWrapper(connection,this);
		return connectionWrapper;
	}
	
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		try {
			Class.forName(dirverClassName);
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			logger.error("找不到驱动类！", e);
		}finally {
			logger.debug("创建连接");
		}
		return null;
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
	
	public String getDirverClassName() {
		return dirverClassName;
	}
	public void setDirverClassName(String dirverClassName) {
		this.dirverClassName = dirverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}
	public Integer getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}
	public Integer getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}
	public static Logger getLogger() {
		return logger;
	}
	public static LinkedList<Connection> getPool() {
		return pool;
	}
	public static void setPool(LinkedList<Connection> pool) {
		ReycoDataSource.pool = pool;
	}
	
	
}
