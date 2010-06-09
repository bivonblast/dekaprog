package com.rasp.dekaederprogram.character.data;

import java.io.Serializable;

/**
 * General stat representation.
 *
 * @author      Jonas Frogvall
 * @version     %I%, %G%
 * @since       1.6
 */

public abstract class Trait<V> implements Serializable {
	//variabler
	private String	name;
	private V	value;

    /**
     * Initiates a new {@code Trait} with no name and the value {@code null}.
     */
	protected Trait() {
		this.name = "";
		this.value = null;
	}

	/**
     * Initiates a new {@code Trait} with the specified name and the value 
         * {@code null}.
	 *
	 * @param  name		The name of the {@code Trait}.
     */
	protected Trait(String name) {
		this.name = name;
		this.value = null;
	}

	/**
     * Initiates a new {@code Trait} with no name and the specified value.
	 *
	 * @param  value	The value of the {@code Trait}.
     */
	protected Trait(V value) {
		this.name = "";
		this.value = value;
	}

	/**
     * Initiates a new {@code Trait} with the specified name and the specified value.
	 *
	 * @param  name		The name of the {@code Trait}.
	 *
	 * @param  value	The value of the {@code Trait}.
     */
	protected Trait(String name, V value) {
		this.name = name;
		this.value = value;
	}

	/**
     * Returns the name of this {@code Trait}.
	 *
	 * @return the name of this {@code Trait}.
     */
	public String getName() {
		return name;
	}

	/**
     * Returns the value of this {@code Trait}.
	 *
	 * @return the value of this {@code Trait}.
     */
	public V getValue() {
		return value;
	}

	/**
     * Sets the name of this {@code Trait}.
     *
     * @param name		The name to be set to this {@code Trait}.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Sets the value of this {@code Trait}.
     *
     * @param value		The value to be set to this {@code Trait}.
     */
	public void setValue(V value) {
		this.value = value;
	}

    /**
     * Method to be overridden.
     * Should return the trait's value as a string.
     * @return the value as a String.
     */
        public abstract String valueToString();
}
