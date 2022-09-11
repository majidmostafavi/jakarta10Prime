package com.majidmostafavi.jakartaee10prime12.core.utils.time;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.*;
import java.util.Locale;

import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoUnit.MONTHS;

public enum PersianMonth implements TemporalAccessor, TemporalAdjuster {

    FARVARDIN,
    ORDIBEHESHT,
    KHORDAD,
    TIR,
    MORDAD,
    SHAHRIVAR,
    MEHR,
    ABAN,
    AZAR,
    DEY,
    BAHMAN,
    ESFAND;

    private static final PersianMonth[] ENUMS = PersianMonth.values();

    public static PersianMonth of(int month) {
        if (month < 1 || month > 12) {
            throw new DateTimeException("Invalid value for MonthOfYear: " + month);
        }
        return ENUMS[month - 1];
    }

    public static PersianMonth from(TemporalAccessor temporal) {
        if (temporal instanceof PersianMonth) {
            return (PersianMonth) temporal;
        }
        try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
                temporal = LocalDate.from(temporal);
            }
            return of(temporal.get(MONTH_OF_YEAR));
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Month from TemporalAccessor: " +
                    temporal + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText(MONTH_OF_YEAR, style).toFormatter(locale).format(this);
    }

    @Override
    public boolean isSupported(TemporalField field) {
        if (field instanceof ChronoField) {
            return field == MONTH_OF_YEAR;
        }
        return field != null && field.isSupportedBy(this);
    }

    @Override
    public ValueRange range(TemporalField field) {
        if (field == MONTH_OF_YEAR) {
            return field.range();
        }
        return TemporalAccessor.super.range(field);
    }

    @Override
    public int get(TemporalField field) {
        if (field == MONTH_OF_YEAR) {
            return getValue();
        }
        return TemporalAccessor.super.get(field);
    }

    @Override
    public long getLong(TemporalField field) {
        if (field == MONTH_OF_YEAR) {
            return getValue();
        } else if (field instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    public PersianMonth plus(long months) {
        int amount = (int) (months % 12);
        return ENUMS[(ordinal() + (amount + 12)) % 12];
    }

    public PersianMonth minus(long months) {
        return plus(-(months % 12));
    }

    public int length(boolean leapYear) {
        switch (this) {
            case MEHR:
            case ABAN:
            case AZAR:
            case DEY:
            case BAHMAN:
                return 30;
            case ESFAND:
                return (leapYear ? 30 : 29);
            default:
                return 31;
        }
    }

    public int minLength() {
        switch (this) {
            case MEHR:
            case ABAN:
            case AZAR:
            case DEY:
            case BAHMAN:
                return 30;
            case ESFAND:
                return 29;
            default:
                return 31;
        }
    }

    public int maxLength() {
        switch (this) {
            case MEHR:
            case ABAN:
            case AZAR:
            case DEY:
            case BAHMAN:
            case ESFAND:
                return 30;
            default:
                return 31;
        }
    }

    public int firstDayOfYear() {
        switch (this) {
            case FARVARDIN:
                return 1;
            case ORDIBEHESHT:
                return 32;
            case KHORDAD:
                return 63;
            case TIR:
                return 94;
            case MORDAD:
                return 125;
            case SHAHRIVAR:
                return 156;
            case MEHR:
                return 187;
            case ABAN:
                return 217;
            case AZAR:
                return 247;
            case DEY:
                return 277;
            case BAHMAN:
                return 307;
            case ESFAND:
            default:
                return 337;
        }
    }

    public PersianMonth firstMonthOfQuarter() {
        return ENUMS[(ordinal() / 3) * 3];
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.chronology()) {
            return (R) IsoChronology.INSTANCE;
        } else if (query == TemporalQueries.precision()) {
            return (R) MONTHS;
        }
        return TemporalAccessor.super.query(query);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        return temporal.with(MONTH_OF_YEAR, getValue());
    }

    @Override
    public String toString() {
        switch (this) {
            case FARVARDIN:
                return "فروردین";
            case ORDIBEHESHT:
                return "اردیبهشت";
            case KHORDAD:
                return "خرداد";
            case TIR:
                return "تیر";
            case MORDAD:
                return "مرداد";
            case SHAHRIVAR:
                return "شهریور";
            case MEHR:
                return "مهر";
            case ABAN:
                return "آبان";
            case AZAR:
                return "آذر";
            case DEY:
                return "دی";
            case BAHMAN:
                return "بهمن";
            case ESFAND:
            default:
                return "اسفند";
        }
    }

}
