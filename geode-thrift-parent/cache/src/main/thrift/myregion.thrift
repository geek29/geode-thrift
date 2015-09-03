
include "region.thrift"
namespace java com.github.geek29.geodethrift.cache.service

/*
	Below is simple declaration of using cache module to create your own
	service for thrift structs.
	
	Following code shows Java Service Handler declaration for struct and service definition.
	Your handler is needed to implement only transformXXX methods. Module provides converter
	to transform any Thrift struct to Geode PDXInstance. PdxInstance is portable object format
	similar to thrift but provide additional benefits - no de-serialization, and queryable fields
	

*/

struct InnerStruct {
	1:i16 shortField,
	2:i32 inteField,
	3:i64 longField
}

struct ThriftStruct {
	1:i16 shortField,
	2:i32 inteField,
	3:i64 longField,
	4:string stringField,
	5:bool booleanField,
	6:list<string> listField,
	7:map<string,string> mapField,
	8:InnerStruct structField
}

service ThriftStructRegionService {

	void clear(1:string region) throws (1:region.CacheException e),
	
	bool containsKey(1:string region, 2:binary key)  throws (1:region.CacheException e),
	
	ThriftStruct put(1:string region, 2:binary key, 3:ThriftStruct value)  throws (1:region.CacheException e),
	
	ThriftStruct get(1:string region, 2:binary key)  throws (1:region.CacheException e),
	
	ThriftStruct remove(1:string region, 2:binary key)  throws (1:region.CacheException e),
	
	void invalidate(1:string region, 2:binary key)  throws (1:region.CacheException e),
	
	void destroyRegion(1:string region)  throws (1:region.CacheException e),
	
	map<binary,ThriftStruct> getAll(1:string region, 2:list<binary> keys)  throws (1:region.CacheException e),
	
	void putAll(1:string region, 2:map<binary,ThriftStruct> entryMap)  throws (1:region.CacheException e),
		
	void removeAll(1:string region, 2:list<binary> keys)  throws (1:region.CacheException e),
	
	list<binary> keySet(1:string region)  throws (1:region.CacheException e),
	
	i64 size(1:string region)  throws (1:region.CacheException e)
	
	/*
	QueryResults query(1:string region, 2:string query),
	
	RegionAttributes getAttributes(1:string region),
	
	CacheStatistics getStatistics(1:string region)*/

}
