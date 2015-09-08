package com.github.geek29.geodethrift.cache.service;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.pdx.PdxInstance;
import com.gemstone.gemfire.pdx.PdxInstanceFactory;
import com.github.geek29.geodethrift.cache.ThriftPDXInstance;

public class ThriftStructRegionServiceHandler extends RegionServiceHandler<ByteBuffer,ThriftStruct, ByteBuffer,PdxInstance> implements ThriftStructRegionService.Iface {
	
	private GemFireCacheImpl cache = null; 
	
	private ThriftStructRegionServiceHandler() {
		cache = GemFireCacheImpl.getExisting();
	}

	@Override
	protected ByteBuffer transformKeyToGeode(ByteBuffer key) {		
		return key;
	}

	@Override
	protected PdxInstance transformValueToGeode(ThriftStruct value) throws CacheException {
		if(value==null)
			return null;
		PdxInstanceFactory factory = cache.createPdxInstanceFactory(value.getClass().getCanonicalName(),
				false);		
		try {
			ThriftPDXInstance instance = new ThriftPDXInstance(value, factory);
			return instance.getPDXInstance();
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {			
			throw new CacheException("transformValueToGeode",e.getMessage());
		}		
	}

	@Override
	protected ThriftStruct transformValueToThrift(PdxInstance putReturn) throws CacheException {
		if(putReturn==null)
			return null;
		try {
			ThriftPDXInstance instance = new ThriftPDXInstance(putReturn,ThriftStruct.class);
			return (ThriftStruct) instance.getTbase();
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {			
			throw new CacheException("transformValueToThrift",e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new CacheException("transformValueToThrift",e.getMessage());
		} catch (InstantiationException e) {
			throw new CacheException("transformValueToThrift",e.getMessage());
		}
	}

	@Override
	protected ByteBuffer transformKeyToThrift(ByteBuffer key) {		
		return key;
	}
	
	protected Collection<ByteBuffer> transformKeysToGeode(List<ByteBuffer> keys) throws CacheException  {		
		return keys;
	}

}
