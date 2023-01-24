/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package example.app.caching.session.http.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring Web MVC {@link Controller} used to count the number of HTTP requests per HTTP Session
 * and the number of HTTP Sessions.
 *
 * @author John Blum
 * @see javax.servlet.http.HttpSession
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.web.bind.annotation.GetMapping
 * @see org.springframework.web.bind.annotation.ResponseBody
 * @see org.springframework.web.servlet.ModelAndView
 * @since 1.1.0
 */
//tag::class[]
@Controller
public class CounterController {

	public static final String INDEX_TEMPLATE_VIEW_NAME = "index";

	private final Set<String> sessionIds = Collections.synchronizedSet(new HashSet<>());

	@GetMapping("/")
	@ResponseBody
	public String home() {
		return format("HTTP Session Caching Example");
	}

	@GetMapping("/ping")
	@ResponseBody
	public String ping() {
		return format("PONG");
	}

	@GetMapping("/session")
	public ModelAndView sessionRequestCounts(HttpSession session) {

		this.sessionIds.add(session.getId());

		Map<String, Object> model = new HashMap<>();

		model.put("sessionType", session.getClass().getName());
		model.put("sessionCount", this.sessionIds.size());
		model.put("requestCount", getRequestCount(session));

		return new ModelAndView(INDEX_TEMPLATE_VIEW_NAME, model);
	}

	private Object getRequestCount(HttpSession session) {

		Integer requestCount = (Integer) session.getAttribute("requestCount");

		requestCount = requestCount != null ? requestCount : 0;
		requestCount++;

		session.setAttribute("requestCount", requestCount);

		return requestCount;
	}

	private String format(String value) {
		return String.format("<h1>%s</h1>", value);
	}
}
//end::class[]
