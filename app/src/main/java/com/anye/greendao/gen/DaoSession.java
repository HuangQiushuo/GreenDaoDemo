package com.anye.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.anye.greendao.entity.Student;
import com.anye.greendao.entity.Subject;
import com.anye.greendao.entity.Teacher;
import com.anye.greendao.entity.TakeSubject;

import com.anye.greendao.gen.StudentDao;
import com.anye.greendao.gen.SubjectDao;
import com.anye.greendao.gen.TeacherDao;
import com.anye.greendao.gen.TakeSubjectDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig subjectDaoConfig;
    private final DaoConfig teacherDaoConfig;
    private final DaoConfig takeSubjectDaoConfig;

    private final StudentDao studentDao;
    private final SubjectDao subjectDao;
    private final TeacherDao teacherDao;
    private final TakeSubjectDao takeSubjectDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        subjectDaoConfig = daoConfigMap.get(SubjectDao.class).clone();
        subjectDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        takeSubjectDaoConfig = daoConfigMap.get(TakeSubjectDao.class).clone();
        takeSubjectDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        subjectDao = new SubjectDao(subjectDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);
        takeSubjectDao = new TakeSubjectDao(takeSubjectDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(Subject.class, subjectDao);
        registerDao(Teacher.class, teacherDao);
        registerDao(TakeSubject.class, takeSubjectDao);
    }
    
    public void clear() {
        studentDaoConfig.getIdentityScope().clear();
        subjectDaoConfig.getIdentityScope().clear();
        teacherDaoConfig.getIdentityScope().clear();
        takeSubjectDaoConfig.getIdentityScope().clear();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public SubjectDao getSubjectDao() {
        return subjectDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public TakeSubjectDao getTakeSubjectDao() {
        return takeSubjectDao;
    }

}
