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

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberService {

    @Inject
    private Logger log;

    @Inject
    private MemberDao dao;

    @Inject
    private Event<Member> memberEventSrc;

    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        dao.Save(member);
        memberEventSrc.fire(member);
    }

	public List<Member> findAllOrderedByName() {
		return dao.findAllOrderedByName();
	}

	public Member findById(long id) {
		return dao.findById(id);
	}

	public Member findByEmail(String email) {
		return dao.findByEmail(email);
	}
}
