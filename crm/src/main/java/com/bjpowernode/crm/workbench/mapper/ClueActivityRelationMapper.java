package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationMapper {

    public int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation);

    int insertClueActivityRelationByList(List<ClueActivityRelation> list);
}
