package be.ehb.notedroidv4.model.util;


import androidx.room.TypeConverter;

import org.threeten.bp.LocalDate;


public class Converters {

    @TypeConverter
    public static LocalDate toDate(String dateString) {
        return (dateString == null)? null : LocalDate.parse(dateString);
    }

    @TypeConverter
    public static String toDateString(LocalDate date) {
        return(date == null)? null : date.toString();
    }
}
