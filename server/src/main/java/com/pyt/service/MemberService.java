/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pyt.service;

import com.pyt.dao.MemberDao;
import com.pyt.model.Member;
import com.pyt.rest.exception.DuplicateKeyException;
import com.pyt.rest.exception.NotFoundException;
import com.pyt.rest.exception.UnauthorizedException;
import com.pyt.service.bean.FreeMarkerDataContainer;

import Enums.EmailTemplateName;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	private MemberDao dao;
	
	@Inject 
	private QuarterService quarterService;

	@Inject
	private Event<Member> memberEventSrc;

	@Inject
	private NotificationService notificationService;

	@Inject
	private EmailTemplatesService emailTemplatesService;

	public Long register(Member user) throws DuplicateKeyException {

		String email = user.getEmail();

		if (dao.getByEmail(email) != null)
			throw new DuplicateKeyException("Email already present.");

		// E' un utente che si registra e si deve attivare con l'email
		if (user.getHash() == null)
			user.setHash(dao.getFreeHash());
		
		user.setQuarter(quarterService.getById((int)Math.random()*3 +1));

		dao.insert(user);

		// Se non è attivo (non ha nessun ruolo impostato, quindi non viene dal
		// BO) mando mail per attivazione
		if (!user.isVerified())
			notificationService.sendActivationLink(user);

		return user.getId();
	}

	public Member activateAccount(String email, String hash) throws UnauthorizedException {
		Member user = dao.getByEmail(email);

		if (user != null) {

			if (user.getHash() == null || !user.getHash().equals(hash))
				throw new UnauthorizedException("Wrong email or hash.");

			if (!user.isVerified()) {
				user.setVerified(true);
				dao.merge(user);

				FreeMarkerDataContainer dataContainer = new FreeMarkerDataContainer(emailTemplatesService.ita);
				dataContainer.setUser(user);
				notificationService.sendEmail(user.getEmail(), null, emailTemplatesService.ita, dataContainer,
						EmailTemplateName.ACTIVATED);
			}
			return user;
		} else {
			logger.debug("Email da attivare non trovata: {}.", email);
			return null;
		}

	}

	public List<Member> findAllOrderedByName() {
		return dao.getAllOrderedByName();
	}

	public Member findById(long id) {
		return dao.getById(id);
	}

	public Member findByEmail(String email) {
		return dao.getByEmail(email);
	}

	public Collection<Member> getMultipleByIds(int ids[]) {
		return dao.getMultipleById(ids);

	}
}
