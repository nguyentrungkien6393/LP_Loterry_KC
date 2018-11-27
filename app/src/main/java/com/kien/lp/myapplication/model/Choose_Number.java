package com.kien.lp.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class Choose_Number implements Serializable {
    String type_object_1;
    List<Number_1> mList1;
    List<Number_1>mList1_Completed;
    String type_object_2;
    List<Number_1> mList2;

    public Choose_Number(String type_object_1, List<Number_1> mList1, List<Number_1> mList1_Completed, String type_object_2, List<Number_1> mList2, List<Number_1> mList2_Completed) {
        this.type_object_1 = type_object_1;
        this.mList1 = mList1;
        this.mList1_Completed = mList1_Completed;
        this.type_object_2 = type_object_2;
        this.mList2 = mList2;
        this.mList2_Completed = mList2_Completed;
    }

    public List<Number_1> getmList1_Completed() {
        return mList1_Completed;
    }

    public void setmList1_Completed(List<Number_1> mList1_Completed) {
        this.mList1_Completed = mList1_Completed;
    }

    public List<Number_1> getmList2_Completed() {
        return mList2_Completed;
    }

    public void setmList2_Completed(List<Number_1> mList2_Completed) {
        this.mList2_Completed = mList2_Completed;
    }

    List<Number_1>mList2_Completed;

    public String getType_object_1() {
        return type_object_1;
    }

    public void setType_object_1(String type_object_1) {
        this.type_object_1 = type_object_1;
    }

    public List<Number_1> getmList1() {
        return mList1;
    }

    public void setmList1(List<Number_1> mList1) {
        this.mList1 = mList1;
    }

    public String getType_object_2() {
        return type_object_2;
    }

    public Choose_Number(String type_object_1, List<Number_1> mList1, String type_object_2, List<Number_1> mList2) {
        this.type_object_1 = type_object_1;
        this.mList1 = mList1;
        this.type_object_2 = type_object_2;
        this.mList2 = mList2;
    }

    public List<Number_1> getmList2() {

        return mList2;
    }

    public void setmList2(List<Number_1> mList2) {
        this.mList2 = mList2;
    }

    public void setType_object_2(String type_object_2) {
        this.type_object_2 = type_object_2;
    }


}
