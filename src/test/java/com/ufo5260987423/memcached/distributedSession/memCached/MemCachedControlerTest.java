/**
 * Copyright 2015年2月3日 Wang Zheng
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *@author Wang Zheng ufo5260987423@163.com
 *
 */
package com.ufo5260987423.memcached.distributedSession.memCached;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @ClassName: MemCachedControlerTest
 * @Description: TODO
 * @author ufo ufo5260987423@163.com
 * @date 2015年2月3日 下午1:11:45
 *
 */
public class MemCachedControlerTest {

	/**
	 * @Title: setUp
	 * @Description: TODO
	 * @param @throws java.lang.Exception
	 * @return void
	 * @throws
	 */
	private static MemCachedControler memCachedControler = (MemCachedControler) new FileSystemXmlApplicationContext(
			"/src/test/resources/applicationContext.xml").getBean("memCachedControler");

	@Before
	public void setUp() throws Exception {
		this.getMemCachedControler().clear();
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#clear()}
	 * .
	 */
	@Ignore
	public void testClear() {
		try {
			this.getMemCachedControler().set("testClear", 10, "testClear");
			this.getMemCachedControler().clear();
			assertEquals(null==this.getMemCachedControler().get("testClear"), false);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#get(java.lang.String)}
	 * .
	 */
	@Test
	public void testGet() {
		try {
			this.getMemCachedControler().set("testGet", 10, "testGet");
			assertEquals(this.getMemCachedControler().get("testGet").equals("testGet"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#set(java.lang.String, int, java.lang.Object)}
	 * .
	 */
	@Test
	public void testSet() {
		try {
			assertEquals(this.getMemCachedControler().set("testSet", 10, "testSet"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#isExist(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsExist() {
		try {
			this.getMemCachedControler().set("testIsExist", 10, "testIsExist");
			assertEquals(this.getMemCachedControler().isExist("testIsExist"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#casSet(java.lang.String, int, java.lang.Object)}
	 * .
	 */
	@Test
	public void testCasSet() {
		try {
			assertEquals(this.getMemCachedControler().casSet("testCasSet", 10, "testCasSet"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#remove(java.lang.String)}
	 * .
	 */
	@Test
	public void testRemove() {
		try {
			this.getMemCachedControler().set("testRemove", 10, "testRemove");
			assertEquals(this.getMemCachedControler().remove("testRemove"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public MemCachedControler getMemCachedControler() {
		return memCachedControler;
	}

}
