package com.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calendar.entities.Invitation;
import com.calendar.repository.InvitationRepository;

@Service("invitationServiceImpl")
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	@Qualifier("invitationRepository")
	private InvitationRepository invitationRepository;

	@Override
	public Invitation addInvitation(Invitation invitation) {
		return invitationRepository.save(invitation);
	}
	
	
}
