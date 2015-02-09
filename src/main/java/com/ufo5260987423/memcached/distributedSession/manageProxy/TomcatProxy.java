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
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
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
import com.ufo5260987423.memcached.distributedSession.session.TomcatDistributedSession;

/**
 * @ClassName: TomcatProxy
 * @Description: A Tomcat Proxy To manage session
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
	private String retryTime;
	private String addresses;
	private String backupAmount;

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

	// ---------------------------------------for Lifecycle
	@Override
	public void load() {
		System.out.println("TomcatProxy loading");

	}

	@Override
	public void unload() {
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
		builder.setCommandFactory(new BinaryCommandFactory());

		MemcachedClient memCachedClient = null;

		try {
			memCachedClient = builder.build();
		} catch (IOException e) {
			e.printStackTrace();
		}

		MemCachedControlerInf memCachedControler = new MemCachedControler(memCachedClient);
		BackupControlerInf backupControler = new ConsistentBackupControler(Integer.parseInt(this.getBackupAmount()),
				memCachedControler);
		this.setSessions(new DistributedSessionsConcurrentHashMap<String, Session>(memCachedControler, this
				.getMaxInactiveInterval(), backupControler, Integer.parseInt(this.getRetryTime())));

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

	// -----------------------------------for Manager

	@Override
	public int getActiveSessions() {
		return 0;
	}

	@Override
	public Session createEmptySession() {
		return new TomcatDistributedSession(this);
	}

	// attention
	@Override
	public Session findSession(String id) throws IOException {

		System.out.println("findSession\t" + id);
		if (id == null)
			return (null);
		Session result = sessions.get(id);

		if (null != result) {
			result.setManager(this);
		}

		return result;

	}

	@Override
	public void add(Session session) {
		System.out.println("add\t" + session.getId());
		sessions.put(session.getIdInternal(), session);
	}

	@Override
	public void remove(Session session, boolean update) {
		if (session.getIdInternal() != null) {
			System.out.println("remove\t" + session.getIdInternal());
			sessions.remove(session.getIdInternal());
		}
	}

	@Override
	public void processExpires() {

	}

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

	public String getRetryTime() {
		return null==retryTime?"1":retryTime;
	}

	public void setRetryTime(String retryTime) {
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

	public String getBackupAmount() {
		return null==backupAmount?"0":backupAmount;
	}

	public void setBackupAmount(String backupAmount) {
		this.backupAmount = backupAmount;
	}

}
