package com.iswust.dao;

import com.iswust.model.vo.RedisIdScore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRedisDao {
    void redisDelete(Integer id);

    void redisDeleteDayAll();

    void redisSaveDay(List<RedisIdScore> redisIdScores);

    void redisDeleteWeekAll();

    void redisSaveWeek(List<RedisIdScore> redisIdScores);
}
