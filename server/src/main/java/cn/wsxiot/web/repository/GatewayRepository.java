package cn.wsxiot.web.repository;

import cn.wsxiot.web.domain.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wsx on 2017-07-18.
 */
public interface GatewayRepository extends JpaRepository<Gateway, Integer> {

    //检验是否用户名和密码是否匹配
    public List<Gateway> findByGidAndGpasswd(int gid, String gpasswd);

//    //更新网关在线状态
//    @Modifying
//    @Query(value = "update Gateway g set g.gonline = ?1 where g.gid = ?2")
//    public int setgonline(Integer gid, Integer gonline);
}
