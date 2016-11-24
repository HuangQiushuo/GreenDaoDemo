package com.anye.greendao.entity;

import org.greenrobot.greendao.annotation.*;

/**
 * Created by Administrator on 2016/11/23.
 */
@Entity
public class TakeSubject {
    @Id private Long id;
    private Long subjectId;
    private Long studentId;
    public Long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public Long getSubjectId() {
        return this.subjectId;
    }
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1025723516)
    public TakeSubject(Long id, Long subjectId, Long studentId) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }
    @Generated(hash = 168555322)
    public TakeSubject() {
    }
}
