package com.github.geek29.geodethrift.cache.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.thrift.TException;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;

/**
 * Base RegionService implementation for Geode Cache API.
 * See JSONRegionServiceHandler and BinaryRegionServiceHandler to see how to use this class
 * ThriftStructRegionServiceHandler provides sample implementation for using thrift domain objects.
 * You can use your own thrift structs directly with this approach. Alternatively you can use JSON
 * service and pass-on json representation of your domain objects
 * 
 * TODO : add proepr javadocs
 * 
 * @author tushark
 *
 * @param <TK> - Thrift Key Type
 * @param <TV> - Thrift Value Type
 * @param <GK> - Geode Key Type
 * @param <GV> - Geode Value Type
 */

public abstract class RegionServiceHandler<TK,TV, GK,GV>  {
	
	private GemFireCacheImpl cache = null;
	
	public RegionServiceHandler() {
		this.cache = GemFireCacheImpl.getExisting();
	}
	
	protected abstract GK transformKeyToGeode(TK key) throws CacheException ;
	
	protected abstract  GV transformValueToGeode(TV key) throws CacheException ;
	
	protected abstract TV transformValueToThrift(GV putReturn) throws CacheException ;
	
	protected abstract TK transformKeyToThrift(GK key) throws CacheException ;
	
	protected Map<TK, TV> transformMapToThrift(Map<GK, GV> map) throws CacheException  {
		Map<TK,TV> outputMap = new HashMap<TK,TV>();
		for(Entry<GK,GV> e : map.entrySet()) {
			outputMap.put(transformKeyToThrift(e.getKey()), transformValueToThrift(e.getValue()));
		}
		return outputMap;
	}
	
	protected Map<GK, GV> transformMapToGeode(Map<TK, TV> map)  throws CacheException {
		Map<GK,GV> outputMap = new HashMap<GK,GV>();
		for(Entry<TK,TV> e : map.entrySet()) {
			outputMap.put(transformKeyToGeode(e.getKey()), transformValueToGeode(e.getValue()));
		}
		return outputMap;
	}
	
	protected Collection<GK> transformKeysToGeode(List<TK> keys) throws CacheException  {
		List<GK> list = new ArrayList<GK>();
		for(TK k : keys) {
			list.add(transformKeyToGeode(k));
		}
		return list;
	}
		
	
	private Region<GK,GV> getRegion(String name, String operation)  throws CacheException {
			@SuppressWarnings("unchecked")
			Region<GK,GV> r = (Region<GK,GV>)cache.getRegion(name);
			if(r==null)
				throw new CacheException(operation, "Region " + name + " not found");
			return r;
	}

	
	public void clear(String region) throws CacheException, TException {
		try {
			getRegion(region, "clear").clear();
		} catch (com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}
	}

	
	public boolean containsKey(String region, TK key)
			throws CacheException, TException {
		try {
			return getRegion(region, "containsKey").containsKey(transformKeyToGeode(key));
		} catch (com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}
	}

	
	public TV put(String region, TK key, TV value)
			throws CacheException, TException {
		try {
			GV putReturn = (GV) getRegion(region, "put").put(
					transformKeyToGeode(key), transformValueToGeode(value));
			return transformValueToThrift(putReturn);
		} catch (com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}
	}
	
	public TV get(String region, TK key)
			throws CacheException, TException {
		try {
			GV putReturn = (GV) getRegion(region, "put").get(
					transformKeyToGeode(key));
			return transformValueToThrift(putReturn);
		} catch (com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}
	}

	public TV remove(String region, TK key)
			throws CacheException, TException {
		try{
			GV value = (GV) getRegion(region, "remove").remove(key);
			return transformValueToThrift(value);
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}	
	}

	
	public void invalidate(String region, TK key)
			throws CacheException, TException {		
		try{
			getRegion(region, "invalidate").invalidate(transformKeyToGeode(key));			
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}	
	}

	
	public void destroyRegion(String region) throws CacheException,
			TException {
		try{
			getRegion(region, "destroyRegion").destroyRegion();			
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}	
	}

	
	public Map<TK, TV> getAll(String region, List<TK> keys)
			throws CacheException, TException {
		try {
			Map<GK,GV> map = getRegion(region, "getAll").getAll(transformKeysToGeode(keys));
			return transformMapToThrift(map);
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}		
	}
	

	

	public void removeAll(String region, List<TK> keys)
			throws CacheException, TException {
		try {
			getRegion(region, "removeAll").removeAll(transformKeysToGeode(keys));			
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}		
	}

	
	public void putAll(String region, Map<TK, TV> entryMap)
			throws CacheException, TException {
		try {
			getRegion(region, "putAll").putAll(transformMapToGeode(entryMap));			
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}
	}

	
	public List<TK> keySet(String region) throws CacheException,
			TException {
		try {
			Set<GK> set = getRegion(region, "keySet").keySet();
			List<TK> list = new ArrayList<TK>();
			for(GK k : set) {
				list.add(transformKeyToThrift(k));
			}
			return list;
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}
	}

	
	public long size(String region) throws CacheException, TException {
		try {
			return getRegion(region, "size").size();
		} catch(com.gemstone.gemfire.cache.CacheException e) {
			throw new TException(e);
		}		
	}
	

}
