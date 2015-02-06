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
import java.util.concurrent.atomic.AtomicLong;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

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
	 * The descriptive information string for this implementation.
	 */
	private static final String info = "TomcatProxy/1.0";

	/**
	 * The descriptive name of this Manager implementation (for logging).
	 */

	protected static final String name = "TomcatProxy";

	/**
	 * identify this tomcat node
	 */
	private String nodeName;
	private Integer retryTime;
	private String addresses;

	protected boolean distributable = true;
	/**
	 * Number of sessions that have expired.
	 */
	protected final AtomicLong expiredSessions = new AtomicLong(0);
	private final Log log = LogFactory.getLog(TomcatProxy.class);

	/*
	 * this sessions must load DistributedSessionsConcurrentHashMap
	 */
	protected Map<String, Session> sessions;

	@Override
	public void load() {
		// TODO Auto-generated method stub
		System.out.println("TomcatProxy loading");

	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		System.out.println("unloading");
	}

	@Override
	protected void initInternal() throws LifecycleException {

		super.initInternal();

		this.setDistributable(true);
		
		// after super.startInternal,we load our own properties
		this.setMaxActiveSessions(-1);
	}

	@Override
	protected synchronized void startInternal() throws LifecycleException {
		super.startInternal();

		XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(this.getAddresses()));
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		builder.setFailureMode(true);

		MemcachedClient memCachedClient = null;

		try {
			memCachedClient = builder.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MemCachedControlerInf memCachedControler = new MemCachedControler(memCachedClient);
		BackupControlerInf backupControler = new ConsistentBackupControler(memCachedControler);
		this.setSessions(new DistributedSessionsConcurrentHashMap<String, Session>(memCachedControler, this
				.getMaxInactiveInterval(), backupControler, 5));

		System.out.println(this.getNodeName());

		this.sessionIdGenerator.setJvmRoute(this.getNodeName());

		this.setState(LifecycleState.STARTING);
	}

	@Override
	protected synchronized void stopInternal() throws LifecycleException {
		if (log.isDebugEnabled())
			log.debug("Stopping");

		// this.unload();

		this.setState(LifecycleState.STOPPING);

		// Require a new random number generator if we are restarted
		super.stopInternal();
	}

	/*
	 * (non-Javadoc) <p>Title: getActiveSessions</p> <p>Description: for
	 * distributedSession,accounting active sessions is a unnecessary</p>
	 * 
	 * @return 0
	 * 
	 * @see org.apache.catalina.session.ManagerBase#getActiveSessions()
	 */
	@Override
	public int getActiveSessions() {
		return 0;
	}

	// attention
	@Override
	public Session findSession(String id) throws IOException {

		if (id == null)
			return (null);
		return sessions.get(id);

	}

	@Override
	public void processExpires() {

	}

	/*
	 * (non-Javadoc) <p>Title: findSessions</p> <p>Description: i can not find
	 * this method is helpful</p>
	 * 
	 * @return null
	 * 
	 * @see org.apache.catalina.session.ManagerBase#findSessions()
	 */
	@Override
	public Session[] findSessions() {
		return null;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getRetryTime() {
		return retryTime;
	}

	public void setRetryTime(Integer retryTime) {
		this.retryTime = retryTime;
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

	public Log getLog() {
		return log;
	}

	@Override
	public String getInfo() {
		return info;
	}

}
