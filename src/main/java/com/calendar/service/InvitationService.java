package com.calendar.service;

import com.calendar.entities.Invitation;

public interface InvitationService {

	public abstract Invitation addInvitation(Invitation invitation);
	
	public abstract Invitation findInvitationByEmailAndEstado(String email, int estado);
}
