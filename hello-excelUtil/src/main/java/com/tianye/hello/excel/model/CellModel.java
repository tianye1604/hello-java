package com.tianye.hello.excel.model;

import java.lang.reflect.Field;

/**
 * The <code>CellModel</code>
 * 
 * @author sargeras.wang
 * @version 1.0, Created at 2013年9月17日
 */
public class CellModel implements Comparable<CellModel> {
    private Field field;
    private int index;
    private String title;

    /**
     * @param field
     */
    public CellModel(Field field) {
        super();
        this.field = field;
    }

    /**
     * @param field
     * @param index
     */
    public CellModel(Field field, int index, String title) {
        super();
        this.field = field;
        this.index = index;
        this.title = title;
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int compareTo(CellModel field) {
        if (field == null) {
            return 1;
        }
        return this.index - field.index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CellModel that = (CellModel) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        return field.hashCode() + title.hashCode() + index;
    }
}
