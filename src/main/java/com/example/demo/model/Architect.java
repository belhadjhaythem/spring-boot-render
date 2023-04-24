package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ARCHITECT")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "id_user"))
public class Architect extends User {

	private LocalDate dateOfBirth;

	@Column(nullable = true)
	private String phone;

	private String speciality;
	
	private String cin;

	@Column(name = "number_of_points")
	private int numberOfPoints = 0;

	@Column(name = "number_of_subscribers")
	private int numberOfSubscribers = 0;

	@Column(name = "last_login")
	private LocalDateTime lastLogin = LocalDateTime.now();

	@Cascade({CascadeType.SAVE_UPDATE})
	@OneToMany(mappedBy = "architect") //, cascade = CascadeType.ALL, orphanRemoval = true
	@JsonIgnoreProperties(value = {"architect"}, allowSetters = true)
	private List<Client> clients = new ArrayList<Client>();
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARCHT__SEQ")
    @SequenceGenerator(sequenceName = "architect_ref_seq", allocationSize = 1, name = "ARCHT__SEQ")
	private String idErp;
	
	public Architect() {}

	public Architect(LocalDate dateOfBirth, String phone, String speciality, String cin, int numberOfPoints,
			int numberOfSubscribers, LocalDateTime lastLogin, List<Client> clients, String idErp) {
		super();
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.speciality = speciality;
		this.cin = cin;
		this.numberOfPoints = numberOfPoints;
		this.numberOfSubscribers = numberOfSubscribers;
		this.lastLogin = lastLogin;
		this.clients = clients;
		this.idErp = idErp;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public int getNumberOfSubscribers() {
		return numberOfSubscribers;
	}

	public void setNumberOfSubscribers(int numberOfSubscribers) {
		this.numberOfSubscribers = numberOfSubscribers;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public String getIdErp() {
		return idErp;
	}

	public void setIdErp(String idErp) {
		this.idErp = idErp;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@Override
	public String toString() {
		return "Architect{" +
				"id=" + getId() +
				"name=" + getFirstName() + " " + getLastName() +
				", phone='" + phone + '\'' +
				", cin='" + cin + '\'' +
				", numberOfPoints=" + numberOfPoints +
				'}';
	}
}
