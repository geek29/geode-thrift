package com.github.geek29.geodethrift.management;

import org.apache.thrift.TException;
import org.json.JSONException;

import com.github.geek29.geodethrift.management.commandResult.CommandResult;
import com.github.geek29.geodethrift.management.service.CommandException;
import com.github.geek29.geodethrift.management.service.GeodeCommandService.Iface;
import com.github.geek29.geodethrift.management.structs.AlterDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.AlterRegionArgs;
import com.github.geek29.geodethrift.management.structs.AlterRuntimeArgs;
import com.github.geek29.geodethrift.management.structs.BackupDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.ChangeLoglevelArgs;
import com.github.geek29.geodethrift.management.structs.CloseDurableClientArgs;
import com.github.geek29.geodethrift.management.structs.CloseDurableCqArgs;
import com.github.geek29.geodethrift.management.structs.CompactDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.CompactOfflineDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.ConfigurePdxArgs;
import com.github.geek29.geodethrift.management.structs.CreateAsyncEventQueueArgs;
import com.github.geek29.geodethrift.management.structs.CreateDefinedIndexesArgs;
import com.github.geek29.geodethrift.management.structs.CreateDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.CreateGatewayReceiverArgs;
import com.github.geek29.geodethrift.management.structs.CreateGatewaySenderArgs;
import com.github.geek29.geodethrift.management.structs.CreateHdfsStoreArgs;
import com.github.geek29.geodethrift.management.structs.CreateIndexArgs;
import com.github.geek29.geodethrift.management.structs.CreateRegionArgs;
import com.github.geek29.geodethrift.management.structs.DefineIndexArgs;
import com.github.geek29.geodethrift.management.structs.DescribeConfigArgs;
import com.github.geek29.geodethrift.management.structs.DescribeDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.DescribeMemberArgs;
import com.github.geek29.geodethrift.management.structs.DescribeOfflineDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.DescribeRegionArgs;
import com.github.geek29.geodethrift.management.structs.DestroyDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.DestroyFunctionArgs;
import com.github.geek29.geodethrift.management.structs.DestroyIndexArgs;
import com.github.geek29.geodethrift.management.structs.DestroyRegionArgs;
import com.github.geek29.geodethrift.management.structs.EncryptPasswordArgs;
import com.github.geek29.geodethrift.management.structs.ExecuteFunctionArgs;
import com.github.geek29.geodethrift.management.structs.ExportClusterConfigurationArgs;
import com.github.geek29.geodethrift.management.structs.ExportConfigArgs;
import com.github.geek29.geodethrift.management.structs.ExportDataArgs;
import com.github.geek29.geodethrift.management.structs.ExportLogsArgs;
import com.github.geek29.geodethrift.management.structs.ExportOfflineDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.ExportStackTracesArgs;
import com.github.geek29.geodethrift.management.structs.GcArgs;
import com.github.geek29.geodethrift.management.structs.GetArgs;
import com.github.geek29.geodethrift.management.structs.HelpArgs;
import com.github.geek29.geodethrift.management.structs.HintArgs;
import com.github.geek29.geodethrift.management.structs.HistoryArgs;
import com.github.geek29.geodethrift.management.structs.ListDeployedArgs;
import com.github.geek29.geodethrift.management.structs.ListDurableCqsArgs;
import com.github.geek29.geodethrift.management.structs.ListFunctionsArgs;
import com.github.geek29.geodethrift.management.structs.ListGatewaysArgs;
import com.github.geek29.geodethrift.management.structs.ListIndexesArgs;
import com.github.geek29.geodethrift.management.structs.ListMembersArgs;
import com.github.geek29.geodethrift.management.structs.ListRegionsArgs;
import com.github.geek29.geodethrift.management.structs.LoadBalanceGatewaySenderArgs;
import com.github.geek29.geodethrift.management.structs.LocateEntryArgs;
import com.github.geek29.geodethrift.management.structs.NetstatArgs;
import com.github.geek29.geodethrift.management.structs.PauseGatewaySenderArgs;
import com.github.geek29.geodethrift.management.structs.PdxRenameArgs;
import com.github.geek29.geodethrift.management.structs.PutArgs;
import com.github.geek29.geodethrift.management.structs.QueryArgs;
import com.github.geek29.geodethrift.management.structs.RebalanceArgs;
import com.github.geek29.geodethrift.management.structs.RemoveArgs;
import com.github.geek29.geodethrift.management.structs.ResumeGatewaySenderArgs;
import com.github.geek29.geodethrift.management.structs.RevokeMissingDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.SetVariableArgs;
import com.github.geek29.geodethrift.management.structs.ShowDeadLocksArgs;
import com.github.geek29.geodethrift.management.structs.ShowLogArgs;
import com.github.geek29.geodethrift.management.structs.ShowMetricsArgs;
import com.github.geek29.geodethrift.management.structs.ShowSubscriptionQueueSizeArgs;
import com.github.geek29.geodethrift.management.structs.ShutdownArgs;
import com.github.geek29.geodethrift.management.structs.StatusGatewayReceiverArgs;
import com.github.geek29.geodethrift.management.structs.StatusGatewaySenderArgs;
import com.github.geek29.geodethrift.management.structs.StatusLocatorArgs;
import com.github.geek29.geodethrift.management.structs.StatusServerArgs;
import com.github.geek29.geodethrift.management.structs.StopGatewayReceiverArgs;
import com.github.geek29.geodethrift.management.structs.StopGatewaySenderArgs;
import com.github.geek29.geodethrift.management.structs.StopLocatorArgs;
import com.github.geek29.geodethrift.management.structs.StopServerArgs;
import com.github.geek29.geodethrift.management.structs.UndeployArgs;
import com.github.geek29.geodethrift.management.structs.UpgradeOfflineDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.ValidateOfflineDiskStoreArgs;
import com.github.geek29.geodethrift.management.structs.VersionArgs;

