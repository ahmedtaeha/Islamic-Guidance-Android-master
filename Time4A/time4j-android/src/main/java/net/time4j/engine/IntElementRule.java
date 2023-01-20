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

package net.time4j.engine;


/**
 * <p>Element rule with support for int-primitives. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must be immutable. </p>
 *
 * @param   <T> generic type of time context compatible to {@code ChronoEntity}
 * @author  Meno Hochschild
 * @since   3.15/4.12
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine Elementregel mit Unterst&uuml;tzung
 * f&uuml;r java-int-primitives. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must be immutable. </p>
 *
 * @param   <T> generic type of time context compatible to {@code ChronoEntity}
 * @author  Meno Hochschild
 * @since   3.15/4.12
 */
public interface IntElementRule<T>
    extends ElementRule<T, Integer> {

    //~ Methoden ------------------------------------------------------

    /**
     * <p>Yields the current value of associated element in given
     * chronological context. </p>
     *
     * <p>Will be called by {@link ChronoEntity#getInt(ChronoElement)}. </p>
     *
     * @param   context     time context to be evaluated
     * @return  current element value as int-primitive
     * @throws  ChronoException if the associated element value cannot be evaluated
     * @since   3.15/4.12
     */
    /*[deutsch]
     * <p>Ermittelt den aktuellen Wert des assoziierten Elements
     * im angegebenen Zeitwertkontext. </p>
     *
     * <p>Wird von {@link ChronoEntity#getInt(ChronoElement)} aufgerufen. </p>
     *
     * @param   context     time context to be evaluated
     * @return  current element value as int-primitive
     * @throws  ChronoException if the associated element value cannot be evaluated
     * @since   3.15/4.12
     */
    int getInt(T context);

    /**
     * <p>Queries if given value is valid for the element associated with this
     * rule in given context. </p>
     *
     * <p>Will be called by {@link ChronoEntity#isValid(ChronoElement, int)}.
     * A numerical overflow causing an {@code ArithmeticException} will usually
     * not be checked. </p>
     *
     * @param   context     time context to be evaluated
     * @param   value       candidate value to be validated
     * @return  {@code true} if valid else {@code false}
     * @since   3.15/4.12
     */
    /*[deutsch]
     * <p>Ist der angegebene Wert zum mit dieser Regel assoziierten
     * Element im angegebenen Kontext g&uuml;ltig? </p>
     *
     * <p>Wird von {@link ChronoEntity#isValid(ChronoElement, int)} aufgerufen.
     * Eine numerische &Uuml;berlaufsituation im Hinblick auf eine {@code ArithmeticException}
     * wird in der Regel nicht gepr&uuml;ft. </p>
     *
     * @param   context     time context to be evaluated
     * @param   value       candidate value to be validated
     * @return  {@code true} if valid else {@code false}
     * @since   3.15/4.12
     */
    boolean isValid(
        T context,
        int value
    );

    /**
     * <p>Determines the new value of the associated element in given
     * chronological context and yields the result. </p>
     *
     * <p>Will be called by {@link ChronoEntity#with(ChronoElement, int)}.
     * The lenient mode causes the tolerant interpretation of invalid
     * values like 31st of April as 1st of May. This mode is only
     * active if an element is either explicitly declared as lenient or if
     * the method {@code StdOperator.setLenient()} is used. </p>
     *
     * @param   context     time context to be evaluated
     * @param   value       new element value
     * @param   lenient     leniency mode
     * @return  changed copy of context which itself remains unaffected
     * @throws  IllegalArgumentException if given value is out of range or
     *          not valid dependent on the given time context
     * @throws  ArithmeticException in case of numerical overflow
     * @see     #isValid(Object, int)
     * @see     StdOperator#setLenient(Object, ChronoElement)
     * @see     ChronoElement#isLenient()
     * @see     net.time4j.ProportionalElement#setLenient(Number)
     * @since   3.15/4.12
     */
    /*[deutsch]
     * <p>Bestimmt den neuen Wert des assoziierten Elements im
     * angegebenen Zeitwertkontext und liefert das Ergebnis. </p>
     *
     * <p>Wird von {@link ChronoEntity#with(ChronoElement, int)} aufgerufen.
     * Der Nachsichtigkeitsmodus f&uuml;hrt dazu, da&szlig; eigentlich
     * ung&uuml;ltige Werte wie der 31. April als 1. Mai interpretiert werden.
     * Aktiv ist dieser Modus nur bei Verwendung der einen Operator liefernden
     * Methode {@code StdOperator.setLenient()} oder wenn das Element
     * ausdr&uuml;cklich als nachsichtig deklariert wird. </p>
     *
     * @param   context     time context to be evaluated
     * @param   value       new element value
     * @param   lenient     leniency mode
     * @return  changed copy of context which itself remains unaffected
     * @throws  IllegalArgumentException if given value is out of range or
     *          not valid dependent on the given time context
     * @throws  ArithmeticException in case of numerical overflow
     * @see     #isValid(Object, int)
     * @see     StdOperator#setLenient(Object, ChronoElement)
     * @see     ChronoElement#isLenient()
     * @see     net.time4j.ProportionalElement#setLenient(Number)
     * @since   3.15/4.12
     */
    T withValue(
        T context,
        int value,
        boolean lenient
    );

}
