namespace java com.github.geek29.geodethrift.management.structs

struct AlterDiskStoreArgs {
	1:i16 argsId=1,
	2:string name,
	3:string region,
	4:list<string> diskDirs,
	5:optional string compressor,
	6:optional i32 concurrencyLevel,
	7:optional bool enableStatistics,
	8:optional i32 initialCapacity,
	9:optional double loadFactor,
	10:optional string lruAction,
	11:optional string lruAlgorthm,
	12:optional i32 lruLimit,
	13:optional bool offHeap,
	14:optional bool remove

}
struct AlterRegionArgs {
	1:i16 argsId=2,
	2:string name,
	3:optional list<string> group,
	4:optional i32 entryIdleTimeExpiration,
	5:optional string entryIdleTimeExpirationAction,
	6:optional i32 entryTimeToLiveExpiration,
	7:optional string entryTimeToLiveExpirationAction,
	8:optional i32 regionIdleTimeExpiration,
	9:optional string regionIdleTimeExpirationAction,
	10:optional i32 regionTimeToLiveExpiration,
	11:optional string regionTimeToLiveExpirationAction,
	12:optional list<string> cacheListener,
	13:optional string cacheLoader,
	14:optional string cacheWriter,
	15:optional list<string> asyncEventQueueId,
	16:optional list<string> gatewaySenderId,
	17:optional bool enableCloning,
	18:optional i32 evictionMax

}
struct AlterRuntimeArgs {
	1:i16 argsId=3,
	2:optional string member,
	3:optional string group,
	4:optional i32 archiveDiskSpaceLimit,
	5:optional i32 archiveFileSizeLimit,
	6:optional i32 logDiskSpaceLimit,
	7:optional i32 logFileSizeLimit,
	8:optional string logLevel,
	9:optional string statisticArchiveFile,
	10:optional i32 statisticSampleRate,
	11:optional bool enableStatistics,
	12:optional bool copyOnRead,
	13:optional i32 lockLease,
	14:optional i32 lockTimeout,
	15:optional i32 messageSyncInterval,
	16:optional i32 searchTimeout

}
struct BackupDiskStoreArgs {
	1:i16 argsId=4,
	2:string dir,
	3:optional string baselineDir

}
struct ChangeLoglevelArgs {
	1:i16 argsId=5,
	2:optional list<string> members,
	3:optional list<string> groups,
	4:string loglevel

}
struct CloseDurableClientArgs {
	1:i16 argsId=6,
	2:string durableClientId,
	3:optional string member,
	4:optional string group

}
struct CloseDurableCqArgs {
	1:i16 argsId=7,
	2:string durableClientId,
	3:string durableCqName,
	4:optional string member,
	5:optional string group

}
struct CompactDiskStoreArgs {
	1:i16 argsId=8,
	2:string name,
	3:optional list<string> group

}
struct CompactOfflineDiskStoreArgs {
	1:i16 argsId=9,
	2:string name,
	3:list<string> diskDirs,
	4:optional i64 maxOplogSize,
	5:optional list<string> J

}
struct ConfigurePdxArgs {
	1:i16 argsId=10,
	2:optional bool readSerialized,
	3:optional bool ignoreUnreadFields,
	4:optional string diskStore,
	5:optional list<string> autoSerializableClasses,
	6:optional list<string> portableAutoSerializableClasses

}
struct CreateAsyncEventQueueArgs {
	1:i16 argsId=11,
	2:string id,
	3:optional list<string> group,
	4:optional bool parallel,
	5:optional bool enableBatchConflation,
	6:optional i32 batchSize,
	7:optional i32 batchTimeInterval,
	8:optional bool persistent,
	9:optional string diskStore,
	10:optional bool diskSynchronous,
	11:optional i32 maxQueueMemory,
	12:optional i32 dispatcherThreads,
	13:optional string orderPolicy,
	14:optional list<string> gatewayEventFilter,
	15:optional string gatewayEventSubstitutionListener,
	16:string listener,
	17:optional list<string> listenerParam

}
struct CreateDefinedIndexesArgs {
	1:i16 argsId=12,
	2:optional string member,
	3:optional string group

}
struct CreateDiskStoreArgs {
	1:i16 argsId=13,
	2:string name,
	3:optional bool allowForceCompaction,
	4:optional bool autoCompact,
	5:optional i32 compactionThreshold,
	6:optional i32 maxOplogSize,
	7:optional i32 queueSize,
	8:optional i64 timeInterval,
	9:optional i32 writeBufferSize,
	10:list<string> dir,
	11:optional list<string> group,
	12:optional double diskUsageWarningPercentage,
	13:optional double diskUsageCriticalPercentage

}
struct CreateGatewayReceiverArgs {
	1:i16 argsId=14,
	2:optional list<string> group,
	3:optional string member,
	4:optional bool manualStart,
	5:optional i32 startPort,
	6:optional i32 endPort,
	7:optional string bindAddress,
	8:optional i32 maximumTimeBetweenPings,
	9:optional i32 socketBufferSize,
	10:optional list<string> gatewayTransportFilter

}
struct CreateGatewaySenderArgs {
	1:i16 argsId=15,
	2:optional list<string> group,
	3:optional string member,
	4:string id,
	5:i32 remoteDistributedSystemId,
	6:optional bool parallel,
	7:optional bool manualStart,
	8:optional i32 socketBufferSize,
	9:optional i32 socketReadTimeout,
	10:optional bool enableBatchConflation,
	11:optional i32 batchSize,
	12:optional i32 batchTimeInterval,
	13:optional bool enablePersistence,
	14:optional string diskStoreName,
	15:optional bool diskSynchronous,
	16:optional i32 maximumQueueMemory,
	17:optional i32 alertThreshold,
	18:optional i32 dispatcherThreads,
	19:optional string orderPolicy,
	20:optional list<string> gatewayEventFilter,
	21:optional list<string> gatewayTransportFilter

}
struct CreateHdfsStoreArgs {
	1:i16 argsId=16,
	2:string name,
	3:optional string namenode,
	4:optional string homeDir,
	5:optional i32 batchSize,
	6:optional i32 batchInterval,
	7:optional double readCacheSize,
	8:optional i32 dispatcherThreads,
	9:optional i32 maxMemory,
	10:optional bool bufferPersistent,
	11:optional bool synchronousDiskWrite,
	12:optional string diskStoreName,
	13:optional bool minorCompact,
	14:optional i32 minorCompactionThreads,
	15:optional bool majorCompact,
	16:optional i32 majorCompactionInterval,
	17:optional i32 majorCompactionThreads,
	18:optional i32 purgeInterval,
	19:optional i32 maxWriteOnlyFileSize,
	20:optional i32 writeOnlyFileRolloverInterval,
	21:optional string clientConfigFiles,
	22:optional list<string> group

}
struct CreateIndexArgs {
	1:i16 argsId=17,
	2:string name,
	3:string expression,
	4:string region,
	5:optional string member,
	6:optional string type,
	7:optional string group

}
struct CreateRegionArgs {
	1:i16 argsId=18,
	2:string name,
	3:optional string type,
	4:optional string templateRegion,
	5:optional list<string> group,
	6:optional bool skipIfExists,
	7:optional list<string> asyncEventQueueId,
	8:optional list<string> cacheListener,
	9:optional string cacheLoader,
	10:optional string cacheWriter,
	11:optional string colocatedWith,
	12:optional string compressor,
	13:optional i32 concurrencyLevel,
	14:optional string diskStore,
	15:optional bool enableAsyncConflation,
	16:optional bool enableCloning,
	17:optional bool enableConcurrencyChecks,
	18:optional bool enableStatistics,
	19:optional bool enableSubscriptionConflation,
	20:optional bool enableSynchronousDisk,
	21:optional i32 entryIdleTimeExpiration,
	22:optional string entryIdleTimeExpirationAction,
	23:optional i32 entryTimeToLiveExpiration,
	24:optional string entryTimeToLiveExpiriationAction,
	25:optional list<string> gatewaySenderId,
	26:optional string hdfsStore,
	27:optional bool hdfsWriteOnly,
	28:optional string keyConstraint,
	29:optional i32 localMaxMemory,
	30:optional bool offHeap,
	31:optional i32 regionIdleTimeExpiration,
	32:optional string regionIdleTimeExpirationAction,
	33:optional i32 regionTimeToLiveExpiration,
	34:optional string regionTimeToLiveExpirationAction,
	35:optional i64 recoveryDelay,
	36:optional i32 redundantCopies,
	37:optional i64 startupRecoveryDelay,
	38:optional i64 totalMaxMemory,
	39:optional i32 totalNumBuckets,
	40:optional string valueConstraint

}
struct DefineIndexArgs {
	1:i16 argsId=19,
	2:string name,
	3:string expression,
	4:string region,
	5:optional string type

}
struct DescribeConfigArgs {
	1:i16 argsId=20,
	2:string member,
	3:optional bool hideDefaults

}
struct DescribeDiskStoreArgs {
	1:i16 argsId=21,
	2:string member,
	3:string name

}
struct DescribeMemberArgs {
	1:i16 argsId=22,
	2:string name

}
struct DescribeOfflineDiskStoreArgs {
	1:i16 argsId=23,
	2:string name,
	3:list<string> diskDirs,
	4:optional bool pdx,
	5:optional string region

}
struct DescribeRegionArgs {
	1:i16 argsId=24,
	2:string name

}
struct DestroyDiskStoreArgs {
	1:i16 argsId=25,
	2:string name,
	3:optional list<string> group

}
struct DestroyFunctionArgs {
	1:i16 argsId=26,
	2:string id,
	3:optional list<string> groups,
	4:optional string member

}
struct DestroyIndexArgs {
	1:i16 argsId=27,
	2:optional string name,
	3:optional string region,
	4:optional string member,
	5:optional string group

}
struct DestroyRegionArgs {
	1:i16 argsId=28,
	2:string name

}
struct EncryptPasswordArgs {
	1:i16 argsId=29,
	2:string password

}
struct ExecuteFunctionArgs {
	1:i16 argsId=30,
	2:string id,
	3:optional list<string> groups,
	4:optional string member,
	5:optional string region,
	6:optional list<string> arguments,
	7:optional string resultCollector,
	8:optional string filter

}
struct ExportClusterConfigurationArgs {
	1:i16 argsId=31,
	2:string zipFileName,
	3:optional string dir

}
struct ExportConfigArgs {
	1:i16 argsId=32,
	2:optional string member,
	3:optional string group,
	4:optional string dir

}
struct ExportDataArgs {
	1:i16 argsId=33,
	2:string region,
	3:string file,
	4:string member

}
struct ExportLogsArgs {
	1:i16 argsId=34,
	2:string dir,
	3:optional list<string> group,
	4:optional string member,
	5:optional string logLevel,
	6:optional bool onlyLogLevel,
	7:optional bool mergeLog,
	8:optional string startTime,
	9:optional string endTime

}
struct ExportOfflineDiskStoreArgs {
	1:i16 argsId=35,
	2:string name,
	3:list<string> diskDirs,
	4:string dir

}
struct ExportStackTracesArgs {
	1:i16 argsId=36,
	2:optional string member,
	3:optional string group,
	4:string file

}
struct GcArgs {
	1:i16 argsId=37,
	2:optional list<string> group,
	3:optional string member

}
struct GetArgs {
	1:i16 argsId=38,
	2:string key,
	3:string region,
	4:optional string keyClass,
	5:optional string valueClass,
	6:optional bool loadOnCacheMiss

}
struct HelpArgs {
	1:i16 argsId=39,
	2: string command

}
struct HintArgs {
	1:i16 argsId=40,
	2: string topic

}
struct HistoryArgs {
	1:i16 argsId=41,
	2:optional string file,
	3:optional bool clear

}
struct ListDeployedArgs {
	1:i16 argsId=42,
	2:optional string group

}
struct ListDurableCqsArgs {
	1:i16 argsId=43,
	2:string durableClientId,
	3:optional string member,
	4:optional string group

}
struct ListFunctionsArgs {
	1:i16 argsId=44,
	2:optional string matches,
	3:optional string group,
	4:optional string member

}
struct ListGatewaysArgs {
	1:i16 argsId=45,
	2:optional string member,
	3:optional string group

}
struct ListIndexesArgs {
	1:i16 argsId=46,
	2:optional bool withStats

}
struct ListMembersArgs {
	1:i16 argsId=47,
	2:optional string group

}
struct ListRegionsArgs {
	1:i16 argsId=48,
	2:optional string group,
	3:optional string member

}
struct LoadBalanceGatewaySenderArgs {
	1:i16 argsId=49,
	2:string id

}
struct LocateEntryArgs {
	1:i16 argsId=50,
	2:string key,
	3:string region,
	4:optional string keyClass,
	5:optional string valueClass,
	6:optional bool recursive

}
struct NetstatArgs {
	1:i16 argsId=51,
	2:optional list<string> member,
	3:optional string group,
	4:optional string file,
	5:optional bool withLsof

}
struct PauseGatewaySenderArgs {
	1:i16 argsId=52,
	2:string id,
	3:optional string group,
	4:optional string member

}
struct PdxRenameArgs {
	1:i16 argsId=53,
	2:string old,
	3:string _new,
	4:string diskStore,
	5:list<string> diskDirs

}
struct PutArgs {
	1:i16 argsId=54,
	2:string key,
	3:string value,
	4:string region,
	5:optional string keyClass,
	6:optional string valueClass,
	7:optional bool skipIfExists

}
struct QueryArgs {
	1:i16 argsId=55,
	2:string query,
	3:optional string stepName,
	4:optional bool interactive

}
struct RebalanceArgs {
	1:i16 argsId=56,
	2:optional list<string> includeRegion,
	3:optional list<string> excludeRegion,
	4:optional i64 timeOut,
	5:optional bool simulate

}
struct RemoveArgs {
	1:i16 argsId=57,
	2:optional string key,
	3:string region,
	4:optional bool all,
	5:optional string keyClass

}
struct ResumeGatewaySenderArgs {
	1:i16 argsId=58,
	2:string id,
	3:optional string group,
	4:optional string member

}
struct RevokeMissingDiskStoreArgs {
	1:i16 argsId=59,
	2:string id

}
struct SetVariableArgs {
	1:i16 argsId=61,
	2:string name,
	3:string value

}
struct ShowDeadLocksArgs {
	1:i16 argsId=62,
	2:string file

}
struct ShowLogArgs {
	1:i16 argsId=63,
	2:string member,
	3:optional i32 lines

}
struct ShowMetricsArgs {
	1:i16 argsId=64,
	2:optional string member,
	3:optional string region,
	4:optional string file,
	5:optional string port,
	6:optional list<string> categories

}
struct ShowSubscriptionQueueSizeArgs {
	1:i16 argsId=65,
	2:string durableClientId,
	3:optional string durableCqName,
	4:optional string member,
	5:optional string group

}
struct ShutdownArgs {
	1:i16 argsId=66,
	2:optional i32 timeOut,
	3:optional bool includeLocators

}
struct StatusGatewayReceiverArgs {
	1:i16 argsId=67,
	2:optional string group,
	3:optional string member

}
struct StatusGatewaySenderArgs {
	1:i16 argsId=68,
	2:string id,
	3:optional string group,
	4:optional string member

}
struct StatusLocatorArgs {
	1:i16 argsId=69,
	2:optional string name,
	3:optional string host,
	4:optional i32 port,
	5:optional i32 pid,
	6:optional string dir

}
struct StatusServerArgs {
	1:i16 argsId=70,
	2:optional string name,
	3:optional i32 pid,
	4:optional string dir

}
struct StopGatewayReceiverArgs {
	1:i16 argsId=71,
	2:optional string group,
	3:optional string member

}
struct StopGatewaySenderArgs {
	1:i16 argsId=72,
	2:string id,
	3:optional string group,
	4:optional string member

}
struct StopLocatorArgs {
	1:i16 argsId=73,
	2:optional string name,
	3:optional i32 pid,
	4:optional string dir

}
struct StopServerArgs {
	1:i16 argsId=74,
	2:optional string name,
	3:optional i32 pid,
	4:optional string dir

}
struct UndeployArgs {
	1:i16 argsId=75,
	2:optional list<string> group,
	3:optional string jar

}
struct UpgradeOfflineDiskStoreArgs {
	1:i16 argsId=76,
	2:string name,
	3:list<string> diskDirs,
	4:optional i64 maxOplogSize,
	5:optional list<string> J

}
struct ValidateOfflineDiskStoreArgs {
	1:i16 argsId=77,
	2:string name,
	3:list<string> diskDirs

}
struct VersionArgs {
	1:i16 argsId=78,
	2:optional bool full

}