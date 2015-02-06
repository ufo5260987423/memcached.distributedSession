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
package com.ufo5260987423.memcached.distributedSession.map;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @ClassName: DistributedConcurrentHashMapTest
 * @Description: TODO
 * @author ufo ufo5260987423@163.com
 * @date 2015年2月4日 下午3:43:11
 *
 */
public class DistributedSessionsConcurrentHashMapTest {
	@SuppressWarnings("unchecked")
	private static DistributedSessionsConcurrentHashMap<String, String> map = 
	(DistributedSessionsConcurrentHashMap<String, String>) new FileSystemXmlApplicationContext(
			"/src/test/resources/applicationContext.xml").getBean("distributedSessionsConcrrentHashMap");

	/**
	 * @Title: setUp
	 * @Description: TODO
	 * @param @throws java.lang.Exception
	 * @return void
	 * @throws
	 */
	@Before
	public void setUp() throws Exception {
		this.getMap().clear();
	}

	/**
	 * 
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#size()}
	 * .
	 */
	@Ignore
	public void testSize() {
		try {
			this.getMap().put("testSize", "testSize");
			// 3=1+2,as the backupControler.amountOfBackup=2
			assertEquals(this.getMap().size(), 3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#isEmpty()}
	 * .
	 */
	@Ignore
	public void testIsEmpty() {
		try {
			assertEquals(this.getMap().isEmpty(), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#containsKey(java.lang.Object)}
	 * .
	 */
	@Test
	public void testContainsKey() {
		try {
			this.getMap().put("testContainsKey", "testContainsKey");
			assertEquals(this.getMap().containsKey("testContainsKey")&&(!this.getMap().containsKey("test")), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#get(java.lang.Object)}
	 * .
	 */
	@Test
	public void testGet() {
		try {
			this.getMap().put("testGet", "testGet");
			assertEquals(this.getMap().get("testGet").equals("testGet"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#put(java.lang.Object, java.io.Serializable)}
	 * .
	 */
	@Test
	public void testPut() {
		try {
			assertEquals(this.getMap().put("testPut", "testPut"), "testPut");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#remove(java.lang.Object)}
	 * .
	 */
	@Test
	public void testRemove() {
		try {
			this.getMap().put("testRemove", "testRemove");
			this.getMap().remove("testRemove");
			assertEquals(this.getMap().containsKey("testRemove"),true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#putAll(java.util.Map)}
	 * .
	 */
	@Test
	public void testPutAll() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			for (Integer i = 0; i < 100; i++)
				map.put(i.toString(), i.toString());

			this.getMap().putAll(map);
			assertEquals(this.getMap().containsKey("5"), true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#clear()}
	 * .
	 */
	@Test
	public void testClear() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			for (Integer i = 0; i < 100; i++)
				map.put(i.toString(), i.toString());

			this.getMap().putAll(map);
			this.getMap().clear();
			assertEquals(this.getMap().containsKey("5"), false);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public DistributedSessionsConcurrentHashMap<String, String> getMap() {
		return map;
	}

}
