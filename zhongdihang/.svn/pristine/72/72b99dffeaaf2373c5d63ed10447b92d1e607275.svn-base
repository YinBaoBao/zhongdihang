package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.bankdispatch.modular.service.dto.BankDispatchCountDto;
import com.zhongdihang.bankdispatch.modular.service.dto.ManagerCountDto;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by win10 on 2017/08/17.
 */
public interface DispatchDao extends Dao<Long,Dispatch>,JpaSpecificationExecutor<Dispatch>{

    List<Dispatch> findDispatchByAssessId(Long assessId);
    List<Dispatch> findDispatchByBankId(Long bankId);
    @Query(value = "SELECT COUNT(*) FROM dispatch WHERE status =?1", nativeQuery = true)
    String countDispatch(String Status);

//    @Query(value = "select new  com.zhongdihang.bankdispatch.modular.service.dto.BankDispatchCountDto(a.name as assessName,u.name as bankNam,count(d.assess)) " +
//            "from Dispatch as d left JOIN d.assess as a  LEFT JOIN d.bank u  group by d.bankUser,d.assess")
//    List<BankDispatchCountDto> assessCount();

    @Query(value = "select new  com.zhongdihang.bankdispatch.modular.service.dto.ManagerCountDto(u.realName as userName,a.name as assessName,count(d.assess)) " +
            "from Dispatch as d left JOIN d.assess as a  LEFT JOIN d.bankUser u  group by d.bankUser,d.assess")
    List<ManagerCountDto> assessCountByUser();

/*    @Query(value = "select new  com.zhongdihang.bankdispatch.modular.service.dto.BankDispatchCountDto(a.name as assessName,u.name as bankNam,count(d.assess)) " +
            "from Dispatch as d left JOIN d.assess as a  LEFT JOIN d.bank u  group by d.bankUser,d.assess")
    List<BankDispatchCountDto> assessCountBySearch(String startDate,String endDate,String search);

    @Query(value = "select new  com.zhongdihang.bankdispatch.modular.service.dto.ManagerCountDto(u.realName as userName,a.name as assessName,count(d.assess)) " +
            "from Dispatch as d left JOIN d.assess as a  LEFT JOIN d.bankUser u  group by d.bankUser,d.assess")
    List<ManagerCountDto> assessCountByUserSearch(String startDate,String endDate,String search);*/

    List<Dispatch> findDispatchByStatus(String Status);

    Page<Dispatch> findAll(Specification<Dispatch> spec,Pageable pageable);

}
