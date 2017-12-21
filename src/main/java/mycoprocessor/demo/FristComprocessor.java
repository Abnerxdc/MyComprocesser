package mycoprocessor.demo;

import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/20.
 * @author xudacheng
 */
public class FristComprocessor extends BaseRegionObserver {
    private static final Logger LOG = Logger.getLogger(FristComprocessor.class);
    // 协处理器是运行于region中的，每一个region都会加载协处理器
    private RegionCoprocessorEnvironment env = null;
    // 这个方法会在regionserver打开region时候执行（还没有真正打开）
    @Override
    public void start(CoprocessorEnvironment e) throws IOException {
        env = (RegionCoprocessorEnvironment) e;
    }

    // 这个方法会在regionserver关闭region时候执行（还没有真正关闭）
    @Override
    public void stop(CoprocessorEnvironment e) throws IOException {
        // nothing to do here
    }

    /**
     * 出发点，比如可以重写prePut postPut等方法，这样就可以在数据插入前和插入后做逻辑控制了。
     */
}
