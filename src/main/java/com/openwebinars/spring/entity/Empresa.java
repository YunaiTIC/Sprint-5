package com.openwebinars.spring.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name="empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //DOCUMENTAR AMB OPENAPI
    //@Schema( example = "Jaime", description = "Esquema para organizar los nombres")
    //private String name;
    //@Schema( example = "Ayudante", description = "Esquema para organizar los puestos")
    //private String job;

    private String name;
    private String job;
    private String description;
    private String location;






    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ofertes> ofertes;
    
    public Empresa() {
		
	}


    public Empresa(String name, String job, String description, String location) {
        this.name = name;
        this.job = job;
        this.description = description;
        this.location = location;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public List<Ofertes> getOfertes() {
        return this.ofertes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOfertes(List<Ofertes> ofertes) {
        this.ofertes = ofertes;
    }


    //@Override
    //public String toString() {
    //return "Empresa [id=" + id + ", name=" + name + ", job=" + job + ", description=" + description + ", location="
    //		+ location + ", ofertes=" + ofertes + "]";
    //}

    //@Override
    // public boolean equals(Object o) {
    //  if (this == o) return true;
    //  if (!(o instanceof Empresa)) return false;
    //  Empresa empresa = (Empresa) o;
    //  return Objects.equals(this.id, empresa.id) &&
    //          Objects.equals(this.name, empresa.name) &&
    // //          Objects.equals(this.job, empresa.job) &&
    //       Objects.equals(this.description, empresa.description) &&
    //          Objects.equals(this.location, empresa.location);
    //}
    
    
    
    
    

 

    // public Empresa(Long id, String name, String job, String description, String location) {
    //  this.id = id;
    //  this.name = name;
    // //  this.job = job;
    //this.description = description;
    //  this.location = location;
    //  this.ofertes = new ArrayList<>();
    //}



    //@Override
    //public int hashCode() {
    //  return Objects.hash(this.id, this.name, this.job, this.description, this.location);
    //}

    
}
