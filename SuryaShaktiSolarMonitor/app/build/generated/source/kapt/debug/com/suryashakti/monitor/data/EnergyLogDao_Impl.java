package com.suryashakti.monitor.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EnergyLogDao_Impl implements EnergyLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EnergyLog> __insertionAdapterOfEnergyLog;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public EnergyLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEnergyLog = new EntityInsertionAdapter<EnergyLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `energy_logs` (`id`,`date`,`generation`,`consumption`,`batteryStart`,`batteryEnd`,`solarUsed`,`gridUsed`,`exported`,`savings`,`weather`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EnergyLog entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        statement.bindDouble(3, entity.getGeneration());
        statement.bindDouble(4, entity.getConsumption());
        statement.bindLong(5, entity.getBatteryStart());
        statement.bindLong(6, entity.getBatteryEnd());
        statement.bindDouble(7, entity.getSolarUsed());
        statement.bindDouble(8, entity.getGridUsed());
        statement.bindDouble(9, entity.getExported());
        statement.bindDouble(10, entity.getSavings());
        if (entity.getWeather() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getWeather());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM energy_logs";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final EnergyLog log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEnergyLog.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<EnergyLog>> observeLast30() {
    final String _sql = "SELECT * FROM energy_logs ORDER BY date DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"energy_logs"}, new Callable<List<EnergyLog>>() {
      @Override
      @NonNull
      public List<EnergyLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfGeneration = CursorUtil.getColumnIndexOrThrow(_cursor, "generation");
          final int _cursorIndexOfConsumption = CursorUtil.getColumnIndexOrThrow(_cursor, "consumption");
          final int _cursorIndexOfBatteryStart = CursorUtil.getColumnIndexOrThrow(_cursor, "batteryStart");
          final int _cursorIndexOfBatteryEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "batteryEnd");
          final int _cursorIndexOfSolarUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "solarUsed");
          final int _cursorIndexOfGridUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "gridUsed");
          final int _cursorIndexOfExported = CursorUtil.getColumnIndexOrThrow(_cursor, "exported");
          final int _cursorIndexOfSavings = CursorUtil.getColumnIndexOrThrow(_cursor, "savings");
          final int _cursorIndexOfWeather = CursorUtil.getColumnIndexOrThrow(_cursor, "weather");
          final List<EnergyLog> _result = new ArrayList<EnergyLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EnergyLog _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final double _tmpGeneration;
            _tmpGeneration = _cursor.getDouble(_cursorIndexOfGeneration);
            final double _tmpConsumption;
            _tmpConsumption = _cursor.getDouble(_cursorIndexOfConsumption);
            final int _tmpBatteryStart;
            _tmpBatteryStart = _cursor.getInt(_cursorIndexOfBatteryStart);
            final int _tmpBatteryEnd;
            _tmpBatteryEnd = _cursor.getInt(_cursorIndexOfBatteryEnd);
            final double _tmpSolarUsed;
            _tmpSolarUsed = _cursor.getDouble(_cursorIndexOfSolarUsed);
            final double _tmpGridUsed;
            _tmpGridUsed = _cursor.getDouble(_cursorIndexOfGridUsed);
            final double _tmpExported;
            _tmpExported = _cursor.getDouble(_cursorIndexOfExported);
            final double _tmpSavings;
            _tmpSavings = _cursor.getDouble(_cursorIndexOfSavings);
            final String _tmpWeather;
            if (_cursor.isNull(_cursorIndexOfWeather)) {
              _tmpWeather = null;
            } else {
              _tmpWeather = _cursor.getString(_cursorIndexOfWeather);
            }
            _item = new EnergyLog(_tmpId,_tmpDate,_tmpGeneration,_tmpConsumption,_tmpBatteryStart,_tmpBatteryEnd,_tmpSolarUsed,_tmpGridUsed,_tmpExported,_tmpSavings,_tmpWeather);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
