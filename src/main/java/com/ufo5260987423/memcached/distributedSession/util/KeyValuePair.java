/**
 * Copyright 2015年1月25日 Wang Zheng
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
package com.ufo5260987423.memcached.distributedSession.util;

/**
 * @ClassName: KeyValuePair
 * @Description: represents a key-value pair
 * @author ufo ufo5260987423@163.com
 * @date 2015年1月25日 下午5:34:06
 *
 */
public class KeyValuePair<Key,Value> {
	private final Key key;
	private final Value value;
	
	public KeyValuePair(final Key _key,final Value _value){
		this.key=_key;
		this.value=_value;
	}
	
	public static <Key,Value>KeyValuePair<Key,Value> of(final Key _key,final Value _value){
		return new KeyValuePair<Key,Value>(_key,_value);
	}
	
	public Key getKey() {
		return key;
	}

	public Value getValue() {
		return value;
	}
}
