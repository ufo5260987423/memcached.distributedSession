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
package com.ufo5260987423.memcached.distributedSession.manageProxy;

import java.io.IOException;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;

import com.ufo5260987423.memcached.distributedSession.backup.BackupControlerInf;
import com.ufo5260987423.memcached.distributedSession.backup.ConsistentBackupControler;
import com.ufo5260987423.memcached.distributedSession.map.DistributedSessionsConcurrentHashMap;
import com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControler;
import com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControlerInf;

/**
 * @ClassName: TomcatProxy
 * @Description: TODO
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月28日 下午1:34:14
 *
 */
public class TomcatProxy extends ManagerBase {

	/**
	 * identify this tomcat node
	 */
	private String nodeName;
	private Integer retryTime;
	private String addresses;
	private Integer survivingTime;

	protected final boolean distributable = true;

	/**
	 * The descriptive name of this Manager implementation (for logging).
	 */

	protected static final String name = "TomcatProxy";

	/*
	 * this sessions must load DistributedSessionsConcurrentHashMap from spring
	 */
	protected Map<String, Session> sessions;

	// = new DistributedSessionsConcurrentHashMap<String, Session>(new
	// MemCachedControler());

	/*
	 * (non-Javadoc) <p>Title: load</p> <p>Description: </p>
	 * 
	 * @throws ClassNotFoundException
	 * 
	 * @throws IOException
	 * 
	 * @see org.apache.catalina.Manager#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		XMemcachedClientBuilder builder=new XMemcachedClientBuilder(AddrUtil.getAddresses(this.getAddresses()));
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		MemcachedClient memCachedClient=builder.build();
		MemCachedControlerInf memCachedControler=new MemCachedControler(memCachedClient);
		BackupControlerInf backupControler=new ConsistentBackupControler(memCachedControler);
		this.sessions = new DistributedSessionsConcurrentHashMap<String, Session>(memCachedControler,60*5,backupControler,5);
		this.sessionIdGenerator.setJvmRoute(this.getNodeName());
	}

	/*
	 * (non-Javadoc) <p>Title: unload</p> <p>Description: </p>
	 * 
	 * @throws IOException
	 * 
	 * @see org.apache.catalina.Manager#unload()
	 */
	@Override
	public void unload() throws IOException {
		// TODO Auto-generated method stub

	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * Return the set of active Sessions associated with this Manager. If this
	 * Manager has no active Sessions, a zero-length array is returned.
	 */
	@Override
	public Session[] findSessions() {
		return null;
	}

	public Integer getRetryTime() {
		return retryTime;
	}

	public void setRetryTime(Integer retryTime) {
		this.retryTime = retryTime;
	}

	public Integer getSurvivingTime() {
		return survivingTime;
	}

	public void setSurvivingTime(Integer survivingTime) {
		this.survivingTime = survivingTime;
	}

	public Map<String, Session> getSessions() {
		return sessions;
	}

	public void setSessions(Map<String, Session> sessions) {
		this.sessions = sessions;
	}

	public boolean isDistributable() {
		return distributable;
	}

	public String getName() {
		return name;
	}

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

}
