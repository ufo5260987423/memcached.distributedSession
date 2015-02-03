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
import org.junit.Test;
import org.springframework.context.ApplicationContext;
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
	private MemCachedControler memCachedControler;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/src/test/resources/applicationContext.xml");
		this.setMemCachedControler((MemCachedControler) ctx.getBean("memCachedControler"));
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#MemCachedControler(net.rubyeye.xmemcached.MemcachedClient)}.
	 */
	@Test
	public void testMemCachedControler() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#set(java.lang.String, int, java.lang.Object)}.
	 */
	@Test
	public void testSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#isExist(java.lang.String)}.
	 */
	@Test
	public void testIsExist() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#casSet(java.lang.String, int, java.lang.Object)}.
	 */
	@Test
	public void testCasSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#remove(java.lang.String)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#getStat(java.net.InetSocketAddress[])}.
	 */
	@Test
	public void testGetStat() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#getMemcachedClient()}.
	 */
	@Test
	public void testGetMemcachedClient() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#setMemcachedClient(net.rubyeye.xmemcached.MemcachedClient)}.
	 */
	@Test
	public void testSetMemcachedClient() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#addServer(java.lang.String, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testAddServer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler#removeServer(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testRemoveServer() {
		fail("Not yet implemented");
	}

	public MemCachedControler getMemCachedControler() {
		return memCachedControler;
	}

	public void setMemCachedControler(MemCachedControler memCachedControler) {
		this.memCachedControler = memCachedControler;
	}

}
