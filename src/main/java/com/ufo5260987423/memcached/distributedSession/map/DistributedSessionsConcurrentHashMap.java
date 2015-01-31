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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ufo5260987423.memcached.distributedSession.backup.BackupControlerInf;
import com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControlerInf;

/**
 * @ClassName: DistributedSessionsConcurrentHashMap
 * @Description: Provide a container for sessions with backup.
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月28日 下午10:08:38
 *
 */
public class DistributedSessionsConcurrentHashMap<KEY, VALUE> implements Map<KEY, VALUE> {
	private MemCachedControlerInf memCachedControler;
	private InetSocketAddress[] address;
	private BackupControlerInf backupControler;
	private int survivingTime;
	private int retryTimes;

	public DistributedSessionsConcurrentHashMap(MemCachedControlerInf memCachedControler, int survivingTime,
			BackupControlerInf backupControler,int retryTimes) {
		this.setMemCachedControler(memCachedControler);
		this.setSurvivingTime(survivingTime);
		this.setRetryTimes(retryTimes);
		this.setBackupControler(backupControler);
	}

	/*
	 * (non-Javadoc) <p>Title: size</p> <p>Description: include the backup</p>
	 * 
	 * @return
	 * 
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		Integer result = new Integer(0);
		try {
			List<Map<String, String>> tmp = this.getMemCachedControler().getStat(this.getAddress());
			for (Map<String, String> item : tmp)
				result += Integer.parseInt(item.get("curr_items"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * (non-Javadoc) <p>Title: isEmpty</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return 0 == this.size();
	}

	/*
	 * (non-Javadoc) <p>Title: containsKey</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = null != this.getMemCachedControler().get(key.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	/*
	 * stop!this method i have no idea how to use it even i haven't write it
	 * <p>Title: containsValue</p> <p>Description: </p>
	 * 
	 * @param value
	 * 
	 * @return false
	 * 
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc) <p>Title: get</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	@Override
	public VALUE get(Object key) {
		// TODO Auto-generated method stub
		VALUE result = null;
		try {
			result = (VALUE) this.getMemCachedControler().get(key.toString());
			if(null==result)
				result=(VALUE) this.getBackupControler().get(key.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	/*
	 * use cas to provide consistence between servers
	 * (non-Javadoc) <p>Title: put</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param value
	 * 
	 * @return value
	 * 
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("finally")
	@Override
	public VALUE put(KEY key, VALUE value) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < this.getRetryTimes()
					&& !this.getMemCachedControler().casSet(key.toString(), this.getSurvivingTime(), value); i++)
				;
			this.getBackupControler().casSet(key.toString(), this.getSurvivingTime(), value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return value;
		}
	}

	/*
	 * (non-Javadoc) <p>Title: remove</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return null
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@SuppressWarnings("finally")
	@Override
	public VALUE remove(Object key) {
		// TODO Auto-generated method stub
		try {
			this.getMemCachedControler().remove(key.toString());
			this.getBackupControler().remove(key.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return null;
		}
	}

	/*
	 * <p>Title: putAll</p> <p>Description: </p>
	 * 
	 * @param m
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void putAll(Map<? extends KEY, ? extends VALUE> m) {
		// TODO Auto-generated method stub
		Iterator<?> i=m.entrySet().iterator();
		while(i.hasNext()){
			Map.Entry entry = (Map.Entry) i.next(); 
			KEY key = (KEY) entry.getKey();
			VALUE value = (VALUE) entry.getValue(); 
			
			this.put(key, value);
		}
	}

	/*
	 * 
	 * (non-Javadoc) <p>Title: clear</p> <p>Description: unsupposed to be use</p>
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		try {
			this.getMemCachedControler().getMemcachedClient().flushAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * this method is unsupposed to be use for the giant burden on netIO
	 * <p>Title: keySet</p> <p>Description: </p>
	 * 
	 * @return null
	 * 
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<KEY> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * this method is unsupposed to be use for the giant burden on netIO
	 * <p>Title: values</p> <p>Description: </p>
	 * 
	 * @return null
	 * 
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<VALUE> values() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * this method is unsupposed to be use for the giant burden on netIO
	 * <p>Title: entrySet</p> <p>Description: </p>
	 * 
	 * @return null
	 * 
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

	public int getSurvivingTime() {
		return survivingTime;
	}

	public void setSurvivingTime(int survivingTime) {
		this.survivingTime = survivingTime;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public BackupControlerInf getBackupControler() {
		return backupControler;
	}

	public void setBackupControler(BackupControlerInf backupControler) {
		this.backupControler = backupControler;
	}

}
