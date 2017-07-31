package cn.wsxiot.web.repository;

import cn.wsxiot.web.domain.Bindinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wsx on 2017-07-18.
 */
public interface BindinfoRepository extends JpaRepository<Bindinfo, Integer> {
    //由uid查询绑定的gid
    public List<Bindinfo> findByUid(int uid);
}
