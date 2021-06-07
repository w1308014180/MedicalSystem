package com.example.medicalsystem.Bean;

import java.util.List;

public class DoctorSchedule {
        private int code;
        private String msg;
        private List<data> data;

        public class data{
            private int doctor_id;
            private String doctor_name;
            private String job_title;
            private int job_title_id;
            private String department_name;
            private int department_id;
            private String subject_name;
            private int subject_id;
            private String sex;

            public int getDoctor_id() {
                return doctor_id;
            }

            public void setDoctor_id(int doctor_id) {
                this.doctor_id = doctor_id;
            }

            public String getDoctor_name() {
                return doctor_name;
            }

            public void setDoctor_name(String doctor_name) {
                this.doctor_name = doctor_name;
            }

            public String getJob_title() {
                return job_title;
            }

            public void setJob_title(String job_title) {
                this.job_title = job_title;
            }

            public int getJob_title_id() {
                return job_title_id;
            }

            public void setJob_title_id(int job_title_id) {
                this.job_title_id = job_title_id;
            }

            public String getDepartment_name() {
                return department_name;
            }

            public void setDepartment_name(String department_name) {
                this.department_name = department_name;
            }

            public int getDepartment_id() {
                return department_id;
            }

            public void setDepartment_id(int department_id) {
                this.department_id = department_id;
            }

            public String getSubject_name() {
                return subject_name;
            }

            public void setSubject_name(String subject_name) {
                this.subject_name = subject_name;
            }

            public int getSubject_id() {
                return subject_id;
            }

            public void setSubject_id(int subject_id) {
                this.subject_id = subject_id;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<com.example.medicalsystem.Bean.DoctorSchedule.data> getData() {
            return data;
        }

        public void setData(List<com.example.medicalsystem.Bean.DoctorSchedule.data> data) {
            this.data = data;
        }

}
