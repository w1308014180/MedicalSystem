package com.example.medicalsystem.Bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

import javax.security.auth.Subject;

public class Json implements IPickerViewData {


    private String name;
    private List<SubjectBean> subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectBean> getSubjectList() {
        return subject;
    }

   /* public void setCityList(List<SubjectBean> subject) {
        this.subject = subject;
    }*/

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.name;
    }

    //科目
    public static class SubjectBean {

        private String name;
        //private List<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /*public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }*/
    }
}

