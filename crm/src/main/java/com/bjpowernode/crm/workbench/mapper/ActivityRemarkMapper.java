//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {
    int deleteByPrimaryKey(String var1);

    int insert(ActivityRemark var1);

    int insertSelective(ActivityRemark var1);

    ActivityRemark selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(ActivityRemark var1);

    int updateByPrimaryKey(ActivityRemark var1);

    List<ActivityRemark> selectActivityRemarkForDetailByActivityId(String var1);
}
