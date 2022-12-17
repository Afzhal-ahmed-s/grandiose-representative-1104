package com.pac.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer memeberId;
	private Boolean doseOneStatus;
	private Boolean doseTwoStatus;
	private LocalDate doseOneDate;
	private LocalDate doseTwoDate;

	@OneToOne(cascade = CascadeType.ALL)
	private IdCard idCard;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VaccineRegistration vaccineRegistration;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;

	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
}

//Demo Member json file
//{
//    "doseOneStatus" : false,
//    "doseTwoStatus" : false,
//    "doseOneDate" : "2022-09-10",
//    "doseTwoDate" : "2022-12-10"
//}


//{
//    "doseOneStatus": false,
//    "doseTwoStatus": false,
//    "doseOneDate": "2000-09-10",
//    "doseTwoDate": "2000-12-10",
//    "idCard": {
//        "name": "Afzhal",
//        "dob" : "1998-09-12",
//        "gender" : "male",
//        "city" : "cbe",
//        "address" : "tn",
//        "state" : "tamilnadu",
//        "pincode" : "641030",
//        "aadharCard" : {
//            "aadharNo" : "aadhar1234"
//        },
//        "panCard" :{
//            "panNo" : "pan1234"
//        }  
//    },
//    "vaccineRegistration": null,
//    "vaccine": null,
//    "appointments": []
//}