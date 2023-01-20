/*
 * Licensed by the author of Time4J-project.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership. The copyright owner
 * licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.time4j;

import net.time4j.base.GregorianMath;
import net.time4j.base.MathUtils;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoOperator;

import java.io.ObjectStreamException;


/**
 * <p>Das Element f&uuml;r den x-ten Wochentag im Monat. </p>
 *
 * <p>Eine Instanz ist erh&auml;ltlich &uuml;ber den Ausdruck
 * {@link PlainDate#WEEKDAY_IN_MONTH}. Diese Klasse bietet neben
 * den vom Interface {@code AdjustableElement} geerbten Methoden
 * weitere Spezialmethoden zum Setzen des Wochentags im Monat. </p>
 *
 * @author      Meno Hochschild
 */
final class WeekdayInMonthElement
    extends AbstractDateElement<Integer>
    implements OrdinalWeekdayElement {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Singleton. </p>
     */
    static final WeekdayInMonthElement INSTANCE = new WeekdayInMonthElement();

    private static final int LAST = 5;
    private static final long serialVersionUID = -2378018589067147278L;

    //~ Konstruktoren -----------------------------------------------------

    private WeekdayInMonthElement() {
        super("WEEKDAY_IN_MONTH");

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<Integer> getType() {

        return Integer.class;

    }

    @Override
    public char getSymbol() {

        return 'F';

    }

    /**
     * Definiert das Standardminimum.
     *
     * @return  {@code 1}
     */
    @Override
    public Integer getDefaultMinimum() {

        return Integer.valueOf(1);

    }

    /**
     * Definiert das Standardmaximum.
     *
     * @return  {@code 5}
     */
    @Override
    public Integer getDefaultMaximum() {

        return Integer.valueOf(LAST);

    }

    @Override
    public boolean isDateElement() {

        return true;

    }

    @Override
    public boolean isTimeElement() {

        return false;

    }

    @Override
    public ElementOperator<PlainDate> setToFirst(Weekday dayOfWeek) {

        return this.setTo(1, dayOfWeek);

    }

    @Override
    public ElementOperator<PlainDate> setToSecond(Weekday dayOfWeek) {

        return this.setTo(2, dayOfWeek);

    }

    @Override
    public ElementOperator<PlainDate> setToThird(Weekday dayOfWeek) {

        return this.setTo(3, dayOfWeek);

    }

    @Override
    public ElementOperator<PlainDate> setToFourth(Weekday dayOfWeek) {

        return this.setTo(4, dayOfWeek);

    }

    @Override
    public ElementOperator<PlainDate> setToLast(Weekday dayOfWeek) {

        return this.setTo(LAST, dayOfWeek);

    }

    @Override
    protected boolean isSingleton() {

        return true; // exists only once in PlainDate

    }

    private ElementOperator<PlainDate> setTo(
        final int ordinal,
        final Weekday dayOfWeek
    ) {

        return new SpecialOperator(ordinal, dayOfWeek);

    }

    private Object readResolve() throws ObjectStreamException {

        return INSTANCE;

    }

    //~ Innere Klassen ----------------------------------------------------

    private static class SpecialOperator
        extends ElementOperator<PlainDate> {

        //~ Instanzvariablen ----------------------------------------------

        private final long ordinal;
        private final Weekday dayOfWeek;
        private final ChronoOperator<PlainTimestamp> specialTS;

        //~ Konstruktoren -------------------------------------------------

        SpecialOperator(
            int ordinal,
            Weekday dayOfWeek
        ) {
            super(WeekdayInMonthElement.INSTANCE, OP_WIM);

            if (dayOfWeek == null) {
                throw new NullPointerException("Missing value.");
            }

            this.ordinal = ordinal;
            this.dayOfWeek = dayOfWeek;

            this.specialTS =
                new ChronoOperator<PlainTimestamp>() {
                    @Override
                    public PlainTimestamp apply(PlainTimestamp entity) {
                        return doApply(entity);
                    }
                };

        }

        //~ Methoden ------------------------------------------------------

        @Override
        public PlainDate apply(PlainDate entity) {

            return this.doApply(entity);

        }

        @Override
        ChronoOperator<PlainTimestamp> onTimestamp() {

            return this.specialTS;

        }

        private <T extends ChronoEntity<T>> T doApply(T entity) {

            if (entity.contains(PlainDate.CALENDAR_DATE)) {
                PlainDate date = entity.get(PlainDate.CALENDAR_DATE);
                Weekday current = date.get(PlainDate.DAY_OF_WEEK);
                int delta = this.dayOfWeek.getValue() - current.getValue();
                int dom = date.getDayOfMonth() + delta;
                long days;
                if (this.ordinal == LAST) {
                    days = (5L - (MathUtils.floorDivide(dom - 1, 7) + 1)) * 7 + delta;
                    int max = GregorianMath.getLengthOfMonth(date.getYear(), date.getMonth());
                    if (date.getDayOfMonth() + days > max) {
                        days -= 7;
                    }
                } else {
                    days = (this.ordinal - (MathUtils.floorDivide(dom - 1, 7) + 1)) * 7 + delta;
                }
                date = date.plus(days, CalendarUnit.DAYS);
                return entity.with(PlainDate.CALENDAR_DATE, date);
            } else {
                throw new ChronoException(
                    "Rule not found for ordinal day of week in month: "
                    + entity);
            }

        }

    }

}
