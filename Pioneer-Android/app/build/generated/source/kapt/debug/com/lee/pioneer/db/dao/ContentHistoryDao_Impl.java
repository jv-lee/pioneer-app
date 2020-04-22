package com.lee.pioneer.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.lee.pioneer.db.converters.StringListConverter;
import com.lee.pioneer.model.entity.Content;
import com.lee.pioneer.model.entity.ContentHistory;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ContentHistoryDao_Impl implements ContentHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ContentHistory> __insertionAdapterOfContentHistory;

  private final StringListConverter __stringListConverter = new StringListConverter();

  private final EntityDeletionOrUpdateAdapter<ContentHistory> __deletionAdapterOfContentHistory;

  private final EntityDeletionOrUpdateAdapter<ContentHistory> __updateAdapterOfContentHistory;

  public ContentHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContentHistory = new EntityInsertionAdapter<ContentHistory>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ContentHistory` (`history_id`,`history_type`,`history_source`,`read_time`,`is_collect`,`_id`,`author`,`category`,`createdAt`,`desc`,`images`,`likeCounts`,`publishedAt`,`stars`,`title`,`type`,`url`,`views`,`viewType`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ContentHistory value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getType());
        stmt.bindLong(3, value.getSource());
        stmt.bindLong(4, value.getReadTime());
        stmt.bindLong(5, value.isCollect());
        final Content _tmpContent = value.getContent();
        if(_tmpContent != null) {
          if (_tmpContent.get_id() == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindString(6, _tmpContent.get_id());
          }
          if (_tmpContent.getAuthor() == null) {
            stmt.bindNull(7);
          } else {
            stmt.bindString(7, _tmpContent.getAuthor());
          }
          if (_tmpContent.getCategory() == null) {
            stmt.bindNull(8);
          } else {
            stmt.bindString(8, _tmpContent.getCategory());
          }
          if (_tmpContent.getCreatedAt() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindString(9, _tmpContent.getCreatedAt());
          }
          if (_tmpContent.getDesc() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmpContent.getDesc());
          }
          final String _tmp;
          _tmp = __stringListConverter.stringListFormJson(_tmpContent.getImages());
          if (_tmp == null) {
            stmt.bindNull(11);
          } else {
            stmt.bindString(11, _tmp);
          }
          stmt.bindLong(12, _tmpContent.getLikeCounts());
          if (_tmpContent.getPublishedAt() == null) {
            stmt.bindNull(13);
          } else {
            stmt.bindString(13, _tmpContent.getPublishedAt());
          }
          stmt.bindLong(14, _tmpContent.getStars());
          if (_tmpContent.getTitle() == null) {
            stmt.bindNull(15);
          } else {
            stmt.bindString(15, _tmpContent.getTitle());
          }
          if (_tmpContent.getType() == null) {
            stmt.bindNull(16);
          } else {
            stmt.bindString(16, _tmpContent.getType());
          }
          if (_tmpContent.getUrl() == null) {
            stmt.bindNull(17);
          } else {
            stmt.bindString(17, _tmpContent.getUrl());
          }
          stmt.bindLong(18, _tmpContent.getViews());
          stmt.bindLong(19, _tmpContent.getViewType());
        } else {
          stmt.bindNull(6);
          stmt.bindNull(7);
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
          stmt.bindNull(14);
          stmt.bindNull(15);
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
          stmt.bindNull(19);
        }
      }
    };
    this.__deletionAdapterOfContentHistory = new EntityDeletionOrUpdateAdapter<ContentHistory>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ContentHistory` WHERE `history_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ContentHistory value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfContentHistory = new EntityDeletionOrUpdateAdapter<ContentHistory>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `ContentHistory` SET `history_id` = ?,`history_type` = ?,`history_source` = ?,`read_time` = ?,`is_collect` = ?,`_id` = ?,`author` = ?,`category` = ?,`createdAt` = ?,`desc` = ?,`images` = ?,`likeCounts` = ?,`publishedAt` = ?,`stars` = ?,`title` = ?,`type` = ?,`url` = ?,`views` = ?,`viewType` = ? WHERE `history_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ContentHistory value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getType());
        stmt.bindLong(3, value.getSource());
        stmt.bindLong(4, value.getReadTime());
        stmt.bindLong(5, value.isCollect());
        final Content _tmpContent = value.getContent();
        if(_tmpContent != null) {
          if (_tmpContent.get_id() == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindString(6, _tmpContent.get_id());
          }
          if (_tmpContent.getAuthor() == null) {
            stmt.bindNull(7);
          } else {
            stmt.bindString(7, _tmpContent.getAuthor());
          }
          if (_tmpContent.getCategory() == null) {
            stmt.bindNull(8);
          } else {
            stmt.bindString(8, _tmpContent.getCategory());
          }
          if (_tmpContent.getCreatedAt() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindString(9, _tmpContent.getCreatedAt());
          }
          if (_tmpContent.getDesc() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmpContent.getDesc());
          }
          final String _tmp;
          _tmp = __stringListConverter.stringListFormJson(_tmpContent.getImages());
          if (_tmp == null) {
            stmt.bindNull(11);
          } else {
            stmt.bindString(11, _tmp);
          }
          stmt.bindLong(12, _tmpContent.getLikeCounts());
          if (_tmpContent.getPublishedAt() == null) {
            stmt.bindNull(13);
          } else {
            stmt.bindString(13, _tmpContent.getPublishedAt());
          }
          stmt.bindLong(14, _tmpContent.getStars());
          if (_tmpContent.getTitle() == null) {
            stmt.bindNull(15);
          } else {
            stmt.bindString(15, _tmpContent.getTitle());
          }
          if (_tmpContent.getType() == null) {
            stmt.bindNull(16);
          } else {
            stmt.bindString(16, _tmpContent.getType());
          }
          if (_tmpContent.getUrl() == null) {
            stmt.bindNull(17);
          } else {
            stmt.bindString(17, _tmpContent.getUrl());
          }
          stmt.bindLong(18, _tmpContent.getViews());
          stmt.bindLong(19, _tmpContent.getViewType());
        } else {
          stmt.bindNull(6);
          stmt.bindNull(7);
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
          stmt.bindNull(14);
          stmt.bindNull(15);
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
          stmt.bindNull(19);
        }
        if (value.getId() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getId());
        }
      }
    };
  }

  @Override
  public void insert(final ContentHistory... value) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContentHistory.insert(value);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final List<? extends ContentHistory> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContentHistory.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ContentHistory... value) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfContentHistory.handleMultiple(value);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final List<? extends ContentHistory> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfContentHistory.handleMultiple(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ContentHistory... value) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfContentHistory.handleMultiple(value);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final List<? extends ContentHistory> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfContentHistory.handleMultiple(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ContentHistory> queryContentHistory() {
    final String _sql = "SELECT `_id`, `author`, `category`, `createdAt`, `desc`, `images`, `likeCounts`, `publishedAt`, `stars`, `title`, `type`, `url`, `views`, `viewType`, `ContentHistory`.`history_id` AS `history_id`, `ContentHistory`.`history_type` AS `history_type`, `ContentHistory`.`history_source` AS `history_source`, `ContentHistory`.`read_time` AS `read_time`, `ContentHistory`.`is_collect` AS `is_collect` FROM ContentHistory ORDER BY read_time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "desc");
      final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
      final int _cursorIndexOfLikeCounts = CursorUtil.getColumnIndexOrThrow(_cursor, "likeCounts");
      final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
      final int _cursorIndexOfStars = CursorUtil.getColumnIndexOrThrow(_cursor, "stars");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
      final int _cursorIndexOfViewType = CursorUtil.getColumnIndexOrThrow(_cursor, "viewType");
      final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "history_id");
      final int _cursorIndexOfType_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "history_type");
      final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "history_source");
      final int _cursorIndexOfReadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "read_time");
      final int _cursorIndexOfIsCollect = CursorUtil.getColumnIndexOrThrow(_cursor, "is_collect");
      final List<ContentHistory> _result = new ArrayList<ContentHistory>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ContentHistory _item;
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId_1);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType_1);
        final int _tmpSource;
        _tmpSource = _cursor.getInt(_cursorIndexOfSource);
        final long _tmpReadTime;
        _tmpReadTime = _cursor.getLong(_cursorIndexOfReadTime);
        final int _tmpIsCollect;
        _tmpIsCollect = _cursor.getInt(_cursorIndexOfIsCollect);
        final Content _tmpContent;
        if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfAuthor) && _cursor.isNull(_cursorIndexOfCategory) && _cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfDesc) && _cursor.isNull(_cursorIndexOfImages) && _cursor.isNull(_cursorIndexOfLikeCounts) && _cursor.isNull(_cursorIndexOfPublishedAt) && _cursor.isNull(_cursorIndexOfStars) && _cursor.isNull(_cursorIndexOfTitle) && _cursor.isNull(_cursorIndexOfType) && _cursor.isNull(_cursorIndexOfUrl) && _cursor.isNull(_cursorIndexOfViews) && _cursor.isNull(_cursorIndexOfViewType))) {
          final String _tmp_id;
          _tmp_id = _cursor.getString(_cursorIndexOfId);
          final String _tmpAuthor;
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
          final String _tmpCategory;
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
          final String _tmpCreatedAt;
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
          final String _tmpDesc;
          _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
          final List<String> _tmpImages;
          final String _tmp;
          _tmp = _cursor.getString(_cursorIndexOfImages);
          _tmpImages = __stringListConverter.stringJsonToList(_tmp);
          final int _tmpLikeCounts;
          _tmpLikeCounts = _cursor.getInt(_cursorIndexOfLikeCounts);
          final String _tmpPublishedAt;
          _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
          final int _tmpStars;
          _tmpStars = _cursor.getInt(_cursorIndexOfStars);
          final String _tmpTitle;
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
          final String _tmpType_1;
          _tmpType_1 = _cursor.getString(_cursorIndexOfType);
          final String _tmpUrl;
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
          final int _tmpViews;
          _tmpViews = _cursor.getInt(_cursorIndexOfViews);
          final int _tmpViewType;
          _tmpViewType = _cursor.getInt(_cursorIndexOfViewType);
          _tmpContent = new Content(_tmp_id,_tmpAuthor,_tmpCategory,_tmpCreatedAt,_tmpDesc,_tmpImages,_tmpLikeCounts,_tmpPublishedAt,_tmpStars,_tmpTitle,_tmpType_1,_tmpUrl,_tmpViews,_tmpViewType);
        }  else  {
          _tmpContent = null;
        }
        _item = new ContentHistory(_tmpId,_tmpType,_tmpSource,_tmpReadTime,_tmpIsCollect,_tmpContent);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ContentHistory> queryContentFavorite() {
    final String _sql = "SELECT `_id`, `author`, `category`, `createdAt`, `desc`, `images`, `likeCounts`, `publishedAt`, `stars`, `title`, `type`, `url`, `views`, `viewType`, `ContentHistory`.`history_id` AS `history_id`, `ContentHistory`.`history_type` AS `history_type`, `ContentHistory`.`history_source` AS `history_source`, `ContentHistory`.`read_time` AS `read_time`, `ContentHistory`.`is_collect` AS `is_collect` FROM ContentHistory WHERE is_collect =1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "desc");
      final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
      final int _cursorIndexOfLikeCounts = CursorUtil.getColumnIndexOrThrow(_cursor, "likeCounts");
      final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
      final int _cursorIndexOfStars = CursorUtil.getColumnIndexOrThrow(_cursor, "stars");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
      final int _cursorIndexOfViewType = CursorUtil.getColumnIndexOrThrow(_cursor, "viewType");
      final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "history_id");
      final int _cursorIndexOfType_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "history_type");
      final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "history_source");
      final int _cursorIndexOfReadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "read_time");
      final int _cursorIndexOfIsCollect = CursorUtil.getColumnIndexOrThrow(_cursor, "is_collect");
      final List<ContentHistory> _result = new ArrayList<ContentHistory>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ContentHistory _item;
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId_1);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType_1);
        final int _tmpSource;
        _tmpSource = _cursor.getInt(_cursorIndexOfSource);
        final long _tmpReadTime;
        _tmpReadTime = _cursor.getLong(_cursorIndexOfReadTime);
        final int _tmpIsCollect;
        _tmpIsCollect = _cursor.getInt(_cursorIndexOfIsCollect);
        final Content _tmpContent;
        if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfAuthor) && _cursor.isNull(_cursorIndexOfCategory) && _cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfDesc) && _cursor.isNull(_cursorIndexOfImages) && _cursor.isNull(_cursorIndexOfLikeCounts) && _cursor.isNull(_cursorIndexOfPublishedAt) && _cursor.isNull(_cursorIndexOfStars) && _cursor.isNull(_cursorIndexOfTitle) && _cursor.isNull(_cursorIndexOfType) && _cursor.isNull(_cursorIndexOfUrl) && _cursor.isNull(_cursorIndexOfViews) && _cursor.isNull(_cursorIndexOfViewType))) {
          final String _tmp_id;
          _tmp_id = _cursor.getString(_cursorIndexOfId);
          final String _tmpAuthor;
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
          final String _tmpCategory;
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
          final String _tmpCreatedAt;
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
          final String _tmpDesc;
          _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
          final List<String> _tmpImages;
          final String _tmp;
          _tmp = _cursor.getString(_cursorIndexOfImages);
          _tmpImages = __stringListConverter.stringJsonToList(_tmp);
          final int _tmpLikeCounts;
          _tmpLikeCounts = _cursor.getInt(_cursorIndexOfLikeCounts);
          final String _tmpPublishedAt;
          _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
          final int _tmpStars;
          _tmpStars = _cursor.getInt(_cursorIndexOfStars);
          final String _tmpTitle;
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
          final String _tmpType_1;
          _tmpType_1 = _cursor.getString(_cursorIndexOfType);
          final String _tmpUrl;
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
          final int _tmpViews;
          _tmpViews = _cursor.getInt(_cursorIndexOfViews);
          final int _tmpViewType;
          _tmpViewType = _cursor.getInt(_cursorIndexOfViewType);
          _tmpContent = new Content(_tmp_id,_tmpAuthor,_tmpCategory,_tmpCreatedAt,_tmpDesc,_tmpImages,_tmpLikeCounts,_tmpPublishedAt,_tmpStars,_tmpTitle,_tmpType_1,_tmpUrl,_tmpViews,_tmpViewType);
        }  else  {
          _tmpContent = null;
        }
        _item = new ContentHistory(_tmpId,_tmpType,_tmpSource,_tmpReadTime,_tmpIsCollect,_tmpContent);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int isCollect(final String id) {
    final String _sql = "SELECT COUNT(*) FROM ContentHistory WHERE _id = ? AND is_collect = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ContentHistory> queryContentById(final String id) {
    final String _sql = "SELECT `_id`, `author`, `category`, `createdAt`, `desc`, `images`, `likeCounts`, `publishedAt`, `stars`, `title`, `type`, `url`, `views`, `viewType`, `ContentHistory`.`history_id` AS `history_id`, `ContentHistory`.`history_type` AS `history_type`, `ContentHistory`.`history_source` AS `history_source`, `ContentHistory`.`read_time` AS `read_time`, `ContentHistory`.`is_collect` AS `is_collect` FROM ContentHistory WHERE _id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "desc");
      final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
      final int _cursorIndexOfLikeCounts = CursorUtil.getColumnIndexOrThrow(_cursor, "likeCounts");
      final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
      final int _cursorIndexOfStars = CursorUtil.getColumnIndexOrThrow(_cursor, "stars");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
      final int _cursorIndexOfViewType = CursorUtil.getColumnIndexOrThrow(_cursor, "viewType");
      final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "history_id");
      final int _cursorIndexOfType_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "history_type");
      final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "history_source");
      final int _cursorIndexOfReadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "read_time");
      final int _cursorIndexOfIsCollect = CursorUtil.getColumnIndexOrThrow(_cursor, "is_collect");
      final List<ContentHistory> _result = new ArrayList<ContentHistory>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ContentHistory _item;
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId_1);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType_1);
        final int _tmpSource;
        _tmpSource = _cursor.getInt(_cursorIndexOfSource);
        final long _tmpReadTime;
        _tmpReadTime = _cursor.getLong(_cursorIndexOfReadTime);
        final int _tmpIsCollect;
        _tmpIsCollect = _cursor.getInt(_cursorIndexOfIsCollect);
        final Content _tmpContent;
        if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfAuthor) && _cursor.isNull(_cursorIndexOfCategory) && _cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfDesc) && _cursor.isNull(_cursorIndexOfImages) && _cursor.isNull(_cursorIndexOfLikeCounts) && _cursor.isNull(_cursorIndexOfPublishedAt) && _cursor.isNull(_cursorIndexOfStars) && _cursor.isNull(_cursorIndexOfTitle) && _cursor.isNull(_cursorIndexOfType) && _cursor.isNull(_cursorIndexOfUrl) && _cursor.isNull(_cursorIndexOfViews) && _cursor.isNull(_cursorIndexOfViewType))) {
          final String _tmp_id;
          _tmp_id = _cursor.getString(_cursorIndexOfId);
          final String _tmpAuthor;
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
          final String _tmpCategory;
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
          final String _tmpCreatedAt;
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
          final String _tmpDesc;
          _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
          final List<String> _tmpImages;
          final String _tmp;
          _tmp = _cursor.getString(_cursorIndexOfImages);
          _tmpImages = __stringListConverter.stringJsonToList(_tmp);
          final int _tmpLikeCounts;
          _tmpLikeCounts = _cursor.getInt(_cursorIndexOfLikeCounts);
          final String _tmpPublishedAt;
          _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
          final int _tmpStars;
          _tmpStars = _cursor.getInt(_cursorIndexOfStars);
          final String _tmpTitle;
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
          final String _tmpType_1;
          _tmpType_1 = _cursor.getString(_cursorIndexOfType);
          final String _tmpUrl;
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
          final int _tmpViews;
          _tmpViews = _cursor.getInt(_cursorIndexOfViews);
          final int _tmpViewType;
          _tmpViewType = _cursor.getInt(_cursorIndexOfViewType);
          _tmpContent = new Content(_tmp_id,_tmpAuthor,_tmpCategory,_tmpCreatedAt,_tmpDesc,_tmpImages,_tmpLikeCounts,_tmpPublishedAt,_tmpStars,_tmpTitle,_tmpType_1,_tmpUrl,_tmpViews,_tmpViewType);
        }  else  {
          _tmpContent = null;
        }
        _item = new ContentHistory(_tmpId,_tmpType,_tmpSource,_tmpReadTime,_tmpIsCollect,_tmpContent);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