public class GeodeManagementHandler implements Iface {
	
	private CommandExecutor executor = null;
	
	public GeodeManagementHandler() {
		executor = new CommandExecutor();
	}
	
	private CommandResult execute(String cmd, Object args) throws CommandException, TException {
		try {
			CommandResult result = executor.execute(cmd, args);
			return result;
		} catch(JSONException e) {
			throw new CommandException(cmd, "JSON Parsing issue : " + e.getMessage());
		} catch(Exception e) { 
			//Add exception cases here for parsing and other expected error during command execution
			throw new TException(e);
		}
	}

	@Override
	public CommandResult alterDiskStore(AlterDiskStoreArgs arguments)
			throws CommandException, TException {		
		return execute("alter disk-store", arguments);
	}

	@Override
	public CommandResult alterRegion(AlterRegionArgs arguments)
			throws CommandException, TException {
		return execute("alter region", arguments);
	}

	@Override
	public CommandResult alterRuntime(AlterRuntimeArgs arguments)
			throws CommandException, TException {
		return execute("alter runtime ", arguments);
	}

	@Override
	public CommandResult backupDiskStore(BackupDiskStoreArgs arguments)
			throws CommandException, TException {
		return execute("backup disk-store", arguments);
	}

	@Override
	public CommandResult changeLoglevel(ChangeLoglevelArgs arguments)
			throws CommandException, TException {
		return execute("change loglevel", arguments);
	}

	@Override
	public CommandResult clearDefinedIndexes() throws TException {
		return execute("clear defined indexes", null);
	}

	@Override
	public CommandResult closeDurableClient(CloseDurableClientArgs arguments)
			throws CommandException, TException {
		return execute("close durable-client", arguments);
	}

	@Override
	public CommandResult closeDurableCq(CloseDurableCqArgs arguments)
			throws CommandException, TException {
		return execute("close durable-cq", arguments);
	}

