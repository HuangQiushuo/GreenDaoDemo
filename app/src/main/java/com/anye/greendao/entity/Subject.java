package com.anye.greendao.entity;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.SubjectDao;
import com.anye.greendao.gen.TeacherDao;
import com.anye.greendao.gen.StudentDao;

/**
 * Created by Administrator on 2016/11/23.
 */
@Entity
public class Subject {
    @Id
    private Long id;
    private String name;
    private Long teacherId;
    @ToOne(joinProperty = "teacherId")
    private Teacher teacher;
    @ToMany
    @JoinEntity(
            entity = TakeSubject.class,
            sourceProperty = "subjectId",
            targetProperty = "studentId"
    )
    private List<Student> students;
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 238993120)
    public synchronized void resetStudents() {
        students = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1209150692)
    public List<Student> getStudents() {
        if (students == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> studentsNew = targetDao._querySubject_Students(id);
            synchronized (this) {
                if(students == null) {
                    students = studentsNew;
                }
            }
        }
        return students;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1178808535)
    public void setTeacher(Teacher teacher) {
        synchronized (this) {
            this.teacher = teacher;
            teacherId = teacher == null ? null : teacher.getId();
            teacher__resolvedKey = teacherId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 127814275)
    public Teacher getTeacher() {
        Long __key = this.teacherId;
        if (teacher__resolvedKey == null || !teacher__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeacherDao targetDao = daoSession.getTeacherDao();
            Teacher teacherNew = targetDao.load(__key);
            synchronized (this) {
                teacher = teacherNew;
                teacher__resolvedKey = __key;
            }
        }
        return teacher;
    }
    @Generated(hash = 155140967)
    private transient Long teacher__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 937984622)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSubjectDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1644932788)
    private transient SubjectDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public Long getTeacherId() {
        return this.teacherId;
    }
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 877646235)
    public Subject(Long id, String name, Long teacherId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
    }
    @Generated(hash = 1617906264)
    public Subject() {
    }
}
