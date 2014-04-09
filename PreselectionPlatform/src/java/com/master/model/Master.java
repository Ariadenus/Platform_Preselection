/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Master.findAll", query = "SELECT m FROM Master m")})
public class Master implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "code_master")
    private Integer codeMaster;
    @Size(max = 255)
    @Column(name = "NOM_MASTER")
    private String nomMaster;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeMaster", fetch = FetchType.EAGER)
    private Collection<Exigences> exigencesCollection;

    public Master() {
    }

    public Master(Integer codeMaster) {
        this.codeMaster = codeMaster;
    }

    public Master(String nomMaster) {
        this.nomMaster = nomMaster;
    }

    public Integer getCodeMaster() {
        return codeMaster;
    }

    public void setCodeMaster(Integer codeMaster) {
        this.codeMaster = codeMaster;
    }

    public String getNomMaster() {
        return nomMaster;
    }

    public void setNomMaster(String nomMaster) {
        this.nomMaster = nomMaster;
    }

    @XmlTransient
    public Collection<Exigences> getExigencesCollection() {
        return exigencesCollection;
    }

    public void setExigencesCollection(Collection<Exigences> exigencesCollection) {
        this.exigencesCollection = exigencesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMaster != null ? codeMaster.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Master)) {
            return false;
        }
        Master other = (Master) object;
        if ((this.codeMaster == null && other.codeMaster != null) || (this.codeMaster != null && !this.codeMaster.equals(other.codeMaster))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.master.model.Master[ codeMaster=" + codeMaster + " ]";
    }

}
