package com.iswust.serves.impl;

import com.iswust.dao.*;
import com.iswust.model.*;
import com.iswust.model.dto.PostUserLikeTopic;
import com.iswust.model.vo.PostPathId;
import com.iswust.model.vo.PostStateId;
import com.iswust.serves.IPostServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServes implements IPostServes {
    private static final String dayHot = "dayHot";
    private static final String weekHot = "weekHot";
    private static final String fresh = "fresh";

    @Value("${myRedis.dayRange}")
    private Integer dayRange;
    @Value("${myRedis.weekRange}")
    private Integer weekRange;
    @Value("${myRedis.recommendNum}")
    private Integer recommendNum;
    @Value("${myRedis.hotNum}")
    private Integer hotNum;
    @Value("${myRedis.allLatestNum}")
    private Integer allLatestNum;
    @Value("${myRedis.themeLatestNum}")
    private Integer themeLatestNum;

    @Autowired
    IPostDao postDao;
    @Autowired
    IThemeDao themeDao;
    @Autowired
    IMessageDao messageDao;
    @Autowired
    IJudgeDao judgeDao;
    @Autowired
    IUserDao userDao;
    @Autowired
    IRedisDao redisDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Post> postState(Integer id) {
        String state = "agree";
        PostStateId postStateId = new PostStateId(state,id);

        return postDao.postState(postStateId);
    }

    @Override
    public void postLike(Post post) {
        postDao.postLike(post.getId());
    }

    @Override
    public void postCommit(Post post) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        post.setTimestamp(timestamp);
        post.setPoint_num(0);
        post.setForward_num(0);
        if(post.getState() == null){
            post.setState("agree");
        }

        postDao.postCommit(post);
        themeDao.themeContentNum(post.getTheme_id());

        if(post.getState() == "apply"){
            Judge judge = new Judge();
            judge.setType("apply");
            judge.setPost_id(post.getId());
            judge.setReceive_uid(post.getUid());

            judgeDao.judgeAdd(judge);

            Message message = new Message();
            message.setType("apply");
            message.setStage(1);
            message.setPost_id(post.getId());
            message.setJudge_id(judge.getId());
            message.setReceive_uid(post.getUid());

            messageDao.messageAdd(message);
            themeDao.themeUnContentNum(post.getTheme_id());
        }else{
            redisTemplate.opsForSet().add(fresh, post.getId() + ":" + post.getTheme_id());
        }
    }

    @Override
    public Integer postFindById(Integer id) {
        return postDao.postFindById(id);
    }

    @Override
    public void postSavePath(StringBuffer pic_path, Integer id) {
        PostPathId postPathId = new PostPathId();
        postPathId.setId(id);
        postPathId.setPic_path(new String(pic_path));

        postDao.postSavePath(postPathId);
    }

    @Override
    public void postDelete(Post post) {
        postDao.postDelete(post.getId());
        themeDao.themeUnContentNum(post.getTheme_id());
    }

    @Override
    public void postUnlike(Post post) {
        postDao.postUnlike(post.getId());
    }

    @Override
    public PostUserLikeTopic postGet(Post post) {
        Post post1 = postDao.postGet(post.getId());
        User user = userDao.userFindNamePhoto(post1.getUid());
        return new PostUserLikeTopic(post1,user,null,null);
    }

    @Override
    public String postFindPathById(Integer id) {
        return postDao.postFindPathById(id);
    }

    @Override
    public List<PostUserLikeTopic> postLatest(Post post) {
        List<PostUserLikeTopic> postUserLikeTopics = new ArrayList<>();
        Integer likeUser = post.getUid();

        if(post.getTheme_id() == null){
            Post voPost = new Post();
            voPost.setForward_num(allLatestNum);
            List<Post> postList = postDao.postFindAll(voPost);

            for(Post post1 : postList){
                User user = userDao.userFindNamePhoto(post1.getUid());
                Post post2 = new Post();
                post2.setId(post1.getId());
                post2.setUid(post.getUid());
                Integer like = messageDao.messageFindByUidPostId(post2);
                if(like == null) like = 0;
                else like = 1;
                Theme theme = themeDao.themeGet(post1.getTheme_id());

                postUserLikeTopics.add(new PostUserLikeTopic(post1,user,like, theme.getTopic()));
            }
        }else{
            Post voPost = new Post();
            voPost.setForward_num(themeLatestNum);
            voPost.setTheme_id(post.getTheme_id());
            List<Post> postList = postDao.postFindAllByThemeId(voPost);

            for(Post post1 : postList){
                User user = userDao.userFindNamePhoto(post1.getUid());
                Post post2 = new Post();
                post2.setId(post1.getId());
                post2.setUid(post.getUid());
                Integer like = messageDao.messageFindByUidPostId(post2);
                like = like == null ? 0 : 1;
                Theme theme = themeDao.themeGet(post1.getTheme_id());

                postUserLikeTopics.add(new PostUserLikeTopic(post1,user,like, theme.getTopic()));
            }
        }

        return postUserLikeTopics;
    }

    @Override
    public List<PostUserLikeTopic> postRecommend(Post post) {
        //将数据存储在set中,方便随机抽取
        Set<String> dayZSets = redisTemplate.opsForZSet().reverseRange(dayHot, 0, dayRange);
        Set<String> weekZSets = redisTemplate.opsForZSet().reverseRange(weekHot, 0, weekRange);
        for(String dayZSet : dayZSets){
            redisTemplate.opsForSet().add("setTemp", dayZSet);
        }
        for(String weekZSet : weekZSets){
            redisTemplate.opsForSet().add("setTemp", weekZSet);
        }

        //按day 1/2,week 3/10,fresh 1/5的比例随机抽取推荐
        Integer num = recommendNum/5;
        Integer len = dayZSets.size() + weekZSets.size();
        if(len < recommendNum - num){
            num = recommendNum -len;
        }
        Set<String> freshRandom = redisTemplate.opsForSet().distinctRandomMembers(fresh, num);
        Set<String> setTemp = redisTemplate.opsForSet().distinctRandomMembers("setTemp", len);
        redisTemplate.delete("setTemp");

        //并集推荐
        Set<String> allRandom = new HashSet<>();
        allRandom.addAll(freshRandom);
        allRandom.addAll(setTemp);

        //转换推荐贴子id的类型
        List<Integer> post_ids = new ArrayList<>();
        for(String temp : allRandom){
            String[] split = temp.split(":");
            post_ids.add(Integer.valueOf(split[0]));
        }

        if(post_ids.size() == 0){
            return null;
        }

        //获取贴子
        List<PostUserLikeTopic> postUserLikeTopics = new ArrayList<>();
        List<Post> postList = postDao.postRecommend(post_ids);
        for(Post post1 : postList){
            User user = userDao.userFindNamePhoto(post1.getUid());

            Post post2 = new Post();
            post2.setId(post1.getId());
            post2.setUid(post.getUid());
            Integer like = messageDao.messageFindByUidPostId(post2);
            like = like == null ? 0 : 1;
            Theme theme = themeDao.themeGet(post1.getTheme_id());

            postUserLikeTopics.add(new PostUserLikeTopic(post1,user,like, theme.getTopic()));
        }
        return postUserLikeTopics;
    }

    @Override
    public List<PostUserLikeTopic> postHot(Post post) {
        //将数据存储在set中,方便随机抽取
        Set<String> dayZSets = redisTemplate.opsForZSet().reverseRange(dayHot, 0, dayRange);
        Set<String> weekZSets = redisTemplate.opsForZSet().reverseRange(weekHot, 0, weekRange);
        Set<String> allZSets = new HashSet<>();
        allZSets.addAll(dayZSets);
        allZSets.addAll(weekZSets);
        List<String> sPost_ids = new ArrayList<>();
        for(String temp : allZSets){
            String[] split = temp.split(":");
            if(Integer.valueOf(split[1]) == post.getTheme_id()){
                sPost_ids.add(split[0]);
            }
        }

        //随机抽取
        for(String temp : sPost_ids){
            redisTemplate.opsForSet().add("setTemp", temp);
        }
        Set<String> allRandom = redisTemplate.opsForSet().distinctRandomMembers("setTemp", hotNum);

        //转换推荐贴子id的类型
        List<Integer> post_ids = new ArrayList<>();
        for(String temp : allRandom){
            String[] split = temp.split(":");
            post_ids.add(Integer.valueOf(split[0]));
        }

        if(post_ids.size() == 0){
            return null;
        }

        //获取贴子
        List<PostUserLikeTopic> postUserLikeTopics = new ArrayList<>();
        List<Post> postList = postDao.postRecommend(post_ids);
        for(Post post1 : postList){
            User user = userDao.userFindNamePhoto(post1.getUid());

            Post post2 = new Post();
            post2.setId(post1.getId());
            post2.setUid(post.getUid());
            Integer like = messageDao.messageFindByUidPostId(post2);
            like = like == null ? 0 : 1;
            Theme theme = themeDao.themeGet(post1.getTheme_id());

            postUserLikeTopics.add(new PostUserLikeTopic(post1,user,like, theme.getTopic()));
        }
        return postUserLikeTopics;
    }

}
