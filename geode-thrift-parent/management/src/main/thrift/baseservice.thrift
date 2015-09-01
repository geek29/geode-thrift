namespace java com.github.geek29.geodethrift.management.service


service BaseService {

	list<string> listMembers();
	list<string> listGroups();
	list<string> listLocators();
	list<string> listCacheServers();
	list<string> listRegions();
	/*map<string, list<string>> listMemberDiskstore();
	map<string, list<string>> listMemberHDFSStore();*/
	list<string> listGatewaySenders();
	list<string> listGatewayReceivers();
	string getAlertLevel();
	i32 getTotalDiskUsage();
	i32 getUsedHeapSize();

}