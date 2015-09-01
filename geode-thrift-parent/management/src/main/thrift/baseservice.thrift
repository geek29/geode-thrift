namespace java com.github.geek29.geodethrift.management.service

/*
	Service to return basic information about Geode Distributed System
*/
service GeodeDSInfoService {
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
}