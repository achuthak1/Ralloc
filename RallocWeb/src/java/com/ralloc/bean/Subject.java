/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralloc.bean;

/**
 *
 * @author Sneha
 */
public class Subject {
    private String Code, Name;
    int TestID;
    public Subject()
    {
        this.Code = "";
        this.Name = "";
        this.TestID = 0;
    }
    public void setCode(String Code)
    {
        this.Code = Code;
        
    }
    public void setName(String Name)
    {
        this.Name = Name;
        
    }
    
    public void setID(int ID)
    {
        this.TestID = ID;
        
    }
    public String getCode()
    {
        return Code;
    }
    public String getName()
    {
        return Name;
    }
    public int getID()
    {
        return TestID;
    }
}
