package com.javatechstack.datajpa.esindex;

import com.javatechstack.datajpa.model.EmployeeCertification;
import com.javatechstack.datajpa.model.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeIndex {

	@Id
	int employeeId;
	String name;
	String designation;
	String email;
	String phoneNo;

	@Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
	LocalDateTime dateOfJoining;
	int prevExperience;

	List<EmployeeSkill> skills;

	List<EmployeeCertification> certification;


	
}
