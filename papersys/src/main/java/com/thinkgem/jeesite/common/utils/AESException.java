package com.thinkgem.jeesite.common.utils;

/**
 * AESException
 * 
 * @author gaozy
 * @version 1.0
 * @since 2016-01-07
 */
public class AESException extends Exception
{
    static final long serialVersionUID = 5850243503337783048L;
   
    public AESException(String msg)
    {
        super(msg);
    }
    
    public AESException(Throwable t)
    {
        super(t);
    }
    
    public AESException(String msg, Throwable t) {
        super(msg, t);
    }
}