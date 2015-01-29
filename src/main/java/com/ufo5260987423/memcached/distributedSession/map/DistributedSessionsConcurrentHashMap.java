/**
 * Copyright 2015年1月28日 Wang Zheng
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

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: DistributedSessionsConcurrentHashMap
 * @Description: Provide a container for sessions with backup.
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月28日 下午10:08:38
 *
 */
public class DistributedSessionsConcurrentHashMap<KEY,VALUE> implements Map<KEY,VALUE>{
	private MemCachedControlerInf memCachedControler;
	private InetSocketAddress[] address;
	
	public DistributedSessionsConcurrentHashMap(MemCachedControlerInf memCachedControler){
		this.setMemCachedControler(memCachedControler);
	}
	/* (non-Javadoc)
	 * <p>Title: size</p>
	 * <p>Description: </p>
	 * @return
	 * @see java.util.Map#size() 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		Integer result=new Integer(0);
		try {
			List<Map<String, String>> tmp=this.getMemCachedControler().getStat(this.getAddress());
			for(Map<String, String> item:tmp)
				result+=Integer.parseInt(item.get("curr_items"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * <p>Title: isEmpty</p>
	 * <p>Description: </p>
	 * @return
	 * @see java.util.Map#isEmpty() 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return 0==this.size();
	}

	/* (non-Javadoc)
	 * <p>Title: containsKey</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object) 
	 */
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * <p>Title: containsValue</p>
	 * <p>Description: </p>
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object) 
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * <p>Title: get</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object) 
	 */
	@Override
	public VALUE get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: put</p>
	 * <p>Description: </p>
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object) 
	 */
	@Override
	public VALUE put(KEY key, VALUE value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: remove</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object) 
	 */
	@Override
	public VALUE remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: putAll</p>
	 * <p>Description: </p>
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map) 
	 */
	@Override
	public void putAll(Map<? extends KEY, ? extends VALUE> m) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * <p>Title: clear</p>
	 * <p>Description: </p>
	 * @see java.util.Map#clear() 
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * this method is unsupposed to be use for the giant burden on netIO
	 * <p>Title: keySet</p>
	 * <p>Description: </p>
	 * @return null
	 * @see java.util.Map#keySet() 
	 */
	@Override
	public Set<KEY> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	/* this method is unsupposed to be use for the giant burden on netIO
	 * <p>Title: values</p>
	 * <p>Description: </p>
	 * @return null
	 * @see java.util.Map#values() 
	 */
	@Override
	public Collection<VALUE> values() {
		// TODO Auto-generated method stub
		return null;
	}

	/* this method is unsupposed to be use for the giant burden on netIO
	 * <p>Title: entrySet</p>
	 * <p>Description: </p>
	 * @return null
	 * @see java.util.Map#entrySet() 
	 */
	@Override
	public Set<java.util.Map.Entry<KEY, VALUE>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public MemCachedControlerInf getMemCachedControler() {
		return memCachedControler;
	}

	public void setMemCachedControler(MemCachedControlerInf memCachedControler) {
		this.memCachedControler = memCachedControler;
	}
	
	public synchronized InetSocketAddress[] getAddress() {
		return address;
	}
	public synchronized void setAddress(InetSocketAddress[] address) {
		this.address = address;
	}

}
