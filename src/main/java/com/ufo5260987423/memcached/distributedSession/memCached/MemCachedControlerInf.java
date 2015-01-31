/**
 * Copyright 2015年1月26日 Wang Zheng
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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * @ClassName: MemCachedControlerInf
 * @Description: Controle the access towards memcached cluster
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月26日 下午10:05:21
 *
 */
public interface MemCachedControlerInf {
	public void setMemcachedClient(MemcachedClient memcachedClient);

	public MemcachedClient getMemcachedClient();

	public void addServer(String serverIp, Integer port, Integer weight) throws IOException;

	public void removeServer(String serverIp, Integer port);

	/*
	 * exp is the living time on memcached
	 */
	public Boolean set(String key, int exp, Object value) throws TimeoutException, InterruptedException,
			MemcachedException;

	/*
	 * exp is the living time on memcached
	 */
	public Boolean casSet(String key, int exp, Object value) throws TimeoutException, InterruptedException,
			MemcachedException;

	public Object get(String key) throws TimeoutException, InterruptedException, MemcachedException;

	public Boolean remove(String key) throws TimeoutException, InterruptedException, MemcachedException;

	/*
	 * @param address is a array of InetSocketAddress
	 */
	public List<Map<String, String>> getStat(InetSocketAddress[] address) throws MemcachedException,
			InterruptedException, TimeoutException;
}