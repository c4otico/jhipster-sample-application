package com.mycompany.myapp.domain;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Este diagrama solo tiene algunas propiedades de cada tabla, las tablas en realidad tienen muchisima más información.
 */
@ApiModel(description = "Este diagrama solo tiene algunas propiedades de cada tabla, las tablas en realidad tienen muchisima más información.")
@Entity
@Table(name = "formula_hdr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FormulaHdr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 18)
    @Column(name = "xi_number", length = 18, nullable = false)
    private String xiNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "frmlname", length = 50, nullable = false)
    private String frmlname;

    @NotNull
    @Size(max = 32)
    @Column(name = "frml_type", length = 32, nullable = false)
    private String frmlType;

    @Size(max = 18)
    @Column(name = "profile_number", length = 18)
    private String profileNumber;

    @Size(max = 18)
    @Column(name = "user_number", length = 18)
    private String userNumber;

    @Size(max = 18)
    @Column(name = "book_number", length = 18)
    private String bookNumber;

    @Size(max = 18)
    @Column(name = "mfg_number", length = 18)
    private String mfgNumber;

    @Size(max = 8)
    @Column(name = "mfglocation", length = 8)
    private String mfglocation;

    @Size(max = 18)
    @Column(name = "cbaliascode", length = 18)
    private String cbaliascode;

    @Size(max = 800)
    @Column(name = "description", length = 800)
    private String description;

    @Size(max = 32)
    @Column(name = "customer", length = 32)
    private String customer;

    @Size(max = 32)
    @Column(name = "base_application", length = 32)
    private String baseApplication;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXiNumber() {
        return xiNumber;
    }

    public FormulaHdr xiNumber(String xiNumber) {
        this.xiNumber = xiNumber;
        return this;
    }

    public void setXiNumber(String xiNumber) {
        this.xiNumber = xiNumber;
    }

    public String getFrmlname() {
        return frmlname;
    }

    public FormulaHdr frmlname(String frmlname) {
        this.frmlname = frmlname;
        return this;
    }

    public void setFrmlname(String frmlname) {
        this.frmlname = frmlname;
    }

    public String getFrmlType() {
        return frmlType;
    }

    public FormulaHdr frmlType(String frmlType) {
        this.frmlType = frmlType;
        return this;
    }

    public void setFrmlType(String frmlType) {
        this.frmlType = frmlType;
    }

    public String getProfileNumber() {
        return profileNumber;
    }

    public FormulaHdr profileNumber(String profileNumber) {
        this.profileNumber = profileNumber;
        return this;
    }

    public void setProfileNumber(String profileNumber) {
        this.profileNumber = profileNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public FormulaHdr userNumber(String userNumber) {
        this.userNumber = userNumber;
        return this;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public FormulaHdr bookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
        return this;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getMfgNumber() {
        return mfgNumber;
    }

    public FormulaHdr mfgNumber(String mfgNumber) {
        this.mfgNumber = mfgNumber;
        return this;
    }

    public void setMfgNumber(String mfgNumber) {
        this.mfgNumber = mfgNumber;
    }

    public String getMfglocation() {
        return mfglocation;
    }

    public FormulaHdr mfglocation(String mfglocation) {
        this.mfglocation = mfglocation;
        return this;
    }

    public void setMfglocation(String mfglocation) {
        this.mfglocation = mfglocation;
    }

    public String getCbaliascode() {
        return cbaliascode;
    }

    public FormulaHdr cbaliascode(String cbaliascode) {
        this.cbaliascode = cbaliascode;
        return this;
    }

    public void setCbaliascode(String cbaliascode) {
        this.cbaliascode = cbaliascode;
    }

    public String getDescription() {
        return description;
    }

    public FormulaHdr description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public FormulaHdr customer(String customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBaseApplication() {
        return baseApplication;
    }

    public FormulaHdr baseApplication(String baseApplication) {
        this.baseApplication = baseApplication;
        return this;
    }

    public void setBaseApplication(String baseApplication) {
        this.baseApplication = baseApplication;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormulaHdr)) {
            return false;
        }
        return id != null && id.equals(((FormulaHdr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FormulaHdr{" +
            "id=" + getId() +
            ", xiNumber='" + getXiNumber() + "'" +
            ", frmlname='" + getFrmlname() + "'" +
            ", frmlType='" + getFrmlType() + "'" +
            ", profileNumber='" + getProfileNumber() + "'" +
            ", userNumber='" + getUserNumber() + "'" +
            ", bookNumber='" + getBookNumber() + "'" +
            ", mfgNumber='" + getMfgNumber() + "'" +
            ", mfglocation='" + getMfglocation() + "'" +
            ", cbaliascode='" + getCbaliascode() + "'" +
            ", description='" + getDescription() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", baseApplication='" + getBaseApplication() + "'" +
            "}";
    }
}
