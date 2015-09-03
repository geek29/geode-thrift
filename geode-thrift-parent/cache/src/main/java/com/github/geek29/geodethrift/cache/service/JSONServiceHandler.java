package com.github.geek29.geodethrift.cache.service;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

import com.gemstone.gemfire.pdx.JSONFormatter;
import com.gemstone.gemfire.pdx.PdxInstance;


public class JSONServiceHandler extends RegionServiceHandler<ByteBuffer,String, ByteBuffer,PdxInstance> implements JSONRegionService.Iface {

	@Override
	protected ByteBuffer transformKeyToGeode(ByteBuffer key) {		
		return key;
	}

	@Override
	protected PdxInstance transformValueToGeode(String key) {
		return JSONFormatter.fromJSON(key);
	}

	@Override
	protected String transformValueToThrift(PdxInstance putReturn) {		
		return JSONFormatter.toJSON(putReturn);
	}

	@Override
	protected ByteBuffer transformKeyToThrift(ByteBuffer key) {
		return key;
	}
	
	@Override
	protected Collection<ByteBuffer> transformKeysToGeode(List<ByteBuffer> keys) {
		return keys;
	}

}