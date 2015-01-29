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

import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;

/**
 * @ClassName: TomcatProxy
 * @Description: TODO
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月28日 下午1:34:14
 *
 */
public class TomcatProxy extends ManagerBase {
	/**
	 * The descriptive name of this Manager implementation (for logging).
	 */
	protected static final String name = "TomcatProxy";
	
	/*
	 * this sessions must load DistributedSessionsConcurrentHashMap from spring 
	 */
	protected Map<String, Session> sessions ;
	//= new DistributedSessionsConcurrentHashMap<String, Session>(new MemCachedControler());

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
}
