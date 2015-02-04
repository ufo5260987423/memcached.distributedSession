/**
 * Copyright 2015年2月4日 Wang Zheng
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
package com.ufo5260987423.memcached.distributedSession.backup;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @ClassName: ConsistentBackupControlerTest
 * @Description: TODO
 * @author ufo ufo5260987423@163.com
 * @date 2015年2月4日 上午9:37:55
 *
 */
public class ConsistentBackupControlerTest {

	private static ConsistentBackupControler consistentBackupControler = (ConsistentBackupControler) new FileSystemXmlApplicationContext(
			"/src/test/resources/applicationContext.xml").getBean("consistentBackupControler");

	/**
	 * @Title: setUp
	 * @Description: TODO
	 * @param @throws java.lang.Exception
	 * @return void
	 * @throws
	 */
	@Before
	public void setUp() throws Exception {
		this.getConsistentBackupControler().getMemCachedControler().clear();
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.backup.ConsistentBackupControler#set(java.lang.String, int, java.lang.Object)}
	 * .
	 */
	@Test
	public void testSet() {
		try {
			assertEquals(this.getConsistentBackupControler().set("testSet", 10, "testSet"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.backup.ConsistentBackupControler#casSet(java.lang.String, int, java.lang.Object)}
	 * .
	 */
	@Test
	public void testCasSet() {
		try {
			assertEquals(this.getConsistentBackupControler().casSet("testCasSet", 10, "testCasSet"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.backup.ConsistentBackupControler#get(java.lang.String)}
	 * .
	 */
	@Test
	public void testGet() {
		try {
			this.getConsistentBackupControler().set("testGet", 10, "testGet");
			assertEquals(this.getConsistentBackupControler().get("testGet").equals("testGet"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.backup.ConsistentBackupControler#remove(java.lang.String)}
	 * .
	 */
	@Test
	public void testRemove() {
		try {
			this.getConsistentBackupControler().set("testRemove", 100, "testRemove");
			assertEquals(this.getConsistentBackupControler().remove("testRemove"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.backup.ConsistentBackupControler#isExist(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsExist() {
		try {
			this.getConsistentBackupControler().set("testIsExist", 10, "testIsExist");
			assertEquals(this.getConsistentBackupControler().isExist("testIsExist"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}	}

	public ConsistentBackupControler getConsistentBackupControler() {
		return consistentBackupControler;
	}

}
