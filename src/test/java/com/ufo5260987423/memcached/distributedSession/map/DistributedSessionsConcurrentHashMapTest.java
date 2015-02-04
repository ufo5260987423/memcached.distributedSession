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

import org.junit.Before;
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
	private static DistributedSessionsConcurrentHashMap<String,String> map=(DistributedSessionsConcurrentHashMap<String, String>) new FileSystemXmlApplicationContext(
			"/src/test/resources/applicationContext.xml").getBean("distributedConcrrentHashMap");

	/**
	 * @Title: setUp
	 * @Description: TODO
	 * @param @throws java.lang.Exception
	 * @return void
	 * @throws
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#size()}.
	 */
	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#containsKey(java.lang.Object)}.
	 */
	@Test
	public void testContainsKey() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#containsValue(java.lang.Object)}.
	 */
	@Test
	public void testContainsValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#get(java.lang.Object)}.
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#put(java.lang.Object, java.io.Serializable)}.
	 */
	@Test
	public void testPut() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#putAll(java.util.Map)}.
	 */
	@Test
	public void testPutAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#clear()}.
	 */
	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#keySet()}.
	 */
	@Test
	public void testKeySet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#values()}.
	 */
	@Test
	public void testValues() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.map.DistributedConcurrentHashMap#entrySet()}.
	 */
	@Test
	public void testEntrySet() {
		fail("Not yet implemented");
	}

	public DistributedSessionsConcurrentHashMap<String,String> getMap() {
		return map;
	}

}