	@Override
	public CommandResult compactDiskStore(CompactDiskStoreArgs arguments)
			throws CommandException, TException {
		return execute("compact disk-store", arguments);
	}

	@Override
	public CommandResult compactOfflineDiskStore(
			CompactOfflineDiskStoreArgs arguments) throws TException {
		return execute("compact offline-disk-store", arguments);
	}

	@Override
	public CommandResult configurePdx(ConfigurePdxArgs arguments)
			throws CommandException, TException {
		return execute("configure pdx", arguments);
	}

	@Override
	public CommandResult createAsyncEventQueue(
			CreateAsyncEventQueueArgs arguments) throws TException {
		return execute("create async-event-queue", arguments);
	}

	@Override
	public CommandResult createDefinedIndexes(CreateDefinedIndexesArgs arguments)
			throws CommandException, TException {
		return execute("create defined indexes", arguments);
	}

	@Override
	public CommandResult createDiskStore(CreateDiskStoreArgs arguments)
			throws CommandException, TException {
		return execute("create disk-store", arguments);
	}

	@Override
	public CommandResult createGatewayReceiver(
			CreateGatewayReceiverArgs arguments) throws TException {
		return execute("create gateway-receiver", arguments);
	}

	@Override
	public CommandResult createGatewaySender(CreateGatewaySenderArgs arguments)
			throws CommandException, TException {
		return execute("create gateway-sender", arguments);
	}

	@Override
	public CommandResult createHdfsStore(CreateHdfsStoreArgs arguments)
			throws CommandException, TException {
		return execute("create hdfs-store", arguments);
	}

	@Override
	public CommandResult createIndex(CreateIndexArgs arguments)
			throws CommandException, TException {
		return execute("create index", arguments);
	}

	@Override
	public CommandResult createRegion(CreateRegionArgs arguments)
			throws CommandException, TException {
		return execute("create region", arguments);
	}

	@Override
	public CommandResult defineIndex(DefineIndexArgs arguments)
			throws CommandException, TException {
		return execute("define index", arguments);
	}

	@Override
	public CommandResult describeConfig(DescribeConfigArgs arguments)
			throws CommandException, TException {
		return execute("describe config", arguments);
	}

	@Override
	public CommandResult describeDiskStore(DescribeDiskStoreArgs arguments)
			throws CommandException, TException {
		return execute("describe disk-store", arguments);
	}

	@Override
	public CommandResult describeMember(DescribeMemberArgs arguments)
			throws CommandException, TException {
		return execute("describe member", arguments);
	}

	@Override
	public CommandResult describeOfflineDiskStore(
			DescribeOfflineDiskStoreArgs arguments) throws TException {
		return execute("describe offline-disk-store", arguments);
	}

	@Override
	public CommandResult describeRegion(DescribeRegionArgs arguments)
			throws CommandException, TException {
		return execute("describe region", arguments);
	}

	@Override
	public CommandResult destroyDiskStore(DestroyDiskStoreArgs arguments)
			throws CommandException, TException {
		return execute("destroy disk-store", arguments);
	}

	@Override
	public CommandResult destroyFunction(DestroyFunctionArgs arguments)
			throws CommandException, TException {
		return execute("destroy function", arguments);
	}

	@Override
	public CommandResult destroyIndex(DestroyIndexArgs arguments)
			throws CommandException, TException {
		return execute("destroy index", arguments);
	}

	@Override
	public CommandResult destroyRegion(DestroyRegionArgs arguments)
			throws CommandException, TException {
		return execute("destroy region", arguments);
	}

	@Override
	public CommandResult encryptPassword(EncryptPasswordArgs arguments)
			throws CommandException, TException {
		return execute("encrypt password", arguments);
	}

	@Override
	public CommandResult executeFunction(ExecuteFunctionArgs arguments)
			throws CommandException, TException {
		return execute("execute function", arguments);
	}

