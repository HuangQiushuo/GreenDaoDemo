package com.anye.greendao.gen;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.anye.greendao.entity.Teacher;

import com.anye.greendao.entity.Subject;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SUBJECT".
*/
public class SubjectDao extends AbstractDao<Subject, Long> {

    public static final String TABLENAME = "SUBJECT";

    /**
     * Properties of entity Subject.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property TeacherId = new Property(2, Long.class, "teacherId", false, "TEACHER_ID");
    };

    private DaoSession daoSession;

    private Query<Subject> teacher_SubjectsQuery;

    public SubjectDao(DaoConfig config) {
        super(config);
    }
    
    public SubjectDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SUBJECT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"TEACHER_ID\" INTEGER);"); // 2: teacherId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SUBJECT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Subject entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long teacherId = entity.getTeacherId();
        if (teacherId != null) {
            stmt.bindLong(3, teacherId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Subject entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long teacherId = entity.getTeacherId();
        if (teacherId != null) {
            stmt.bindLong(3, teacherId);
        }
    }

    @Override
    protected final void attachEntity(Subject entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Subject readEntity(Cursor cursor, int offset) {
        Subject entity = new Subject( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // teacherId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Subject entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTeacherId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Subject entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Subject entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "subjects" to-many relationship of Teacher. */
    public List<Subject> _queryTeacher_Subjects(Long teacherId) {
        synchronized (this) {
            if (teacher_SubjectsQuery == null) {
                QueryBuilder<Subject> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TeacherId.eq(null));
                teacher_SubjectsQuery = queryBuilder.build();
            }
        }
        Query<Subject> query = teacher_SubjectsQuery.forCurrentThread();
        query.setParameter(0, teacherId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getTeacherDao().getAllColumns());
            builder.append(" FROM SUBJECT T");
            builder.append(" LEFT JOIN TEACHER T0 ON T.\"TEACHER_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Subject loadCurrentDeep(Cursor cursor, boolean lock) {
        Subject entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Teacher teacher = loadCurrentOther(daoSession.getTeacherDao(), cursor, offset);
        entity.setTeacher(teacher);

        return entity;    
    }

    public Subject loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Subject> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Subject> list = new ArrayList<Subject>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Subject> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Subject> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}