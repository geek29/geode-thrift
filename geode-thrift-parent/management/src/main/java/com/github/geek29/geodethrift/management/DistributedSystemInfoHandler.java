package com.github.geek29.geodethrift.management;

import java.util.Arrays;
import java.util.List;

import org.apache.thrift.TException;

import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.DistributedSystemMXBean;
import com.gemstone.gemfire.management.ManagementService;
import com.github.geek29.geodethrift.management.service.GeodeDSInfoService.Iface;

public class DistributedSystemInfoHandler implements Iface {
	
	private DistributedSystemMXBean dsMxBean = null;
	
	public DistributedSystemInfoHandler(){
		GemFireCacheImpl cache = GemFireCacheImpl.getExisting();
		ManagementService service = ManagementService.getManagementService(cache);
		dsMxBean = service.getDistributedSystemMXBean();
	}

	@Override
	public List<String> listMembers() throws TException {		
		return Arrays.asList(dsMxBean.listMembers());
	}

	@Override
	public List<String> listGroups() throws TException {
		return Arrays.asList(dsMxBean.listGroups());
	}

	@Override
	public List<String> listLocators() throws TException {
		return Arrays.asList(dsMxBean.listLocators());
	}

	@Override
	public List<String> listCacheServers() throws TException {
		return Arrays.asList(dsMxBean.listCacheServers());
	}

	@Override
	public List<String> listRegions() throws TException {
		return Arrays.asList(dsMxBean.listRegions());
	}

	@Override
	public List<String> listGatewaySenders() throws TException {
		return Arrays.asList(dsMxBean.listGatewaySenders());
	}

	@Override
	public List<String> listGatewayReceivers() throws TException {
		return Arrays.asList(dsMxBean.listGatewayReceivers());
	}

	@Override
	public String getAlertLevel() throws TException {
		return dsMxBean.getAlertLevel();
	}

}
