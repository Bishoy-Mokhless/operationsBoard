package com.kazyon.operationsBoard.Stores.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Stores")
public class Stores {
    //Name, Address, Governorate, Longitude, Latitude, and Status
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String address;
    String governorate;
    Float longitude;
    Float latitude;
    String Status;


}
