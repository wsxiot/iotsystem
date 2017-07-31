package cn.wsxiot.web.repository;

import cn.wsxiot.web.domain.Temhumlig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wsx on 2017-07-18.
 */
public interface TemhumligRepository extends JpaRepository<Temhumlig, Integer> {

    //以gid获取最新的一条温湿度光照记录
    public Temhumlig findFirstByGidOrderByThlidDesc(int gid);
}
