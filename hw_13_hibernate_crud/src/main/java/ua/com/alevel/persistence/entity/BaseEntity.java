package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

     public BaseEntity() {
         this.created = new Date();
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
