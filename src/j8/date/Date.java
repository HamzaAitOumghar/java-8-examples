package j8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date {


    public static void main(String[] args) {

        //Converting a string to date and time
        //1-via a predefined formatter :  ISO-8601 (yyyy-MM-dd) (mm:hh:ss) (yyyy-MM-ddTmm:hh:ss) (yyyy-MM-ddTmm:hh:ssZ[VV])

        LocalDate localDate = LocalDate.parse("2020-01-12");
        LocalTime localTime = LocalTime.parse("10:12:23");
        LocalDateTime localDateTime = LocalDateTime.parse("2020-01-12T10:12:23");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-01-12T10:12:23Z[Africa/Casablanca]");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2020-01-12T10:12:23Z");
        OffsetTime offsetTime = OffsetTime.parse("10:15:30Z");

        //2-via costume  pattern

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH/mm/ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH/mm/ss");
        DateTimeFormatter zonedDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH/mm/ss '['VV']'");

        localDate = LocalDate.parse("12/01/2020",dateFormatter);
        localTime = LocalTime.parse("09/45/20",timeFormatter);
        localDateTime = LocalDateTime.parse("12/01/2020 09/45/20",dateTimeFormatter);
        zonedDateTime = ZonedDateTime.parse("12/01/2020 09/45/20 [Africa/Casablanca]",zonedDateTimeFormatter);


        //Converting a Date to string
        //1-using toString()
        String stringDateTime = localDateTime.toString();
        //2-using format()
        String stringDate = dateFormatter.format(localDate);


        //MAachine Time via an instance
        //Instance class represents an instantanenous point on the timeline beginnin form the epoch

        Instant instant = Instant.now();
        instant = Instant.parse("2012-03-11T11:22:50Z");
        instant=instant.plus(2, ChronoUnit.HOURS);

        //Converting between Instant and LocalDateTime,
        //ZonedDateTime, and OffsetDateTime

        //1-Convert between Instant and LocalDateTime

        localDateTime = LocalDateTime.ofInstant(Instant.now(),ZoneOffset.UTC);
        instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);

        System.out.println(instant);


    }
}
