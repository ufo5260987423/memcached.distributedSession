/**
 * Copyright 2015年1月30日 Wang Zheng
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
package com.ufo5260987423.memcached.distributedSession.backup;

import com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControlerInf;

/**
 * @ClassName: BackupInf
 * @Description: TODO
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月30日 下午10:41:41
 *
 */
public interface BackupControlerInf {
	
	public void activeAllBackup(String key);
	
	public Boolean isExist(String key);
	
	public int getBackupAmount();

	public void setBackupAmount(int backupAmount);

	public MemCachedControlerInf getMemCachedControler();

	public void setMemCachedControler(MemCachedControlerInf memCachedControler);
	
	/*
	 * exp is the living time on memcached
	 */
	public Boolean set(String key, int exp, Object value);

	/*
	 * exp is the living time on memcached
	 */
	public Boolean casSet(String key, int exp, Object value);

	public Object get(String key);

	public Boolean remove(String key);

}
