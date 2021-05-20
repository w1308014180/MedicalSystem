package com.example.medicalsystem.Bean;

public class Doctor {
    public int DoctorId;
    public String DoctorName;
    public int DoctorAge;
    //职称
    public String JobTile;
    public int JobTileId;
    //科室
    public int DepartmentId;
    public String DepartmentName;
    //科目
    public int SubjectId;
    public String SubjectName;

    public int sex;

    public Doctor(String DoctorName, String DepartmentName , String SubjectName, String JobTitle){
        this.DoctorName = DoctorName;
        this.DepartmentName = DepartmentName;
        this.SubjectName = SubjectName;
        this.JobTile = JobTitle;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getJobTile() {
        return JobTile;
    }

    public void setJobTile(String jobTile) {
        JobTile = jobTile;
    }
}
