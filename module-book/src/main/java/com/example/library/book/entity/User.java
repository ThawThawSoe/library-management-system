package com.example.library.book.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
	
	@Column(unique = true)
	private String registerId;
	
	@Column(columnDefinition = "VARCHAR(100) CHARACTER SET utf8 COLLATE utf8mb4_unicode_ci",
	nullable = false, unique = true, length = 50)
	@NotBlank(message = "Username is required")
	@Size(max = 100, message="Username must not exceed 100 characters")
    private String username;
	
	
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be greater than 6 characters")
    private String password;
	
    @Column(columnDefinition = "VARCHAR(100) CHARACTER SET utf8 COLLATE utf8mb4_unicode_ci")
	@Size(max=50, message ="Full Name must not be exceed than 100 characters")
    private String fullName;
    
	@Size(max=50, message ="Email must not be exceed than 50 characters")
	private String email;
	
	@Column(columnDefinition = "VARCHAR(50) CHARACTER SET utf8 COLLATE utf8mb4_unicode_ci")
	@Size(max=15, message ="Phone No must not be exceed than 15 characters")
	private String phoneNo;
	
	@Column(columnDefinition = "VARCHAR(100) CHARACTER SET utf8 COLLATE utf8mb4_unicode_ci")
	@Size(max=100, message ="Address must not be exceed than 100 characters")
	private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Role is required")
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime   createdAt = LocalDateTime.now();
    
   
    private String createdBy;
    
    
    private LocalDateTime updatedAt;
    
    
    private String updatedBy;
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
    
    
}
