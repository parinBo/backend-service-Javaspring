package teampcc.backendservice.entity;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="premuser", catalog = "premdb")
public class PremUser implements java.io.Serializable {
	
	private Long id;
	private Integer number;
	private String name;
	private String lastname;
	private Date birth;
	private Integer age;
	private String gender;
	private Date timeStamp;
	private String saveby;
	
	public PremUser() {
	}

	public PremUser(Long userID, Integer number,String userFName, String userLastname, Integer userAge,
			String userGender, Date userBirth, Date timeStamp,String saveby) {
		this.id = userID;
		this.number = number;
		this.name = userFName;
		this.lastname = userLastname;
		this.age = userAge;
		this.gender = userGender;
		this.birth = userBirth;
		this.timeStamp = timeStamp;
		this.saveby = saveby;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "number", length = 200)
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	@Column(name = "name", length = 1000)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "lastname", length = 1000)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name = "gender", length = 100)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "timestamp")
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Column(name = "savebyname")
	public String getSaveby() {
		return saveby;
	}

	public void setSaveby(String saveby) {
		this.saveby = saveby;
	}
	
}












