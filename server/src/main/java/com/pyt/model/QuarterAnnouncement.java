package com.pyt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "QuarterAnnouncement")
public class QuarterAnnouncement {
	
	/** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;
    
    
    @Id
    private int idQuarter;
    
    
    private int idAnnouncement;

	public int getIdQuarter() {
		return idQuarter;
	}

	public int getIdAnnouncement() {
		return idAnnouncement;
	}
    
    

}
