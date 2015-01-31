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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ufo5260987423.memcached.distributedSession.memCached.MemCachedControlerInf;

/**
 * @ClassName: ConsistentBackupControler
 * @Description: re-hash the hash-code-key as the backup's new key
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月30日 下午10:45:21
 *
 */
public class ConsistentBackupControler implements BackupControlerInf {
	private final String ALGORITHM = "MD5";
	private final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private int backupAmount;
	private MemCachedControlerInf memCachedControler;

	public ConsistentBackupControler(int backupAmount ,MemCachedControlerInf memCachedControler){
		this.setBackupAmount(backupAmount);
		this.setMemCachedControler(memCachedControler);
	}
	
	private String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	@SuppressWarnings("finally")
	public String hash(String key) {
		String result = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(key.getBytes());
			result = this.getFormattedText(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	public int getBackupAmount() {
		return backupAmount;
	}

	public void setBackupAmount(int backupAmount) {
		this.backupAmount = backupAmount;
	}

	public MemCachedControlerInf getMemCachedControler() {
		return memCachedControler;
	}

	public void setMemCachedControler(MemCachedControlerInf memCachedControler) {
		this.memCachedControler = memCachedControler;
	}

	/*
	 * (non-Javadoc) <p>Title: set</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param exp
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @throws TimeoutException
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws MemcachedException
	 * 
	 * @see
	 * com.ufo5260987423.memcached.distributedSession.backup.BackupControlerInf
	 * #set(java.lang.String, int, java.lang.Object)
	 */
	@Override
	public Boolean set(String key, int exp, Object value) {
		// TODO Auto-generated method stub
		String tmp = key;
		try {
			for (int i = 0; i < this.getBackupAmount(); i++) {
				tmp = this.hash(tmp);
				this.getMemCachedControler().set(tmp, exp, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc) <p>Title: casSet</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @param exp
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @throws TimeoutException
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws MemcachedException
	 * 
	 * @see
	 * com.ufo5260987423.memcached.distributedSession.backup.BackupControlerInf
	 * #casSet(java.lang.String, int, java.lang.Object)
	 */
	@Override
	public Boolean casSet(String key, int exp, Object value) {
		// TODO Auto-generated method stub
		String tmp = key;
		try {
			for (int i = 0; i < this.getBackupAmount(); i++) {
				tmp = this.hash(tmp);
				this.getMemCachedControler().casSet(tmp, exp, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc) <p>Title: get</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @throws TimeoutException
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws MemcachedException
	 * 
	 * @see
	 * com.ufo5260987423.memcached.distributedSession.backup.BackupControlerInf
	 * #get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		String tmp = key;
		Object result = null;
		try {
			for (int i = 0; i < this.getBackupAmount(); i++) {
				tmp = this.hash(tmp);
				result = this.getMemCachedControler().get(tmp);
				if (null != result)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc) <p>Title: remove</p> <p>Description: </p>
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 * @throws TimeoutException
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws MemcachedException
	 * 
	 * @see
	 * com.ufo5260987423.memcached.distributedSession.backup.BackupControlerInf
	 * #remove(java.lang.String)
	 */
	@Override
	public Boolean remove(String key) {
		// TODO Auto-generated method stub
		String tmp = key;
		Boolean result = true;
		try {
			for (int i = 0; i < this.getBackupAmount(); i++) {
				tmp = this.hash(tmp);
				result = result && this.getMemCachedControler().remove(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}

}
