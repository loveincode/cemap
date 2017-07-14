package com.hyf.cemap.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class WebSessionListener implements HttpSessionListener,HttpSessionAttributeListener,ServletRequestListener{

    
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    
    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void requestInitialized(ServletRequestEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    
    
    
    
    
}
