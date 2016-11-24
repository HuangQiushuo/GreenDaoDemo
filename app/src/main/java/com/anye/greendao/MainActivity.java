package com.anye.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.anye.greendao.entity.Student;
import com.anye.greendao.entity.Subject;
import com.anye.greendao.entity.TakeSubject;
import com.anye.greendao.entity.Teacher;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.StudentDao;
import com.anye.greendao.gen.SubjectDao;
import com.anye.greendao.gen.TeacherDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAdd,mDelete,mUpdate,mFind;
    private ListView listView;
    private List<Map<String,String>> lines = new ArrayList<Map<String,String>>();
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private SubjectDao subjectDao;
    private DaoSession daoSession;

    private StudentDao mStudentDao;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        daoSession = MyApplication.getInstances().getDaoSession();
        subjectDao = daoSession.getSubjectDao();
        initListView();
        updateListView(subjectDao.loadAll());
    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFind.setOnClickListener(this);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        mAdd = (Button) findViewById(R.id.addButton);
        mDelete = (Button) findViewById(R.id.deleteButton);
        mUpdate = (Button) findViewById(R.id.changeButton);
        mFind = (Button) findViewById(R.id.searchButton);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                addDate();
                break;
            case R.id.deleteButton:
                deleteDate();
                break;
            case R.id.changeButton:
                updateDate();
                break;
            case R.id.searchButton:
                findDate();
                break;
        }
    }

    private void initListView(){

        lines = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(listView.getContext(),lines,R.layout.subject_line,
                new String[]{"subject","teacher","student"},
                new int[]{R.id.subjecTextView,R.id.teacherTextView,R.id.studentTextView});
        listView.setAdapter(adapter);
    }

    public void updateListView(List<Subject> subjects){

        lines.clear();
        for(Subject subject:subjects){
            List<Student> students = subject.getStudents();
            for(Student student:students){
                Map<String, String> map = new HashMap<String, String>();
                map.put("subject", subject.getName());
                map.put("teacher", subject.getTeacher().getName());
                map.put("student", student.getName());
                lines.add(map);
            }
        }
        SimpleAdapter adapter = (SimpleAdapter)listView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initData(){
        Student student1 = new Student((long)1, "student1", "male");
        Student student2 = new Student((long)2, "student2", "male");
        Student student3 = new Student((long)3, "student3", "male");
        Student student4 = new Student((long)4, "student4", "male");
        Teacher teacher1 = new Teacher((long)1, "teacher1");
        Teacher teacher2 = new Teacher((long)2, "teacher2");

        daoSession.insert(student1);
        daoSession.insert(student2);
        daoSession.insert(student3);
        daoSession.insert(student4);
        daoSession.insert(teacher1);
        daoSession.insert(teacher2);

        Subject subject1 = new Subject((long)1, "KT", (long)1);
        Subject subject2 = new Subject((long)2, "DP", (long)1);
        Subject subject3 = new Subject((long)3, "DS", (long)2);
        subject1.__setDaoSession(daoSession);
        subject2.__setDaoSession(daoSession);
        subject3.__setDaoSession(daoSession);
        subject1.getStudents().add(student1);
        subject1.getStudents().add(student2);
        subject2.getStudents().add(student3);
        subject3.getStudents().add(student4);

        daoSession.insert(subject1);
        daoSession.insert(subject2);
        daoSession.insert(subject3);
    }

    /**
     * 增加数据
     */
    private void addDate() {
//        mUser = new User((long)id,"anye3");
//        mUserDao.insert(mUser);//添加一个
//        mContext.setText(mUser.getName());
        initData();
        updateListView( subjectDao.loadAll());
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        daoSession.deleteAll(Subject.class);
        daoSession.deleteAll(Student.class);
        daoSession.deleteAll(Teacher.class);
        initView();
    }

    private void query(){
    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteUserById(long id) {

    }

    /**
     * 更改数据
     */
    private void updateDate() {
        QueryBuilder qb = subjectDao.queryBuilder();
        List<Subject> subjects = qb.where(SubjectDao.Properties.TeacherId.eq(1)).list();
        for(Subject subject:subjects){
            subject.setTeacherId((long)2);
            subjectDao.update(subject);
        }
        updateListView(subjectDao.loadAll());
    }

    /**
     * 查找数据
     */
    private void findDate() {
        QueryBuilder qb = subjectDao.queryBuilder();
        List<Subject> subjects = qb.where(SubjectDao.Properties.TeacherId.eq(1)).list();
        updateListView(subjects);
    }
}
