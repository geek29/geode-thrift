package com.github.geek29.geodethrift.cache.service;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BinaryRegionService extends RegionServiceHandler<ByteBuffer,ByteBuffer, ByteBuffer, ByteBuffer> 
	implements RegionService.Iface {

	@Override
	protected ByteBuffer transformKeyToGeode(ByteBuffer key) {		
		return key;
	}

	@Override
	protected ByteBuffer transformValueToGeode(ByteBuffer key) {
		return key;
	}

	@Override
	protected ByteBuffer transformValueToThrift(ByteBuffer putReturn) {
		return putReturn;
	}

	@Override
	protected ByteBuffer transformKeyToThrift(ByteBuffer key) {
		return key;
	}
	
	@Override
	protected Map<ByteBuffer,ByteBuffer> transformMapToThrift(Map<ByteBuffer,ByteBuffer>map) {
		return map;
	}
	
	@Override
	protected Map<ByteBuffer,ByteBuffer>transformMapToGeode(Map<ByteBuffer,ByteBuffer> map) {
		return map;
	}
	
	@Override
	protected Collection<ByteBuffer> transformKeysToGeode(List<ByteBuffer> keys) {
		return keys;
	}
	
}