package com.seethayya.shoppingcart.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/11/14
 * Time: 11:50 AM
 */
@MappedSuperclass
public abstract class BaseDto implements Serializable {

    private Long id ;
    private String lastUpdatedBy;
    private Date lastUpdated;
    private Long optLockVersion;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LAST_UPDATED_BY")
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "LAST_UPDATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Column(name = "OPTLOCK_VERSION")
    @Version
    public Long getOptLockVersion() {
        return optLockVersion;
    }

    public void setOptLockVersion(Long optLockVersion) {
        this.optLockVersion = optLockVersion;
    }

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        lastUpdatedBy = "online@shoppingcart.com";
        lastUpdated = new Date();
    }
}
