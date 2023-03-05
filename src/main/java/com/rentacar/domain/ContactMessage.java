package com.rentacar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_cmessage")
public class ContactMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	@Size(min = 1,max = 50, message = "Your name '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message = "Please provide your name")
	private String name;
	
	@Column(length = 50, nullable = false)
	@Size(min = 5, max = 50, message = "Your subject '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message = "Please provide message subject")
	private String subject;
	
	@Column(length = 50, nullable = false)
	@Size(min = 5, max = 50, message = "Your body '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message = "Please provide message body")
	private String body;
	
	@Column(length = 50, nullable = false)
	@Email(message = "Provide valid email")
	private String email;

}
