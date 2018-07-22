package domain;

import java.io.Serializable;

public class Major implements Serializable {

    private int id;
    private String name;//专业名
    private String introduce;//专业介绍
    private College college;//专业关联的学院对象

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intrduce='" + introduce + '\'' +
                ", college=" + college +
                '}';
    }
}
