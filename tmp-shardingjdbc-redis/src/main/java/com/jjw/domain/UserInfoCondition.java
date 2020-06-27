package com.jjw.domain;

public class UserInfoCondition {

    private Integer id;

    private String name;

    private Integer sex;

    private Integer preciseAge;

    private Integer minAge;

    private Integer maxAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getPreciseAge() {
        return preciseAge;
    }

    public void setPreciseAge(Integer preciseAge) {
        this.preciseAge = preciseAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}
