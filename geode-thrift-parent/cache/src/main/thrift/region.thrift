
namespace java com.github.geek29.geodethrift.cache.service

exception CacheException {
	1: string operation,
	2: string reason
}

service RegionService {

	void clear(1:string region) throws (1:CacheException e),
	
	bool containsKey(1:string region, 2:binary key)  throws (1:CacheException e),
	
	binary put(1:string region, 2:binary key, 3:binary value)  throws (1:CacheException e),
	
	binary get(1:string region, 2:binary key)  throws (1:CacheException e),
	
	binary remove(1:string region, 2:binary key)  throws (1:CacheException e),
	
	void invalidate(1:string region, 2:binary key)  throws (1:CacheException e),
	
	void destroyRegion(1:string region)  throws (1:CacheException e),
	
	map<binary,binary> getAll(1:string region, 2:list<binary> keys)  throws (1:CacheException e),
	
	void putAll(1:string region, 2:map<binary,binary> entryMap)  throws (1:CacheException e),
		
	void removeAll(1:string region, 2:list<binary> keys)  throws (1:CacheException e),
	
	list<binary> keySet(1:string region)  throws (1:CacheException e),
	
	i64 size(1:string region)  throws (1:CacheException e)
	
	/*
	QueryResults query(1:string region, 2:string query),
	
	RegionAttributes getAttributes(1:string region),
	
	CacheStatistics getStatistics(1:string region)*/

}


service JSONRegionService {

	void clear(1:string region) throws (1:CacheException e),
	
	bool containsKey(1:string region, 2:binary key)  throws (1:CacheException e),
	
	string put(1:string region, 2:binary key, 3:string value)  throws (1:CacheException e),
	
	string get(1:string region, 2:binary key)  throws (1:CacheException e),
	
	string remove(1:string region, 2:binary key)  throws (1:CacheException e),
	
	void invalidate(1:string region, 2:binary key)  throws (1:CacheException e),
	
	void destroyRegion(1:string region)  throws (1:CacheException e),
	
	map<binary,string> getAll(1:string region, 2:list<binary> keys)  throws (1:CacheException e),
	
	void putAll(1:string region, 2:map<binary,string> entryMap)  throws (1:CacheException e),
		
	void removeAll(1:string region, 2:list<binary> keys)  throws (1:CacheException e),
	
	list<binary> keySet(1:string region)  throws (1:CacheException e),
	
	i64 size(1:string region)  throws (1:CacheException e)
	
	/*
	QueryResults query(1:string region, 2:string query),
	
	RegionAttributes getAttributes(1:string region),
	
	CacheStatistics getStatistics(1:string region)*/

}


/*
clear
containsKey
put
destroy/delete
destroyRegion
getAll
getAttributes : toJSON
getStatistics
invalidate
keySet
putAll
query
size

function service to be next

cq : how to push events to clients

for domain objects which have their own thrift definitons provide code-generator
and a generic hander which converts the thrift struct into pdxinstance
*/