	@Override
	public CommandResult exportClusterConfiguration(
			ExportClusterConfigurationArgs arguments) throws TException {
		return execute("export cluster-configuration", arguments);
	}

	@Override
	public CommandResult exportConfig(ExportConfigArgs arguments)
			throws CommandException, TException {
		return execute("export config", arguments);
	}

	@Override
	public CommandResult exportData(ExportDataArgs arguments) throws TException {
		return execute("export data", arguments);
	}

	@Override
	public CommandResult exportLogs(ExportLogsArgs arguments) throws TException {
		return execute("export logs", arguments);
	}

	@Override
	public CommandResult exportOfflineDiskStore(
			ExportOfflineDiskStoreArgs arguments) throws TException {
		return execute("export offline-disk-store", arguments);
	}

	@Override
	public CommandResult exportStackTraces(ExportStackTracesArgs arguments)
			throws CommandException, TException {
		return execute("export stack-traces", arguments);
	}

	@Override
	public CommandResult gc(GcArgs arguments) throws TException {
		return execute("gc", arguments);
	}

	@Override
	public CommandResult get(GetArgs arguments) throws TException {
		return execute("get", arguments);
	}

	@Override
	public CommandResult help(HelpArgs arguments) throws TException {
		return execute("help", arguments);
	}

	@Override
	public CommandResult hint(HintArgs arguments) throws TException {
		return execute("hint", arguments);
	}

	@Override
	public CommandResult history(HistoryArgs arguments) throws TException {
		return execute("history", arguments);
	}

	@Override
	public CommandResult listAsyncEventQueues() throws TException {
		return execute("list async-event-queues", null);
	}

	@Override
	public CommandResult listClients() throws TException {
		return execute("list clients", null);
	}

	@Override
	public CommandResult listDeployed(ListDeployedArgs arguments)
			throws CommandException, TException {
		return execute("list deployed", arguments);
	}

	@Override
	public CommandResult listDiskStores() throws TException {
		return execute("list disk-stores", null);
	}

	@Override
	public CommandResult listDurableCqs(ListDurableCqsArgs arguments)
			throws CommandException, TException {
		return execute("list durable-cqs", arguments);
	}

	@Override
	public CommandResult listFunctions(ListFunctionsArgs arguments)
			throws CommandException, TException {
		return execute("list functions", arguments);
	}

	@Override
	public CommandResult listGateways(ListGatewaysArgs arguments)
			throws CommandException, TException {
		return execute("list gateways", arguments);
	}

	@Override
	public CommandResult listIndexes(ListIndexesArgs arguments)
			throws CommandException, TException {
		return execute("list indexes", arguments);
	}

	@Override
	public CommandResult listMembers(ListMembersArgs arguments)
			throws CommandException, TException {
		return execute("list members", arguments);
	}

	@Override
	public CommandResult listRegions(ListRegionsArgs arguments)
			throws CommandException, TException {
		return execute("list regions", arguments);
	}

	@Override
	public CommandResult loadBalanceGatewaySender(
			LoadBalanceGatewaySenderArgs arguments) throws TException {
		return execute("load-balance gateway-sender", arguments);
	}

	@Override
	public CommandResult locateEntry(LocateEntryArgs arguments)
			throws CommandException, TException {
		return execute("locate entry", arguments);
	}

	@Override
	public CommandResult netstat(NetstatArgs arguments) throws TException {
		return execute("netstat", arguments);
	}

	@Override
	public CommandResult pauseGatewaySender(PauseGatewaySenderArgs arguments)
			throws CommandException, TException {
		return execute("pause gateway-sender", arguments);
	}

	@Override
	public CommandResult pdxRename(PdxRenameArgs arguments) throws TException {
		return execute("pdx rename", arguments);
	}

	@Override
	public CommandResult put(PutArgs arguments) throws TException {
		return execute("put", arguments);
	}

	@Override
	public CommandResult query(QueryArgs arguments) throws TException {
		return execute("query", arguments);
	}

