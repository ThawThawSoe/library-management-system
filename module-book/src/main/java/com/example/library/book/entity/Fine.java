package com.example.library.book.entity;

import javax.persistence.*;

@Entity
@Table(name = "fines")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fineId;

    @OneToOne
    @JoinColumn(name = "borrow_id", nullable = false)
    private BorrowRecord borrowRecord;

    private Double amount;
    private Boolean paid = false;
	public Long getFineId() {
		return fineId;
	}
	public BorrowRecord getBorrowRecord() {
		return borrowRecord;
	}
	public void setBorrowRecord(BorrowRecord borrowRecord) {
		this.borrowRecord = borrowRecord;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public void setFineId(Long fineId) {
		this.fineId = fineId;
	}
	
    
    
}
