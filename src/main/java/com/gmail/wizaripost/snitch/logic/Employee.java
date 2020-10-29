package com.gmail.wizaripost.snitch.logic;

public class Employee {
    private boolean status;
    private String name;
    private String groupId;


    public Employee() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Employee(boolean status, String name, String groupId) {
        this.status = status;
        this.name = name;
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name + ";\n";
//        status + " " ++ "\n"
//        return "[ Писатель " + name +
//                ", id = " + id +
////                ", Owner=" + owner +
////                ", age=" + age +
////                ", name =  " + name + '\'' +
//                " ]"+ "\n" ;
    }
}
