/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.session.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.Region;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.gemfire.AbstractGemFireOperationsSessionRepository;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration Tests for the Spring Boot, Apache Geode {@link HttpSession} state caching example Web application.
 *
 * @author John Blum
 * @see java.time.Instant
 * @see javax.servlet.http.HttpSession
 * @see org.junit.Test
 * @see org.apache.geode.cache.Region
 * @see org.springframework.boot.test.context.SpringBootTest
 * @see org.springframework.data.gemfire.tests.integration.IntegrationTestsSupport
 * @see org.springframework.session.Session
 * @see org.springframework.session.SessionRepository
 * @see org.springframework.test.context.junit4.SpringRunner
 * @since 1.1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
	properties = { "spring.boot.data.gemfire.security.ssl.environment.post-processor.enabled=false" },
	webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@SuppressWarnings("unused")
public class BootGeodeHttpSessionCachingApplicationIntegrationTests extends IntegrationTestsSupport {

	@Resource(name = "ClusteredSpringSessions")
	private Region<String, Session> sessions;

	@Autowired
	private SessionRepository sessionRepository;

	@Before
	public void setup() {

		assertThat(this.sessions).isNotNull();
		assertThat(this.sessions.getName()).isEqualTo("Sessions");
		assertThat(this.sessions.getAttributes()).isNotNull();
		assertThat(this.sessions.getAttributes().getDataPolicy()).isEqualTo(DataPolicy.NORMAL);
		assertThat(this.sessionRepository).isNotNull();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void createdSessionStoredInAndRetrievableFromCacheRegion() {

		Instant now = Instant.now();

		Session session = this.sessionRepository.createSession();

		assertThat(session).isInstanceOf(AbstractGemFireOperationsSessionRepository.GemFireSession.class);
		assertThat(session.getId()).isNotEmpty();
		assertThat(session.isExpired()).isFalse();
		assertThat(session.getCreationTime()).isAfterOrEqualTo(now);
		assertThat(this.sessions).doesNotContainKey(session.getId());

		this.sessionRepository.save(session);

		assertThat(this.sessions).containsKey(session.getId());
		assertThat(this.sessions.get(session.getId())).isEqualTo(session);
	}
}