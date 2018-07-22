package domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String num;//学号
    private String name;//姓名
    private String sex;//性别
    private String age;//年龄
    private String birthday;//出生日期
    private String nation;//民族
    private String phone;//电话
    private String pol;//政治面貌
    private String natives;//籍贯
    private String school;//学校
    private String photo;//照片
    private String spec;//特长
    private String hobby;//爱好
    private String remark;//备注
    private Grade grade;//学生关联的班级对象
    private Major major;//学生关联的专业对象
    private College college;//学生关联的学院对象
    private MultipartFile file;//照片


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getNatives() {
        return natives;
    }

    public void setNatives(String natives) {
        this.natives = natives;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nation='" + nation + '\'' +
                ", phone='" + phone + '\'' +
                ", pol='" + pol + '\'' +
                ", natives='" + natives + '\'' +
                ", school='" + school + '\'' +
                ", photo='" + photo + '\'' +
                ", spec='" + spec + '\'' +
                ", hobby='" + hobby + '\'' +
                ", remark='" + remark + '\'' +
                ", grade=" + grade +
                ", major=" + major +
                ", college=" + college +
                '}';
    }
}
