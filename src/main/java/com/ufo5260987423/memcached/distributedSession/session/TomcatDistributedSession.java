/**
 * Copyright 2015年2月8日 Wang Zheng
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
package com.ufo5260987423.memcached.distributedSession.session;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.catalina.Manager;
import org.apache.catalina.session.StandardSession;

/**
 * @ClassName: TomcatDistributedSession
 * @Description: A Proxy replacing some method of StandardSession
 * @author ufo ufo5260987423@163.com
 * @date 2015年2月8日 下午2:55:41
 *
 */
public class TomcatDistributedSession extends StandardSession{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 6355804473949135626L;

	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param manager
	 */ 
	public TomcatDistributedSession(Manager manager) {
		super(manager);
        this.manager = manager;
        // Initialize access count
        if (ACTIVITY_CHECK) {
            accessCount = new AtomicInteger();
        }
	}
	
    @Override
    public void setAttribute(String name, Object value) {
    	
    	  // Name cannot be null
        if (name == null)
            throw new IllegalArgumentException();

        // Null value is the same as removeAttribute()
        if (value == null) {
            removeAttribute(name);
            return;
        }
        
    	Object oldValue = attributes.put(name, value);
    	
    	if(!value.equals(oldValue)){
    		this.getManager().add(this);
    	}
    }
    
    @Override
    public void removeAttribute(String name) {
    	if(null!= attributes.get(name)){
    		attributes.remove(name);
    		this.getManager().add(this);
    	}
    }
    
}
