package com.wisdomelon.sys.entity.ser;

import javax.persistence.*;

@Table(name = "FT_SER_FESTIVAL")
public class FtSerFestival {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "NOW_YEAR")
    private String nowYear;

    @Column(name = "NAME")
    private String name;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "DAY_COUNT")
    private Integer dayCount;

    @Column(name = "REMARK")
    private String remark;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return NOW_YEAR
     */
    public String getNowYear() {
        return nowYear;
    }

    /**
     * @param nowYear
     */
    public void setNowYear(String nowYear) {
        this.nowYear = nowYear;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return START_DATE
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return END_DATE
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return DAY_COUNT
     */
    public Integer getDayCount() {
        return dayCount;
    }

    /**
     * @param dayCount
     */
    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}