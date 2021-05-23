package com.iswust.dao;

import com.iswust.model.Judge;
import com.iswust.model.vo.JudgePathId;
import org.springframework.stereotype.Repository;

@Repository
public interface IJudgeDao {
    void judgeAdd(Judge judge);

    Integer judgeFindById(Integer id);

    void judgeSaveImage(JudgePathId judgePathId);

    void judgeDelete(Integer id);

    String judgeFindPathById(Integer id);

    void judgeUpdateResult(Judge judge);

    Judge judgeGet(Integer id);

    String judgeFindType(Integer id);

    Integer judgeFindPostId(Integer id);
}
