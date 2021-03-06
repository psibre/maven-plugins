/*
 * Copyright (c) 2011 GitHub Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
package com.github.maven.plugins.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.egit.github.core.RepositoryId;
import org.junit.Test;

/**
 * Unit tests of {@link RepositoryUtils}
 * 
 * @author Kevin Sawicki (kevin@github.com)
 */
public class RepositoryUtilsTest {

	/**
	 * Test repository extraction from SCM anonymous Git URL
	 */
	@Test
	public void extractFromAnonymousUrl() {
		RepositoryId repo = RepositoryUtils
				.extractRepositoryFromScmUrl("scm:git:git://github.com/owner/project.git");
		assertNotNull(repo);
		assertEquals("owner", repo.getOwner());
		assertEquals("project", repo.getName());
		assertEquals("owner/project", repo.generateId());
	}

	/**
	 * Test repository extraction from malformed URLs
	 */
	@Test
	public void extractFromMalformedUrls() {
		assertNull(RepositoryUtils
				.extractRepositoryFromScmUrl("scm:git:git://github.com"));
		assertNull(RepositoryUtils
				.extractRepositoryFromScmUrl("scm:git:git://github.com/"));
		assertNull(RepositoryUtils
				.extractRepositoryFromScmUrl("scm:git:git@github.com"));
		assertNull(RepositoryUtils
				.extractRepositoryFromScmUrl("scm:git:git@github.com:"));
		assertNull(RepositoryUtils.extractRepositoryFromScmUrl(null));
		assertNull(RepositoryUtils.extractRepositoryFromScmUrl(""));
		assertNull(RepositoryUtils.extractRepositoryFromScmUrl(" "));
	}

	/**
	 * Test repository extraction from SCM SSH Git URL
	 */
	@Test
	public void extractFromSshUrl() {
		RepositoryId repo = RepositoryUtils
				.extractRepositoryFromScmUrl("scm:git:git@github.com:owner/project.git");
		assertNotNull(repo);
		assertEquals("owner", repo.getOwner());
		assertEquals("project", repo.getName());
		assertEquals("owner/project", repo.generateId());
	}
}
