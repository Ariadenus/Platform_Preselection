/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "exigences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exigences.findAll", query = "SELECT e FROM Exigences e")})
public class Exigences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "code_matiere")
    private Integer codeMatiere;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOM_MATIERE")
    private String nomMatiere;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COEFFICIENT")
    private String coefficient;
    @JoinColumn(name = "code_master", referencedColumnName = "code_master")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Master codeMaster;

    public Exigences() {
    }

    public Exigences(Integer codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public Exigences(Integer codeMatiere, String nomMatiere, String coefficient) {
        this.codeMatiere = codeMatiere;
        this.nomMatiere = nomMatiere;
        this.coefficient = coefficient;
    }

    public Integer getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(Integer codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public Master getCodeMaster() {
        return codeMaster;
    }

    public void setCodeMaster(Master codeMaster) {
        this.codeMaster = codeMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMatiere != null ? codeMatiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exigences)) {
            return false;
        }
        Exigences other = (Exigences) object;
        if ((this.codeMatiere == null && other.codeMatiere != null) || (this.codeMatiere != null && !this.codeMatiere.equals(other.codeMatiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.master.model.Exigences[ codeMatiere=" + codeMatiere + " ]";
    }

}
