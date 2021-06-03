package com.example.medicalsystem.Bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Record {
    @PrimaryKey(autoGenerate =  true)
    public int recordId;

    @ColumnInfo(name = "record_content")
    public String recordContent;

    @ColumnInfo(name = "record_patient_id")
    public int recordPatientId;

    @ColumnInfo(name = "record_time")
    public String recordTime;

    @ColumnInfo(name = "record_doctor_id")
    public int recordDoctorId;

    @ColumnInfo(name = "record_doctor_name")
    public String recordDoctorName;

    public Record(String recordContent, int recordPatientId, String recordTime, int recordDoctorId, String recordDoctorName){
        this.recordContent = recordContent;
        this.recordPatientId = recordPatientId;
        this.recordTime = recordTime;
        this.recordDoctorId = recordDoctorId;
        this.recordDoctorName = recordDoctorName;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public int getRecordPatientId() {
        return recordPatientId;
    }

    public void setRecordPatientId(int recordPatientId) {
        this.recordPatientId = recordPatientId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public int getRecordDoctorId() {
        return recordDoctorId;
    }

    public void setRecordDoctorId(int recordDoctorId) {
        this.recordDoctorId = recordDoctorId;
    }

    public String getRecordDoctorName() {
        return recordDoctorName;
    }

    public void setRecordDoctorName(String recordDoctorName) {
        this.recordDoctorName = recordDoctorName;
    }
}
