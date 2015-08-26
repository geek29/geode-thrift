package com.github.geek29.geodethrift.management;

import org.apache.thrift.TException;

import com.github.geek29.geodethrift.management.commandResult.CommandResult;
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

	@Override
	public CommandResult alterDiskStore(AlterDiskStoreArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult alterRegion(AlterRegionArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult alterRuntime(AlterRuntimeArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult backupDiskStore(BackupDiskStoreArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult changeLoglevel(ChangeLoglevelArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult clearDefinedIndexes() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult closeDurableClient(CloseDurableClientArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult closeDurableCq(CloseDurableCqArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult compactDiskStore(CompactDiskStoreArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult compactOfflineDiskStore(
			CompactOfflineDiskStoreArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult configurePdx(ConfigurePdxArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createAsyncEventQueue(
			CreateAsyncEventQueueArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createDefinedIndexes(CreateDefinedIndexesArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createDiskStore(CreateDiskStoreArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createGatewayReceiver(
			CreateGatewayReceiverArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createGatewaySender(CreateGatewaySenderArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createHdfsStore(CreateHdfsStoreArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createIndex(CreateIndexArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult createRegion(CreateRegionArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult defineIndex(DefineIndexArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult describeConfig(DescribeConfigArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult describeDiskStore(DescribeDiskStoreArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult describeMember(DescribeMemberArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult describeOfflineDiskStore(
			DescribeOfflineDiskStoreArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult describeRegion(DescribeRegionArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult destroyDiskStore(DestroyDiskStoreArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult destroyFunction(DestroyFunctionArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult destroyIndex(DestroyIndexArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult destroyRegion(DestroyRegionArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult encryptPassword(EncryptPasswordArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult executeFunction(ExecuteFunctionArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult exportClusterConfiguration(
			ExportClusterConfigurationArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult exportConfig(ExportConfigArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult exportData(ExportDataArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult exportLogs(ExportLogsArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult exportOfflineDiskStore(
			ExportOfflineDiskStoreArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult exportStackTraces(ExportStackTracesArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult gc(GcArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult get(GetArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult help(HelpArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult hint(HintArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult history(HistoryArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listAsyncEventQueues() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listClients() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listDeployed(ListDeployedArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listDiskStores() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listDurableCqs(ListDurableCqsArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listFunctions(ListFunctionsArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listGateways(ListGatewaysArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listIndexes(ListIndexesArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listMembers(ListMembersArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult listRegions(ListRegionsArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult loadBalanceGatewaySender(
			LoadBalanceGatewaySenderArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult locateEntry(LocateEntryArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult netstat(NetstatArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult pauseGatewaySender(PauseGatewaySenderArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult pdxRename(PdxRenameArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult put(PutArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult query(QueryArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult rebalance(RebalanceArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult remove(RemoveArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult resumeGatewaySender(ResumeGatewaySenderArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult revokeMissingDiskStore(
			RevokeMissingDiskStoreArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult setVariable(SetVariableArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult showDeadLocks(ShowDeadLocksArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult showLog(ShowLogArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult showMetrics(ShowMetricsArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult showMissingDiskStores() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult showSubscriptionQueueSize(
			ShowSubscriptionQueueSizeArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult shutdown(ShutdownArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult statusClusterConfigService() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult statusGatewayReceiver(
			StatusGatewayReceiverArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult statusGatewaySender(StatusGatewaySenderArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult statusLocator(StatusLocatorArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult statusServer(StatusServerArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult stopGatewayReceiver(StopGatewayReceiverArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult stopGatewaySender(StopGatewaySenderArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult stopLocator(StopLocatorArgs arguments)
			throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult stopServer(StopServerArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult undeploy(UndeployArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult upgradeOfflineDiskStore(
			UpgradeOfflineDiskStoreArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult validateOfflineDiskStore(
			ValidateOfflineDiskStoreArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult version(VersionArgs arguments) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	/*private GemFireCacheImpl impl;
	private CommandService service;

	public GeodeManagementServer() {
		impl = GemFireCacheImpl.getExisting();
		service = CommandService.getUsableLocalCommandService();
	}

	@Override
	public String execute(Command cmd) throws CommandException, TException {
		try {
			Result result = service.processCommand(cmd.command);
			StringBuilder sb = new StringBuilder();
			while (result.hasNextLine()) {
				sb.append(result.nextLine());
			}
			return sb.toString();
		} catch (Exception e) {
			throw new CommandException(cmd.command, e.getMessage());
		}
	}*/

}
