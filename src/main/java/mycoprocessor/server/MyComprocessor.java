package mycoprocessor.server;

import mycoprocessor.demo.FristComprocessor;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 * @author xudacheng
 */
public class MyComprocessor extends BaseRegionObserver {
    private static final Logger LOG = Logger.getLogger(FristComprocessor.class);

    private RegionCoprocessorEnvironment env = null;

    @Override
    public void start(CoprocessorEnvironment e) throws IOException {
        env = (RegionCoprocessorEnvironment) e;
    }

    @Override
    public void stop(CoprocessorEnvironment e) throws IOException {
        // nothing to do here
    }
    /**
     * 需求 1.不允许插入family 为cf qualifier为c的 2.插入的数据必须为整数 3.允许插入family 不为cf qualifier 不为c的
     */
    @Override
    public void prePut(final ObserverContext<RegionCoprocessorEnvironment> e,
                       final Put put, final WALEdit edit, final Durability durability)
            throws IOException {

        // 首先查看单个put中是否有对只读列有写操作
        List<Cell> cells = put.get(Bytes.toBytes("cf"),
                Bytes.toBytes("c"));
        if (cells != null && cells.size() != 0) {
            LOG.warn("User is not allowed to write read_only col.");
            throw new IOException("User is not allowed to write read_only col.");
        }

        // 当A列存在的情况下在进行值得检查，查看是否插入了整数
        byte[] aValue = null;
        for (Cell cell : cells) {
            try {
                aValue = CellUtil.cloneValue(cell);
                LOG.warn("aValue = " + Bytes.toString(aValue));
                Integer.valueOf(Bytes.toString(aValue));
            } catch (Exception e1) {
                LOG.warn("Can not put un number value to A col.");
                throw new IOException("Can not put un number value to A col.");
            }
        }
        //
        cells = put.get(Bytes.toBytes("cf"),
                Bytes.toBytes("c"));
        if (cells == null || cells.size() == 0) {
            // 允许插入family 为cf qualifier为c的
            LOG.info("No A col operation, just do it.");
            return;
        }
    }
}