package com.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendar.entities.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
	
	public abstract Invitation findInvitationByEmailAndEstado(String email, int estado);

}
