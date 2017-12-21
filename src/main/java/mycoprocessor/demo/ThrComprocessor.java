package mycoprocessor.demo;

import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.coprocessor.BaseMasterObserver;
import org.apache.hadoop.hbase.coprocessor.MasterCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/20.
 * @author xudacheng
 */
public class ThrComprocessor extends BaseMasterObserver {
    MasterCoprocessorEnvironment env = null;

    @Override
    public void preCreateTable(ObserverContext<MasterCoprocessorEnvironment> ctx, HTableDescriptor desc, HRegionInfo[] regions) throws IOException {
        env = (MasterCoprocessorEnvironment)ctx;
    }

}
