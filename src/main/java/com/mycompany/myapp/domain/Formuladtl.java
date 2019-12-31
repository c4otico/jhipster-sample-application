package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Formuladtl
 */
@ApiModel(description = "Formuladtl")
@Entity
@Table(name = "formuladtl")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Formuladtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dtlid")
    private Integer dtlid;

    @Column(name = "dtlseq")
    private Integer dtlseq;

    @Size(max = 18)
    @Column(name = "itemcode", length = 18)
    private String itemcode;

    @Column(name = "qty")
    private Integer qty;

    @Size(max = 18)
    @Column(name = "old_itemcode", length = 18)
    private String oldItemcode;

    @Size(max = 18)
    @Column(name = "refxi_number", length = 18)
    private String refxiNumber;

    @ManyToOne
    @JsonIgnoreProperties("formuladtls")
    private FormulaHdr frmlid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDtlid() {
        return dtlid;
    }

    public Formuladtl dtlid(Integer dtlid) {
        this.dtlid = dtlid;
        return this;
    }

    public void setDtlid(Integer dtlid) {
        this.dtlid = dtlid;
    }

    public Integer getDtlseq() {
        return dtlseq;
    }

    public Formuladtl dtlseq(Integer dtlseq) {
        this.dtlseq = dtlseq;
        return this;
    }

    public void setDtlseq(Integer dtlseq) {
        this.dtlseq = dtlseq;
    }

    public String getItemcode() {
        return itemcode;
    }

    public Formuladtl itemcode(String itemcode) {
        this.itemcode = itemcode;
        return this;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public Integer getQty() {
        return qty;
    }

    public Formuladtl qty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getOldItemcode() {
        return oldItemcode;
    }

    public Formuladtl oldItemcode(String oldItemcode) {
        this.oldItemcode = oldItemcode;
        return this;
    }

    public void setOldItemcode(String oldItemcode) {
        this.oldItemcode = oldItemcode;
    }

    public String getRefxiNumber() {
        return refxiNumber;
    }

    public Formuladtl refxiNumber(String refxiNumber) {
        this.refxiNumber = refxiNumber;
        return this;
    }

    public void setRefxiNumber(String refxiNumber) {
        this.refxiNumber = refxiNumber;
    }

    public FormulaHdr getFrmlid() {
        return frmlid;
    }

    public Formuladtl frmlid(FormulaHdr formulaHdr) {
        this.frmlid = formulaHdr;
        return this;
    }

    public void setFrmlid(FormulaHdr formulaHdr) {
        this.frmlid = formulaHdr;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Formuladtl)) {
            return false;
        }
        return id != null && id.equals(((Formuladtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Formuladtl{" +
            "id=" + getId() +
            ", dtlid=" + getDtlid() +
            ", dtlseq=" + getDtlseq() +
            ", itemcode='" + getItemcode() + "'" +
            ", qty=" + getQty() +
            ", oldItemcode='" + getOldItemcode() + "'" +
            ", refxiNumber='" + getRefxiNumber() + "'" +
            "}";
    }
}