	@Override
	public CommandResult rebalance(RebalanceArgs arguments) throws TException {
		return execute("rebalance", arguments);
	}

	@Override
	public CommandResult remove(RemoveArgs arguments) throws TException {
		return execute("remove", arguments);
	}

	@Override
	public CommandResult resumeGatewaySender(ResumeGatewaySenderArgs arguments)
			throws CommandException, TException {
		return execute("resume gateway-sender", arguments);
	}

	@Override
	public CommandResult revokeMissingDiskStore(
			RevokeMissingDiskStoreArgs arguments) throws TException {
		return execute("revoke missing-disk-store", arguments);
	}

	@Override
	public CommandResult setVariable(SetVariableArgs arguments)
			throws CommandException, TException {
		return execute("set variable", arguments);
	}

	@Override
	public CommandResult showDeadLocks(ShowDeadLocksArgs arguments)
			throws CommandException, TException {
		return execute("show dead-locks", arguments);
	}

	@Override
	public CommandResult showLog(ShowLogArgs arguments) throws TException {
		return execute("show log", arguments);
	}

	@Override
	public CommandResult showMetrics(ShowMetricsArgs arguments)
			throws CommandException, TException {
		return execute("show metrics", arguments);
	}

	@Override
	public CommandResult showMissingDiskStores() throws TException {
		return execute("show missing-disk-stores", null);
	}

	@Override
	public CommandResult showSubscriptionQueueSize(
			ShowSubscriptionQueueSizeArgs arguments) throws TException {
		return execute("show subscription-queue-size", arguments);
	}

	@Override
	public CommandResult shutdown(ShutdownArgs arguments) throws TException {
		return execute("shutdown", arguments);
	}

	@Override
	public CommandResult statusClusterConfigService() throws TException {
		return execute("status cluster-config-service", null);
	}

	@Override
	public CommandResult statusGatewayReceiver(
			StatusGatewayReceiverArgs arguments) throws TException {
		return execute("status gateway-receiver", arguments);
	}

	@Override
	public CommandResult statusGatewaySender(StatusGatewaySenderArgs arguments)
			throws CommandException, TException {
		return execute("status gateway-sender", arguments);
	}

	@Override
	public CommandResult statusLocator(StatusLocatorArgs arguments)
			throws CommandException, TException {
		return execute("status locator", arguments);
	}

	@Override
	public CommandResult statusServer(StatusServerArgs arguments)
			throws CommandException, TException {
		return execute("status server", arguments);
	}

	@Override
	public CommandResult stopGatewayReceiver(StopGatewayReceiverArgs arguments)
			throws CommandException, TException {
		return execute("stop gateway-receiver", arguments);
	}

	@Override
	public CommandResult stopGatewaySender(StopGatewaySenderArgs arguments)
			throws CommandException, TException {
		return execute("stop gateway-sender", arguments);
	}

	@Override
	public CommandResult stopLocator(StopLocatorArgs arguments)
			throws CommandException, TException {
		return execute("stop locator", arguments);
	}

	@Override
	public CommandResult stopServer(StopServerArgs arguments) throws CommandException, TException {
		return execute("stop server", arguments);
	}

	@Override
	public CommandResult undeploy(UndeployArgs arguments) throws CommandException, TException {
		return execute("undeploy", arguments);
	}

	@Override
	public CommandResult upgradeOfflineDiskStore(
			UpgradeOfflineDiskStoreArgs arguments) throws CommandException, TException {
		return execute("upgrade offline-disk-store", arguments);
	}

	@Override
	public CommandResult validateOfflineDiskStore(
			ValidateOfflineDiskStoreArgs arguments) throws CommandException, TException {
		return execute("validate offline-disk-store", arguments);
	}

	@Override
	public CommandResult version(VersionArgs arguments) throws CommandException, TException {
		return execute("version", arguments);
	}	

}
