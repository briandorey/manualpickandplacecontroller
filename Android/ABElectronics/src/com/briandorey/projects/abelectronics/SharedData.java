package com.briandorey.projects.abelectronics;

import android.app.Application;

public class SharedData extends Application {
    private String mStringValue;
    private Boolean mConnectionOK;
    @Override
    public void onCreate() {
        mStringValue = "0";
        mConnectionOK = false;
        super.onCreate();
    }
 
    public String getStringValue() {
        return mStringValue;
    }
 
    public void setStringValue(String value) {
        mStringValue = value;
    }
    
    public Boolean getConnectionStatus() {
        return mConnectionOK;
    }
 
    public void setConnectionStatus(Boolean value) {
    	mConnectionOK = value;
    }
}