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
package com.ufo5260987423.memcached.distributedSession.map;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * @ClassName: MemCachedControler
 * @Description: Controle the access towards memcached cluster
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月26日 下午5:01:41
 *
 */
public class MemCachedControler implements MemCachedControlerInf{
	private MemcachedClient memcachedClient;
	
	public MemCachedControler(MemcachedClient memcachedClient){
		this.setMemcachedClient(memcachedClient);
	}
	
	public Object get(String key) throws TimeoutException, InterruptedException, MemcachedException{
		return this.getMemcachedClient().get(key);
	}
	
	public void set(String key,int exp,Object value) throws TimeoutException, InterruptedException, MemcachedException{
		this.getMemcachedClient().set(key, exp, value);
	}

	public List<Map<String,String>> getStat(InetSocketAddress[] address) throws MemcachedException, InterruptedException, TimeoutException{
		List<Map<String, String>> result=new ArrayList<Map<String, String>>();
		
		for(InetSocketAddress tmp :address)
			result.add(this.getMemcachedClient().stats(tmp));
		
		return result;
	}
	
	/**
	 * @return the memcachedClient
	 */
	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	/**
	 * this memcachedClient may contain many servers
	 * @param memcachedClient the memcachedClient to set
	 */
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/* (non-Javadoc)
	 * <p>Title: addServer</p>
	 * <p>Description: </p>
	 * @param serverIp
	 * @param port
	 * @param weight
	 * @throws IOException
	 * @see com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControlerInf#addServer(java.lang.String, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public synchronized void addServer(String serverIp, Integer port, Integer weight) throws IOException {
		// TODO Auto-generated method stub
		this.getMemcachedClient().addServer(serverIp, port, weight);
	}

	/* (non-Javadoc)
	 * <p>Title: removeServer</p>
	 * <p>Description: </p>
	 * @param serverIp
	 * @param port
	 * @see com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControlerInf#removeServer(java.lang.String, java.lang.Integer) 
	 */
	@Override
	public synchronized void removeServer(String serverIp, Integer port) {
		// TODO Auto-generated method stub
		this.getMemcachedClient().removeServer(serverIp+" "+port);
	}
}
