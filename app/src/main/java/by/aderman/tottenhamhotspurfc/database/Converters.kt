package by.aderman.tottenhamhotspurfc.database

import androidx.room.TypeConverter
import by.aderman.tottenhamhotspurfc.models.news.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String? {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}