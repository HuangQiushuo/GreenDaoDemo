package com.anye.greendao.entity;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.TeacherDao;
import com.anye.greendao.gen.SubjectDao;

/**
 * Created by Administrator on 2016/11/23.
 */
@Entity
public class Teacher {
    @Id
    private Long id;
    private String name;
    @ToMany(referencedJoinProperty = "teacherId")
    private List<Subject> subjects;
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
    @Generated(hash = 1744012163)
    public synchronized void resetSubjects() {
        subjects = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1704598912)
    public List<Subject> getSubjects() {
        if (subjects == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SubjectDao targetDao = daoSession.getSubjectDao();
            List<Subject> subjectsNew = targetDao._queryTeacher_Subjects(id);
            synchronized (this) {
                if(subjects == null) {
                    subjects = subjectsNew;
                }
            }
        }
        return subjects;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1349174479)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeacherDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 648119699)
    private transient TeacherDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
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
    @Generated(hash = 1434396195)
    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
    }
}
