package teampcc.backendservice.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name="bossuser")
public class BossEntity implements java.io.Serializable {
    private Integer id;
    private String code;
    private String fname;
    private String lname;
    private Integer age;
    private String sex;
    private Date birth;
    private Date dateAt;
    private String saveBy;

    public BossEntity() {
    }

    public BossEntity(Integer userID,String code, String userFName, String userLastname, Integer userAge,
                    String userGender, Date userBirth, Date timeStamp,String saveBy) {
        this.id = userID;
        this.code = code;
        this.fname = userFName;
        this.lname = userLastname;
        this.age = userAge;
        this.sex = userGender;
        this.birth = userBirth;
        this.dateAt = timeStamp;
        this.saveBy = saveBy;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "code", length = 500)
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "fname", length = 1000)
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Column(name = "lname", length = 1000)
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "sex", length = 100)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "birth")
    public Date getBirthDate() {
        return birth;
    }

    public void setBirthDate(Date birth) {
        this.birth = birth;
    }

    @Column(name = "dateAt")
    public Date getDateAt() {
        return dateAt;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }

    @Column(name = "saveBy", length = 1000)
    public String getBy(){
        return saveBy;
    }
    public void setBy(String by){
        this.saveBy=by;
    }

}
