package teampcc.backendservice.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="ranguser")
public class RangEntity {
    private Integer id;
    private String name;

    public RangEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Column(name = "name", length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
