package com.eyunhome.appframe.exception;

/**
 * 数据库异常
 */
public class DbException extends QkException {
	private static final long serialVersionUID = 1L;
	
	public DbException() {}
	
	
	public DbException(String msg) {
		super(msg);
	}
	
	public DbException(Throwable ex) {
		super(ex);
	}
	
	public DbException(String msg,Throwable ex) {
		super(msg,ex);
	}
	
}
