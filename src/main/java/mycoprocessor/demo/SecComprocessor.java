package mycoprocessor.demo;

import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.BaseWALObserver;
import org.apache.hadoop.hbase.coprocessor.WALCoprocessorEnvironment;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/20.
 * @author xudacheng
 */
public class SecComprocessor extends BaseWALObserver {
    WALCoprocessorEnvironment env = null;

    @Override
    public void start(CoprocessorEnvironment e) throws IOException {
        env = (WALCoprocessorEnvironment)e;
    }
}
